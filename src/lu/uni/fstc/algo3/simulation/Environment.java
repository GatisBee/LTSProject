package lu.uni.fstc.algo3.simulation;

import lu.uni.fstc.algo3.billing.VehicleRegister;
import lu.uni.fstc.algo3.system.*;
import lu.uni.fstc.algo3.utilities.ParameterGenerator;

import java.util.ArrayList;
import java.util.List;

/** Represents LTS environment with its road map (road sections, scanners, etc.). It contains methods for
 * initializing environment for simulation of the system.
 * Created by Gatis on 25/04/2015.
 */
public class Environment {
    /**
     * List of all road sections
     */
    private List<RoadSection> sections;
    /**
     * A parameter generator for constructing environment objects.
     */
    private ParameterGenerator generator;
    /**
     * LTS system object
     */
    private LTS lts;
    private int numberOfSections;

    /**
     * Default constructor creates and initializes environment for LTS system.
     * @param numberOfSections number of road sections that should be created.
     */
    public Environment(int numberOfSections) {
        //create LTS
        lts = LTS.getInstance();
        this.numberOfSections = numberOfSections;
        // create array to hold sections
        sections = new ArrayList<>(numberOfSections);
        // create generator for parameters
        generator = new ParameterGenerator();
        // create specified number of sections
        createSections(sections);
        // assign road sections to lts road map
        System.out.println("Adding road sections to map: " + lts.getRoadMap().addAllSections(sections));
        // setup speeding penalty for this lts
        lts.setSpeedingPenalty(20.0d);

        //create vehicle registry and related objects

    }

    /**
     * Creates road sections and related objects. Number of road sections depends on the number
     * passed to the default constructor.
     * @param sections Collection of desired size for all road sections. Size = number of sections.
     */
    private void createSections(List<RoadSection> sections) {
        // generate the sections
        for (int i = 0; i < numberOfSections; i++) {
            sections.add(new RoadSection(generator.getCarTime(), generator.getBusTime(),
                    generator.getTruckTime(), generator.getRoadSectionName()));
        }
        // for every section create checkpoints and scanners
        for (RoadSection rs : sections) {
            // 2 checkpoints per section
            rs.addCheckpoint(new Checkpoint("Checkpoint #1, " + rs.getName(), generator.getCheckpointId()));
            rs.addCheckpoint(new Checkpoint("Checkpoint #2, " + rs.getName(), generator.getCheckpointId()));
            // add 2 scanners for each checkpoint, 1 scanner for 1 direction
            Checkpoint[] checkpoints = rs.getCheckpoints();
            for (Checkpoint cp : checkpoints) {
                cp.addScannerIn(new Scanner(rs, cp, generator.getScannerId(), Direction.IN));
                cp.addScannerOut(new Scanner(rs, cp, generator.getScannerId(), Direction.OUT));
            }
        }
    }
    //todo: populate the register with vehicle and owner data
    private void populateRegister() {
        // get register
        VehicleRegister register = LTS.getInstance().getVehicleRegister();
        // create streams to read data from file
        // create parsers of that data
        // add entries to the register
    }
}
