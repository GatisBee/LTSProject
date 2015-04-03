package lu.uni.fstc.algo3.system;

import java.util.Collection;
import java.util.LinkedList;

import lu.uni.fstc.algo3.statistics.ScanEntry;

/**This class represents top level of the LTS system. It provides public interfaces for different actors to access
 * system functionality. It uses a singleton pattern to ensure that a single consistent instance of this class is maintained
 * and other lower level classes can easily access fields of this class.
 * Created by Gatis on 27/03/2015.
 */
public class LTS {

    private double speedingPenalty;
    private RoadMap roadMap; // will be used when system will be ready
    private Collection<ScanEntry> allScans;


    private static LTS _instance;

    private LTS(RoadMap roadMap) {
        this.roadMap = roadMap;
        /*
         * I don't use array list, because there is will be sequential access and
         * for big lists it is costly to increase the size of array list (doubling the size and copying all elements)
         */
        allScans = new LinkedList<ScanEntry>();
    }

    /**
     * Returns an instance of this class.
     * @return instance of this class.
     */
    public static LTS getInstance() {
        if (_instance == null) {
            _instance = new LTS(new RoadMap());
            return _instance;
        } else {
            return _instance;
        }
    }

    /**
     * Adds new scans to the central system registry of scans. Used by scanners to add their recent scans.
     * @param scans scans to be added.
     * @return success or failure of the operation.
     */
    public synchronized boolean addScans(Collection<? extends ScanEntry> scans) {
        allScans.addAll(scans);
        return true;
    }

	public double getSpeedingPenalty() {
		return speedingPenalty;
	}

	public void setSpeedingPenalty(double speedingPenalty) {
		this.speedingPenalty = speedingPenalty;
	}

    public RoadMap getRoadMap() {
        return roadMap;
    }
}
