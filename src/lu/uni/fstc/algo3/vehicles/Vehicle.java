package lu.uni.fstc.algo3.vehicles;

import lu.uni.fstc.algo3.billing.VehicleOwner;

/**
 * This class represents a vehicle in Luxembourg Toll System. It contains some basic vehicle characteristics.
 * This class is immutable. If for some reason vehicle changes the owner or number plate, this vehicle should be
 * deleted from vehicle registry and a new vehicle object should be created. It can be queried for all its fields via
 * public getter methods.
 * Created by Gatis on 27/03/2015.
 */
public class Vehicle {
    private NumberPlate numberPlate;
    private String maker;
    private String model;
    private String color;
    private int weight;
    private VehicleOwner owner;

    /**
     * Default constructor of this vehicle. All its fields are initialized from the parameters.
     *
     * @param numberPlate number plate of the vehicle
     * @param maker       maker of the vehicle
     * @param model       model of the vehicle
     * @param color       color of the vehicle
     * @param weight      weight of the vehicle
     * @param owner       owner of the vehicle
     */
    public Vehicle(NumberPlate numberPlate, String maker, String model, String color, int weight, VehicleOwner owner) {
        this.numberPlate = numberPlate;
        this.maker = maker;
        this.model = model;
        this.color = color;
        this.weight = weight;
        this.owner = owner;
    }

    /**
     * Returns the number plate of this vehicle.
     *
     * @return number plate.
     */
    public NumberPlate getNumberPlate() {
        return numberPlate;
    }

    /**
     * Returns the manufacturer of this vehicle.
     *
     * @return manufacturer of the vehicle.
     */
    public String getMaker() {
        return maker;
    }

    /**
     * Returns the model of this vehicle.
     *
     * @return model of the vehicle.
     */
    public String getModel() {
        return model;
    }

    /**
     * Returns the color of this vehicle.
     *
     * @return color of the vehicle.
     */
    public String getColor() {
        return color;
    }

    /**
     * Returns the weight of this vehicle.
     *
     * @return weight of the vehicle.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns the owner of this vehicle.
     *
     * @return owner of the vehicle.
     */
    public VehicleOwner getOwner() {
        return owner;
    }

    /**
     * Set vehicle owner.
     *
     * @param owner new owner of the vehicle
     */
    public void setOwner(VehicleOwner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "numberPlate=" + numberPlate +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", weight=" + weight +
                ", owner=" + owner +
                '}';
    }
}
