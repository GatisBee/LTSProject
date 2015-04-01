package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;

import java.util.ArrayList;
import java.util.Collection;

/**This class represents top level of the LTS system. It provides public interfaces for different actors to access
 * system functionality. It uses a singleton pattern to ensure that a single consistent instance of this class is maintained
 * and other lower level classes can easily access fields of this class.
 * Created by Gatis on 27/03/2015.
 */
//TODO: Interfaces for basic functionality of the system go here. Some additional support classes may be added or changed.
    // MOST OF THE MEAT GOES IN THIS CLASS!
public class LTS {

    private double speedingPenalty;
    private RoadMap roadMap;
    private Collection<ScanEntry> allScans;


    private LTS _instance;

    private LTS(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    /**
     * Returns an instance of this class.
     * @return instance of this class.
     */
    public LTS getInstance() {
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
}
