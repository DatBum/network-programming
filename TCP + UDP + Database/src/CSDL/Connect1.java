package CSDL;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.sql.*;

public class Connect1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect1 frame = new Connect1();
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
	public Connect1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin:");
		lblNhpThngTin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNhpThngTin.setBounds(34, 11, 92, 14);
		contentPane.add(lblNhpThngTin);
		
		JLabel lblSql = new JLabel("SQL:");
		lblSql.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSql.setBounds(97, 36, 29, 14);
		contentPane.add(lblSql);
		
		textField = new JTextField();
		textField.setBounds(136, 8, 234, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(136, 36, 234, 20);
		contentPane.add(textField_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 94, 370, 80);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			null,
			new String[] {
				"ID", "TenKH", "DiaChi", "Luong"
			}
		));
		table.setColumnSelectionAllowed(true);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(textField.getText());
					Statement stm = conn.createStatement();
					String sqlStm = textField_1.getText();
					ResultSet rs = stm.executeQuery(sqlStm);
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					while(rs.next()) {
						int id = rs.getInt("Id");
						double luong = rs.getDouble("Luong");
						String s = rs.getString("TenKH");
						String d = rs.getString("Diachi");
						model.addRow(new Object[] {id, s, d, luong});
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Sai URL hoac sai cau lenh SQL!");
				}
			}
		});
		btnSubmit.setBounds(32, 227, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		btnReset.setBounds(178, 227, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(315, 227, 89, 23);
		contentPane.add(btnExit);
	}
}
