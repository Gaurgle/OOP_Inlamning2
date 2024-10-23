package Gym;

import javax.swing.*;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class MemberNameChecker {

    // Metod för att hitta personer genom namn, och lista personer med samma namn
    public static List<Person> findMembersByName(DataInput gymMembersDataList, String checkInput) {
        List<Person> matchingPersons = new ArrayList<>();

        Long inputAsPnr = null;
        try {
            inputAsPnr = Long.parseLong(checkInput);
        } catch (NumberFormatException _) {
        }

        for (Person person : gymMembersDataList.gymMembersDataList.values()) {
            String[] nameParts = person.getFullName().split(" ", 2);
            String firstName = nameParts[0];
            String surName = nameParts.length > 1 ? nameParts[1] : "";

            if (person.getFullName().equalsIgnoreCase(checkInput)
                    || firstName.equalsIgnoreCase(checkInput)
                    || surName.equalsIgnoreCase(checkInput)
                    || (person.getpNr().equals(inputAsPnr))) {
                matchingPersons.add(person);
            }
        }
        return matchingPersons;

    }

    // Metod för att välja en person om det finns flera med samma namn
    public Person askUserToSelectPerson(List<Person> matchingPersons) {
        if (matchingPersons.size() == 1) {
            return matchingPersons.get(0);
        }

        String[] options = new String[matchingPersons.size()];
        for (int i = 0; i < matchingPersons.size(); i++) {
            Person person = matchingPersons.get(i);
            options[i] = person.getFullName() + " (" + person.getpNr() + ")";
        }

        String selectedOption = (String) JOptionPane.showInputDialog(
                null,
                "Multiple members share this name. Select the correct person:",
                "Select person",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (selectedOption != null) {
            for (Person person : matchingPersons) {
                if (selectedOption.contains(person.getpNr().toString())) {
                    return person;
                }
            }
        }
        return null;
    }
}