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
     * Removes conflicting scans from a collection and returns a separate list of conflicting scans. This method exactly the same
     * as {@Link Filter#removeConflicts(ArrayList) removeConflits} with the difference that it returns a list of conflicting scans.
     *
     * For more details about implementation see {@link Filter#removeConflicts(ArrayList) removeConflicts} method documentation.
     *
     * @param collection A sorted collection of scan entries that may contain conflicting scans.
     * @return Returns a list with conflicting scans.
     */
    public static List<ScanEntry> removeAndReturnConflicts(ArrayList<ScanEntry> collection) {
        ScanEntry current, next;
        // single purpose to store a null entry
        ArrayList<ScanEntry> elementsToRemove = new ArrayList<>();
        // a list with conflicting scans
        ArrayList<ScanEntry> conflictingElements = new ArrayList<>();
        /*
        Main loop in which current index is maintained and comparisons against elements of this index and all the following elements are made.

        I chose nulling conflicts because of performance considerations. It is easier and faster to compare a null entry
        than to check each entry against conflicting element list. The list of conflicting scans is kept and can be used for other functionality
        such as sending information about such conflicts to authorities because that can imply that some one is using an illegal number plate
        or that there is a system failure.
         */

        for (int currentIndex = 0, nextIndex = 1; nextIndex < collection.size(); ++currentIndex, nextIndex = currentIndex + 1) {
            current = collection.get(currentIndex);
            // in case of null just go to the next element
            if (current != null) {
                /*
                Secondary loop to search through following elements and perform comparisons
                 */
                for (; nextIndex < collection.size(); nextIndex++) {
                    next = collection.get(nextIndex);
                    // in case of null go to the next element
                    if (next != null) {
                        // once same number plate encountered, make sure that the next scan is "legal" with additional nested checks
                        if (current.getNumberPlate().equals(next.getNumberPlate())) {
                            // after in or out there must be the opposite direction scan
                            if (current.getDirection().equals(next.getDirection())) {
                                conflictingElements.add(next);
                                // if the list is not empty it has a null in it, so no  need to add another
                                if (elementsToRemove.isEmpty()) {
                                    elementsToRemove.add(null);
                                }
                                // set conflict to null, so that on next iterations it will be ignored
                                collection.set(nextIndex, null);
                                // if failed to find a valid scan at the end of the list finish both loops
                            } else {
                                // in the case when a vehicle has exited a road section it can enter it in any of the other or same road sections
                                if (current.getDirection().equals(Direction.OUT) && next.getDirection().equals(Direction.IN)) {
                                    // found the next legal scan, can advance current index of main loop
                                    break;
                                } else {
                                    // after IN , OUT scan MUST be on the same road section to be valid
                                    if (!current.getRoadSection().equals(next.getRoadSection())) {
                                        conflictingElements.add(next);
                                        if (elementsToRemove.isEmpty()) {
                                            elementsToRemove.add(null);
                                        }
                                        collection.set(nextIndex, null);
                                    } else {
                                        // if the road section is valid, exit scan must be on a different end(checkpoint) of the section
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
        // perform the removal of duplicates
        System.out.println("SORTED LIST WITH NULLED CONFLICTS size: " + collection.size());
//        printCollection(collection);
        // removes all null elements (duplicates)
        collection.removeAll(elementsToRemove);
//        System.out.println();
        System.out.println("SORTED LIST WITH NULLS REMOVED... size: " + collection.size());
//        printCollection(collection);
        System.out.println("PRINTING CONFLICTS");
        printCollection(conflictingElements);
        return conflictingElements;
    }

    /**
     * Removes scan conflicts from a sorted <code>ArrayList</code>. Sorting must be based on timestamp from lowest to highest value
     * to ensure correct conflict removal. If the list is not sorted, the behaviour is undetermined.
     *
     * Note that <code>ArrayList</code> was chosen because of its support for <code>null</code> elements, fast element retrieval by index and support of all list optional operations.
     *
     *  The removal policy in short is as follows: if a conflict is detected, first scan is valid and following overlapping scans are removed.
     *  That includes scans on the same road section as well as on others. There is one known highly unlikely possibility that a conflict may result in shorter travel time across a road section than originally was made.
     *  This can happen when the following sequence on the SAME ROAD SECTION, with the SAME NUMBER PLATE appears - IN1, IN2, OUT2, OUT1 (assuming scans are ordered by their timestamps).
     *  This results in a sequence IN1, OUT2, which may sometimes (depending on speed of vehicles and time differences of scans) lead to a speeding penalty since OUT2 will be considered legal and appears before OUT1.
     *
     *  A duplicate in this context is an overlapping scan on time-scale of the same vehicle number plate.
     *
     *
     * @param collection A sorted collection of scan entries that may contain conflicting scans.
     * @see java.util.ArrayList for desctription of its support for the earlier mentioned considerations
     */
    public static void removeConflicts(ArrayList<ScanEntry> collection) {
        ScanEntry current, next;
        // single purpose to store a null entry
        ArrayList<ScanEntry> elementsToRemove = new ArrayList<>();
        // a list with conflicting scans
        ArrayList<ScanEntry> conflictingElements = new ArrayList<>();
        /*
        Main loop in which current index is maintained and comparisons against elements of this index and all the following elements are made.

        I chose nulling conflicts because of performance considerations. It is easier and faster to compare a null entry
        than to check each entry against conflicting element list. The list of conflicting scans is kept and can be used for other functionality
        such as sending information about such conflicts to authorities because that can imply that some one is using an illegal number plate
        or that there is a system failure.
         */

        for (int currentIndex = 0, nextIndex = 1; nextIndex < collection.size(); ++currentIndex, nextIndex = currentIndex + 1) {
            current = collection.get(currentIndex);
            // in case of null just go to the next element
            if (current != null) {
                /*
                Secondary loop to search through following elements and perform comparisons
                 */
                for (; nextIndex < collection.size(); nextIndex++) {
                    next = collection.get(nextIndex);
                    // in case of null go to the next element
                    if (next != null) {
                        // once same number plate encountered, make sure that the next scan is "legal" with additional nested checks
                        if (current.getNumberPlate().equals(next.getNumberPlate())) {
                            // after in or out there must be the opposite direction scan
                            if (current.getDirection().equals(next.getDirection())) {
                                conflictingElements.add(next);
                                // if the list is not empty it has a null in it, so no  need to add another
                                if (elementsToRemove.isEmpty()) {
                                    elementsToRemove.add(null);
                                }
                                // set conflict to null, so that on next iterations it will be ignored
                                collection.set(nextIndex, null);
                                // if failed to find a valid scan at the end of the list finish both loops
                            } else {
                                // in the case when a vehicle has exited a road section it can enter it in any of the other or same road sections
                                if (current.getDirection().equals(Direction.OUT) && next.getDirection().equals(Direction.IN)) {
                                    // found the next legal scan, can advance current index of main loop
                                    break;
                                } else {
                                    // after IN , OUT scan MUST be on the same road section to be valid
                                    if (!current.getRoadSection().equals(next.getRoadSection())) {
                                        conflictingElements.add(next);
                                        if (elementsToRemove.isEmpty()) {
                                            elementsToRemove.add(null);
                                        }
                                        collection.set(nextIndex, null);
                                    } else {
                                        // if the road section is valid, exit scan must be on a different end(checkpoint) of the section
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
        // perform the removal of duplicates
        System.out.println("SORTED LIST WITH NULLED CONFLICTS size: " + collection.size());
//        printCollection(collection);
        // removes all null elements (duplicates)
        collection.removeAll(elementsToRemove);
//        System.out.println();
        System.out.println("SORTED LIST WITH NULLS REMOVED... size: " + collection.size());
//        printCollection(collection);
        System.out.println("PRINTING CONFLICTS");
        printCollection(conflictingElements);
    }
    /*
    Utility for debugging and demonstration
     */
    public static void printCollection(Collection<ScanEntry> collection) {
        for (ScanEntry se : collection) {
            System.out.println(se);
        }
    }

}
