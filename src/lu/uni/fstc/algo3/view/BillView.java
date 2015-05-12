/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JScrollPane scroll;

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
		this.setLayout(null);
	}

	/**
	 * 
	 */
	private void initGUI() {

		generateBillBtn = new JButton("Calculate Bill");
		generateBillBtn.setBounds(10, 10, 200, 25);
		this.add(generateBillBtn);

		billText = new JTextPane();
		billText.setFont(new Font("Courier 10 Pitch", Font.PLAIN, 13));
		billText.setEditable(false);
		billText.setBounds(10, 50, 400, 400);

		this.add(billText);

		/*scroll = new JScrollPane();
		scroll.setViewportView(this.billText);
		this.add(scroll);*/

	}

	private void event() {

		this.generateBillBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String value = "reating and printing monthly bills... \n";

				for (Bill bill : new BillingManager().createMonthlyBills()) {

					value += bill + "\n";
				}

				billText.setText(value);

			}
		});

	}

}