package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.Date;

/**
 * A bill that will be created for the usage of the road network. Billing policy is not yet defined.
 * Created by Gatis on 27/03/2015.
 */
public class Bill {
    /**
     * Date the bill was created.
     */
    private Date date;
    /**
     * Ammount to pay for the usage of the road
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
    //VERY IMPORTANT! Without a policy we can't bill anyone. There must be a set of rules, when why and how we charge for
    // using the road. This may also add additional changes to existing classes.
    //TODO: billing policy, pay each time you use some road section, pay for the day you used a section? several possible policies?

    // here we can design whatever style of bill we would like, but it should contain some information about why
    // the final bill price is as it is.
    //TODO: list of scans with prices, dates of scans? depending on the billing policy?
}
