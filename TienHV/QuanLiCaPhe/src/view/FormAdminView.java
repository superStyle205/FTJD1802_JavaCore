package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import common.database.ConnectionUntil;
import model.dao.FoodCategoryDao;
import model.dao.FoodDao;
import model.dao.TableFoodDao;
import model.dao.UserDao;
import testing.KetNoiCSDL;

public class FormAdminView extends JFrame implements IFormAdminView, ActionListener, MouseListener {
	//All
	private JScrollPane scroll;
	
	//Revenue Area (Khu vực doanh thu)
	private JButton btnRevenue;
	private JTable tableRevenue;
	
	//Food Area (Khu vực sản phẩm) 
	private JButton btnAddFood;
	private JButton btnUpdateFood;
	private JButton btnDeleteFood;
	private JButton btnViewFood;
	private JTable tableFood;
	private JButton btnSearchFood;
	private JTextField food;
	private JLabel lblIdFood;
	private JTextField idFood;
	private JLabel lblFoodName;
	private JTextField foodName;
	private JLabel lblIdFoodCategory_food;
	private JTextField idFoodCategory_food;
	private JLabel lblPrice;
	private JTextField price;
	
	//FoodCategory Area (Khu vực loại sản phẩm)
	private JButton btnAddFoodCategory;
	private JButton btnUpdateFoodCategory;
	private JButton btnDeleteFoodCategory;
	private JButton btnViewFoodCategory;
	private JTable tableFoodCategory;
	private JLabel lblIdFoodCategory;
	private JLabel lblFoodCategoryName;
	private JTextField idFoodCategory;
	private JTextField foodCategoryName;

	//TableFood Area (Khu vực bàn ăn)
	private JButton btnAddTable;
	private JButton btnUpdateTable;
	private JButton btnDeleteTable;
	private JButton btnViewTable;
	private JTable table;
	private JLabel lblIdTableFood;
	private JTextField idTableFood;
	private JLabel lblTableFoodName;
	private JTextField tableFoodName;
	private JLabel lblStatus;
	private JTextField tableStatus;
	
	//User Area (Khu vực tài khoản)
	private JButton btnAddUser;
	private JButton btnUpdateUser;
	private JButton btnDeleteUser;
	private JButton btnViewUser;
	private JTable tableUser;
	private JLabel lblUsername;
	private JTextField username;
	private JLabel lbltype;
	private JTextField type;
	private JButton btnUpdatePassword;
	
	//SQL
	private ResultSet rs;
	private Statement st;
	private DefaultTableModel model;
	private Connection conn;
	
	@Override
	public void display() {
		add(managerTab());
		//Tiêu đề
		setTitle("Admin");
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(900, 500);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(0);
		//Tắt chức năng phóng to màn hình của form.
		setResizable(false);
		//Sự kiện khi bấm nút đỏ thoát form thì chỉ thoát form hiện tại.
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
	}

	@Override
	public JTabbedPane managerTab() {
		JTabbedPane myTabled = new JTabbedPane();
		
		JPanel revenue = managerRevenue();
		JSplitPane food = managerFoodArea();
		JSplitPane foodCategory = managerFoodCategoryArea();
		JSplitPane tableFood = managerTableFoodArea();
		JSplitPane user = managerUserArea();
		
		myTabled.addTab("Quản lí doanh thu", revenue);
		myTabled.addTab("Quản lí sản phẩm", food);
		myTabled.addTab("Quản lí loại sản phẩm", foodCategory);
		myTabled.addTab("Quản lí bàn", tableFood);
		myTabled.addTab("Quản lí tài khoản", user);
		
	
		return myTabled;
	}

