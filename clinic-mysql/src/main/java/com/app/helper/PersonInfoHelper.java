package com.app.helper;

public class PersonInfoHelper {

    private String firstName;
    private String lastName;
    private long value;

    public PersonInfoHelper() {
    }

    public PersonInfoHelper(String firstName, String lastName, long value) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.value = value;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PersonInfoHelper{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", value=" + value +
                '}';
    }
}
