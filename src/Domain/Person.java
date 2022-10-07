package Domain;

import java.util.ArrayList;

public class Person {
    String firstName;
    ArrayList<Slot> slots;

    public Person(String firstName) {
        this.firstName = firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    @Override
    public String toString() {
        return firstName;
    }
}
