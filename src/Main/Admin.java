package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
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
import java.awt.Color;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField nameFild; 
	private static JTextField qntFild; 
	private static JTextField priceFild; 
	private static JTable table;
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
		panel.setBackground(new Color(0, 64, 128));
		panel.setBounds(0, 0, 927, 655);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Inventory Managment\r\n");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(10, 0, 907, 60);
		panel.add(lblNewLabel);
		
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 60, 236, 595);
		panel.add(panel_2);
		
		JMenu productmanageMenuBtn = new JMenu("Product Managment");
		productmanageMenuBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
;
			}
		}); 
		panel_2.setLayout(null);
		productmanageMenuBtn.setOpaque(true);
		productmanageMenuBtn.setForeground(Color.WHITE);
		productmanageMenuBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		productmanageMenuBtn.setBackground(new Color(0, 128, 192));
		productmanageMenuBtn.setBounds(23, 110, 187, 30);
		panel_2.add(productmanageMenuBtn);
		
		ImageIcon menuIconStudentRegistation = new ImageIcon(Admin.class.getResource("/icon/courses.png"));
		Image menuIconStudentRegistationImage = menuIconStudentRegistation.getImage();
		Image resizeMenuIconStudentRegistationImage = menuIconStudentRegistationImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		productmanageMenuBtn.setIcon(new ImageIcon(resizeMenuIconStudentRegistationImage));
		
		
		JMenu LogoutBtn = new JMenu("Logout");
		LogoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Login.main(null);
			} 
		});
		LogoutBtn.setOpaque(true);
		LogoutBtn.setForeground(Color.WHITE);
		LogoutBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		LogoutBtn.setBackground(new Color(0, 128, 192));
		LogoutBtn.setBounds(23, 177, 187, 30);
		panel_2.add(LogoutBtn);
		
		ImageIcon menuIconLogout = new ImageIcon(Admin.class.getResource("/icon/logout.png"));
		Image menuIconLogoutImage = menuIconLogout.getImage();
		Image resizeMenuIconLogoutImage = menuIconLogoutImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		LogoutBtn.setIcon(new ImageIcon(resizeMenuIconLogoutImage));
		
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(0, 128, 192));
		panel_3.setBounds(233, 60, 694, 595);
		panel.add(panel_3);
		panel_3.setLayout(null);
		productManage(panel_3);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setBounds(136, 525, 118, 34);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 111, 164));
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(305, 525, 118, 34);
		panel_3.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 111, 164));
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(473, 525, 118, 34);
		panel_3.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 111, 164));
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
	}
	
	
	public static void productManage(JPanel panel_3) throws ClassNotFoundException, SQLException {
		JLabel lblNewLabel_4_1 = new JLabel("Product Manage");
		lblNewLabel_4_1.setBounds(10, 26, 674, 47);
		panel_3.add(lblNewLabel_4_1);
		lblNewLabel_4_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Raleway SemiBold", Font.BOLD, 28));
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(136, 112, 86, 30);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		nameFild = new JTextField();
		nameFild.setBounds(232, 108, 359, 34);
		panel_3.add(nameFild);
		nameFild.setColumns(10);
		
		qntFild = new JTextField();
		qntFild.setBounds(232, 153, 125, 34);
		panel_3.add(qntFild);
		qntFild.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Price");
		lblNewLabel_3.setBounds(383, 151, 70, 32);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		priceFild = new JTextField();
		priceFild.setBounds(463, 153, 128, 34);
		panel_3.add(priceFild);
		priceFild.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Qnt");
		lblNewLabel_2.setBounds(136, 153, 86, 29);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(136, 219, 456, 281);
		panel_3.add(panel_1);
		panel_1.setLayout(null);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 436, 223);
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
		scrollPane.setViewportView(table);
		ResultSet rs = dataGet();
		
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
		
		JLabel lblNewLabel_4 = new JLabel("Product List");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_4.setBounds(85, 11, 289, 25);
		panel_1.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setBounds(136, 525, 118, 34);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 111, 164));
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(305, 525, 118, 34);
		panel_3.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(0, 111, 164));
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setBounds(473, 525, 118, 34);
		panel_3.add(btnNewButton_2);
		btnNewButton_2.setFont(new Font("Raleway SemiBold", Font.PLAIN, 16));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(0, 111, 164));
		
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
