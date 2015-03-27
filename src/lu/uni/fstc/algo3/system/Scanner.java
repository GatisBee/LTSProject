package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class Scanner {
    private Collection<ScanEntry> buffer;
    private RoadSection roadSection;
    private Checkpoint checkpoint;
    private String direction;

    protected Scanner(RoadSection roadSection, Checkpoint checkpoint, String direction) {
        this.roadSection = roadSection;
        this.checkpoint = checkpoint;
        this.direction = direction;
    }

    protected boolean scan(NumberPlate plate) {
        return true;
    }

    /**
     * Sends all scan entries from the buffer to centralized system registry and resets the buffer.
     * @return A boolean value indicating operation success or failure.
     */
    private boolean flushBuffer() {
        return true;
    }
}
