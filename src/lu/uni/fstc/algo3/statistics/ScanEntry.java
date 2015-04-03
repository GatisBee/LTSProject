package lu.uni.fstc.algo3.statistics;

import lu.uni.fstc.algo3.system.Checkpoint;
import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.Instant;

/**
 * Immutable class for representing a scan entry in LTS system.
 * Created by Gatis on 27/03/2015.
 */
public class ScanEntry {
    private NumberPlate numberPlate;
    private Instant timestamp;
    private int scannerID;
    private Checkpoint checkpoint;
    private Direction direction;

    public ScanEntry(NumberPlate numberPlate, Instant timestamp, int scannerID, Checkpoint checkpoint, Direction direction) {
        this.numberPlate = numberPlate;
        this.timestamp = timestamp;
        this.scannerID = scannerID;
        this.checkpoint = checkpoint;
        this.direction = direction;
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

	public int getScannerID() {
		return scannerID;
	}

	public Checkpoint getCheckpoint() {
		return checkpoint;
	}

	public Direction getDirection() {
		return direction;
	}

}
