package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * This class represents a bus in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 *
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class Bus extends Vehicle {
    /**
     * Default constructor of this bus. All its fields are initialized from the parameters.
     *
     * @param numberPlate number plate of the bus
     * @param maker       maker of the bus
     * @param model       model of the bus
     * @param color       color of the bus
     * @param weight      weight of the bus
     * @param owner       owner of the bus
     */
    public Bus(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        super(numberPlate, maker, model, color, weight, owner);
    }
}
