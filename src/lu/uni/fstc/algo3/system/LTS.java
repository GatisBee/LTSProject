package lu.uni.fstc.algo3.system;

import lu.uni.fstc.algo3.statistics.ScanEntry;

import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class LTS {
    private RoadMap roadMap;
    /** 
    * This is the place where I imagine scans from all scanners could end up.
    * And then different actors could query this collection. Since it is not constantly bothered with scan updates
    * (scanners send their data once in some time period) it would make more sense to query this collection.
    * On the other hand, it can happen that the road network is very large and many scanners want to send their data,
    * so this collection could end up very bussy anyway. What kind of data struct we use and how we synchronize it will
    * be very important.
    */
    private Collection<ScanEntry> allScans;
}
