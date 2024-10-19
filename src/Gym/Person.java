package Gym;

public class Person {

    protected Long pNr;
    protected String fullName;
    protected String firstName;
    protected String surnName;
    protected String lastPaymentDate;

    public Person(Long pNr, String fullName, String lastPaymentDate, String paymentDate) {
        this.pNr = pNr;
        this.fullName = firstName;
        this.firstName = fullName.split(" ")[0];
        this.surnName = fullName.split(" ")[1];
        this.lastPaymentDate = lastPaymentDate;
    }

    public Long getpNr() {
        return pNr;
    }
    public String getFullName() {
        return fullName;
    }
    public String setFirstName(String firstName) {
        return this.firstName = firstName;
    }
    public String setSurName(String surName) {
        return this.surnName = surName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getSurnName() {
        return surnName;
    }
    public String getLastPaymentDate() {
        return lastPaymentDate;
    }

    @Override
    public String toString() {
        return "Person{" +
                "pNr=" + pNr +
                ", name='" + fullName + '\'' +
                ", lastPaymentDate='" + lastPaymentDate + '\'' +
                '}';
    }
}
