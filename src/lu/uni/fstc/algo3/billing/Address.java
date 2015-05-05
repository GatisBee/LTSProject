package lu.uni.fstc.algo3.billing;

/**
 * Billing address of a vehicle owner.
 * Created by Gatis on 27/03/2015.
 */
public class Address {
    private String street;
    private String country;
    private String city;
    private String zipCode;

    public Address(String street, String country, String city, String zipCode) {
        super();
        this.street = street;
        this.country = country;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreetName() {
        return street;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreetNumberAppendix() {
        return zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        if (!country.equals(address.country)) return false;
        if (!city.equals(address.city)) return false;
        return zipCode.equals(address.zipCode);

    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + country.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + zipCode.hashCode();
        return result;
    }
}
