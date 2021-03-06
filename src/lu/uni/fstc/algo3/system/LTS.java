package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.billing.VehicleRegistry;
import lu.uni.fstc.algo3.statistics.ScanEntry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This class represents top level of the LTS system. It provides public interfaces for different actors to access
 * system functionality. It uses a singleton pattern to ensure that a single consistent instance of this class is maintained
 * and other lower level classes can easily access fields of this class.
 * Created by Gatis on 27/03/2015.
 */
public class LTS {

    private double speedingPenalty;
    private RoadMap roadMap; // will be used when system will be ready
    private List<ScanEntry> allScans;
    private VehicleRegistry vehicleRegistry;


    private static LTS _instance;


    private LTS() {
        this.roadMap = new RoadMap();
        allScans = new ArrayList<>();
        vehicleRegistry = new VehicleRegistry();
    }

    /**
     * Returns an instance of this class.
     *
     * @return instance of this class.
     */
    public static LTS getInstance() {
        if (_instance == null) {
            _instance = new LTS();
            return _instance;
        } else {
            return _instance;
        }
    }

    /**
     * Adds new scans to the central system registry of scans. Used by scanners to add their recent scans.
     *
     * @param scans scans to be added.
     * @return success or failure of the operation.
     */
    public synchronized boolean addScans(Collection<? extends ScanEntry> scans) {
        allScans.addAll(scans);
        return true;
    }

    /**
     * Can be used for billing speeders
     *
     * @return speed penalty in this LTS
     */
    public double getSpeedingPenalty() {
        return speedingPenalty;
    }

    /**
     * Set value of speed penalty when system is created.
     *
     * @param speedingPenalty speed penalty
     */
    public void setSpeedingPenalty(double speedingPenalty) {
        this.speedingPenalty = speedingPenalty;
    }

    /**
     * Get instance of road map for this LTS. Can be used to initialize the road map when system is created.
     *
     * @return road map of this LTS
     */
    public RoadMap getRoadMap() {
        return roadMap;
    }

    /**
     * Get an instance of this LTS vehicle register.
     *
     * @return vehicle register
     */
    public VehicleRegistry getVehicleRegistry() {
        return vehicleRegistry;
    }

    /**
     * Get a copy of scan registry so that the underlying list doesn't get changed during manipulations
     * with registry data.
     *
     * @return copy of LTS scan registry
     */
    public synchronized List<ScanEntry> getAllScans() {
        List<ScanEntry> returnList = new ArrayList<>();
        returnList.addAll(allScans);
        return returnList;
    }

    /**
     * Prints contents of the central scan repository. More or less for debugging/demonstration.
     */
    public void printScans() {
        System.out.println("Printing contents of the central scan repository.");
        for (ScanEntry se : allScans) {
            System.out.println(se);
        }
    }

}
