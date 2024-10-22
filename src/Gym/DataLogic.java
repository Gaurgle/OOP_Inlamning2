package Gym;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DataLogic {

    protected DataInput gymMembersDataList;
    private IFileWriter fileWriter;

    public DataLogic(DataInput gymMembersDataList, IFileWriter fileWriter) {
        this.gymMembersDataList = gymMembersDataList;
        this.fileWriter = new writeToFile();
    }

    // tar input från användaren och kollar om personen är medlem eller inte
    public void checkMembership() {
        String checkInput = JOptionPane.showInputDialog("Enter name or social security ID nr: ");

         if (checkInput == null) {
            System.out.println("User canceled input, exiting program.");
            System.exit(0);  // Exit program if no input
        }

        checkInput = checkInput.trim();

        // om ingen input avslutas programmet.
        if (checkInput.isBlank() || checkInput.isEmpty()) {
            System.out.println("No input, exiting program");
            System.exit(0);
        }

        Person foundPerson = findMemberByNameOrId(checkInput);
        if (foundPerson == null && !checkInput.isBlank()) {
            JOptionPane.showMessageDialog(null, "\"" + checkInput + "\"" + " does not exist in the system.");
            return;
        }

        if (foundPerson != null) {
            checkMembershipStatus(foundPerson);
        }
    }

    // hittar personen i listan
    public Person findMemberByNameOrId(String checkInput) {
        MemberNameChecker memberNameChecker = new MemberNameChecker();

        List<Person> matchingPersons = MemberNameChecker.findMembersByName(gymMembersDataList, checkInput);
        if (matchingPersons.isEmpty()) {
            return null;
        }
        return memberNameChecker.askUserToSelectPerson(matchingPersons);
    }

    // kollar om medlemskapet är aktivt eller inaktivt
    private void checkMembershipStatus(Person foundPerson) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lastPaymentDate = LocalDate.parse(foundPerson.getLastPaymentDate(), formatter);
        LocalDate today = LocalDate.now();
        LocalDate oneYearAgo = today.minusYears(1);

        if (!lastPaymentDate.isBefore(oneYearAgo) && !lastPaymentDate.isAfter(today)) {
            foundPerson.setMembership("Active");
            System.out.println(foundPerson.toString());
        } else {
            foundPerson.setMembership("Inactive");
            System.out.println(foundPerson.toString());
        }
    }
}