package lu.uni.fstc.algo3.system;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class represents a starting/ending point of a road section. Each checkpoint can have [0,n] scanners facing
 * each direction (in, out).
 * Created by Gatis on 27/03/2015.
 */
public class Checkpoint {
    /**
     * Name of the checkpoint
     */
    private String name;
    /**
     * A unique identifier of a checkpoint
     * The uniqueness of the identifier should be enforced from outside of this class on the scope of whole system.
     */
    private UUID id;
    /**
     * Scanners for inbound traffic
     */
    private List<Scanner> scannersIn;
    /**
     * Scanners for outbound traffic
     */
    private List<Scanner> scannersOut;

    public Checkpoint(String name, UUID id) {
        this.name = name;
        this.id = id;
        scannersIn = new ArrayList<>();
        scannersOut = new ArrayList<>();
    }

    public boolean addScannerIn(Scanner scanner) {
        scannersIn.add(scanner);
        return true;
    }

    public boolean addScannerOut(Scanner scanner) {
        scannersOut.add(scanner);
        return true;
    }

    public List<Scanner> getScannersIn() {
        return scannersIn;
    }

    public List<Scanner> getScannersOut() {
        return scannersOut;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checkpoint that = (Checkpoint) o;

        if (!name.equals(that.name)) return false;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
