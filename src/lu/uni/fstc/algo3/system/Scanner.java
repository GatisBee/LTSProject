package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

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
    /**
     * A unique scanner ID, should be provided with a uniqueness on system level.
     */
    private int scannerID;
    private static final int BUFFER_THRESHOLD = 1000; //flush at this buffer size
    private static final long TIME_TILL_FLUSH = 600000; //ms
    private Timer timer; // timer for flush

    public Scanner(RoadSection roadSection, Checkpoint checkpoint, int scannerID, Direction direction) {
        this.roadSection = roadSection;
        this.checkpoint = checkpoint;
        this.direction = direction;
        this.scannerID = scannerID;

        buffer = new LinkedList<ScanEntry>(); // inserting elements at the end of the list, no retrieval operations
        timer = new Timer();
        timer.schedule(new FushTask(), TIME_TILL_FLUSH, TIME_TILL_FLUSH); //execute every TIME_TILL_FLUSH milliseconds
    }

    /**
     * Scans cars passing this scanner.
     * @param plate number plate of the scanned vehicle
     * @return success or failure of the operation.
     */
    protected boolean scan(NumberPlate plate) {
        buffer.add(new ScanEntry(plate, Instant.now(), scannerID, checkpoint, direction));
        if (buffer.size() >= BUFFER_THRESHOLD) {
            flushBuffer();
        }
        return true;
    }

    /**
     * Sends all scan entries from the buffer to centralized system registry and resets the buffer.
     * @return A boolean value indicating operation success or failure.
     */
    private boolean flushBuffer() {
        //TODO: implement flush method (flush to LTS.getInstance().addScans(buffer);)
        return true;
    }

    /**
     * Task for a timer to flush the buffer.
     */
    class FushTask extends TimerTask {
        @Override
        public void run() {
            /**
             * Flushes the buffer of this scanner.
             */
            Scanner.this.flushBuffer(); // have to test, but looks legit :)
        }
    }
}
