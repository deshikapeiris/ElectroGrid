package view.billManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class CustomerBilling extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerBilling frame = new CustomerBilling();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerBilling() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1400, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(102, 153, 153));
		panel.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(102, 153, 153)));
		panel.setBounds(10, 11, 1360, 700);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBounds(10, 27, 408, 456);
		panel_1.setBackground(new Color(102, 153, 153));
		panel_1.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(153, 204, 204)));
		
		JLabel lblNewLabel = new JLabel("Customer Account Number");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(42, 50, 30, 12);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(237, 43, 86, 20);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CustomerName           ");
		lblNewLabel_1.setBounds(237, 43, 86, 20);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(237, 43, 86, 20);
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_2.setColumns(10);
		panel_1.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("Unit                            ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblNewLabel_2);
		lblNewLabel_1.setBounds(237, 43, 86, 20);
		
		textField_3 = new JTextField();
		textField_2.setBounds(237, 43, 86, 20);
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Total                          ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblNewLabel_3);
		lblNewLabel_1.setBounds(237, 43, 86, 20);
		
		textField_4 = new JTextField();
		textField_2.setBounds(237, 43, 86, 20);
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_4.setColumns(10);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_4 = new JLabel("Date                           ");
		lblNewLabel_1.setBounds(237, 43, 86, 20);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_1.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(237,43,86,20);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(102, 153, 153)));
		panel_2.setBackground(new Color(176, 224, 230));
		panel_2.setBounds(446, 27, 347, 510);
		panel.add(panel_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_2.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBounds(100,300, 800, 800);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(textField_5);
		textField_5.setBounds(237,43,86,20);
		textField_5.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_2.add(chckbxNewCheckBox_1);
		
		textField_8 = new JTextField();
		panel_2.add(textField_8);
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		panel_2.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("New check box");
		panel_2.add(chckbxNewCheckBox_1_1);
		chckbxNewCheckBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		textField_7 = new JTextField();
		panel_2.add(textField_7);
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_7.setColumns(10);
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(153, 204, 204)));
		panel_3.setBackground(new Color(102, 153, 153));
		panel_3.setBounds(42, 517, 347, 111);
		panel.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(153, 204, 204)));
		panel_4.setBackground(new Color(102, 153, 153));
		panel_4.setBounds(853, 517, 347, 125);
		panel.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(14, 14, 14, 14, (Color) new Color(153, 204, 204)));
		panel_5.setBackground(new Color(102, 153, 153));
		panel_5.setBounds(853, 27, 347, 456);
		panel.add(panel_5);
		
	}
}
