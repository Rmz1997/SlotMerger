package Domain;

public class Slot implements Comparable<Slot> {
    int start;
    int end;
    boolean isAvailable;

    Person person;

    public Slot(int start, int end, boolean isAvailable, Person p) {
        this.start = start;
        this.end = end;
        this.isAvailable = isAvailable;
        this.person = p;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "start=" + start +
                ", end=" + end +
                ", isAvailable=" + isAvailable +
                ", person=" + person +
                '}';
    }

    @Override
    public int compareTo(Slot o) {
        if (this.end > o.end) {
            return 1;
        } else if (this.end == o.end) {
            return 0;
        } else {
            return -1;
        }
    }
}
