/**
 * 
 */
package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.system.Direction;

/**
 * @author philippe
 *
 */
public class ScanEntryDirectionFilterCriteria implements FilterCriteria {

	private Direction direction;

	public ScanEntryDirectionFilterCriteria(Direction direction) {
		this.direction = direction;
	}

	@Override
	public boolean passes(Object objet) {
		return ((ScanEntry) objet).getDirection().equals(direction);
	}

}
