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

	public Collection<Bill> createMonthlyBills(YearMonth yearMonth) {
		Collection<Bill> bills = new ArrayList<>();
		Collection<ScanEntry> monthlyScans = Filter.filterByYearMonth(
				lts.getAllScans(), yearMonth);
		Set<NumberPlate> plates = Filter
				.filterDistinctNumberPlates(monthlyScans);
		for (NumberPlate p : plates) {
			Collection<ScanEntry> scans = Filter.filterByNumberPlate(
					monthlyScans, p, Direction.IN);
			double ammountToPay = scans.size() * pricePerSection;
			Vehicle vehicle = vehicleRegistry.getVehicle(p);
			VehicleOwner vehicleOwner = vehicle.getOwner();
			Address address = vehicleOwner.getAddress();
			Bill bill = new Bill(ammountToPay, vehicle, vehicleOwner, address);
			double speedingPenalty = 0.0d;
			//todo: how to calculate if car was speeding
//			if ((speedingPenalty = calculatePenalty(monthlyScans)) > 0.0) {
//				bill.setSpeedingPenalty(speedingPenalty);
//			}
			bills.add(bill);
		}
		return bills;
	}

	public Collection<Bill> createMonthlyBills() {
		Collection<Bill> bills = new ArrayList<>();
		YearMonth yearMonth = YearMonth.now();
		Collection<ScanEntry> thisMonthScans = Filter.filterByYearMonth(
				lts.getAllScans(), yearMonth);
		Set<NumberPlate> plates = Filter
				.filterDistinctNumberPlates(thisMonthScans);
		for (NumberPlate p : plates) {
			Collection<ScanEntry> scans = Filter.filterByNumberPlate(
					thisMonthScans, p, Direction.IN);
			double ammountToPay = scans.size() * pricePerSection;
			Vehicle vehicle = vehicleRegistry.getVehicle(p);
			VehicleOwner vehicleOwner = vehicle.getOwner();
			Address address = vehicleOwner.getAddress();
			Bill bill = new Bill(ammountToPay, vehicle, vehicleOwner, address);
			double speedingPenalty = 0.0d;
//			if ((speedingPenalty = calculatePenalty(thisMonthScans)) > 0.0) {
//				bill.setSpeedingPenalty(speedingPenalty);
//			}
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
