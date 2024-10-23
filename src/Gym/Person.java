package Gym;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

public class Person {

    protected Long pNr;
    protected String fullName;
    protected String lastPaymentDate;
    protected String membership;

    public Person(Long pNr, String fullName, String lastPaymentDate, String membership) {
        this.pNr = pNr;
        this.fullName = fullName;
        this.lastPaymentDate = lastPaymentDate;
        this.membership = "";
    }

    public Long getpNr() {
        return pNr;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(String date) {
        this.lastPaymentDate = date;
    }

    public String getMembershipStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate lastPaymentDate = LocalDate.parse(getLastPaymentDate(), formatter);
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        if (lastPaymentDate.isAfter(oneYearAgo)) {
            return "Active";
        } else {
            return "Inactive";
        }
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getFirstName() {
        return getFullName().split(" ")[0];
    }

    public String getSurName() {
        String[] nameParts = getFullName().split(" ", 3);
        return nameParts.length > 1 ? nameParts[1] : "";
    }

    public static int maxNameLength(Collection<Person> names) {
        int maxLength = 0;
        for (Person person : names) {
            maxLength = Math.max(maxLength, person.getFullName().length() + 6);
        }
        return maxLength;
    }

    public String toString() {
        return "Person:\n" +
                getFullName() +
                ". pnr: " + pNr + ".\n" +
                "Last payment: " + lastPaymentDate +
                ", Membership: " + membership + '\n';
    }

    private String formatName(String name, int maxLengthPerLine) {
        StringBuilder formattedName = new StringBuilder();
        int start = 0;

        // Break the name into substrings of maxLengthPerLine characters
        while (start < name.length()) {
            int end = Math.min(start + maxLengthPerLine, name.length());
            formattedName.append(name, start, end);
            if (end < name.length()) {
                formattedName.append("\n");  // Add new line if more text is remaining
            }
            start = end;
        }

        return formattedName.toString();
    }

    public String toStringList(int maxNameLength) {
        if (getFullName().length() > 25) {
            // Name exceeds 25 characters: print name on one line, and info on the next, aligned properly
            return String.format("Name: %s\n%-" + maxNameLength + "s %-15s %-24s %-15s",
                    getFullName() +":",
                    "",  // This adds empty space for alignment
                    "pnr: " + pNr,
                    "Last payment: " + lastPaymentDate,
                    "Membership: " + getMembershipStatus());
        } else {
            // Name is 25 characters or fewer: print everything on one line
            return String.format("%-" + maxNameLength + "s %-15s %-24s %-15s",
                    "Name: " + getFullName(),
                    "pnr: " + pNr,
                    "Last payment: " + lastPaymentDate,
                    "Membership: " + getMembershipStatus());
        }
    }
}