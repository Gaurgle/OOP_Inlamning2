package Gym;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static Gym.writeToFile.writeToGymMembers;

public class DataLogic {

    protected DataInput gymMembersDataList;
    private IFileWriter fileWriter;

    public DataLogic(DataInput gymMembersDataList, IFileWriter fileWriter) {
        this.gymMembersDataList = gymMembersDataList;
        this.fileWriter = new writeToFile();
    }

    public void checkMembership() {
        String checkInput = JOptionPane.showInputDialog("Enter name or social security ID nr: ");
        checkInput = checkInput.trim();

        Person foundPerson = findMemberByNameOrId(checkInput);

        if (foundPerson == null) {
            JOptionPane.showMessageDialog(null, checkInput + " does not exist in the system.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lastPaymentDate = LocalDate.parse(foundPerson.getLastPaymentDate(), formatter);
        LocalDate today = LocalDate.now();
        LocalDate oneYearAgo = today.minusYears(1);

/*        for (Person person : gymMembersDataList.gymMembersDataList.values()) {
            String[] nameParts = person.getFullName().split(" ", 2);
            String firstName = nameParts[0];
            String surName = nameParts.length > 1 ? nameParts[1] : "";

            if (    person.getFullName().equalsIgnoreCase(checkInput)
                    || firstName.equalsIgnoreCase(checkInput)
                    || surName.equalsIgnoreCase(checkInput)){

                foundPerson = person;
                break;
            }

            try {
                Long checkInputLong = Long.parseLong(checkInput);
                if (person.getpNr().equals(checkInputLong)) {
                    foundPerson = person;
                    break;
                }
            } catch (NumberFormatException _) {

            }
        }
        if (foundPerson == null) {
            JOptionPane.showMessageDialog(null,checkInput + "s membership is invalid.");
            return;
        }*/

        if (!lastPaymentDate.isBefore(oneYearAgo) && !lastPaymentDate.isAfter(today)) {
            foundPerson.setMembership("Active");
            System.out.println(foundPerson.toString()); //behövs
        } else {
            foundPerson.setMembership("Inactive");
            System.out.println(foundPerson.toString());
        }
    }

    public Person findMemberByNameOrId(String checkInput) {
        for (Person person : gymMembersDataList.gymMembersDataList.values()) {
            String[] nameParts = person.getFullName().split(" ", 2);
            String firstName = nameParts[0];
            String surName = nameParts.length > 1 ? nameParts[1] : "";

            if (person.getFullName().equalsIgnoreCase(checkInput)
                    || firstName.equalsIgnoreCase(checkInput)
                    || surName.equalsIgnoreCase(checkInput)) {

                person.setMembership("Active");
                return person;
            }

            try {
                Long checkInputLong = Long.parseLong(checkInput);
                if (person.getpNr().equals(checkInputLong)) {
                    return person;
                }
            } catch (NumberFormatException _) {
            }
        }
        return null;

/*        // hjälpmetod för att skriva till filen
        public void writeToGymMembers(Person person) {
            String gymMembersFilePath = "src/Gym/GymMembersData.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
            writer.write(person.toStringList());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }*/ //hjälpmetod writeToGymMembers flyttad till writeToFile.java
    }
}
