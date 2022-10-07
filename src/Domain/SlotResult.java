package Domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SlotResult {
    int start;
    int end;
    HashSet<Person> availablePersons;

    public SlotResult(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
    public void setAvailablePersons(HashSet<Person> availablePersons) {
        this.availablePersons = availablePersons;
    }

    @Override
    public String toString() {
        return "SlotResult{" +
                "start=" + start +
                ", end=" + end +
                ", availablePersons=" + availablePersons +
                '}';
    }
}
