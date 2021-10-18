package model;

import java.util.Date;

public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private long nationalCode;
    private String phoneNUmber;
    private Date birthDate;
    private String email;


    public Person(int id, String firstName, String lastName, long nationalCode, String phoneNUmber,
                  Date birthDate, String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNUmber = phoneNUmber;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Person(String firstName, String lastName, long nationalCode, String phoneNUmber,
                  Date birthDate, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.phoneNUmber = phoneNUmber;
        this.birthDate = birthDate;
        this.email = email;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(long nationalCode) {
        this.nationalCode = nationalCode;
    }
}
