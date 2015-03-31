package lu.uni.fstc.algo3.statistics;

import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.Instant;

/**
 * Immutable class for representing a scan entry in LTS system.
 * It contains 2 fields describing a scan entry. Namely licence plate of a vehicle in question
 * and the time the scan was made.
 * Created by Gatis on 27/03/2015.
 */
public class ScanEntry {
    private NumberPlate numberPlate;
    private Instant timestamp;

    public ScanEntry(NumberPlate numberPlate, Instant timestamp) {
        this.numberPlate = numberPlate;
        this.timestamp = timestamp;
    }

    /**
     * Gets the number plate of the vehicle fixed by this scan.
     * @return number plate of a vehicle.
     */
    public NumberPlate getNumberPlate() {
        return numberPlate;
    }

    /**
     * Gets time when the scan was made.
     * @return time of the scan.
     */
    public Instant getTimestamp() {
        return timestamp;
    }

}
