package lu.uni.fstc.algo3.statistics;

import lu.uni.fstc.algo3.system.Checkpoint;
import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.system.RoadSection;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Immutable class for representing a scan entry in LTS system.
 * Created by Gatis on 27/03/2015.
 */
public class ScanEntry implements Comparable<ScanEntry> {
    private NumberPlate numberPlate;
    private LocalDateTime timestamp;
    private UUID scannerID;
    private Checkpoint checkpoint;
    private RoadSection roadSection;
    private Direction direction;

    public ScanEntry(NumberPlate numberPlate, LocalDateTime timestamp, UUID scannerID, Checkpoint checkpoint, RoadSection roadSection, Direction direction) {
        this.numberPlate = numberPlate;
        this.timestamp = timestamp;
        this.scannerID = scannerID;
        this.checkpoint = checkpoint;
        this.roadSection = roadSection;
        this.direction = direction;
    }

    /**
     * Gets the number plate of the vehicle fixed by this scan.
     *
     * @return number plate of a vehicle.
     */
    public NumberPlate getNumberPlate() {
        return numberPlate;
    }

    /**
     * Gets time when the scan was made.
     *
     * @return time of the scan.
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public UUID getScannerID() {
        return scannerID;
    }

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public Direction getDirection() {
        return direction;
    }

    public RoadSection getRoadSection() {
        return roadSection;
    }

    @Override
    public String toString() {
        return numberPlate.toString() + " " + timestamp.toString() + " " + scannerID.toString()
                + " " + checkpoint.toString() + " " + direction.toString();
    }

    /**
     * Delegates compareTo to LocalDateTime.CompareTo() method, since the comparison for ordering is performed on the timestamps of scan entries.
     *
     * @param o scan entry to compare against this scan entry
     * @return negative value if less, positive if greater
     */
    @Override
    public int compareTo(ScanEntry o) {
        return timestamp.compareTo(o.getTimestamp());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScanEntry scanEntry = (ScanEntry) o;

        if (!numberPlate.equals(scanEntry.numberPlate)) return false;
        if (!timestamp.equals(scanEntry.timestamp)) return false;
        if (!scannerID.equals(scanEntry.scannerID)) return false;
        if (!checkpoint.equals(scanEntry.checkpoint)) return false;
        if (!roadSection.equals(scanEntry.roadSection)) return false;
        return direction == scanEntry.direction;

    }

    @Override
    public int hashCode() {
        int result = numberPlate.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + scannerID.hashCode();
        result = 31 * result + checkpoint.hashCode();
        result = 31 * result + roadSection.hashCode();
        result = 31 * result + direction.hashCode();
        return result;
    }
}
