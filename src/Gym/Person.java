package Gym;

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

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getFirstName() {
        return getFullName().split(" ")[0];
    }

    public String getSurName() {
        String[] nameParts = getFullName().split(" ", 2);
        return nameParts.length > 1 ? nameParts[1] : "";

    }

    @Override
    public String toString() {
        return "Person:\n" +
                "Name: " + getSurName() +
                ", " + getFirstName() +
                ". P nr: " + pNr + ".\n" +
                "Last payment: " + lastPaymentDate +
                ", Membership: " + membership + '\n'
                ;
    }
}
