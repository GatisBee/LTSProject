package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.statistics.ScanEntry;
import lu.uni.fstc.algo3.system.LTS;

public class ScaneEntryMakerFilterCriteria implements FilterCriteria {

	private String maker;
	private LTS lts = LTS.getInstance();

	public ScaneEntryMakerFilterCriteria(String maker) {
		this.maker = maker;
	}

	@Override
	public boolean passes(Object objet) {

		ScanEntry scanEntry = ((ScanEntry) objet);

		String currentMaker = lts.getVehicleRegistry()
				.getVehicle(scanEntry.getNumberPlate()).getMaker();

		return currentMaker.equals(maker);
	}
}
