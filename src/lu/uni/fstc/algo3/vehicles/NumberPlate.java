package lu.uni.fstc.algo3.vehicles;

/**
 * This class represents a number plate of a vehicle in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class NumberPlate {
    private String numberPlate;

    /**
     * Default constructor for this number plate. Initializes the number plate from parameters.
     * @param numberPlate number plate
     */
    public NumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }
    //TODO methods for comparison, hashCode and equals

    /**
     * Returns this number plate.
     * @return number plate
     */
	public String getNumberPlate() {
		return numberPlate;
	}
}
