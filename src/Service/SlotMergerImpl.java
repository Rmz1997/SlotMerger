package Service;

import Domain.Person;
import Domain.Slot;
import Domain.SlotResult;

import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class SlotMergerImpl implements SlotMergerInterface {
    ArrayList<SlotResult> slotResults = new ArrayList<>();
    ArrayList<Slot> slots = new ArrayList<>();
    ArrayList<Slot> availabilitySlots = new ArrayList<>();

    HashMap<Integer, HashSet<Person>> personPerAvailableTime = new HashMap<>();
    int newStart;

    public ArrayList<SlotResult> mergePersonAvailabilities(ArrayList<Person> persons) {
        int counter = 0;
        for (Person person : persons) {
            slots.addAll(person.getSlots());

            for (Slot slot : person.getSlots()) {
                if (slot.isAvailable()) {
                    counter++;
                    availabilitySlots.add(slot);
                }
            }
        }
        Collections.sort(slots);

        System.out.println("Amount of slots " + slots.size());
        newStart = findEarliestStart().getStart();

        while (slots.size() > 0) {
            int end = findEarliestEnd().getEnd();
            setSlotResults(newStart, end);
            if (newStart == findLatestEnd().getEnd()) {
                break;
            }
        }


        //slotResults.forEach(slres -> slres.setAvailablePersons(findAvailablePersons(slres.getStart(), slres.getEnd())));
        return slotResults;
    }

    public void setSlotResults(int start, int end) {
        Slot earliestEnd = findEarliestEnd();
        if (earliestEnd != null) {
            if (start != end) {
                SlotResult result = new SlotResult(start, end);
              //  result.setAvailablePersons(personPerAvailableTime.get(start));
                result.setAvailablePersons(findAvailablePersons(start, end));
                slotResults.add(result);
            }
            newStart = end;
        }
    }

    Slot findEarliestStart() {
        if (slots.size() == 0) {
            return null;
        }
        Slot smallest = slots.get(0);
        for (Slot slot : slots) {
            if (slot.getStart() < smallest.getStart()) {
                smallest = slot;
            }
        }
        return smallest;
    }

    Slot findEarliestEnd() {
        if (slots.size() == 0) {
            return null;
        }
        Slot smallest = slots.get(0);
        slots.remove(0);
        return smallest;
    }

    Slot findLatestEnd() {
        if (slots.size() == 0) {
            return null;
        }
        return slots.get(slots.size() - 1);
    }

    HashSet<Person> findAvailablePersons(int start, int end) {
        HashSet<Person> availablePersons = new HashSet<>();
        availabilitySlots.stream().filter(a -> (start < a.getEnd() && a.getStart() < end)).forEach(e -> availablePersons.add(e.getPerson()));

        return availablePersons;
    }
}
