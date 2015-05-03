package lu.uni.fstc.algo3.utilities;

import lu.uni.fstc.algo3.simulation.NumberPlateLoader;
import lu.uni.fstc.algo3.system.*;
import lu.uni.fstc.algo3.system.Scanner;
import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.util.*;


/**
 * Generates scans for simulation of the LTS
 * Created by Gatis on 03/04/2015.
 */
public class ScanGenerator {
    private LTS lts;
    private RoadMap map;
    private Collection<RoadSection> roadSections;
    private boolean generateScans = false;
    private List<NumberPlate> numberPlates;

    public ScanGenerator() {
        lts = LTS.getInstance();
        map = lts.getRoadMap();
        roadSections = map.getRoadSections();
        numberPlates = Collections.synchronizedList(new NumberPlateLoader().getNumberPlates());
    }

    public void startGenerating() {
        // perform necessary checks
        if (roadSections != null && numberPlates != null) {
            setGenerateScans(true);
        } else {
            System.out.println("System has not been initialized.");
            System.exit(-1);
        }
        // create a thread for each road section, that will generate scans for that section
        if (isGenerateScans())
        {
            System.out.println("Creating scan generator threads for each road section... ");
            for (RoadSection rs : roadSections) {
                new Thread(new ScanGeneratorThread(rs)).start();
            }
        }
    }

    public void stopGenerating() {
        setGenerateScans(false);
    }

    private void setGenerateScans(boolean generateScans) {
        this.generateScans = generateScans;
    }

    private boolean isGenerateScans() {
        return generateScans;
    }


    /**
     * This inner class will control scans for a single road section. It has state of the section, vehicles on it and other
     * information needed to provide a reasonable scan simulation.
     */
    class ScanGeneratorThread implements Runnable {
        private RoadSection roadSection;
        private Set<NumberPlate> vehiclesInSection;
        private Random rnd;
        private int indexBound;
        private Timer scanTimer;
        private int exitTimerDelay;

        public ScanGeneratorThread(RoadSection roadSection) {
            this.roadSection = roadSection;
            this.vehiclesInSection = new HashSet<NumberPlate>();
            rnd = new Random();
            indexBound = numberPlates.size() - 1;
            exitTimerDelay = roadSection.getTimeForCar();
            scanTimer = new Timer();
        }
        //TODO: seems to be working

        /**
         * Generates entry and exit scans for the road section for which this generator was created.
         * Eliminates duplicate number plates on this road section (by storing them in a <code>Set</code>),
         * but does not guarantee that the same plate does not appear on other road section.
         */
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " starting");
            int checkpointIndex = 0;
            NumberPlate plate;
            Scanner scanner;
            while (isGenerateScans()) {
                synchronized (this) {
                    // get a unique number plate and add it to the set of vehicles on this road section
                    int index = rnd.nextInt(indexBound);
                    /*
                    There is no guarantee that another generator thread won't pick a number plate that is used in this road section.
                    It is highly dependent on the random number generator for indexing number plate list.
                    */
                    while (!vehiclesInSection.add(plate = numberPlates.get(index))) {
                        index = rnd.nextInt(indexBound);
                    }
                    System.out.println(Thread.currentThread().getName() + " generating in scan for " + plate);
                    // make an entry scan of the vehicle
                    roadSection.getCheckpoints()[checkpointIndex].getScannersIn().get(0).scan(plate);
                    // update index, get exit scanner of the next checkpoint
                    checkpointIndex = checkpointIndex % 1;
                    scanner = roadSection.getCheckpoints()[checkpointIndex].getScannersOut().get(0);
                    // create a timer for exit scan of this vehicle
                    scanTimer.schedule(new ExitScanTimerTask(plate, scanner, vehiclesInSection, this), exitTimerDelay);
                    // release lock and wait 10 seconds till next iteration, if the condition is still true
                    try {
                        if (isGenerateScans()) {
                            wait(10000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * A task for exit scans, it is assigned to each vehicle that enters a road section
     * and should be executed only once.
     */
    // todo: for now it seems to work
    class ExitScanTimerTask extends TimerTask {
        private NumberPlate plate;
        private Scanner scanner;
        private Set<NumberPlate> vehiclesInSection;
        private ScanGeneratorThread scanGeneratorThread;

        /**
         * This constructor takes all the necessary references to perform its intended action as parameters.
         * It scans an exit scan for a vehicle after a specified period of time and removes it from the <code>Set</code>
         * of vehicles currently on the road section. This is done in a thread safe manner by locking the calling object.
         * @param plate number plate that should be used for exit scan
         * @param scanner scanner that will perform exit scan. The correct scanner must be provided by the caller (
         * @param vehiclesInSection a set of vehicles on road section
         * @param callingObj a reference to object that is calling this method. It is used to gain lock on this object.
         */
        public ExitScanTimerTask(NumberPlate plate, Scanner scanner, Set<NumberPlate> vehiclesInSection, ScanGeneratorThread callingObj) {
            this.plate = plate;
            this.scanner = scanner;
            this.vehiclesInSection = vehiclesInSection;
            this.scanGeneratorThread = callingObj;
        }
        @Override
        public void run() {
            // lock scan generator while modifying its field, a clash is highly unlikely but anyway to keep it on the safe side
            synchronized (scanGeneratorThread) {
                System.out.println(Thread.currentThread().getName() + " generating out scan for " + plate);
                scanner.scan(plate);
                vehiclesInSection.remove(plate);
            }
        }
    }
}

