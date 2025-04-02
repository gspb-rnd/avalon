package com.gspb.avalon.client.domain.model;

import com.gspb.avalon.shared.domain.ValueObject;

import java.util.Objects;

/**
 * Value object representing a physical address.
 */
public class Address implements ValueObject {
    
    private final String street;
    private final String city;
    private final String state;
    private final String postalCode;
    private final String country;
    
    /**
     * Creates a new address.
     *
     * @param street The street address
     * @param city The city
     * @param state The state or province
     * @param postalCode The postal code
     * @param country The country
     */
    public Address(String street, String city, String state, String postalCode, String country) {
        this.street = Objects.requireNonNull(street, "Street cannot be null");
        this.city = Objects.requireNonNull(city, "City cannot be null");
        this.state = Objects.requireNonNull(state, "State cannot be null");
        this.postalCode = Objects.requireNonNull(postalCode, "Postal code cannot be null");
        this.country = Objects.requireNonNull(country, "Country cannot be null");
    }
    
    /**
     * Gets the street address.
     *
     * @return The street address
     */
    public String getStreet() {
        return street;
    }
    
    /**
     * Gets the city.
     *
     * @return The city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Gets the state or province.
     *
     * @return The state or province
     */
    public String getState() {
        return state;
    }
    
    /**
     * Gets the postal code.
     *
     * @return The postal code
     */
    public String getPostalCode() {
        return postalCode;
    }
    
    /**
     * Gets the country.
     *
     * @return The country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Gets the formatted address.
     *
     * @return The formatted address
     */
    public String getFormattedAddress() {
        return String.format("%s, %s, %s %s, %s", street, city, state, postalCode, country);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
               Objects.equals(city, address.city) &&
               Objects.equals(state, address.state) &&
               Objects.equals(postalCode, address.postalCode) &&
               Objects.equals(country, address.country);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, postalCode, country);
    }
    
    @Override
    public String toString() {
        return getFormattedAddress();
    }
}
