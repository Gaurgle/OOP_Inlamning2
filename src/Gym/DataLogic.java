package Gym;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataLogic {

    protected DataInput gymMembersDataList;
    public DataLogic(DataInput gymMembersDataList) {
        this.gymMembersDataList = gymMembersDataList;
    }

    public void checkMembership() throws IOException {
        String checkInput = JOptionPane.showInputDialog("Enter name or social security ID nr: ");
        checkInput = checkInput.trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Person foundPerson = null;

        for (Person person : gymMembersDataList.gymMembersDataList.values()) {
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
            JOptionPane.showMessageDialog(null,"Error: " + checkInput + " is not a valid name or social security ID nr.");
            return;
        }

        LocalDate lastPaymentDate = LocalDate.parse(foundPerson.getLastPaymentDate(), formatter);
        LocalDate today = LocalDate.now();
        LocalDate oneYearAgo = today.minusYears(1);

        if (!lastPaymentDate.isBefore(oneYearAgo) && !lastPaymentDate.isAfter(today)) {
            writeToGymMembers(foundPerson);
            foundPerson.setMembership("Active");
            System.out.println(foundPerson.toString()); //behövs
        } else if (lastPaymentDate.isBefore(oneYearAgo)) {
            writeToGymMembers(foundPerson);
            foundPerson.setMembership("Inactive");
            System.out.println(foundPerson.toString());
        } else {
            System.out.println("Error: " + foundPerson.getFullName() + "'s membership is invalid.");
        }
    }


        // hjälpmetod för att skriva till filen
        public void writeToGymMembers(Person person) {
            String gymMembersFilePath = "src/Gym/GymMembersData.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(gymMembersFilePath, true))) {
            writer.write(person.getFullName());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
