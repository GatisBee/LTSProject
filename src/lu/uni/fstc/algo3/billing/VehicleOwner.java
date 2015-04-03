package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.billing.Address;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * Owner of a vehicle.
 * Created by Gatis on 27/03/2015.
 */
public class VehicleOwner {
    private List<Vehicle> ownedVehicles;
    private String firstName;
    private String lastName;
    private Address address;

    /**
     * Default constructor of an owner.
     * @param firstName first name
     * @param lastName last name
     * @param address address
     * @param vehicle must own at least 1 vehicle
     */
    public VehicleOwner(String firstName, String lastName, Address address, Vehicle vehicle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;

        ownedVehicles = new LinkedList<Vehicle>(); // linked list just for a change, probably sequential access will be used
        ownedVehicles.add(vehicle);
    }

	public List<Vehicle> getOwnedVehicles() {
		return ownedVehicles;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}
}
