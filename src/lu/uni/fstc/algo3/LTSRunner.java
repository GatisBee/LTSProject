package lu.uni.fstc.algo3;

import lu.uni.fstc.algo3.utilities.DataGenerator;

public class LTSRunner {

    public static void main(String[] args) {
	    System.out.println("Hello LTS user!");
    }

    /**
     * Generates and writes unique number plates to a hardcoded sample file
     */
    public static void generateNumberPlates() {
        // tested this method works
        System.out.print("Generating numbers...");
        DataGenerator generator = new DataGenerator();
        generator.generateNumbers();
        System.out.print("...trying to write results to file...");
        generator.writeNumbersToFile("numbers.sample");
        System.out.print("... finished writing , you can check the results ...");
        System.exit(0);
    }
}