	@Override
	public JPanel managerRevenue() {
		JPanel panel = new JPanel();
		
		JPanel managerDate = new JPanel(new GridLayout(1, 3, 5, 5));
		JPanel managerTable = new JPanel();
		
		JDateChooser dateCheckIn = new JDateChooser();
		dateCheckIn.setPreferredSize(new Dimension(250, 20));
		btnRevenue = new JButton("Thống kê");
		JDateChooser dateCheckOut = new JDateChooser();
		dateCheckOut.setPreferredSize(new Dimension(250, 20));
		
		JTable tableRevenue = new JTable();
		tableRevenue.setPreferredSize(new Dimension(800, 350));
		managerDate.add(dateCheckIn);
		managerDate.add(btnRevenue);
		managerDate.add(dateCheckOut);
		managerTable.add(tableRevenue);
		
		panel.add(managerDate);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerFoodAreaLeft() {
		JPanel panel = new JPanel();
		JPanel managerButton = new JPanel();
		JPanel managerTable = new JPanel(new GridLayout(1, 1, 5, 5));
		
		//quản lí panel trên trái
		btnAddFood = new JButton("Thêm");
		btnAddFood.addActionListener(this);
		btnAddFood.setPreferredSize(new Dimension(100, 50));
		
		btnUpdateFood = new JButton("Sửa");
		btnUpdateFood.addActionListener(this);
		btnUpdateFood.setPreferredSize(new Dimension(100, 50));
		
		btnDeleteFood = new JButton("Xóa");
		btnDeleteFood.addActionListener(this);
		btnDeleteFood.setPreferredSize(new Dimension(100, 50));
		
		btnViewFood = new JButton("Xem");
		btnViewFood.addActionListener(this);
		btnViewFood.setPreferredSize(new Dimension(100, 50));
		
		tableFood = new JTable(model);
		tableFood.addMouseListener(this);
		tableFood.setPreferredSize(new Dimension(450, 800));	
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		scroll = new JScrollPane(tableFood);
		scroll.setPreferredSize(new Dimension(450, 340));
		
		loadDatabaseFood();
		
		managerButton.add(btnAddFood);
		managerButton.add(btnUpdateFood);
		managerButton.add(btnDeleteFood);
		managerButton.add(btnViewFood);
		managerTable.add(scroll, tableFood);

		
		panel.add(managerButton);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerFoodAreaRight() {
		JPanel panel = new JPanel();
		JPanel magagerSearch = new JPanel();
		JPanel manager = new JPanel(new GridLayout(8, 1, 5, 5));

	
		
		//Giao diện phía trên
		food = new JTextField(20);
		btnSearchFood = new JButton("Tìm");
		btnSearchFood.addActionListener(this);
		btnSearchFood.setPreferredSize(new Dimension(100, 50));
		
		//Giao diện phía trái và giữa
		lblIdFood = new JLabel("ID");
		idFood = new JTextField(30);
		lblFoodName = new JLabel("Tên món");
		foodName = new JTextField(30);
		lblIdFoodCategory_food = new JLabel("Danh Mục");
		idFoodCategory_food = new JTextField(30);
		lblPrice = new JLabel("Giá");
		price = new JTextField(30);
		

		
		magagerSearch.add(food);
		magagerSearch.add(btnSearchFood);
		manager.add(lblIdFood);
		manager.add(idFood);
		manager.add(lblFoodName);
		manager.add(foodName);
		manager.add(lblIdFoodCategory_food);
		manager.add(idFoodCategory_food);
		manager.add(lblPrice);
		manager.add(price);
	
		panel.add(magagerSearch);
		panel.add(manager);
		
		return panel;
	}

	@Override
	public JSplitPane managerFoodArea() {
		JSplitPane managerArea = new JSplitPane();
		
		managerArea.setLeftComponent(managerFoodAreaLeft());
		managerArea.setRightComponent(managerFoodAreaRight());

		managerArea.setDividerLocation(500);
		
		return managerArea;
	}

	@Override
	public JPanel managerFoodCategoryAreaLeft() {
		JPanel panel = new JPanel();
		JPanel managerButton = new JPanel();
		JPanel managerTable = new JPanel(new GridLayout(1, 1, 5, 5));
		
		//quản lí panel trên trái
		btnAddFoodCategory = new JButton("Thêm");
		btnAddFoodCategory.addActionListener(this);
		btnAddFoodCategory.setPreferredSize(new Dimension(100, 50));
		
		btnUpdateFoodCategory = new JButton("Sửa");
		btnUpdateFoodCategory.addActionListener(this);
		btnUpdateFoodCategory.setPreferredSize(new Dimension(100, 50));
		
		btnDeleteFoodCategory = new JButton("Xóa");
		btnDeleteFoodCategory.addActionListener(this);
		btnDeleteFoodCategory.setPreferredSize(new Dimension(100, 50));
		
		btnViewFoodCategory = new JButton("Xem");
		btnViewFoodCategory.addActionListener(this);
		btnViewFoodCategory.setPreferredSize(new Dimension(100, 50));
		
		tableFoodCategory = new JTable();
		tableFoodCategory.addMouseListener(this);
		tableFoodCategory.setPreferredSize(new Dimension(450, 800));
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		scroll = new JScrollPane(tableFoodCategory);
		scroll.setPreferredSize(new Dimension(450, 340));
		
		loadDatabaseFoodCategory();
		
		managerButton.add(btnAddFoodCategory);
		managerButton.add(btnUpdateFoodCategory);
		managerButton.add(btnDeleteFoodCategory);
		managerButton.add(btnViewFoodCategory);
		managerTable.add(scroll, tableFoodCategory);

		
		panel.add(managerButton);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerFoodCategoryAreaRight() {
		JPanel panel = new JPanel();
		
		JPanel manager = new JPanel(new GridLayout(4, 1, 5, 5));

		lblIdFoodCategory = new JLabel("Mã Loại");
		idFoodCategory = new JTextField(30);
		lblFoodCategoryName = new JLabel("Loại sản phẩm");
		foodCategoryName = new JTextField(30);

		manager.add(lblIdFoodCategory);
		manager.add(idFoodCategory);	
		manager.add(lblFoodCategoryName);
		manager.add(foodCategoryName);

		panel.add(manager);

		
		return panel;
	}

	@Override
	public JSplitPane managerFoodCategoryArea() {
		JSplitPane managerArea = new JSplitPane();
		
		managerArea.setLeftComponent(managerFoodCategoryAreaLeft());
		managerArea.setRightComponent(managerFoodCategoryAreaRight());

		managerArea.setDividerLocation(500);
		
		return managerArea;
	}

	@Override
	public JPanel managerTableFoodAreaLeft() {
		JPanel panel = new JPanel();
		JPanel managerButton = new JPanel();
		JPanel managerTable = new JPanel(new GridLayout(1, 1, 5, 5));
		
		//quản lí panel trên trái
		btnAddTable = new JButton("Thêm");
		btnAddTable.addActionListener(this);
		btnAddTable.setPreferredSize(new Dimension(100, 50));
		
		btnUpdateTable = new JButton("Sửa");
		btnUpdateTable.addActionListener(this);
		btnUpdateTable.setPreferredSize(new Dimension(100, 50));
		
		btnDeleteTable = new JButton("Xóa");
		btnDeleteTable.addActionListener(this);
		btnDeleteTable.setPreferredSize(new Dimension(100, 50));
		
		btnViewTable = new JButton("Xem");
		btnViewTable.addActionListener(this);
		btnViewTable.setPreferredSize(new Dimension(100, 50));
		
		table = new JTable(model);
		table.addMouseListener(this);
		table.setPreferredSize(new Dimension(450, 800));
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		scroll =  new JScrollPane(table); 
		scroll.setPreferredSize(new Dimension(450, 340));
		
		loadDatabaseTableFood();
		
		managerButton.add(btnAddTable);
		managerButton.add(btnUpdateTable);
		managerButton.add(btnDeleteTable);
		managerButton.add(btnViewTable);
		managerTable.add(scroll, table);

		panel.add(managerButton);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerTableFoodAreaRight() {
		JPanel panel = new JPanel();
		
		JPanel manager = new JPanel(new GridLayout(6, 1, 5, 5));

		//Giao diện phía trái và giữa
		lblIdTableFood = new JLabel("ID");
		idTableFood = new JTextField(30);
		lblTableFoodName = new JLabel("Tên bàn");
		tableFoodName = new JTextField(30);
		lblStatus = new JLabel("Trạng thái");
		tableStatus = new JTextField(30);

		manager.add(lblIdTableFood);
		manager.add(idTableFood);
		manager.add(lblTableFoodName);
		manager.add(tableFoodName);
		manager.add(lblStatus);
		manager.add(tableStatus);

		panel.add(manager);

		
		return panel;
	}

	@Override
	public JSplitPane managerTableFoodArea() {
		JSplitPane managerArea = new JSplitPane();
		
		managerArea .setLeftComponent(managerTableFoodAreaLeft());
		managerArea .setRightComponent(managerTableFoodAreaRight());

		managerArea .setDividerLocation(500);
		
		return managerArea ;
	}

	@Override
	public JPanel managerUserAreaLeft() {
		JPanel panel = new JPanel();
		JPanel managerButton = new JPanel();
		JPanel managerTable = new JPanel(new GridLayout(1, 1, 5, 5));
		
		//quản lí panel trên trái
		btnAddUser = new JButton("Thêm");
		btnAddUser.setPreferredSize(new Dimension(100, 50));
		btnAddUser.addActionListener(this);
		
		btnUpdateUser = new JButton("Sửa");
		btnUpdateUser.addActionListener(this);
		btnUpdateUser.setPreferredSize(new Dimension(100, 50));
		
		btnDeleteUser = new JButton("Xóa");
		btnDeleteUser.addActionListener(this);
		btnDeleteUser.setPreferredSize(new Dimension(100, 50));
		
		btnViewUser = new JButton("Xem");
		btnViewUser.addActionListener(this);
		btnViewUser.setPreferredSize(new Dimension(100, 50));
		
		tableUser = new JTable(model);
		tableUser.addMouseListener(this);
		tableUser.setPreferredSize(new Dimension(450, 800));
		
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		scroll = new JScrollPane(tableUser);
		scroll.setPreferredSize(new Dimension(450, 340));
		
		loadDatabaseUser();

		managerButton.add(btnAddUser);
		managerButton.add(btnUpdateUser);
		managerButton.add(btnDeleteUser);
		managerButton.add(btnViewUser);
		managerTable.add(scroll, tableUser);
		
		panel.add(managerButton);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerUserAreaRight() {
		JPanel panel = new JPanel();
		
		JPanel manager = new JPanel(new GridLayout(8, 1, 5, 5));

		//Giao diện phía trái và giữa
		lblUsername = new JLabel("Tên tài khoản");
		username = new JTextField(30);
		lbltype = new JLabel("Loại tài khoản");
		type = new JTextField(30);
		btnUpdatePassword = new JButton("Đặt lại mật khẩu");
		btnUpdatePassword.addActionListener(this);
		//Space
		JLabel space = new JLabel("");
		JLabel lblAdvertise = new JLabel("-----------------------Mật khẩu mặc định là: 123456---------------------");
		manager.add(lblUsername);
		manager.add(username);
		manager.add(lbltype);
		manager.add(type);
		manager.add(space);
		manager.add(btnUpdatePassword);
		manager.add(lblAdvertise);
		
		panel.add(manager);

		
		return panel;
	}

	@Override
	public JSplitPane managerUserArea() {
		JSplitPane manager = new JSplitPane();
		
		manager.setLeftComponent(managerUserAreaLeft());
		manager.setRightComponent(managerUserAreaRight());

		manager.setDividerLocation(500);
		
		return manager;
	}

	@Override
	public void loadDatabaseFood() {
		FoodDao kn = new FoodDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllFood();
			String[] arr = {"Mã sản phẩm","Tên sản phẩm", "Loại sản phẩm", "Giá"};
			model = new DefaultTableModel(arr, 0);
			while(rs.next()) {
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString("idFood"));
				vc.add(rs.getString("FoodName"));
				vc.add(rs.getString("idFoodCategory"));
				vc.add(rs.getString("price"));
				
				model.addRow(vc);
			}
			
			tableFood.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}	
	}

	@Override
	public void loadDatabaseFoodCategory() {
		FoodCategoryDao kn = new FoodCategoryDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllFoodCategory();
			String[] arr = {"Mã loại","Tên loại"};
			model = new DefaultTableModel(arr, 0);
			while(rs.next()) {
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString("idFoodCategory"));
				vc.add(rs.getString("FoodCategoryName"));
				
				model.addRow(vc);
			}
			
			tableFoodCategory.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
		
	}

	@Override
	public void loadDatabaseTableFood() {
		TableFoodDao kn = new TableFoodDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllTableFood();
			String[] arr = {"Mã bàn","Tên bàn", "Trạng thái bàn"};
			model = new DefaultTableModel(arr, 0);
			while(rs.next()) {
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString("idTableFood"));
				vc.add(rs.getString("tableName"));
				vc.add(rs.getString("tableStatus"));
				
				model.addRow(vc);
			}
			
			table.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
	}

	@Override
	public void loadDatabaseUser() {
		UserDao kn = new UserDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllUser();
			String[] arr = {"Tài khoản","Loại tài khoản"};
			model = new DefaultTableModel(arr, 0);
			while(rs.next()) {
				Vector<String> vc = new Vector<String>();
				vc.add(rs.getString("username"));
				vc.add(rs.getString("type"));
				
				model.addRow(vc);
			}
			
			tableUser.setModel(model);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAddTable) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.insertTableFood(Integer.parseInt(idTableFood.getText()), Integer.parseInt(tableFoodName.getText()), tableStatus.getText());
				loadDatabaseTableFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
			
		} if(e.getSource() == btnUpdateTable) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.updateTableFood(Integer.parseInt(idTableFood.getText()), Integer.parseInt(tableFoodName.getText()), tableStatus.getText());
				loadDatabaseTableFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		} if(e.getSource() == btnDeleteTable) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.deleteTableFood(Integer.parseInt(idTableFood.getText()));
				loadDatabaseTableFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Xóa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
			
		} if(e.getSource()==btnAddFood) {
			FoodDao kn = new FoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.insertFood(Integer.parseInt(idFood.getText()), foodName.getText(),Integer.parseInt(idFoodCategory_food.getText()), Double.parseDouble(price.getText()));
				loadDatabaseFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}	
		} if(e.getSource() == btnUpdateFood) {
			FoodDao kn = new FoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.updateFood(Integer.parseInt(idFood.getText()), foodName.getText(),Integer.parseInt(idFoodCategory_food.getText()), Double.parseDouble(price.getText()));
				loadDatabaseFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		} if(e.getSource() == btnDeleteFood) {
			FoodDao kn = new FoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.deleteFood(Integer.parseInt(idFood.getText()));
				loadDatabaseFood();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Xóa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}	
		} if(e.getSource() == btnSearchFood) {
			//Xóa các record trong tableFood
			model.getDataVector().removeAllElements();
			model.fireTableDataChanged();
			FoodDao kn = new FoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				rs = kn.searchFood(food.getText());
				String[] arr = {"Mã sản phẩm","Tên sản phẩm", "Loại sản phẩm", "Giá"};
				model = new DefaultTableModel(arr, 0);
				while(rs.next()) {
					Vector<String> vc = new Vector<String>();
					vc.add(rs.getString("idFood"));
					vc.add(rs.getString("FoodName"));
					vc.add(rs.getString("idFoodCategory"));
					vc.add(rs.getString("price"));
					
					model.addRow(vc);
				}
				tableFood.setModel(model);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Tìm kiếm thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeResultSet(rs);
				con.closeStatement(st);
			}			
		} if(e.getSource()==btnAddFoodCategory) {
			FoodCategoryDao kn = new FoodCategoryDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.insertFoodCategory(Integer.parseInt(idFoodCategory.getText()), foodCategoryName.getText());
				loadDatabaseFoodCategory();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
			
		} if(e.getSource() == btnUpdateFoodCategory) {
			FoodCategoryDao kn = new FoodCategoryDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.updateFoodCategory(Integer.parseInt(idFoodCategory.getText()), foodCategoryName.getText());
				loadDatabaseFoodCategory();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		} if(e.getSource() == btnDeleteFoodCategory) {
			FoodCategoryDao kn = new FoodCategoryDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.deleteFoodCategory(Integer.parseInt(idFoodCategory.getText()));
				loadDatabaseFoodCategory();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Xóa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
			
		} if(e.getSource()==btnAddUser) {
			UserDao kn = new UserDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.insertUser(username.getText(), Integer.parseInt(type.getText()));
				loadDatabaseUser();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Thêm thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
			
		} if(e.getSource() == btnUpdateUser) {
			UserDao kn = new UserDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.updateUser(username.getText(), Integer.parseInt(type.getText()));
				loadDatabaseUser();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Sửa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		} if(e.getSource() == btnDeleteUser) {
			UserDao kn = new UserDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.deleteUser(username.getText());
				loadDatabaseUser();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Xóa thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		} if(e.getSource() == btnUpdatePassword) {
			UserDao kn = new UserDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.resetPassword(username.getText());
				loadDatabaseUser();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Đặt lại mật khẩu thất bại");
			} finally {
				con.closeConnection(conn);
				con.closeStatement(st);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//Sự kiên click table
		int i = table.getSelectedRow();
		idTableFood.setText(table.getValueAt(i, 0).toString());
		tableFoodName.setText(table.getValueAt(i, 1).toString());
		tableStatus.setText(table.getValueAt(i, 2).toString());
		
//		//Sự kiên click Food
//		int n = tableFood.getSelectedRow();
//		idFood.setText(tableFood.getValueAt(n, 0).toString());
//		foodName.setText(tableFood.getValueAt(n, 1).toString());
//		idFoodCategory_food.setText(tableFood.getValueAt(n, 2).toString());
//		price.setText(tableFood.getValueAt(n, 3).toString());
		
		//Sự kiên click FoodCategory
//		int a = tableFoodCategory.getSelectedRow();
//		idFoodCategory.setText(tableFoodCategory.getValueAt(a, 0).toString());
//		foodCategoryName.setText(tableFoodCategory.getValueAt(a, 1).toString());
//		}
		
//		//Sự kiên click user
//		int b = tableUser.getSelectedRow();
//		username.setText(tableUser.getValueAt(b, 0).toString());
//		password.setText(tableUser.getValueAt(b, 1).toString());
//		displayName.setText(tableUser.getValueAt(b, 2).toString());
//		type.setText(tableUser.getValueAt(b, 3).toString());		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
