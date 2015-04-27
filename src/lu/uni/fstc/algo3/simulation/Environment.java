package lu.uni.fstc.algo3.simulation;

import lu.uni.fstc.algo3.system.*;

import java.util.ArrayList;
import java.util.List;

/** Represents LTS environment with its road map (road sections, scanners, etc.). It contains methods for
 * initializing environment for simulation of the system.
 * Created by Gatis on 25/04/2015.
 */
public class Environment {
    /**
     * Road map
     */
    private RoadMap map;
    /**
     * List of all road sections
     */
    private List<RoadSection> sections;
    /**
     * A parameter generator for constructing environment objects.
     */
    private ParameterGenerator generator;

    /**
     * Default constructor creates and initializes environment for LTS system.
     * @param numberOfSections number of road sections that should be created.
     */
    public Environment(int numberOfSections) {
        map = new RoadMap();
        sections = new ArrayList<>(numberOfSections);
        generator = new ParameterGenerator();
        createSections(sections);
        map.addAllSections(sections);
    }

    /**
     * Creates road sections and related objects. Number of road sections depends on the number
     * passed to the default constructor.
     * @param sections Collection of desired size for all road sections. Size = number of sections.
     */
    private void createSections(List<RoadSection> sections) {
        // generate the sections
        for (int i = 0; i < sections.size(); i++) {
            sections.add(new RoadSection(generator.getCarTime(), generator.getBusTime(),
                    generator.getTruckTime(), generator.getRoadSectionName()));
        }
        // for every section create checkpoints and scanners
        for (RoadSection rs : sections) {
            // 2 checkpoints per section
            rs.addCheckpoint(new Checkpoint(rs.getName() + " : checkpoint 1", generator.getCheckpointId()));
            rs.addCheckpoint(new Checkpoint(rs.getName() + " : checkpoint 2", generator.getCheckpointId()));
            // add 2 scanners for each checkpoint, 1 scanner for 1 direction
            Checkpoint[] checkpoints = rs.getCheckpoints();
            for (Checkpoint cp : checkpoints) {
                cp.addScannerIn(new Scanner(rs, cp, generator.getScannerId(), Direction.IN));
                cp.addScannerOut(new Scanner(rs, cp, generator.getScannerId(), Direction.OUT));
            }
        }

    }
}
