/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lu.uni.fstc.algo3.billing.Bill;
import lu.uni.fstc.algo3.billing.BillingManager;
import lu.uni.fstc.algo3.filter.CollectionFilter;
import lu.uni.fstc.algo3.filter.VehicleOwnerFilterCriteria;
import lu.uni.fstc.algo3.simulation.ScanGenerator;

/**
 * @author philippe
 *
 */
public class BillView extends JPanel {

	ScanGenerator scanGenerator;

	private JTextField ownerField;
	private JButton generateBillBtn;
	private JTextArea billText;

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

		ownerField = new JTextField("Owner");
		ownerField.setBounds(10, 10, 200, 25);
		this.add(ownerField);

		generateBillBtn = new JButton("Calculate Bill");
		generateBillBtn.setBounds(220, 10, 200, 25);
		this.add(generateBillBtn);

		billText = new JTextArea("The Bill is show here...");
		billText.setFont(new Font("Serif", Font.ITALIC, 16));
		billText.setLineWrap(true);
		billText.setWrapStyleWord(true);

		JScrollPane areaScrollPane = new JScrollPane(billText);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(250, 250));
		areaScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Bill"),
						BorderFactory.createEmptyBorder(5, 5, 5, 5)),
				areaScrollPane.getBorder()));

		areaScrollPane.setBounds(10, 50, 750, 450);

		this.add(areaScrollPane);

	}

	private void event() {

		this.generateBillBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String value = "";

				Collection<Bill> bills = new BillingManager()
						.createMonthlyBills();

				if (!ownerField.getText().equals("Owner")
						&& !ownerField.getText().equals("")) {

					CollectionFilter collectionFilter = new CollectionFilter();

					collectionFilter
							.addFilterCriteria(new VehicleOwnerFilterCriteria(
									ownerField.getText()));

					collectionFilter.filter(bills);
				}

				for (Bill bill : bills) {
					value += bill + "\n";
				}

				billText.setText(value);

			}
		});

	}

}
