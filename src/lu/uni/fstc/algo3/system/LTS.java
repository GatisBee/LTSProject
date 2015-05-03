package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.billing.VehicleRegister;
import lu.uni.fstc.algo3.statistics.ScanEntry;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents top level of the LTS system. It provides public interfaces for different actors to access
 * system functionality. It uses a singleton pattern to ensure that a single consistent instance of this class is maintained
 * and other lower level classes can easily access fields of this class.
 * Created by Gatis on 27/03/2015.
 */
public class LTS {

    private double speedingPenalty;
    private RoadMap roadMap; // will be used when system will be ready
    private Collection<ScanEntry> allScans;
    private VehicleRegister vehicleRegister;


    private static LTS _instance;


    private LTS() {
        this.roadMap = new RoadMap();

        /*
         * I don't use array list, because there is will be sequential access and
         * for big lists it is costly to increase the size of array list (doubling the size and copying all elements)
         */
        allScans = new LinkedList<ScanEntry>();
        vehicleRegister = new VehicleRegister();
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
    public VehicleRegister getVehicleRegister() {
        return vehicleRegister;
    }

    public void printScans() {
        System.out.println("Printing contents of the central scan repository.");
        for (ScanEntry se : allScans) {
            System.out.println(se);
        }
    }

}
