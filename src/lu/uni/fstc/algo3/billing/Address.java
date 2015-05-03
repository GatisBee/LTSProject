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
    
}
