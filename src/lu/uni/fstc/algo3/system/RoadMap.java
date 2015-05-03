package lu.uni.fstc.algo3.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A road roadSections for Luxembourg Toll System consisting of road sections.
 * Created by Gatis on 27/03/2015.
 */
public class RoadMap {
    /**
     * Here I suppose any List will work. Not a point of performance problems.
     */
    private List<RoadSection> roadSections;

    /**
     * Default constructor
     */
    public RoadMap() {
        roadSections = new ArrayList<>();
    }

    /**
     * Get the roadSections with road sections (if any are present).
     *
     * @return roadSections collection representing this road roadSections.
     */
    public List<RoadSection> getRoadSections() {
        return roadSections;
    }

    //TODO: decide if we need this, we don't actually use it
    public boolean addSection(RoadSection section) {
        roadSections.add(section);
        return true;
    }

    /**
     * Adds all road sections to this road roadSections.
     *
     * @param allSections collection of road sections to add
     * @return success if true
     */
    public boolean addAllSections(Collection<? extends RoadSection> allSections) {
        roadSections.addAll(allSections);
        return true;
    }

    //TODO: not used, mby delete
    public boolean removeSection(RoadSection section) {
        roadSections.add(section);
        return true;
    }

    //TODO: not used so far.
    public RoadSection getSection(RoadSection section) {
        int index = roadSections.indexOf(section);
        if (index >= 0) {
            return roadSections.get(index);
        }
        return null;
    }


}
