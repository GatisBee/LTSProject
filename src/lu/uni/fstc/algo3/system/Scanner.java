package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.util.Collection;

/**
 * This class represents a scanner number plate scanner device in LTS road monitoring system.
 * Created by Gatis on 27/03/2015.
 */
public class Scanner {
    /** 
    * I consider this as a software buffer, assuming that during buffer flush there is another hardware buffer
    * that can handle scans while this buffer is bussy transfering data.
    */
    private Collection<ScanEntry> buffer;
    /** 
    * Road section on which this scanner is located, can be used to change road sections vehicle counter.
    */
    private RoadSection roadSection;
    /** 
    * Idk if this is usefull yet. But for now we can leave it here, kinda makes sense.
    */
    private Checkpoint checkpoint;

    //TODO: We can use enum for this, but anyway direction is passed during construction from another class.
    // so checks should be made elsewhere
    private String direction;

    protected Scanner(RoadSection roadSection, Checkpoint checkpoint, String direction) {
        this.roadSection = roadSection;
        this.checkpoint = checkpoint;
        this.direction = direction;
    }

    /**
     * Scan each cars that the scanner see.
     * @param plate
     * @return
     */
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
