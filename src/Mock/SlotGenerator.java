package Mock;

import Domain.Person;
import Domain.Slot;

import java.util.ArrayList;
import java.util.Random;

public class SlotGenerator {
    public static Person randomizePerson(String name, int maxRange, int maxInterval) {
        Person person = new Person(name);
        ArrayList<Slot> slots = randomizeSlots(maxRange, maxInterval, person);
        person.setSlots(slots);
        return person;
    }

    static ArrayList<Slot> randomizeSlots(int maxRange, int avMod, Person p) {
        ArrayList<Slot> slots = new ArrayList<>();
        for (int i = 0; i < maxRange; i++) {
            int newEnd = i + new Random().nextInt(avMod);
            if (newEnd > maxRange) {
                newEnd = maxRange;
            }
            Slot slot = new Slot(i, newEnd, new Random().nextBoolean(), p);
            slots.add(slot);
            i = slot.getEnd() - 1;
        }
        return slots;
    }
}
