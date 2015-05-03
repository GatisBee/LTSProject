package lu.uni.fstc.algo3.utilities;

import lu.uni.fstc.algo3.system.LTS;
import lu.uni.fstc.algo3.system.RoadMap;

import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Generates random numbers with the specified boundaries of the number of total numbers
 * Backed by a hash set, bounds generating random number work like random.nextInt(max) + min.
 * Created by Gatis on 03/04/2015.
 */
public class DataGenerator {
    private LTS lts;
    private RoadMap map;
    private Random random;
    private static final int MAX_NUMBER = 999998;
    private static final int MIN_NUMBER = 1;
    private static final int SET_SIZE = 100;
    private Set<Integer> numbers;
    private FileOutputStream fos;

    public DataGenerator() {
        lts = LTS.getInstance();
        map = lts.getRoadMap();
        random = new Random(System.currentTimeMillis() * MAX_NUMBER);
        numbers = new HashSet<Integer>();
    }

    public void generateNumbers() {
        // try to add unique numbers, if addition fails because of a duplicate, decrees i and repeat
        for (int i = 0; i < SET_SIZE; i++) {
            if (!numbers.add(random.nextInt(MAX_NUMBER) + MIN_NUMBER)) {
                i--;
            }
        }
    }

    public void writeNumbersToFile(String path) {
        // create a file from the specified path
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        }
        try {
            // create a  buffered writer to that file
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            // try to write every number to file
            for (Integer i : numbers) {
                writer.write(i.toString() + "\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }
    }

}
