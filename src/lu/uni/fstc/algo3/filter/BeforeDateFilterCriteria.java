package lu.uni.fstc.algo3.filter;

import java.time.LocalDateTime;

import lu.uni.fstc.algo3.billing.Bill;

public class BeforeDateFilterCriteria implements FilterCriteria {

	private LocalDateTime date;

	public BeforeDateFilterCriteria(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public boolean passes(Object objet) {
		return ((Bill) objet).getDate().isBefore(date);
	}
}