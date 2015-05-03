package lu.uni.fstc.algo3.system;


import java.util.ArrayList;
import java.util.List;

/** Road section for Luxembourg Toll System. Contains single section related information, like time needed to drive
 * through a section without exceeding speed limits, it's checkpoints etc.
 * Created by Gatis on 27/03/2015.
 */
public class RoadSection {
    /**
     * Name of the this road section. User must ensure the uniqueness of the name, since it is also used as a unique
     * identifier of this road section.
     */
    private String name;

    /**
     * The time (measured in millis) needed for a car to drive through this road section without exceeding speed limit(s).
     * Since speed limit on a single road section may vary and due to several reasons there can be fluctuations in
     * drive-through time. It is mandatory to establish a reasonable buffer for overhead after which a driver could be
     * charged with additional fee for speeding.
     */
    private int timeForCar;
    /**
     * Almost the same as for cars, but the speed limits may be different thus a different drive-through time
     * (measured in millis).
     */
    private int timeForBus;
    /**
     * Almost the same as for cars, but the speed limits may be different thus a different drive-through time
     * (measured in millis).
     */
    private int timeForTruck;
    /**
     * 2 checkpoints per road section.
     */
    private Checkpoint[] checkpoints;
    /**
    * Number of vehicles currently on the road section. Increased/decreased by scanners through
    * vehicleEnters() and vehicleLeaves() methods. Access to this field is synchronized.
    */
    private long vehiclesOnSection;

    /**
     * A default constructor for this road section.
     * @param timeForCar time needed for a car and motor cycle to drive through this road section
     * @param timeForBus time needed for a bus to drive through this road section
     * @param timeForTruck time needed for a truck to drive through this road section
     * @param name name of the road section
     */
    public RoadSection(int timeForCar, int timeForBus, int timeForTruck, String name) {
        vehiclesOnSection = 0L;
        checkpoints = new Checkpoint[2];
        this.timeForCar = timeForCar;
        this.timeForBus = timeForBus;
        this.timeForTruck = timeForTruck;
        this.name = name;
    }

    /**
     * Adds a checkpoint to this road section. Road section can have 2 checkpoints.
     * If this road section already has 2 checkpoints an error will be printed and method will return false.
     * @param checkpoint checkpoint to add
     * @return success or failure of the operation
     */
    public boolean addCheckpoint(Checkpoint checkpoint) {
        if (checkpoints[0] == null) {
            checkpoints[0] = checkpoint;
        } else if (checkpoints[1] == null) {
            checkpoints[1] = checkpoint;
        } else {
            System.err.print("This road section already has 2 checkpoints, please remove 1 checkpoint first and then try to add again.");
            return false;
        }
        return true;
    }

    /**
     * Remove a checkpoint (entry/exit) from this road section. If there are no checkpoints on this road section
     * an error will be printed and method will return false.
     * @param checkpoint checkpoint that should be removed.
     * @return success or failure of the operation.
     */
    public boolean removeCheckpoint(Checkpoint checkpoint) {
        if (checkpoints[1] != null) {
            checkpoints[1] = null;
        } else if (checkpoints[0] != null) {
            checkpoints[0] = null;
        } else {
            System.err.print("This road section has no checkpoints, nothing to remove.");
            return false;
        }
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

    public String getName() {
        return name;
    }

    public int getTimeForCar() {
        return timeForCar;
    }

    public int getTimeForBus() {
        return timeForBus;
    }

    public int getTimeForTruck() {
        return timeForTruck;
    }

    public Checkpoint[] getCheckpoints() {
        return checkpoints;
    }
    @Override
    public String toString() {
        return new String(getName());
    }
}

