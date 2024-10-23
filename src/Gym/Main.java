package Gym;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        try {
            // load data
            DataInput dataInput = new DataInput();
            DataLogic dataLogic = new DataLogic(dataInput, new writeToFile());

            // JOption meny
            String[] options = {
                    "Check member status",
                    "Show all members",
                    "Clear all members from file",
                    "Log member checkin",
                    "Exit"};
            String selectedOption = (String) JOptionPane.showInputDialog(null,
                    "Select option",
                    "Gym", JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (selectedOption == null || selectedOption.equals("Exit")) {
                boolean clearFiles = JOptionPane.showConfirmDialog(null, "Do you want to clear list of members?", "Exit", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                if (clearFiles) {
                    writeToFile.clearAllMembers();
                } else {
                    System.out.println("Exiting program.");
                    System.exit(0);
                }
            }

            writeToFile writeToFile = new writeToFile();

            // enhanced switch kollar valt alternativ
            switch (selectedOption) {
                case "Check member status" -> dataLogic.checkMembership();
                case "Show all members" -> {
                    Collection<Person> allMembers = dataInput.gymMembersDataList.values();
                    writeToFile.writeAllMembersToFile(allMembers);
                }
                case "Clear all members from file" -> Gym.writeToFile.clearAllMembers();
                case "Log member checkin" -> {
                    String checkInput = JOptionPane.showInputDialog("Enter name or social security ID nr: ");
                    checkInput = checkInput.trim();

                    Person foundPerson = dataLogic.findMemberByNameOrId(checkInput);
                    if(foundPerson != null) {
                        writeToFile.logMemberCheckin(foundPerson);
                    } else {
                        JOptionPane.showMessageDialog(null, checkInput + " does not exist in the system.");
                    }
                }
                case null -> {}
                default -> System.out.println("Invalid option");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}