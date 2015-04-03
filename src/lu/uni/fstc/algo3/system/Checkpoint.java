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
     * A unique identifier of a checkpoint
     * The uniqueness of the identifier should be enforced from outside of this class on the scope of whole system.
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
    //TODO: define a reasonable access for scanner arrays, so they don't get modified outside of this class.
    /*
    * If you want to remove some scanners you probably have to know what scanners you have on this checkpoint.
    * There is more than 1 way how to do this, but in this particular case you get a reference to scannersIn/Out arrays
    * which implies that you can remove scanners by using those references (from outside of this class in uncontrolled manner).
    * On the other hand, we could provide methods in this class for such removals 
    * and to NOT give direct references to ArrayLists storing those scanners.
    */
    public Collection<Scanner> getScannersIn() {
    	//TODO: immutable scanners, unmodifiable arrays?
        return scannersIn;
    }

    public Collection<Scanner> getScannersOut() {
        return scannersOut;
    }

	public RoadSection getRoadSection() {
		return roadSection;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}
}
