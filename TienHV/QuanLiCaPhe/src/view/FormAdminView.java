package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import model.bean.Employees;
import model.bean.Food;
import model.bean.FoodCategory;
import model.bean.TableFood;
import model.bean.User;
import model.dao.EmployeesDao;
import model.dao.FoodCategoryDao;
import model.dao.FoodDao;
import model.dao.TableFoodDao;
import model.dao.UserDao;


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
	private JButton btnCleanFood;
	private JTable tableFood;
	private JButton btnSearchFood;
	private JTextField searchFood;
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
	private JButton btnCleanFoodCategory;
	private JTable tableFoodCategory;
	private JLabel lblIdFoodCategory;
	private JLabel lblFoodCategoryName;
	private JTextField idFoodCategory;
	private JTextField foodCategoryName;

	//TableFood Area (Khu vực bàn ăn)
	private JButton btnAddTable;
	private JButton btnUpdateTable;
	private JButton btnDeleteTable;
	private JButton btnCleanTable;
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
	private JButton btnCleanUser;
	private JTable tableUser;
	private JLabel lblUsername;
	private JTextField username;
	private JLabel lblPassword;
	private JTextField password;
	private JLabel lblType;
	private JTextField type;
	
	//User Area (Khu vực thông tin nhân viên)
	private JButton btnAddEmployees;
	private JButton btnUpdateEmployees;
	private JButton btnDeleteEmployees;
	private JButton btnCleanEmployees;
	private JTable tableEmployees;
	private JLabel lblIdEmployees;
	private JTextField idEmployees;
	private JLabel lblUsername_employees;
	private JTextField username_employees;
	private JLabel lblFullName;
	private JTextField fullName;
	private JLabel lblAge;
	private JTextField age;
	private JLabel lblAddress;
	private JTextField address;
	private JLabel lblPhoneNumber;
	private JTextField phoneNumber;
	private JLabel lblHomeTown;
	private JTextField homeTown;
	private JLabel lblIdentityCard;
	private JTextField identityCard;

	//SQL
	private DefaultTableModel model;
	
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
		JSplitPane employees = managerEmployeesArea();
		
		myTabled.addTab("Quản lí doanh thu", revenue);
		myTabled.addTab("Quản lí sản phẩm", food);
		myTabled.addTab("Quản lí loại sản phẩm", foodCategory);
		myTabled.addTab("Quản lí bàn", tableFood);
		myTabled.addTab("Quản lí tài khoản", user);
		myTabled.addTab("Quản lí thông tin người dùng", employees);
		
	
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
		
		btnCleanFood = new JButton("Clean");
		btnCleanFood.addActionListener(this);
		btnCleanFood.setPreferredSize(new Dimension(100, 50));
		
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
		managerButton.add(btnCleanFood);
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
		searchFood = new JTextField(20);
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
		

		
		magagerSearch.add(searchFood);
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
		
		btnCleanFoodCategory = new JButton("Clean");
		btnCleanFoodCategory.addActionListener(this);
		btnCleanFoodCategory.setPreferredSize(new Dimension(100, 50));
		
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
		managerButton.add(btnCleanFoodCategory);
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
		
		btnCleanTable = new JButton("Clean");
		btnCleanTable.addActionListener(this);
		btnCleanTable.setPreferredSize(new Dimension(100, 50));
		
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
		managerButton.add(btnCleanTable);
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
		
		btnCleanUser = new JButton("Clean");
		btnCleanUser.addActionListener(this);
		btnCleanUser.setPreferredSize(new Dimension(100, 50));
		
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
		managerButton.add(btnCleanUser);
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
		lblPassword = new JLabel("Mật khẩu");
		password = new JTextField(30);
		lblType = new JLabel("Loại tài khoản");
		type = new JTextField(30);

		manager.add(lblUsername);
		manager.add(username);
		manager.add(lblPassword);
		manager.add(password);
		manager.add(lblType);
		manager.add(type);
		
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
	public JPanel managerEmployeesAreaLeft() {
		JPanel panel = new JPanel();
		JPanel managerButton = new JPanel();
		JPanel managerTable = new JPanel(new GridLayout(1, 1, 5, 5));
		
		//quản lí panel trên trái
		btnAddEmployees = new JButton("Thêm");
		btnAddEmployees.setPreferredSize(new Dimension(100, 50));
		btnAddEmployees.addActionListener(this);
		
		btnUpdateEmployees = new JButton("Sửa");
		btnUpdateEmployees.addActionListener(this);
		btnUpdateEmployees.setPreferredSize(new Dimension(100, 50));
		
		btnDeleteEmployees = new JButton("Xóa");
		btnDeleteEmployees.addActionListener(this);
		btnDeleteEmployees.setPreferredSize(new Dimension(100, 50));
		
		btnCleanEmployees = new JButton("Clean");
		btnCleanEmployees.addActionListener(this);
		btnCleanEmployees.setPreferredSize(new Dimension(100, 50));
		
		tableEmployees = new JTable(model);
		tableEmployees.addMouseListener(this);
		tableEmployees.setPreferredSize(new Dimension(530, 800));
		
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		scroll = new JScrollPane(tableEmployees);
		scroll.setPreferredSize(new Dimension(530, 340));
		
		loadDatabaseEmployees();

		managerButton.add(btnAddEmployees);
		managerButton.add(btnUpdateEmployees);
		managerButton.add(btnDeleteEmployees);
		managerButton.add(btnCleanEmployees);
		managerTable.add(scroll, tableEmployees);
		
		panel.add(managerButton);
		panel.add(managerTable);
		
		return panel;
	}

	@Override
	public JPanel managerEmployeesAreaRight() {
		JPanel panel = new JPanel();
		
		JPanel manager = new JPanel(new GridLayout(16, 1, 5, 5));

		//Giao diện phía trái và giữa
		lblIdEmployees = new JLabel("ID");
		idEmployees = new JTextField(25);
		lblUsername_employees = new JLabel("Tài khoản");
		username_employees = new JTextField(25);
		lblFullName = new JLabel("Họ và tên");
		fullName = new JTextField(25);
		lblAge = new JLabel("Tuổi");
		age = new JTextField(25);
		lblAddress = new JLabel("Địa chỉ");
		address = new JTextField(25);
		lblPhoneNumber = new JLabel("Số điện thoại");
		phoneNumber = new JTextField(25);
		lblHomeTown = new JLabel("Quê quán");
		homeTown = new JTextField(25);
		lblIdentityCard= new JLabel("Số chứng minh nhân dân");
		identityCard = new JTextField(25);

		manager.add(lblIdEmployees);
		manager.add(idEmployees);
		manager.add(lblUsername_employees);
		manager.add(username_employees);
		manager.add(lblFullName);
		manager.add(fullName);
		manager.add(lblAge);
		manager.add(age);
		manager.add(lblAddress);
		manager.add(address);
		manager.add(lblPhoneNumber);
		manager.add(phoneNumber);
		manager.add(lblHomeTown);
		manager.add(homeTown);
		manager.add(lblIdentityCard);
		manager.add(identityCard);

		panel.add(manager);

		
		return panel;
	}

	@Override
	public JSplitPane managerEmployeesArea() {
		JSplitPane manager = new JSplitPane();
		
		manager.setLeftComponent(managerEmployeesAreaLeft());
		manager.setRightComponent(managerEmployeesAreaRight());

		manager.setDividerLocation(550);
		
		return manager;
	}
	
	@Override
	public void loadDatabaseFood() {
		FoodDao foodDao = new FoodDao();
		List<Food> listFood = foodDao.getAllFood();
		String[] arr = {"Mã sản phẩm","Tên sản phẩm", "Loại sản phẩm", "Giá"};
		model = new DefaultTableModel(arr, 0);
		for(Food food : listFood) {
			if(food.getDeleteValue() == 1) {
				model.addRow(new String[] {String.valueOf(food.getIdFood()), 
						food.getFoodName(), 
						String.valueOf(food.getIdFoodCategory()), 
						String.valueOf(food.getPrice())});
			}
		}
		tableFood.setModel(model);
	}

	@Override
	public void loadDatabaseFoodCategory() {
		FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
		List<FoodCategory> listFoodCategory = foodCategoryDao.getAllFoodCategory();
		String[] arr = {"Mã loại sản phẩm","Tên loại sản phẩm"};
		model = new DefaultTableModel(arr, 0);
		for(FoodCategory foodCategory : listFoodCategory) {
			if(foodCategory.getDeleteValue() == 1) {
				model.addRow(new String[] {String.valueOf(foodCategory.getIdFoodCategory()), 
						foodCategory.getFoodCategoryName()});
			}
		}
		tableFoodCategory.setModel(model);
	}

	@Override
	public void loadDatabaseTableFood() {
		TableFoodDao tableFoodDao = new TableFoodDao();
		List<TableFood> listTableFood= tableFoodDao.getAllTableFood();
		String[] arr = {"Mã bàn", "Tên bàn","Trạng thái bàn"};
		model = new DefaultTableModel(arr, 0);
		for(TableFood tableFood : listTableFood) {
			if(tableFood.getDeleteValue() == 1) {
				model.addRow(new String[] {String.valueOf(tableFood.getIdTableFood()), 
						String.valueOf(tableFood.getTableName()),
						tableFood.getTableStatus()});
			}
		}
		table.setModel(model);
	}

	@Override
	public void loadDatabaseUser() {
		UserDao userDao = new UserDao();
		List<User> listUser = userDao.getAllUser();
		String[] arr = {"Username", "Password", "Loại tài khoản"};
		model = new DefaultTableModel(arr, 0);
		for(User user : listUser) {
			if(user.getDeleteValue() == 1) {
				model.addRow(new String[] {user.getUsername(), 
						user.getPassword(),
						String.valueOf(user.getType())});
			}
		}
		tableUser.setModel(model);
	}
	
	@Override
	public void loadDatabaseEmployees() {
		EmployeesDao employeesDao = new EmployeesDao();
		List<Employees> listEmployees = employeesDao.getAllEmployees();
		String[] arr = {"ID","Tài khoản", "Họ và tên", "Tuổi", "Địa chỉ", "Số điện thoại", "Quê quán", "CMND"};
		model = new DefaultTableModel(arr, 0);
		for(Employees employees : listEmployees) {
			if(employees.getDeleteValue() == 1) {
				model.addRow(new String[] {String.valueOf(employees.getIdEmployees()), 
						employees.getUsername(),
						employees.getFullName(),
						String.valueOf(employees.getAge()),
						employees.getAddress(),
						String.valueOf(employees.getPhoneNumber()),
						employees.getHomeTown(),
						String.valueOf(employees.getIdentityCard())});
			}
		}
		tableEmployees.setModel(model);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnAddTable) {
			int deleteValue = 0;
			TableFoodDao tableFoodDao = new TableFoodDao();	
			TableFood tableFood = new TableFood(Integer.parseInt(idTableFood.getText()), 
					Integer.parseInt(tableFoodName.getText()), 
					tableStatus.getText(), 
					deleteValue);	
			tableFoodDao.insertTableFood(tableFood);
			loadDatabaseTableFood();	
		} if(e.getSource() == btnUpdateTable) {
			int deleteValue = 0;
			TableFoodDao tableFoodDao = new TableFoodDao();	
			TableFood tableFood = new TableFood(Integer.parseInt(idTableFood.getText()), 
					Integer.parseInt(tableFoodName.getText()), 
					tableStatus.getText(), 
					deleteValue);	
			tableFoodDao.updateTableFood(tableFood);
			loadDatabaseTableFood();	
		} if(e.getSource() == btnDeleteTable) {
			int deleteValue = 0;
			TableFoodDao tableFoodDao = new TableFoodDao();	
			TableFood tableFood = new TableFood(Integer.parseInt(idTableFood.getText()), 
					Integer.parseInt(tableFoodName.getText()), 
					tableStatus.getText(), 
					deleteValue);	
			tableFoodDao.deleteTableFood(tableFood);
			loadDatabaseTableFood();	
		} if(e.getSource() == btnCleanTable) {
			idTableFood.setText("");
			tableFoodName.setText("");
			tableStatus.setText("");
		} if(e.getSource()==btnAddFood) {
			int deleteValue = 0;
			FoodDao foodDao = new FoodDao();	
			Food food = new Food(Integer.parseInt(idFood.getText()), 
					foodName.getText(), 
					Integer.parseInt(idFoodCategory_food.getText()), 
					Double.parseDouble(price.getText()),
					deleteValue);	
			foodDao.insertFood(food);
			loadDatabaseFood();	
		} if(e.getSource() == btnUpdateFood) {
			int deleteValue = 0;
			FoodDao foodDao = new FoodDao();
			Food food = new Food(Integer.parseInt(idFood.getText()), 
					foodName.getText(), 
					Integer.parseInt(idFoodCategory_food.getText()), 
					Double.parseDouble(price.getText()),
					deleteValue);	
			foodDao.updateFood(food);
			loadDatabaseFood();	
		} if(e.getSource() == btnDeleteFood) {
			int deleteValue = 0;
			FoodDao foodDao = new FoodDao();	
			Food food = new Food(Integer.parseInt(idFood.getText()), 
					foodName.getText(), 
					Integer.parseInt(idFoodCategory_food.getText()), 
					Double.parseDouble(price.getText()),
					deleteValue);	
			foodDao.deleteFood(food);
			loadDatabaseFood();	
		}  if(e.getSource() == btnCleanFood) {
			idFood.setText("");
			foodName.setText("");
			idFoodCategory_food.setText("");
			price.setText("");
		} if(e.getSource() == btnSearchFood) {
			FoodDao foodDao = new FoodDao();
			List<Food> listFood = foodDao.searchFood(searchFood.getText());
			String[] arr = {"Mã sản phẩm","Tên sản phẩm", "Loại sản phẩm", "Giá", "Giá trị xóa"};
			model = new DefaultTableModel(arr, 0);
			for(Food food : listFood) {
				if(food.getDeleteValue() == 1) {
					model.addRow(new String[] {String.valueOf(food.getIdFood()), 
							food.getFoodName(), 
							String.valueOf(food.getIdFoodCategory()), 
							String.valueOf(food.getPrice()),  
							String.valueOf(food.getDeleteValue())});
				}
			}
			tableFood.setModel(model);
		} if(e.getSource()==btnAddFoodCategory) {
			int deleteValue = 0;
			FoodCategoryDao foodCategoryDao = new FoodCategoryDao();	
			FoodCategory foodCategory = new FoodCategory(Integer.parseInt(idFoodCategory.getText()), 
					foodCategoryName.getText(), 
					deleteValue);	
			foodCategoryDao.insertFoodCategory(foodCategory);
			loadDatabaseFoodCategory();	
		} if(e.getSource() == btnUpdateFoodCategory) {
			int deleteValue = 0;
			FoodCategoryDao foodCategoryDao = new FoodCategoryDao();	
			FoodCategory foodCategory = new FoodCategory(Integer.parseInt(idFoodCategory.getText()), 
					foodCategoryName.getText(), 
					deleteValue);	
			foodCategoryDao.updateFoodCategory(foodCategory);
			loadDatabaseFoodCategory();
		} if(e.getSource() == btnDeleteFoodCategory) {
			int deleteValue = 0;
			FoodCategoryDao foodCategoryDao = new FoodCategoryDao();	
			FoodCategory foodCategory = new FoodCategory(Integer.parseInt(idFoodCategory.getText()), 
					foodCategoryName.getText(), 
					deleteValue);	
			foodCategoryDao.deleteFoodCategory(foodCategory);
			loadDatabaseFoodCategory();
		}  if(e.getSource() == btnCleanFoodCategory) {
			idFoodCategory.setText("");
			foodCategoryName.setText("");
		} if(e.getSource()==btnAddUser) {
			int deleteValue = 0;
			UserDao userDao = new UserDao();	
			User user = new User(username.getText(), 
					password.getText(), 
					Integer.parseInt(type.getText()),
					deleteValue);	
			userDao.insertUser(user);
			loadDatabaseUser();	
		} if(e.getSource() == btnUpdateUser) {
		
		} if(e.getSource() == btnDeleteUser) {
			int deleteValue = 0;
			UserDao userDao = new UserDao();	
			User user = new User(username.getText(), 
					password.getText(), 
					Integer.parseInt(type.getText()),
					deleteValue);	
			userDao.deleteUser(user);
			loadDatabaseUser();	
		}  if(e.getSource() == btnCleanUser) {
			username.setText("");
			password.setText("");
			type.setText("");
		} if(e.getSource() == btnAddEmployees) {
			int deleteValue = 0;
			EmployeesDao employeesDao = new EmployeesDao();	
			Employees employees = new Employees(Integer.parseInt(idEmployees.getText()),
					username_employees.getText(),
					fullName.getText(), 
					Integer.parseInt(age.getText()),
					address.getText(),
					Integer.parseInt(phoneNumber.getText()),
					homeTown.getText(),
					Integer.parseInt(identityCard.getText()),
					deleteValue);	
			employeesDao.insertEmployees(employees);
			loadDatabaseEmployees();
		} if(e.getSource() == btnUpdateEmployees) {
			int deleteValue = 0;
			EmployeesDao employeesDao = new EmployeesDao();	
			Employees employees = new Employees(Integer.parseInt(idEmployees.getText()),
					username_employees.getText(),
					fullName.getText(), 
					Integer.parseInt(age.getText()),
					address.getText(),
					Integer.parseInt(phoneNumber.getText()),
					homeTown.getText(),
					Integer.parseInt(identityCard.getText()),
					deleteValue);	
			employeesDao.updateEmployees(employees);
			loadDatabaseEmployees();
		} if(e.getSource() == btnDeleteEmployees) {
			int deleteValue = 0;
			EmployeesDao employeesDao = new EmployeesDao();	
			Employees employees = new Employees(Integer.parseInt(idEmployees.getText()),
					username_employees.getText(),
					fullName.getText(), 
					Integer.parseInt(age.getText()),
					address.getText(),
					Integer.parseInt(phoneNumber.getText()),
					homeTown.getText(),
					Integer.parseInt(identityCard.getText()),
					deleteValue);	
			employeesDao.deleteEmployees(employees);
			loadDatabaseEmployees();
		} if(e.getSource() == btnCleanEmployees) {
			idEmployees.setText("");
			username_employees.setText("");
			fullName.setText("");
			age.setText("");
			address.setText("");
			phoneNumber.setText("");
			homeTown.setText("");
			identityCard.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		//Sự kiên click table
//		int i = table.getSelectedRow();
//		idTableFood.setText(table.getValueAt(i, 0).toString());
//		tableFoodName.setText(table.getValueAt(i, 1).toString());
//		tableStatus.setText(table.getValueAt(i, 2).toString());
		
		//Sự kiên click Food
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
		
		//Sự kiên click user
		int b = tableUser.getSelectedRow();
		username.setText(tableUser.getValueAt(b, 0).toString());
		password.setText(tableUser.getValueAt(b, 1).toString());
		type.setText(tableUser.getValueAt(b, 2).toString());
		
//		//Sự kiện click employees
//		int b = tableEmployees.getSelectedRow();
//		idEmployees.setText(tableEmployees.getValueAt(b, 0).toString());
//		username_employees.setText(tableEmployees.getValueAt(b, 1).toString());
//		fullName.setText(tableEmployees.getValueAt(b, 2).toString());
//		age.setText(tableEmployees.getValueAt(b, 3).toString());
//		address.setText(tableEmployees.getValueAt(b, 4).toString());
//		phoneNumber.setText(tableEmployees.getValueAt(b, 5).toString());
//		homeTown.setText(tableEmployees.getValueAt(b, 6).toString());
//		identityCard.setText(tableEmployees.getValueAt(b, 7).toString());
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
