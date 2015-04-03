package lu.uni.fstc.algo3.billing;

/**
 * Billing address of a vehicle owner.
 * Created by Gatis on 27/03/2015.
 */
public class Address {
    private String streetName;
    private String country;
    private String city;
    private int streetNumber;
    private String streetNumberAppendix;
	
    public Address(String streetName, String country, String city,
			int streetNumber, String streetNumberAppendix) {
		super();
		this.streetName = streetName;
		this.country = country;
		this.city = city;
		this.streetNumber = streetNumber;
		this.streetNumberAppendix = streetNumberAppendix;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public String getStreetNumberAppendix() {
		return streetNumberAppendix;
	}
    
}
