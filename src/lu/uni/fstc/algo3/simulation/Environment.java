package lu.uni.fstc.algo3.simulation;

import lu.uni.fstc.algo3.billing.Address;
import lu.uni.fstc.algo3.billing.VehicleOwner;
import lu.uni.fstc.algo3.billing.VehicleRegistry;
import lu.uni.fstc.algo3.system.*;
import lu.uni.fstc.algo3.vehicles.NumberPlate;
import lu.uni.fstc.algo3.vehicles.Vehicle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Represents LTS environment with its road map (road sections, scanners, etc.). It contains methods for
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
    private static Environment _instance = null;

    /**
     * Default constructor creates and initializes environment for LTS system.
     *
     * @param numberOfSections number of road sections that should be created.
     */
    private Environment(int numberOfSections) {
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
        // populate registry with sample data
        populateRegistry();

        //create vehicle registry and related objects

    }
    
    public static void init(int numberOfSections) {
    	if (_instance == null) {
    		_instance = new Environment(numberOfSections);
    	}
    }
    /**
     * Initialize environment.
     * @param numberOfSections initial number of road sections in this LTS.
     */
    public static void init(int numberOfSections) {
        if (_instance == null) {
            _instance = new Environment(numberOfSections);
        }
    }

    /**
     * Creates road sections and related objects. Number of road sections depends on the number
     * passed to the default constructor.
     *
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

    /**
     * Populate vehicle registry with sample data
     */
    private void populateRegistry() {
        // get registry
        VehicleRegistry registry = LTS.getInstance().getVehicleRegistry();
        // create streams to read data from file
        ArrayList<String> people = new ArrayList<String>(100);
        ArrayList<String> numberPlates = new ArrayList<String>(100);
        
        BufferedReader plateReader = null;
        BufferedReader peopleReader = null;
        try {
            peopleReader = new BufferedReader(new FileReader("people.sample"));
            plateReader = new BufferedReader(new FileReader("numbers.sample"));
            // skip the first line
            peopleReader.readLine();
            while (peopleReader.ready()) {
                people.add(peopleReader.readLine());
            }
            while (plateReader.ready()) {
                numberPlates.add(plateReader.readLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
        	if (peopleReader != null) {
        		try {
					peopleReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        	if (plateReader != null) {
        		try {
					plateReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        
        /*
         * Parse sample data and create necessary objects to populate vehicle registry.
         */
        if (!people.isEmpty() && !numberPlates.isEmpty()) {
            for (int i = 0; i < people.size(); i++) {
                // tokenize each person data line
                StringTokenizer tokenizer = new StringTokenizer(people.get(i), "|");
                NumberPlate plate = new NumberPlate(numberPlates.get(i));
                // parse persons information
                String name = tokenizer.nextToken();
                String street = tokenizer.nextToken();
                String city = tokenizer.nextToken();
                String zip = tokenizer.nextToken();
                String country = tokenizer.nextToken();
                // create address , owner and vehicle
                Address address = new Address(street, country, city, zip);
                VehicleOwner owner = new VehicleOwner(name, address);
                // leave other information null for the time being, as it is not very important
                Vehicle vehicle = new Vehicle(plate, "BMW", "M4", "Orange", 1572, owner);
                // add entry to registry
                registry.addEntry(plate, vehicle);
            }
            //todo: remove after debugging
            registry.printContents();
        }
    }
}
