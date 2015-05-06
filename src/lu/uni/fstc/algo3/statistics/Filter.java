package lu.uni.fstc.algo3.statistics;

import lu.uni.fstc.algo3.system.Direction;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.time.YearMonth;
import java.util.*;

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
    public static List<ScanEntry> filterByYearMonth(List<ScanEntry> collection, YearMonth yearMonth) {
        List<ScanEntry> returnCollection = new ArrayList<>();
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
    public static Set<NumberPlate> filterDistinctNumberPlates(List<ScanEntry> collection) {
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
    public static List<ScanEntry> filterByNumberPlate(List<ScanEntry> collection, NumberPlate numberPlate, Direction direction) {
        List<ScanEntry> returnCollection = new ArrayList<>();
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
    public static List<ScanEntry> filterByNumberPlate(List<ScanEntry> collection, NumberPlate numberPlate) {
        List<ScanEntry> returnCollection = new ArrayList<>();
        for (ScanEntry sc : collection) {
            if (sc.getNumberPlate().equals(numberPlate)) {
                returnCollection.add(sc);
            }
        }
        return returnCollection;
    }

    /**
     * Removes scan conflicts from a sorted <code>ArrayList</code>. Sorting must be based on timestamp from lowest to highest value.
     * If the list is not sorted, the behaviour is undetermined. There is one known highly unlikely possibility that a conflict
     * may result in shorter travel time across a road section than originally was made. That is in case if on the same road section
     * this sequence appears (provided other checks do not fail) IN1, IN2, OUT2, OUT1, resulting in a sequence IN1,OUT2.
     * In this case 2nd car with the same number plate would need to overtake 1st car before exiting the road section.
     *
     * @param collection A sorted collection of scan entries that may contain conflicting scans.
     * @return Returns a list with conflicting scans.
     */
    public static List<ScanEntry> removeAndReturnConflicts(ArrayList<ScanEntry> collection) {
        ScanEntry current, next;
        ArrayList<ScanEntry> elementsToRemove = new ArrayList<>();
        for (int currentIndex = 0, nextIndex = 1; nextIndex < collection.size(); nextIndex = currentIndex + 1) {
            current = collection.get(currentIndex);
            for (; nextIndex < collection.size(); nextIndex++) {
                next = collection.get(nextIndex);
                if (current.getNumberPlate().equals(next.getNumberPlate())) {
                    if (current.getDirection().equals(next.getDirection())) {
                        //del at nextIndex, continue
                        elementsToRemove.add(next);
                        // if failed to find a valid scan at the end of the list finish both loops
                        if (nextIndex == collection.size() - 1) {
                            currentIndex = nextIndex;
                        }
                        continue;
                    } else if (current.getDirection().equals(Direction.OUT) && next.getDirection().equals(Direction.IN)) {
                        currentIndex = nextIndex;
                        break;
                    } else if (!current.getRoadSection().equals(next.getRoadSection())) {
                        // del at nextIndex , continue
                        elementsToRemove.add(next);
                        // if failed to find a valid scan at the end of the list finish both loops
                        if (nextIndex == collection.size() - 1) {
                            currentIndex = nextIndex;
                        }
                        continue;
                    } else if (!current.getCheckpoint().equals(next.getCheckpoint())) {
                        currentIndex = nextIndex;
                        break;
                    } else {
                        // del at nextIndex, continue
                        elementsToRemove.add(next);
                        // if failed to find a valid scan at the end of the list finish both loops
                        if (nextIndex == collection.size() - 1) {
                            currentIndex = nextIndex;
                        }
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        // perform the remove of duplicates
        //todo: verify that the collection of input is modified by this operation
        collection.removeAll(elementsToRemove);
        return elementsToRemove;
    }

    /**
     * Removes scan conflicts from a sorted <code>ArrayList</code>. Sorting must be based on timestamp from lowest to highest value.
     * If the list is not sorted, the behaviour is undetermined. There is one known highly unlikely possibility that a conflict
     * may result in shorter travel time across a road section than originally was made.
     *
     * @param collection A sorted collection of scan entries that may contain conflicting scans.
     */
    public static void removeConflicts(ArrayList<ScanEntry> collection) {
        ScanEntry current, next;
        ArrayList<ScanEntry> elementsToRemove = new ArrayList<>();
        ArrayList<ScanEntry> conflictingElements = new ArrayList<>();
        for (int currentIndex = 0, nextIndex = 1; nextIndex < collection.size(); ++currentIndex, nextIndex = currentIndex + 1) {
            current = collection.get(currentIndex);
            if (current != null) {
                for (; nextIndex < collection.size(); nextIndex++) {
                    next = collection.get(nextIndex);
                    if (next != null) {
                        if (current.getNumberPlate().equals(next.getNumberPlate())) {
                            if (current.getDirection().equals(next.getDirection())) {
                                //del at nextIndex, continue
                                conflictingElements.add(next);
                                if (elementsToRemove.isEmpty()) {
                                    elementsToRemove.add(null);
                                }
                                collection.set(nextIndex, null);
                                // if failed to find a valid scan at the end of the list finish both loops
                            } else {
                                if (current.getDirection().equals(Direction.OUT) && next.getDirection().equals(Direction.IN)) {
                                    break;
                                } else {
                                    if (!current.getRoadSection().equals(next.getRoadSection())) {
                                        conflictingElements.add(next);
                                        if (elementsToRemove.isEmpty()) {
                                            elementsToRemove.add(null);
                                        }
                                        collection.set(nextIndex, null);
                                    } else {
                                        if (!current.getCheckpoint().equals(next.getCheckpoint())) {
                                            break;
                                        } else {
                                            conflictingElements.add(next);
                                            if (elementsToRemove.isEmpty()) {
                                                elementsToRemove.add(null);
                                            }
                                            collection.set(nextIndex, null);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // perform the remove of duplicates
        System.out.println("SORTED LIST WITH NULLED CONFLICTS size: " + collection.size());
//        printCollection(collection);
        collection.removeAll(elementsToRemove);
//        System.out.println();
        System.out.println("SORTED LIST WITH NULLS REMOVED... size: " + collection.size());
//        printCollection(collection);
        System.out.println("PRINTING CONFLICTS");
        printCollection(conflictingElements);
    }
    public static void printCollection(Collection<ScanEntry> collection) {
        for (ScanEntry se : collection) {
            System.out.println(se);
        }
    }

}
