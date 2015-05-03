package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * Owner of a vehicle.
 * Created by Gatis on 27/03/2015.
 */
public class VehicleOwner {
    private List<Vehicle> ownedVehicles;
    private String name;
    private Address address;

    /**
     * Default constructor of an owner.
     *
     * @param name    name of the owner (firstName lastName)
     * @param address address
     * @param vehicle must own at least 1 vehicle
     */
    public VehicleOwner(String name, Address address, Vehicle vehicle) {
        this.name = name;
        this.address = address;

        ownedVehicles = new LinkedList<Vehicle>(); // linked list just for a change, probably sequential access will be used
        ownedVehicles.add(vehicle);
    }

    public List<Vehicle> getOwnedVehicles() {
        return ownedVehicles;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return address;
    }
}
