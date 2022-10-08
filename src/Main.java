import Domain.Person;
import Domain.Slot;
import Domain.SlotResult;
import Mock.SlotGenerator;
import Service.SlotMergerImpl;
import Service.SlotMergerInterface;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //testSimple();
        testBulk(2000,86400,5000);
    }

    static void testSimple() {
        ArrayList<Person> people = new ArrayList<>();
        Person p1 = SlotGenerator.randomizePerson("Remz", 20, 10);
        Person p2 = SlotGenerator.randomizePerson("Sander", 20, 10);
        Person p3 = SlotGenerator.randomizePerson("Gerard", 20, 10);
        Person p4 = SlotGenerator.randomizePerson("Robin", 20, 10);
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        for (Person person : people) {
            System.out.println("=================" + person.getFirstName() + "=================");
            for (Slot slot : person.getSlots()) {
                System.out.println(slot);
            }
        }
        SlotMergerInterface slotMerger = new SlotMergerImpl();
        ArrayList<SlotResult> resultList = slotMerger.mergePersonAvailabilities(people);
        for (SlotResult result : resultList) {
            System.out.println(result);
        }
    }

    static void testBulk(int amountOfPersons, int max, int maxInterval) {
        long start = System.nanoTime();
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < amountOfPersons; i++) {
            Person p4 = SlotGenerator.randomizePerson("Test Person " + i, max, maxInterval);
            people.add(p4);
        }
        long endData = System.nanoTime();
        long dataSeconds = (endData - start) / 1000000000;
        System.out.println("data creation is " + dataSeconds + " seconds");
        System.out.println("Amount of people " + people.size());
        SlotMergerInterface slotMerger = new SlotMergerImpl();
        ArrayList<SlotResult> resultList = slotMerger.mergePersonAvailabilities(people);
        long end = System.nanoTime();

        long seconds = (end - start) / 1000000000;
        System.out.println("Process duration is " + seconds + " seconds");
    }
}