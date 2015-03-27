package lu.uni.fstc.algo3.system;

import java.util.ArrayList;
import java.util.Collection;

/**This class represents a starting/ending point of a road section. Each checkpoint can have [0,n] scanners facing
 * each direction (in, out).
 * Created by Gatis on 27/03/2015.
 */
public class Checkpoint {
    /**
     * Road section to which this checkpoint belongs.
     */
    private RoadSection roadSection;
    /**
     * Name of the checkpoint
     */
    private String name;
    /**
     * A unique identifier of the checkpoint
     */
    private long id;
    /**
     * Scanners for inbound traffic
     */
    private Collection<Scanner> scannersIn;
    /**
     * Scanners for outbound traffic
     */
    private Collection<Scanner> scannersOut;

    public Checkpoint(RoadSection roadSection, String name, long id) {
        this.roadSection = roadSection;
        this.name = name;
        this.id = id;
        //TODO figure out if the data type fits usage
        scannersIn = new ArrayList<Scanner>();
        scannersOut = new ArrayList<Scanner>();
    }

    public boolean addScannerIn(Scanner scanner) {
        scannersIn.add(scanner);
        return true;
    }

    public boolean addScannerOut(Scanner scanner) {
        scannersOut.add(scanner);
        return true;
    }

    public Collection<Scanner> getScannersIn() {
        return scannersIn;
    }

    public Collection<Scanner> getScannersOut() {
        return scannersOut;
    }
}
