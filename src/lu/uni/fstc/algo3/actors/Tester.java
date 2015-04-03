package lu.uni.fstc.algo3.actors;

import lu.uni.fstc.algo3.system.*;

import java.util.List;


/**
 * Class for testing the system. Contains some test methods. Use with care.
 * Created by Gatis on 03/04/2015.
 */
public class Tester implements SystemUser {
    private LTS lts;
    private RoadMap map;
    private RoadSection roadSection;
    private Checkpoint checkpoint;
    private Scanner scanner;

    public Tester() {
        lts = LTS.getInstance();
        map = lts.getRoadMap();
    }

    /*
    This section is for system initialization manually. Methods should be called exactly in this sequence.
    Once a new road section is created there must be a new checkpoint added -> and new scanners added to that checkpoint.
    Afterwords a second checkpoint with scanners. Only then new road section should be allowed to create.
    This is so for simplicity and safety that everything is initialized as it should be. Direction and minimum count of
    scanners per checkpoint could be but not necessary checked too.
     */
    public void addRoadSection(int timeForCar, int timeForBus, int timeForTruck, String name) {
        roadSection = new RoadSection(timeForCar, timeForBus, timeForTruck, name);
        map.addSection(roadSection);
        System.out.println("Road section " + name + " created and added to map.");
    }

    public void addCheckpoint(String name, long id) {
        checkpoint = new Checkpoint(name, id);
        System.out.println("Checkpoint " + name + " added");
    }
    public void addScanner(Direction direction, long id) {
        if (direction == Direction.IN) {
            //checkpoint.addScannerIn(new Scanner(roadSection, checkpoint, id, direction));
        } else {
            //checkpoint.addScannerOut(new Scanner(roadSection, checkpoint, id, direction));
        }
        System.out.println("Scanner " + id + " with direction " + direction + " added.");
    }

    public void printRoadSections() {
        for (RoadSection rs : map.getMap()) {
            System.out.println(rs.getName());
        }
    }

}
