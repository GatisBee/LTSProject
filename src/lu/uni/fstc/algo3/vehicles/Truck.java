package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * Created by Gatis on 27/03/2015.
 */
public class Truck extends Vehicle {
    public Truck(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        super(numberPlate, maker, model, color, weight, owner);
    }
}
