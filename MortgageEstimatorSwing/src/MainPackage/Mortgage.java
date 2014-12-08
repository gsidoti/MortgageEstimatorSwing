package MainPackage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class Mortgage {
	private JFrame frame;
	private JTextField Income;
	private JTextField Debt;
	private JTextField Interest;
	private JTextField DownPayment;
	private JTextField MaxP;
	private JTextField MaxM;
	private JTextField HPayment;
	private JTextField OPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mortgage window = new Mortgage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mortgage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Mortgage Estimator");
		frame.setBounds(100, 100, 514, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lblTotalGrossIncome = new JLabel("Total Gross Income");
		lblTotalGrossIncome.setBounds(29, 29, 128, 16);
		frame.getContentPane().add(lblTotalGrossIncome);
		Income = new JTextField();
		Income.setBounds(23, 58, 134, 28);
		frame.getContentPane().add(Income);
		Income.setColumns(10);
		JLabel lblTotalMonthlyDebt = new JLabel("Total Monthly Debt");
		lblTotalMonthlyDebt.setBounds(23, 99, 128, 16);
		frame.getContentPane().add(lblTotalMonthlyDebt);
		Debt = new JTextField();
		Debt.setBounds(23, 128, 134, 28);
		frame.getContentPane().add(Debt);
		Debt.setColumns(10);
		JLabel lblMorgageInterestRate = new JLabel("Morgage Interest Rate");
		lblMorgageInterestRate.setBounds(23, 169, 137, 16);
		frame.getContentPane().add(lblMorgageInterestRate);
		Interest = new JTextField();
		Interest.setBounds(23, 198, 134, 28);
		frame.getContentPane().add(Interest);
		Interest.setColumns(10);
		JLabel lblTerm = new JLabel("Term");
		lblTerm.setBounds(229, 29, 36, 16);
		frame.getContentPane().add(lblTerm);
		final JComboBox<Object> comboBox = new JComboBox();
		comboBox.setBounds(213, 59, 52, 27);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "10", "15",
				"30" }));
		frame.getContentPane().add(comboBox);
		JLabel lblDownPaymentoptional = new JLabel("Down Payment (Optional)");
		lblDownPaymentoptional.setBounds(23, 232, 176, 16);
		frame.getContentPane().add(lblDownPaymentoptional);
		DownPayment = new JTextField();
		DownPayment.setText("0");
		DownPayment.setBounds(23, 261, 134, 28);
		frame.getContentPane().add(DownPayment);
		DownPayment.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double GrossIncome = Double.parseDouble(Income.getText());
				double monthlyDebt = Double.parseDouble(Debt.getText());
				double down = Double.parseDouble(DownPayment.getText());
				double years = Double.parseDouble((String) comboBox
						.getSelectedItem());
				double rate = Double.parseDouble(Interest.getText());
				double HousingPayment = Engine.HousingPayment(GrossIncome);
				double OblPayment = Engine.OblPayment(GrossIncome,
						monthlyDebt);
				double Payment = Engine.allowedPayment(GrossIncome, monthlyDebt);
				double interest = (rate / 100 /12);
				double pv = Engine.pv(OblPayment, HousingPayment, years, interest, down);
				double PV = Math.round(pv * 100.0) / 100.0;
				HPayment.setText("$"+HousingPayment);
				OPayment.setText("$"+OblPayment);
				MaxP.setText("$" + Payment);
				MaxM.setText("$" + PV);
			}
		});
		btnCalculate.setBounds(201, 112, 85, 177);
		frame.getContentPane().add(btnCalculate);
		MaxP = new JTextField();
		MaxP.setEditable(false);
		MaxP.setBounds(340, 115, 134, 28);
		frame.getContentPane().add(MaxP);
		MaxP.setColumns(10);
		JLabel lblMaxPayment = new JLabel("Max Payment Allowed");
		lblMaxPayment.setBounds(340, 86, 134, 16);
		frame.getContentPane().add(lblMaxPayment);
		MaxM = new JTextField();
		MaxM.setEditable(false);
		MaxM.setBounds(340, 47, 134, 28);
		frame.getContentPane().add(MaxM);
		MaxM.setColumns(10);
		JLabel lblMaxMorgage = new JLabel("Max Morgage");
		lblMaxMorgage.setBounds(338, 19, 121, 16);
		frame.getContentPane().add(lblMaxMorgage);
		
		HPayment = new JTextField();
		HPayment.setEditable(false);
		HPayment.setBounds(340, 186, 134, 28);
		frame.getContentPane().add(HPayment);
		HPayment.setColumns(10);
		
		JLabel lblHousingPayment = new JLabel("Housing Payment");
		lblHousingPayment.setBounds(340, 156, 134, 16);
		frame.getContentPane().add(lblHousingPayment);
		
		OPayment = new JTextField();
		OPayment.setEditable(false);
		OPayment.setBounds(338, 264, 136, 25);
		frame.getContentPane().add(OPayment);
		OPayment.setColumns(10);
		
		JLabel lblHousingPayment_1 = new JLabel("Housing Payment & ");
		lblHousingPayment_1.setBounds(340, 232, 128, 16);
		frame.getContentPane().add(lblHousingPayment_1);
		
		JLabel lblOtherObligations = new JLabel("Other Obligations");
		lblOtherObligations.setBounds(340, 245, 105, 16);
		frame.getContentPane().add(lblOtherObligations);
	}
}