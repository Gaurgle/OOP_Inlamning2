package Gym;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DataLogic {

    final private String localDateTimeToday = java.time.LocalDate.now().toString();
    final private String localDateTimeMinusOneYear = java.time.LocalDate.now().minusYears(1).toString();
    private HashMap<Long, Person> gymMembersDataList;
    private final String gymMembersFilePath = "src/Gym/GymMembersData.txt";

    public DataLogic(DataInput dataInput) {
        this.gymMembersDataList = dataInput.gymMembersDataList;
    }


    public void checkMembership() throws IOException {
        String checkName = JOptionPane.showInputDialog("Ange namn: ");
        checkName = checkName.trim();

        Person foundPerson = null;

        for (Person person : gymMembersDataList.values()) {
            if (    person.getFullName().equalsIgnoreCase(checkName) ||
                    person.getFirstName().equalsIgnoreCase(checkName) ||
                    person.getSurnName().equalsIgnoreCase(checkName)) {
                foundPerson = person;
                break;
            }
        }

        if (foundPerson != null) {
            String memberShipStatus = switch (foundPerson.getLastPaymentDate()) {
                case String date when date.equals(localDateTimeToday) -> {
                    writeToGymMembers(foundPerson);
                    yield "Medlemskapet är aktivt.";
                }
                case String date when date.equals(localDateTimeMinusOneYear) -> "Medlemskapet har gått ut.";
                default -> "Personen har aldrig varit medlem";

                //TODO fixa sen. ska in i listan.
            };
            System.out.println(foundPerson.getFullName() + ": " + memberShipStatus);
            } else {
                System.out.println("Personen finns inte i registret.");
            }
        }

        public void writeToGymMembers(Person person) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {;
            writer.write(person.getFullName() + "Har aktivt medlemskap");
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
