package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.LocalDateTime;
import java.util.*;

/**
 * This class represents a number plate scanner device in Luxembourg Toll System. We assume that a single device
 * can scan single lane in one direction only.
 * Created by Gatis on 27/03/2015.
 */
public class Scanner {
    /**
     * We consider this as a software buffer, assuming that during buffer flush there is another hardware buffer
     * that can handle scans while this buffer is busy transferring data.
     */
    private List<ScanEntry> buffer;
    /**
     * Road section on which this scanner is located, can be used to change road sections vehicle counter.
     */
    private RoadSection roadSection;
    /**
     * IDK if this is useful yet. But for now we can leave it here, kind of makes sense.
     */
    private Checkpoint checkpoint;
    /**
     * It is responsibility of the user to indicate in which direction this scanner is pointed.
     */
    private Direction direction;
    /**
     * A unique scanner ID, should be provided with a uniqueness on system level.
     */
    private UUID scannerID;
    private static final int BUFFER_THRESHOLD = 1000; //flush at this buffer size
    private static final long TIME_TILL_FLUSH = 15000; //ms
    private Timer timer; // timer for flush buffer

    public Scanner(RoadSection roadSection, Checkpoint checkpoint, UUID scannerID, Direction direction) {
        this.roadSection = roadSection;
        this.checkpoint = checkpoint;
        this.direction = direction;
        this.scannerID = scannerID;

        buffer = new LinkedList<>(); // inserting elements at the end of the list, no retrieval operations
        timer = new Timer();
        timer.schedule(new FushTask(), TIME_TILL_FLUSH, TIME_TILL_FLUSH); //execute every TIME_TILL_FLUSH milliseconds
    }

    /**
     * Scans cars passing this scanner.
     *
     * @param plate number plate of the scanned vehicle
     * @return success or failure of the operation.
     */
    public synchronized boolean scan(NumberPlate plate) {
        /* Add  the new scan entry to buffer */
        buffer.add(new ScanEntry(plate, LocalDateTime.now(), scannerID, checkpoint, roadSection, direction));
        System.out.println("Scanner: " + scannerID + "; Checkpoint: " + checkpoint.getName() + "; Number plate: " + plate + "; Direction: " + direction);
        System.out.println();
        /* Increase or decrease the vehicle on road section counter, depending on the direction of the scanner */
        if (direction == Direction.IN) {
            roadSection.vehicleEnters();
        } else {
            roadSection.vehicleLeaves();
        }
        /* Flush the buffer is buffer threshold is reached */
        if (buffer.size() >= BUFFER_THRESHOLD) {
            System.out.println("Scanner buffer full, flushing...");
            flushBuffer();
        }
        return true;
    }

    /**
     * Sends all scan entries from the buffer to centralized system registry and resets the buffer.
     *
     * @return A boolean value indicating operation success or failure.
     */
    private boolean flushBuffer() {
        LTS.getInstance().addScans(buffer);
        buffer.removeAll(buffer); // linked lists iterator supports remove
        return true;
    }

    /**
     * Task for a timer to flush the buffer.
     */
    class FushTask extends TimerTask {
        @Override
        public void run() {
            /**
             * Flushes the buffer of this scanner if it is not empty.
             */
            if (!buffer.isEmpty()) {
                System.out.println(Thread.currentThread().getName() + " buffer timer expired, flushing scanners buffer...");
                flushBuffer();
            }
        }
    }
}
