package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.NumberPlate;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry of vehicles mapping vehicles to their owners. Can be used for billing, police, etc.
 * Created by Gatis on 31/03/2015.
 */
public class VehicleRegister {
    /**
     * A map between a number plate of a vehicle and vehicles owner
     */
    private Map<NumberPlate, Vehicle> vehicleRegister;
    private VehicleRegister register;

    /**
     * Default constructor, initializes private fields.
     */
	public VehicleRegister() {
		vehicleRegister = new HashMap<NumberPlate, Vehicle>();
	}

    /**
     * A an entry to the vehicle register.
     * @param numberPlate number plate of the vehicle
     * @param vehicle vehicle
     * @return success if true
     */
	public boolean addEntry(NumberPlate numberPlate, Vehicle vehicle) {
		vehicleRegister.put(numberPlate, vehicle);
		return true;
	}

    /**
     * Remove an entry from the vehicle register.
     * @param numberPlate number plate of the vehicle to remove
     * @return success if true
     */
	public boolean removeEntry(NumberPlate numberPlate) {
		vehicleRegister.remove(numberPlate);
		return true;
	}
}
