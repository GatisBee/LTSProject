package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.NumberPlate;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry of vehicles mapping vehicles to their owners. Can be used for billing, police, etc.
 * Created by Gatis on 31/03/2015.
 */
public class VehicleRegistry {
    /**
     * A map between a number plate of a vehicle and vehicles owner
     */
    private Map<NumberPlate, Vehicle> vehicleRegistry;

    /**
     * Default constructor, initializes private fields.
     */
    public VehicleRegistry() {
        vehicleRegistry = new HashMap<NumberPlate, Vehicle>();
    }

    /**
     * A an entry to the vehicle register.
     *
     * @param numberPlate number plate of the vehicle
     * @param vehicle     vehicle
     * @return success if true
     */
    public boolean addEntry(NumberPlate numberPlate, Vehicle vehicle) {
        vehicleRegistry.put(numberPlate, vehicle);
        return true;
    }

    /**
     * Remove an entry from the vehicle register.
     *
     * @param numberPlate number plate of the vehicle to remove
     * @return success if true
     */
    public boolean removeEntry(NumberPlate numberPlate) {
        vehicleRegistry.remove(numberPlate);
        return true;
    }

    //TODO: print contents for debugging
    public void printContents() {
        for (NumberPlate np : vehicleRegistry.keySet()) {
            System.out.println(vehicleRegistry.get(np));
        }
    }
}
