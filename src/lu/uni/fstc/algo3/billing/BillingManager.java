package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.system.LTS;

/**
 * Responsible for bill creation and sending ? Needs utilities for filtering scans by some criteria e.g. time.
 * Billing policy to be defined, which in turn will shape this whole package.
 * Created by Gatis on 27/03/2015.
 */

public class BillingManager {
    // to get scan results, probably will need some utility classes to filter and process data

    /**
     * Instance variable of the system.
     */
    private LTS lts;
    /**
     * Vehicle register, to get information about scanned vehicle owners.
     */
    private VehicleRegistry vehicleRegister;

    //TODO: will improve as we go depending on our needs. Meat of this package goes here.
    public BillingManager() {
        lts = LTS.getInstance();
        vehicleRegister = new VehicleRegistry();
    }

}
