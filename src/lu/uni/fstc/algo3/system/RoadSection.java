package lu.uni.fstc.algo3.system;

import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class RoadSection {
    private Collection<Checkpoint> checkpoints;

    protected boolean addCheckpoint(Checkpoint checkpoint) {
        return true;
    }

    protected boolean removeCheckpoint(Checkpoint checkpoint) {
        return true;
    }
}
