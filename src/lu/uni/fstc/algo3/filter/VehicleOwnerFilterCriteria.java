package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.billing.Bill;

public class VehicleOwnerFilterCriteria implements FilterCriteria {

	private String name;

	public VehicleOwnerFilterCriteria(String name) {
		this.name = name;
	}

	@Override
	public boolean passes(Object objet) {
		return ((Bill) objet).getVehicleOwner().getName().equals(name);
	}

}
