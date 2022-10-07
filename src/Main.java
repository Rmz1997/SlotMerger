import Domain.Person;
import Domain.Slot;
import Domain.SlotResult;
import Mock.SlotGenerator;
import Service.SlotMergerImpl;
import Service.SlotMergerInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person p1 = SlotGenerator.randomizePerson("Remz", 10, 10);
        Person p2 = SlotGenerator.randomizePerson("Sander",10, 10);
        Person p3 = SlotGenerator.randomizePerson("Gerard",10, 10);
        Person p4 = SlotGenerator.randomizePerson("Robin",10, 10);
        ArrayList<Person> people = new ArrayList<>();
        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);

        for (Person person : people) {
            System.out.println("=================" +person.getFirstName() + "=================");
            for (Slot slot : person.getSlots()) {
                if (slot.isAvailable()) {
                    System.out.println(slot);
                }
            }
        }
        System.out.println();
        SlotMergerInterface slotMerger = new SlotMergerImpl();
        ArrayList<SlotResult> resultList = slotMerger.mergePersonAvailabilities(people);
        for (SlotResult result : resultList) {
            System.out.println(result);
        }
    }
}