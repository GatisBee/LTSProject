package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.LinkedList;
import java.util.List;

/**
 * Owner of a vehicle.
 * Created by Gatis on 27/03/2015.
 */
public class VehicleOwner {
    private String name;
    private Address address;

    /**
     * Default constructor of an owner.
     *
     * @param name    name of the owner (firstName lastName)
     * @param address address
     */
    public VehicleOwner(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "VehicleOwner{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleOwner that = (VehicleOwner) o;

        if (!name.equals(that.name)) return false;
        return address.equals(that.address);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
