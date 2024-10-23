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
            maxLength = Math.max(maxLength, person.getFullName().length() +6);
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

    public String toStringList(int maxNameLength) {
        return String.format("%-" +maxNameLength + "s %-15s. %-24s. %-15s",
                "Name: " + getFullName(),
                "pnr: " + pNr,
                "Last payment: " + lastPaymentDate,
                "Membership: " + getMembershipStatus());
    }
}