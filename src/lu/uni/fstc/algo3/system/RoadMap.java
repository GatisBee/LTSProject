package lu.uni.fstc.algo3.system;

import java.util.ArrayList;
import java.util.List;

/**
 * A road map for Luxembourg Toll System consisting of road sections.
 * Created by Gatis on 27/03/2015.
 */
public class RoadMap {
    /**
     * Here I suppose any List will work. Not a point of performance problems.
     */
    private List<RoadSection> map;
    /**
     * Default constructor
     */
    public RoadMap() {
    	map = new ArrayList<RoadSection>();
    }
    /**
     * Get the map with road sections (if any are present).
     * @return map collection representing this road map.
     */
    public List<RoadSection> getMap() {
        return map;
    }

    public boolean addSection(RoadSection section) {
        map.add(section);
        return true;
    }

    public boolean removeSection(RoadSection section) {
        map.add(section);
        return true;
    }
    public RoadSection getSection(RoadSection section) {
        int index = map.indexOf(section);
        if (index >= 0) {
            return map.get(index);
        }
        return null;
    }
}
