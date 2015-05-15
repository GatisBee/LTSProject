package lu.uni.fstc.algo3.vehicles;

/**
 * This class represents a number plate of a vehicle in Luxembourg Toll System.
 * Created by Gatis on 27/03/2015.
 *
 * @see lu.uni.fstc.algo3.vehicles.Vehicle
 */
public class NumberPlate {
	private String numberPlate;

	/**
	 * Default constructor for this number plate. Initializes the number plate
	 * from parameters.
	 *
	 * @param numberPlate
	 *            number plate
	 */
	public NumberPlate(String numberPlate) {
		if (numberPlate == null)
			throw new NullPointerException();
		this.numberPlate = numberPlate;
	}

	/**
	 * Returns this number plate.
	 *
	 * @return number plate
	 */
	public String getNumberPlate() {
		return numberPlate;
	}

	@Override
	public String toString() {
		return "NumberPlate{" + "numberPlate='" + numberPlate + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		NumberPlate that = (NumberPlate) o;

		return getNumberPlate().equals(that.getNumberPlate());

	}

	@Override
	public int hashCode() {
		return getNumberPlate().hashCode();
	}
}
