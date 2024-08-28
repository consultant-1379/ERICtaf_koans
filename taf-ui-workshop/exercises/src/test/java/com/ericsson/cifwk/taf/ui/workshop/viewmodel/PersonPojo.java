package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

/**
 * @author Mihails Volkovs mihails.volkovs@ericsson.com
 *         Date: 03.06.2016
 */
public class PersonPojo {

    private final String firstName;

    private final String lastName;

    private AddressPojo homeAddress;

    private AddressPojo workAddress;

    public PersonPojo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public AddressPojo getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(AddressPojo homeAddress) {
        this.homeAddress = homeAddress;
    }

    public AddressPojo getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(AddressPojo workAddress) {
        this.workAddress = workAddress;
    }
}
