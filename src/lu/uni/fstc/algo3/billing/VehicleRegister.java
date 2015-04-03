package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.util.HashMap;
import java.util.Map;

/**
 * Registry of vehicles mapping vehicles to their owners. Can be used for billing, police, etc.
 * Created by Gatis on 31/03/2015.
 */
//TODO: register related methods
public class VehicleRegister {
    private Map<NumberPlate, VehicleOwner> vehicleRegister;

	public VehicleRegister() {
		//TODO: verify if this map is optimal
		vehicleRegister = new HashMap<NumberPlate, VehicleOwner>();
	}
	public boolean addEntry(NumberPlate numberPlate, VehicleOwner vehicleOwner) {
		vehicleRegister.put(numberPlate, vehicleOwner);
		return true;
	}
	public boolean removeEntry(NumberPlate numberPlate) {
		vehicleRegister.remove(numberPlate);
		return true;
	}
}
