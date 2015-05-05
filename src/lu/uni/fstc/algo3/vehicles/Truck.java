package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * This class represents a truck in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 *
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class Truck extends Vehicle {
    /**
     * Default constructor of this truck. All its fields are initialized from the parameters.
     *
     * @param numberPlate number plate of the truck
     * @param maker       maker of the truck
     * @param model       model of the truck
     * @param color       color of the truck
     * @param weight      weight of the truck
     * @param owner       owner of the truck
     */
    public Truck(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        super(numberPlate, maker, model, color, weight, owner);
    }
}
