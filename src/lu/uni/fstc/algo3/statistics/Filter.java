package lu.uni.fstc.algo3.statistics;

import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides filtering methods for LTS collections.
 * Created by Gatis on 27/03/2015.
 */
public class Filter {
    /**
     * Filters given collection by year and month and returns a new filtered collection.
     *
     * @param collection collection to be filtered
     * @param yearMonth  year month by which the entries should be filtered
     * @return filtered collection by year month
     */
    public static Collection<ScanEntry> filterByYearMonth(Collection<ScanEntry> collection, YearMonth yearMonth) {
        Collection<ScanEntry> returnCollection = new ArrayList<>();
        for (ScanEntry sc : collection) {
//            if (sc.getDirection() == Direction.IN) {
            YearMonth scanYearMonth = YearMonth.from(sc.getTimestamp());
            if (yearMonth.compareTo(scanYearMonth) == 0) {
                returnCollection.add(sc);
            }
//            }
        }
        return returnCollection;
    }

    /**
     * Derives a set of distinct number plates from a collection of scan entries.
     * Main purpose is for billing each driver.
     *
     * @param collection collection of scan entries
     * @return set of number plates derived from scan entries
     */
    public static Set<NumberPlate> filterDistinctNumberPlates(Collection<ScanEntry> collection) {
        Set<NumberPlate> returnSet = new HashSet<>();
        for (ScanEntry sc : collection) {
            NumberPlate plate = sc.getNumberPlate();
            returnSet.add(plate);
        }
        return returnSet;
    }

    /**
     * Filters given collection of scan entries by number plate and direction and returns a filtered collection.
     *
     * @param collection  collection to be filtered
     * @param numberPlate number plate by which collection will be filtered
     * @param direction   Direction of the vehicle. Direction.IN - vehicles that enter a road section, Direction.OUT vehicles that exit the road section
     * @return filtered collection by number plate
     */
    public static Collection<ScanEntry> filterByNumberPlate(Collection<ScanEntry> collection, NumberPlate numberPlate, Direction direction) {
        Collection<ScanEntry> returnCollection = new ArrayList<>();
        for (ScanEntry sc : collection) {
            if (sc.getDirection() == direction && sc.getNumberPlate().equals(numberPlate)) {
                returnCollection.add(sc);
            }
        }
        return returnCollection;
    }

    /**
     * Filters given collection of scan entries by number plate and returns a filtered collection.
     *
     * @param collection  collection to be filtered
     * @param numberPlate number plate by which collection will be filtered
     * @return filtered collection by number plate
     */
    public static Collection<ScanEntry> filterByNumberPlate(Collection<ScanEntry> collection, NumberPlate numberPlate) {
        Collection<ScanEntry> returnCollection = new ArrayList<>();
        for (ScanEntry sc : collection) {
            if (sc.getNumberPlate().equals(numberPlate)) {
                returnCollection.add(sc);
            }
        }
        return returnCollection;
    }
    //todo: define duplicate scan policy, implement a method for dealing with duplicate scans

}
