package lu.uni.fstc.algo3.system;

import java.util.*;

/**
 * Created by Gatis on 27/03/2015.
 */
public class RoadMap {
    /**
     * Here I suppose any List will work. Not a point of performance problems.
     */
    private Collection<RoadSection> map;

    //TODO: Ensure that the map or it's elements are not modified from outside of this class except by the methods provided by this class.
    /**
     * Get the map with road sections (if any are present).
     * @return map collection representing this road map.
     */
    public Collection<RoadSection> getMap() {
        return map;
    }

    protected boolean addSection(RoadSection section) {
        map.add(section);
        return true;
    }

    protected boolean removeSection(RoadSection section) {
        map.add(section);
        return true;
    }
}
