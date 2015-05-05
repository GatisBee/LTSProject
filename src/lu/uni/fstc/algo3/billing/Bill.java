package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.time.LocalDateTime;

/**
 * A bill that will be created for the usage of the road network. Billing policy is defined in billing manager.
 * Created by Gatis on 27/03/2015.
 */
public class Bill {
    /**
     * Date the bill was created.
     */
    private LocalDateTime date;
    /**
     * Amount to pay for the usage of the road
     */
    private double ammountToPay;
    /**
     * Indicates if the bill is payed
     */
    private boolean billPayed;
    /**
     * Vehicle for that was detected by scanners
     */
    private Vehicle vehicle;
    /**
     * Owner of the vehicle
     */
    private VehicleOwner vehicleOwner;
    /**
     * Billing address (can be derived from vehicleOwner).
     */
    private Address address;

    private double speedingPenalty;

    public Bill(double ammountToPay, Vehicle vehicle, VehicleOwner vehicleOwner, Address address) {
        super();
        this.date = LocalDateTime.now();
        this.ammountToPay = ammountToPay;
        this.billPayed = false;
        this.vehicle = vehicle;
        this.vehicleOwner = vehicleOwner;
        this.address = address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getAmmountToPay() {
        return ammountToPay;
    }

    public boolean isBillPayed() {
        return billPayed;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public VehicleOwner getVehicleOwner() {
        return vehicleOwner;
    }

    public Address getAddress() {
        return address;
    }

    public void setBillPayed(boolean payed) {
        billPayed = payed;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date=" + date +
                ", ammountToPay=" + ammountToPay +
                ", speedingPenalty=" + speedingPenalty +
                ", billPayed=" + billPayed +
                ", vehicle=" + vehicle +
                ", vehicleOwner=" + vehicleOwner +
                ", address=" + address +
                '}';
    }

    public void setSpeedingPenalty(double speedingPenalty) {
        this.speedingPenalty = speedingPenalty;
        // update the total amount to pay with the new speeding penalty
        this.ammountToPay += this.speedingPenalty;
    }
}
