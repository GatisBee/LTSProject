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

    private boolean flushBuffer() {
        return true;
    }
}
