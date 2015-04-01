package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class RoadSection {
    /**
     * Name of the this road section. User must ensure the uniqueness of the name, since it is also used as a unique
     * identifier of this road section.
     */
    private String name;
    /**
     * The time (measured in seconds) needed for a car to drive through this road section without exceeding speed limit(s).
     * Since speed limit on a single road section may vary and due to several reasons there can be fluctuations in
     * drive-through time. It is mandatory to establish a reasonable buffer for overhead after which a driver could be
     * charged with additional fee for speeding.
     */
    private int timeForCar;
    /**
     * Almost the same as for cars, but the speed limits may be different thus a different drive-through time
     * (measured in seconds).
     */
    private int timeForBus;
    /**
     * Almost the same as for cars, but the speed limits may be different thus a different drive-through time
     * (measured in seconds).
     */
    private int timeForTruck;
    /**
     * A collection of checkpoints for this road section. We assume that a single road section can have more than 2
     * entry/exit points thus a dynamic collection is used for storing this information.
     */
    private Collection<Checkpoint> checkpoints;
    /** 
    * Number of vehicles currently on the road section. Increased/decreased by scanners through
    * vehicleEnters() and vehicleLeaves() methods. Access to this field is synchronized.
    */
    private long vehiclesOnSection;

    /**
     * A default constructor for this road section.
     * @param timeForCar
     * @param timeForBus
     * @param timeForTruck
     * @param name
     */
    public RoadSection(int timeForCar, int timeForBus, int timeForTruck, String name) {
        vehiclesOnSection = 0L;
        checkpoints = new ArrayList<Checkpoint>();
        this.timeForCar = timeForCar;
        this.timeForBus = timeForBus;
        this.timeForTruck = timeForTruck;
        this.name = name;
    }

    /**
     * Adds a checkpoint (entry/exit) point to this road section.
     * @param checkpoint checkpoint that should be added.
     * @return success or failure of the operation.
     */
    protected boolean addCheckpoint(Checkpoint checkpoint) {
        checkpoints.add(checkpoint);
        return true;
    }

    /**
     * Remove a checkpoint (entry/exit) from this road section.
     * @param checkpoint checkpoint that should be removed.
     * @return success or failure of the operation.
     */
    protected boolean removeCheckpoint(Checkpoint checkpoint) {
        checkpoints.remove(checkpoint);
        return true;
    }

    /**
     * Call when a vehicle enters this road section.
     * @return success or failure of the operation.
     */
    public synchronized boolean vehicleEnters() {
        vehiclesOnSection++;
        return true;
    }

    /**
     * Call when a vehicle leaves this road section.
     * @return success or failure of the operation.
     */
    public synchronized boolean vehicleLeaves() {
        vehiclesOnSection--;
        return true;
    }

    /**
     * Gets the current number of vehicles on this road section.
     * @return number of vehicles on this road section.
     */
    public synchronized long getVehiclesOnSection() {
        return vehiclesOnSection;
    }

    /**
     * Sets the name of this road section.
     * @param name new name of the road section.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the time needed to drive-through this road section without exceeding the speed limit(s).
     * @param timeForCar time needed for drive-through
     */
    public void setTimeForCar(int timeForCar) {
        this.timeForCar = timeForCar;
    }
    /**
     * Sets the time needed to drive-through this road section without exceeding the speed limit(s).
     * @param timeForBus time needed for drive-through
     */
    public void setTimeForBus(int timeForBus) {
        this.timeForBus = timeForBus;
    }
    /**
     * Sets the time needed to drive-through this road section without exceeding the speed limit(s).
     * @param timeForTruck time needed for drive-through
     */
    public void setTimeForTruck(int timeForTruck) {
        this.timeForTruck = timeForTruck;
    }

}
