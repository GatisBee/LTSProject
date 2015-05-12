/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import lu.uni.fstc.algo3.billing.Bill;
import lu.uni.fstc.algo3.billing.BillingManager;
import lu.uni.fstc.algo3.simulation.ScanGenerator;

/**
 * @author philippe
 *
 */
public class BillView extends JPanel {

	ScanGenerator scanGenerator;

	private JButton generateBillBtn;
	private JTextPane billText;

	/**
	 * @param scanGenerator
	 * 
	 */
	public BillView(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;

		this.init();
		this.initGUI();
		this.event();
	}

	private void init() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
	}

	/**
	 * 
	 */
	private void initGUI() {

		generateBillBtn = new JButton("Calculate Bill");
		this.add(generateBillBtn);

		billText = new JTextPane();
		this.add(billText);

	}

	private void event() {

		this.generateBillBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String value = "reating and printing monthly bills...";

				for (Bill bill : new BillingManager().createMonthlyBills()) {

					value += bill;
				}

				billText.setText(value);

			}
		});

	}

}