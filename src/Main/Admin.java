package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameFild; 
	private JTextField qntFild; 
	private JTextField priceFild; 
	private JTable table;
	public static String name;
	public static Integer price ; 
	public static Integer qty;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Admin() throws ClassNotFoundException, SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 694);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 927, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(33, 186, 138, 30);
		panel.add(lblNewLabel_1);
		
		
		JLabel lblNewLabel = new JLabel("All in One Shop");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(10, 48, 907, 49);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Qnt");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(33, 235, 138, 29);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(33, 275, 138, 32);
		panel.add(lblNewLabel_3);
		
		nameFild = new JTextField();
		nameFild.setBounds(175, 187, 215, 34);
		panel.add(nameFild);
		nameFild.setColumns(10);
		
		qntFild = new JTextField();
		qntFild.setBounds(175, 232, 215, 29);
		panel.add(qntFild);
		qntFild.setColumns(10);
		
		priceFild = new JTextField();
		priceFild.setBounds(175, 275, 215, 32);
		panel.add(priceFild);
		priceFild.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(427, 149, 456, 367);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		
		ResultSet rs = dataGet();
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 436, 309);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row =table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				nameFild.setText( model.getValueAt(row, 1).toString());
				priceFild.setText(model.getValueAt(row, 2).toString());
				qntFild.setText(model.getValueAt(row, 3).toString()); 
			}
		});
		DefaultTableModel model= (DefaultTableModel) table.getModel();
		
		String colName[] = {"id","Name", "Price", "Quantity"};
		String id, nm, qn,pr;
		model.setColumnIdentifiers(colName);
		while(rs.next())
		{
			id = rs.getString("id");
			nm = rs.getString("name"); 
			qn = rs.getString("price");
			pr = rs.getString("quntity");
			String rows[] = {id,nm,qn,pr};
			model.addRow(rows); 
		}
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Product List");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_4.setBounds(85, 11, 289, 25);
		panel_1.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 name = nameFild.getText();
				 price =Integer.valueOf(priceFild.getText());
				 qty = Integer.valueOf(qntFild.getText());	
				 
				 try {
					dataSave();
					dispose();
				    Admin.main(null);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				} 
				 } 
		});
		btnNewButton.setBounds(175, 330, 104, 34);
		panel.add(btnNewButton); 
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int id = Integer.parseInt(model.getValueAt(row, 0).toString());
				 name = nameFild.getText();
				 price =Integer.valueOf(priceFild.getText());
				 qty = Integer.valueOf(qntFild.getText());	
				 
				 try {
					 dataUpdate(id);
					dispose();
				    Admin.main(null);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(289, 330, 101, 34);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row =table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int id = Integer.parseInt(model.getValueAt(row, 0).toString());
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you went to delete this data","Warning", JOptionPane.YES_NO_CANCEL_OPTION);
			
				if(dialogResult == JOptionPane.YES_OPTION)
				{
					try {
						dataDelete(id);
						dispose();
					    Admin.main(null);
					} catch (ClassNotFoundException | SQLException e1) {
				
						e1.printStackTrace();
					}
				}
			
			
			}
		});
		btnNewButton_2.setBounds(175, 375, 215, 34);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_4_1 = new JLabel("Product Details ");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_4_1.setBounds(47, 150, 289, 25);
		panel.add(lblNewLabel_4_1);
	}
	
	
	public static void getCon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emonProject", "root", "");
		Statement str = con.createStatement();
		String sql ="CREATE TABLE PRODUCTS" +
					"(id int not NULL AUTO_INCREMENT,"+
					"name VARCHAR(255),"+
					"price int ,"+
					"quntity int,"+
					"update_at TIMESTAMP default CURRENT_TIMESTAMP ," +
					"PRIMARY KEY (id))";
		str.executeUpdate(sql);
	}
	
	public static void dataSave() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emonProject", "root", "");
		PreparedStatement pr = con.prepareStatement("insert into products (name, price, quntity) values(?,?,?)");
							pr.setString(1, name);
							pr.setInt(2, price);
							pr.setInt(3, qty);
							pr.executeUpdate();

	} 
	
	public static void dataUpdate(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emonProject", "root", "");
		PreparedStatement pr = con.prepareStatement("update products set name=? , price=?, quntity=? where id=?");
							pr.setString(1, name);
							pr.setInt(2, price);
							pr.setInt(3, qty);
							pr.setInt(4,id);
							pr.executeUpdate();

	} 

	public static void dataDelete(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emonProject", "root", "");
		PreparedStatement pr = con.prepareStatement("delete from products where id=?");
							pr.setInt(1, id);
							pr.executeUpdate();

	} 
	
	public static ResultSet dataGet() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/emonProject", "root", "");
		PreparedStatement pr = con.prepareStatement("select * from products ");
				ResultSet rs = 	pr.executeQuery();
				
				return rs;

	} 
	
}
