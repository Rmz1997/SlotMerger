package Service;

import Domain.Person;
import Domain.SlotResult;

import java.util.ArrayList;

public interface SlotMergerInterface {
    ArrayList<SlotResult> mergePersonAvailabilities(ArrayList<Person> persons);
}
