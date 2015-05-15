/**
 * 
 */
package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.statistics.ScanEntry;

/**
 * @author philippe
 *
 */
public class NumberPlateFilterCriteria implements FilterCriteria {

	private String numberPlate;

	public NumberPlateFilterCriteria(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	@Override
	public boolean passes(Object objet) {
		return ((ScanEntry) objet).getNumberPlate().getNumberPlate()
				.equals(numberPlate);
	}

}
