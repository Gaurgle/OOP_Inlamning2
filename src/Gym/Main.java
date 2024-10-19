package Gym;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        // load data
        try {
            DataInput dataInput = new DataInput();
            DataLogic dataLogic = new DataLogic(dataInput, new writeToFile());

            String[] options = {"Check member", "Show all members", "Clear all members from file", "Log member checkin", "Exit"};
            String selectedOption = (String) JOptionPane.showInputDialog(null,
                    "Select option",
                    "Gym", JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);

            if (selectedOption == null) {
                System.out.println("Exiting program");
                System.exit(0);
            }

            writeToFile writeToFile = new writeToFile();

            switch (selectedOption) {
                case "Check member":
                    dataLogic.checkMembership();
                    break;
                case "Show all members":
                    Collection<Person> allMembers = dataInput.gymMembersDataList.values();
                    writeToFile.writeAllMembersToFile(allMembers);
                    break;
                case "Clear all members from file":
                    writeToFile.clearAllMembers();
                    break;
                case "Log member checkin":
                    String checkInput = JOptionPane.showInputDialog("Enter name or social security ID nr: ");
                    checkInput = checkInput.trim();

                    Person foundPerson = dataLogic.findMemberByNameOrId(checkInput);
                    if(foundPerson != null) {
                        writeToFile.logMemberCheckin(foundPerson);

                    } else {
                        JOptionPane.showMessageDialog(null, checkInput + " does not exist in the system.");
                    }
                    break;
                case "Exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}