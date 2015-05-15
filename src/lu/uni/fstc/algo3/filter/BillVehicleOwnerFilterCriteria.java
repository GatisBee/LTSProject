package lu.uni.fstc.algo3.filter;

import lu.uni.fstc.algo3.billing.Bill;

public class BillVehicleOwnerFilterCriteria implements FilterCriteria {

	private String name;

	public BillVehicleOwnerFilterCriteria(String name) {
		this.name = name;
	}

	@Override
	public boolean passes(Object objet) {
		return ((Bill) objet).getVehicleOwner().getName().equals(name);
	}

}
