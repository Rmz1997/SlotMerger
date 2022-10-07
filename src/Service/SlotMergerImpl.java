package Service;

import Domain.Person;
import Domain.Slot;
import Domain.SlotResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class SlotMergerImpl implements SlotMergerInterface {
    ArrayList<SlotResult> slotResults = new ArrayList<>();
    ArrayList<Slot> slots = new ArrayList<>();
    ArrayList<Slot> availabilitySlots = new ArrayList<>();

    int latestEnd;

    public ArrayList<SlotResult> mergePersonAvailabilities(ArrayList<Person> persons) {
        for (Person person : persons) {
            slots.addAll(person.getSlots());
            for (Slot slot : person.getSlots()) {
                if (slot.isAvailable()) {
                    availabilitySlots.add(slot);
                }
            }
        }
        Collections.sort(slots);

        System.out.println("Amount of slots " + slots.size());
        latestEnd = findEarliestStart().getStart();

        while (slots.size() > 0) {
            setSlotResults(latestEnd, findEarliestEnd().getEnd());
        }
      //  setSlotResults(findEarliestStart().getStart(), findEarliestEnd().getEnd()); //earliest start will be 0 most of the time
        return slotResults;
    }

    public void setSlotResults(int start, int end) {
        Slot earliestEnd = findEarliestEnd();
        if (earliestEnd != null) {
            if (start != end) {
                SlotResult result = new SlotResult(start, end);
                result.setAvailablePersons(findAvailablePersons(start, end));
                slotResults.add(result);
            }
            latestEnd = end;
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
        if (slots.size() == 0) {return null;}
        Slot smallest = slots.get(0);
        try {
            slots.remove(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return smallest;
    }
    HashSet<Person> findAvailablePersons(int start, int end) {
        HashSet<Person> availablePersons = new HashSet<>();
        for (Slot availabilitySlot : availabilitySlots) {
            if ((start < availabilitySlot.getEnd()
                    && availabilitySlot.getStart() <= end)) {
                availablePersons.add(availabilitySlot.getPerson());
            }
        }
        return availablePersons;
    }

}
