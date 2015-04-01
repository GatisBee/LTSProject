package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.Instant;
import java.util.Collection;

/**
 * This class represents a number plate scanner device in LTS road monitoring system. We assume that a single device
 * can scan single lane in one direction only.
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
    /**
     * It is responsibility of the user to indicate in which direction this scanner is pointed.
     */
    private Direction direction;

    public Scanner(RoadSection roadSection, Checkpoint checkpoint, Direction direction) {
        this.roadSection = roadSection;
        this.checkpoint = checkpoint;
        this.direction = direction;
    }

    /**
     * Scans cars passing this scanner.
     * @param plate number plate of the scanned vehicle
     * @return success or failure of the operation.
     */
    protected boolean scan(NumberPlate plate) {
        buffer.add(new ScanEntry(plate, Instant.now()));
        return true;
    }

    /**
     * Sends all scan entries from the buffer to centralized system registry and resets the buffer.
     * @return A boolean value indicating operation success or failure.
     */
    private boolean flushBuffer() {
        //TODO : define a policy for buffer flush (e.g. threshold for buffer when to flush and/or timer)
        return true;
    }
}
