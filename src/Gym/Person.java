package Gym;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    public String toString() {
        return "Person:\n" +
                getFullName() +
                ". pnr: " + pNr + ".\n" +
                "Last payment: " + lastPaymentDate +
                ", Membership: " + membership + '\n';
    }

    public String toStringList() {
        return String.format("%-25s %-20s %-20s %-15s",
                "Name: " + getSurName() + ", " + getFirstName() +".",
                "pnr: " + pNr + " ",
                "Last payment: " + lastPaymentDate,
                "Membership: " + getMembershipStatus());
    }
}