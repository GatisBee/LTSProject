package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * This class represents a motorcycle in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 *
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class Motorcycle extends Vehicle {
    /**
     * Default constructor of this motorcylce. All its fields are initialized from the parameters.
     *
     * @param numberPlate number plate of the motorcylce
     * @param maker       maker of the motorcylce
     * @param model       model of the motorcylce
     * @param color       color of the motorcylce
     * @param weight      weight of the motorcylce
     * @param owner       owner of the motorcylce
     */
    public Motorcycle(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        super(numberPlate, maker, model, color, weight, owner);
    }
}
