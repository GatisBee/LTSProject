package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * This class represents a car in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class Car extends Vehicle {
    /**
     * Default constructor of this car. All its fields are initialized from the parameters.
     * @param numberPlate number plate of the car
     * @param maker maker of the car
     * @param model model of the car
     * @param color color of the car
     * @param weight weight of the car
     * @param owner owner of the car
     */
    public Car(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        super(numberPlate, maker, model, color, weight, owner);
    }
}
