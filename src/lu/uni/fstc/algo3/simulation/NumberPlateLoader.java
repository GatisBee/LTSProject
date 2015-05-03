package lu.uni.fstc.algo3.simulation;

import lu.uni.fstc.algo3.vehicles.NumberPlate;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Loads sample number plates from a file and provides a getter for loaded number plate collection.
 * Created by Gatis on 03/05/2015.
 */
public class NumberPlateLoader {
    private List<NumberPlate> numberPlates;
    private BufferedReader fileReader;

    /**
     * Tries to load number plates from a hardcoded file.
     */
    public NumberPlateLoader() {
        numberPlates = new ArrayList<NumberPlate>();
        try {
            fileReader = new BufferedReader(new FileReader("numbers.sample"));
            System.out.print("Thread: " + Thread.currentThread().getName() + " load number plates from file... ");
            while (fileReader.ready()) {
                numberPlates.add(new NumberPlate(fileReader.readLine()));
            }
            System.out.println("done.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(-2);
                }
            }
        }
    }

    /**
     * A getter for <code>NumberPlate</code> collection.
     * @return collection of <code>NumberPlate</code> if load was successful empty collection otherwise.
     */
    public List<NumberPlate> getNumberPlates() {
        return numberPlates;
    }
}
