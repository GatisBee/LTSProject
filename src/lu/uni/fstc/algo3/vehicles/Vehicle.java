package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * Created by Gatis on 27/03/2015.
 */
public class Vehicle {

    private NumberPlate numberPlate;
    private String maker;
    private String model;
    private String color;
    private int weight;
    private VehicleOwner owner;

    public Vehicle(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        this.numberPlate = numberPlate;
        this.maker = maker;
        this.model = model;
        this.color = color;
        this.weight = weight;
        this.owner = owner;
    }

}
