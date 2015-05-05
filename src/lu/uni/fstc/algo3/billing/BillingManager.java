package lu.uni.fstc.algo3.billing;

import lu.uni.fstc.algo3.statistics.Filter;
import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.system.LTS;
import lu.uni.fstc.algo3.vehicles.NumberPlate;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Responsible for bill creation and sending ? Needs utilities for filtering
 * scans by some criteria e.g. time. Billing policy to be defined, which in turn
 * will shape this whole package. Created by Gatis on 27/03/2015.
 */

public class BillingManager {
    // to get scan results, probably will need some utility classes to filter
    // and process data
    /**
     * Instance variable of the system.
     */
    private LTS lts;
    /**
     * Vehicle register, to get information about scanned vehicle owners.
     */
    private VehicleRegistry vehicleRegistry;

    private double pricePerSection;

    public BillingManager() {
        lts = LTS.getInstance();
        vehicleRegistry = lts.getVehicleRegistry();
        pricePerSection = 0.5d;
    }

    /**
     * Creates bills for all vehicles that where using LTS during the specified year-month.
     * There are no tests for the year month comparison, but it uses java API functionality for comparing
     * so if the logic is correct, it should work.
     *
     * @param yearMonth year and month for which the bills must be created.
     * @return collection of bills that where created
     */
    public Collection<Bill> createMonthlyBills(YearMonth yearMonth) {
        Collection<Bill> bills = new ArrayList<>(); // collection to store all bills
        /*
        Get scans of the specified month
         */
        Collection<ScanEntry> monthlyScans = Filter.filterByYearMonth(
                lts.getAllScans(), yearMonth);
        /*
        Get unique number plates that where scanned during the specified month
         */
        Set<NumberPlate> plates = Filter
                .filterDistinctNumberPlates(monthlyScans);
        for (NumberPlate p : plates) {
            /*
            For each number plate get all scans of that number plate
             */
            Collection<ScanEntry> scansAllDirections = Filter.filterByNumberPlate(monthlyScans, p);
            /*
            For billing get only the inbound scans of number plate
             */
            Collection<ScanEntry> scansInbound = Filter.filterByNumberPlate(
                    scansAllDirections, p, Direction.IN);// in this case there are redundant checks on number plate
            /*
            Prepare billing parameters and create the bill
             */
            double ammountToPay = scansInbound.size() * pricePerSection;
            Vehicle vehicle = vehicleRegistry.getVehicle(p);
            VehicleOwner vehicleOwner = vehicle.getOwner();
            Address address = vehicleOwner.getAddress();
            Bill bill = new Bill(ammountToPay, vehicle, vehicleOwner, address);
            double speedingPenalty = 0.0d;
            /*
            Check if driver was speeding and calculate the penalty if he was
             */
            if ((speedingPenalty = calculatePenalty(scansAllDirections)) > 0.0) {
                // add the penalty to the bill
                bill.setSpeedingPenalty(speedingPenalty);
            }
            bills.add(bill);
        }
        return bills;
    }

    /**
     * Creates bills for all vehicles that where using LTS during the current year-month.
     * This version of method is more or less for illustration purposes that the billing system works.
     *
     * @return collection of created bills
     */
    public Collection<Bill> createMonthlyBills() {
        /*
        Collection to store all bills.
         */
        Collection<Bill> bills = new ArrayList<>();
        // get current year month
        YearMonth yearMonth = YearMonth.now();
        /*
        Filter scans by current month
         */
        Collection<ScanEntry> thisMonthScans = Filter.filterByYearMonth(
                lts.getAllScans(), yearMonth);
        /*
        Get unique number plates that where scanned this month
         */
        Set<NumberPlate> plates = Filter
                .filterDistinctNumberPlates(thisMonthScans);
        for (NumberPlate p : plates) {
            /*
            For each number plate that was scanned get all scans, in and out
             */
            Collection<ScanEntry> scansAllDirections = Filter.filterByNumberPlate(thisMonthScans, p);
            /*
            For billing, get only the scans that where inbound (once entered road section, driver has to pay for usage)
             */
            Collection<ScanEntry> scansInbound = Filter.filterByNumberPlate(
                    scansAllDirections, p, Direction.IN);
            /*
            Prepare bill parameters and create the bill
             */
            double ammountToPay = scansInbound.size() * pricePerSection; // global price for road section usage
            Vehicle vehicle = vehicleRegistry.getVehicle(p);
            VehicleOwner vehicleOwner = vehicle.getOwner();
            Address address = vehicleOwner.getAddress();
            Bill bill = new Bill(ammountToPay, vehicle, vehicleOwner, address);
            double speedingPenalty = 0.0d;
            /*
            Determine if the driver was speeding and calculate penalty if he was.
             */
            if ((speedingPenalty = calculatePenalty(scansAllDirections)) > 0.0) {
                // add the penalty to the bill
                bill.setSpeedingPenalty(speedingPenalty);
            }
            bills.add(bill);
        }
        return bills;
    }

    //todo: implement this
    private double calculatePenalty(Collection<ScanEntry> scans) {
        // get in and out scans mby order by date
        // collection should have scans of a single vehicle, mby filter without direction criterion
        return 0.0d;
    }

}
