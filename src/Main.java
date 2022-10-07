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
 /*       Person p2 = SlotGenerator.randomizePerson("Sander");
        Person p3 = SlotGenerator.randomizePerson("Gerard");
        Person p4 = SlotGenerator.randomizePerson("Robin");*/
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Person p1 = SlotGenerator.randomizePerson("Remz " + i, 100000,100000);
            people.add(p1);
        }
        SlotMergerInterface slotMerger = new SlotMergerImpl();
        ArrayList<SlotResult> resultList = slotMerger.mergePersonAvailabilities(people);
        System.out.println(resultList.size());
        for (SlotResult result : resultList) {
            System.out.println(result);
        }
    }
}