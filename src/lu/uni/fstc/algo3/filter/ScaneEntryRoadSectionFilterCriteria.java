/**
 * 
 */
package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.system.RoadSection;

/**
 * @author philippe
 *
 */
public class ScaneEntryRoadSectionFilterCriteria implements FilterCriteria {

	private RoadSection roadSection;

	public ScaneEntryRoadSectionFilterCriteria(RoadSection roadSection) {
		this.roadSection = roadSection;
	}

	@Override
	public boolean passes(Object objet) {
		return ((ScanEntry) objet).getRoadSection().equals(roadSection);
	}

}
