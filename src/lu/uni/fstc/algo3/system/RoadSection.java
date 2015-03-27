package lu.uni.fstc.algo3.system;

import java.util.Collection;

/**
 * Created by Gatis on 27/03/2015.
 */
public class RoadSection {
    private Collection<Scanner> scanners;
    private Collection<Checkpoint> checkpoints;
    private static final byte MIN_CHECKPOINTS = 2;

    protected boolean addScanner(Scanner scanner) {
        return true;
    }

    protected boolean removeScanner(Scanner scanner) {
        return true;
    }
}
