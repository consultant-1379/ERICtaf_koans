package com.ericsson.cifwk.taf.ui.workshop.viewmodel;

import com.google.common.base.Preconditions;

/**
 * @author Mihails Volkovs mihails.volkovs@ericsson.com
 *         Date: 03.06.2016
 */ // let's imagine that this type comes to us from data source as test step parameter
public class AddressPojo {

    private final String city;

    private final String street;

    private final String zip;

    public AddressPojo(String city, String street) {
        this(city, street, "");
    }

    public AddressPojo(String city, String street, String zip) {
        Preconditions.checkNotNull(city);
        Preconditions.checkNotNull(street);
        this.city = city;
        this.street = street;
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return String.format("%s\n%s %s", street, city, zip);
    }
}
