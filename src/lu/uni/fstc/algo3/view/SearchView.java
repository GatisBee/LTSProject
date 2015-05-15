/**
 * 
 */
package lu.uni.fstc.algo3.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import lu.uni.fstc.algo3.simulation.ScanGenerator;
import lu.uni.fstc.algo3.view.HomeView.State;

/**
 * @author philippe
 *
 */
public class SearchView extends JPanel {

	ScanGenerator scanGenerator;

	private State state;

	private JButton searchBtn;

	/**
	 * @param scanGenerator
	 * 
	 */
	public SearchView(ScanGenerator scanGenerator) {
		this.scanGenerator = scanGenerator;

		this.init();
		this.initGUI();
		this.event();
	}

	private void init() {
		this.setLayout(null);
	}

	/**
	 * 
	 */
	private void initGUI() {

		searchBtn = new JButton("Search");
		searchBtn.setBounds(10, 10, 100, 25);
		this.add(searchBtn);

	}

	private void event() {

		this.searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Search");
			}
		});

	}
}
