package lu.uni.fstc.algo3;

import lu.uni.fstc.algo3.billing.Bill;
import lu.uni.fstc.algo3.billing.BillingManager;
import lu.uni.fstc.algo3.simulation.DataGenerator;
import lu.uni.fstc.algo3.simulation.Environment;
import lu.uni.fstc.algo3.simulation.ScanGenerator;
import lu.uni.fstc.algo3.system.LTS;

import java.util.Scanner;

public class LTSRunner {

    public static void main(String[] args) {
        System.out.println("Hello LTS user!");
//        LTSRunner.generateNumberPlates();
        Environment.init(5);
        ScanGenerator scanGenerator = new ScanGenerator();
        Scanner reader = new Scanner(System.in);

        while (true) {
            String line = null;
            System.out.print(">");
            if (reader.hasNextLine()) {
                line = reader.nextLine();
            }
            // avoid null pointer exception if line is not ready to read
            if (line != null) {
                // interpret line, otherwise continue
                if (line.equals("start")) {
                    // start simulation
                    scanGenerator.startGenerating();
                } else if (line.equals("stop")) {
                    // stop simulation
                    scanGenerator.stopGenerating();
                } else if (line.equals("bill")) {
                    System.out.println("Creating and printing monthly bills...");
                    for (Bill b : new BillingManager().createMonthlyBills()) {
                        System.out.println(b);
                    }
                } else if (line.equals("exit") || line.equals("quit")) {
                    System.out.println("Exiting LTS...");
                    // cleanup and exit
                    scanGenerator.stopGenerating();
                    reader.close();
                    LTS.getInstance().printScans();
                    System.exit(0);
                }
                // etc.
            }
        }

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
