package lu.uni.fstc.algo3.simulation;

import java.util.UUID;

/**
 * Utility class for getting/generating arbitrary parameters to initialize different system classes.
 * Created by Gatis on 25/04/2015.
 */
public class ParameterGenerator {
    /**
     * Builder to generate new names for <code>RoadSection</code>
     */
    private String sectionName;
    /**
     * Index to append to <code>RoadSection</code> name, so that it would be unique.
     */
    private int sectionIndex;
    /**
     * Times for cars
     */
    private int[] carTimes;
    /**
     * Times for buses
     */
    private int[] busTimes;
    /**
     * Times for trucks
     */
    private int[] truckTimes;
    /**
     * Index used to traverse vehicle time arrays, whenever 1 of the arrays is used
     * others should be used with the same index
     */
    private int carIndex;
    private int busIndex;
    private int truckIndex;

    public ParameterGenerator() {
        sectionName = new String("Road Section #");
        sectionIndex = 1;

        carTimes = new int[]{10000, 11000, 12000, 13000, 14000, 15000, 16000, 17000, 18000, 19000};
        busTimes = new int[carTimes.length];
        for (int i = 0; i < carTimes.length; i++) {
            busTimes[i] = (int) (carTimes[i] * 0.9);
        }
        truckTimes = new int[carTimes.length];
        for (int i = 0; i < carTimes.length; i++) {
            truckTimes[i] = (int) (carTimes[i] * 0.8);
        }
    }


    /**
     * Generates a unique name for a <code>RoadSection</code>. Name consists of 'Road Section : ' <code>String</code>
     * and incremental number of the section that is created.
     *
     * @return returns a unique name <code>String</code> for a road section
     */
    public String getRoadSectionName() {
        // append number to name and increment the number
        return sectionName + sectionIndex++;
    }

    /**
     * Gets time needed for a car to drive through a road section. Times are stored in a predefined array which is
     * traversed in a sequential manner. Each time method is called index of array is incremented or reset to 0 when
     * end of the array is reached.
     *
     * @return time in millis
     */
    public int getCarTime() {
        return carTimes[carIndex++];
    }

    /**
     * Gets time needed for a bus to drive through a road section. Times are stored in a predefined array which is
     * traversed in a sequential manner. Each time method is called index of array is incremented or reset to 0 when
     * end of the array is reached.
     *
     * @return time in millis
     */
    public int getBusTime() {
        return busTimes[busIndex++];
    }

    /**
     * Gets time needed for a truck to drive through a road section. Times are stored in a predefined array which is
     * traversed in a sequential manner. Each time method is called index of array is incremented or reset to 0 when
     * end of the array is reached.
     *
     * @return time in millis
     */
    public int getTruckTime() {
        return truckTimes[truckIndex++];
    }

    /**
     * Generates a unique id for a checkpoint.
     *
     * @return a unique id
     */
    public UUID getCheckpointId() {
        return UUID.randomUUID();
    }

    /**
     * Generates a unique id for a scanner.
     *
     * @return unique id
     */
    public UUID getScannerId() {
        return UUID.randomUUID();
    }
}
