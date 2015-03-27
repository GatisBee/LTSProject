package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class RoadSection {

    private String name;
    private int speedLimit;
    private Collection<Checkpoint> checkpoints;
    /** 
    * Number of vehicles currently on the road section. Increased/decreased by scanners through
    * vehicleEnters() and vehicleLeaves() methods.
    */
    private static long vehiclesOnSection;

    protected boolean addCheckpoint(Checkpoint checkpoint) {
        return true;
    }

    protected boolean removeCheckpoint(Checkpoint checkpoint) {
        return true;
    }

    /**
     * Call when a vehicle enter in this road section
     * @param vehicle
     * @return
     */
    public boolean vehicleEnters() {
        return true;
    }

    /**
     * Call when a vehicle leave this road section
     * @param vehicle
     * @return
     */
    public boolean vehicleLeaves() {
        return true;
    }
}
