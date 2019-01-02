package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import common.database.ConnectionUntil;
import model.dao.FormManagerMainDao;
import model.dao.TableFoodDao;
import model.dao.UserDao;


public class FormManagerMainView extends JFrame implements IFormManagerMainView, ActionListener {

	//MenuBar
	private JMenu mnAdmin;
	private JMenu mnThongTin;
	private JMenuItem mnThongTinCaNhan;
	private JMenuItem mnDoiMatKhau;
	private JMenuItem mnDatLaiMatKhau;
	private JMenuItem mnDangXuat;
	private JMenuItem mnFileExit;
	private JMenuItem mnAdminstrator;
	
	//managerMainViewLeft
	private JButton btnTable;
	//
	private JButton btnAddFood;
	private JButton btnChangeTable;
	private JButton btnSale;
	private JButton btnPay;
	private JTable tableBill;
	private JTextField txtTotalMoney;
	private JComboBox cbbListFoodCategory;
	private JComboBox cbbListFood;
	
	private FormManagerMainView formManagerMain;
	//SQL
	private ResultSet rs;
	private Statement st;
	private DefaultTableModel model;
	private Connection conn;
	
	//DataCombobox
	private String idFoodCategory_Food;
	private String idFoodCategory;
	
	@Override
	public void display() {
		add(managerMainViewArea());
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(1200, 800);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Tiêu đề
		setTitle("Quản lí quán cà phê");
		menuBar();
		setResizable(false);
		
	}

	@Override
	public void menuBar() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		//Khởi tạo 2 menu cha là admin và thông tin cá nhân
		mnAdmin = new JMenu("Admin");
		mnThongTin = new JMenu("Thông tin cá nhân");
		
		//Thêm 2 menu cha vào menu chính của Frame
		menubar.add(mnAdmin);
		menubar.add(mnThongTin);
		
		//Khởi tạo 3 menu con là thông tin cá nhân, đăng xuất và thoát.
		mnThongTinCaNhan = new JMenuItem("Thông tin cá nhân");
		mnThongTinCaNhan.addActionListener(this);
		mnDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mnDoiMatKhau.addActionListener(this);
		mnDatLaiMatKhau = new JMenuItem("Đặt lại mật khẩu");
		mnDatLaiMatKhau.addActionListener(this);
		mnDangXuat = new JMenuItem("Đăng xuất");
		mnDangXuat.addActionListener(this);
		mnFileExit = new JMenuItem("Exit");
		mnFileExit.addActionListener(this);
		
		//khởi tạo menu con là adminstrator cho menu
		mnAdminstrator = new JMenuItem("Adminstrator");
		mnAdminstrator.addActionListener(this);
		
		//thêm 3 menu con vào menu cha là Thông tin cá nhân
		mnThongTin.add(mnThongTinCaNhan);
		mnThongTin.add(mnDoiMatKhau);
		mnThongTin.add(mnDatLaiMatKhau);
		mnThongTin.add(mnDangXuat);
		mnThongTin.addSeparator();
		mnThongTin.add(mnFileExit);
		
		//them 1 menu con vào menu cha là admin
		mnAdmin.add(mnAdminstrator);
		
	}

	@Override
	public JPanel managerMainViewLeft() {
		JPanel panel = new JPanel();
		
		ConnectionUntil con = new ConnectionUntil();
		FormManagerMainDao kn = new FormManagerMainDao();
		try {
			rs = kn.getAllTable();
			while(rs.next()) {
				String table_Name = rs.getString("tableName");
				String table_Status = rs.getString("tableStatus");
				
				btnTable = new JButton();
				btnTable.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						FormManagerMainDao kn = new FormManagerMainDao();
						ConnectionUntil con = new ConnectionUntil();
						try {
							rs = kn.getAllTableBill(table_Name);
							String[] arr = {"Tên món","Số lượng", "Đơn giá", "Thành tiền"};
							model = new DefaultTableModel(arr, 0);
							double TotalMoney = 0;
							while(rs.next()) {
								Vector<String> vec = new Vector<String>();
								// lấy dữ liệu theo tên(ứng với tên)
								vec.add(rs.getString("foodName"));
								vec.add(rs.getString("count"));
								vec.add(rs.getString("price"));
								vec.add(rs.getString("intoMoney"));
								TotalMoney += rs.getDouble("intoMoney");
								
								model.addRow(vec);
							}
							String s = String.valueOf(TotalMoney);
							txtTotalMoney.setText(s + " VNĐ");
							// kết nối jtable với tableModel
							tableBill.setModel(model);
						} catch (SQLException ex) {
							// TODO: handle exception
						} finally {
							con.closeConnection(conn);
							con.closeResultSet(rs);
							con.closeStatement(st);
						}
						
					}
					
				});
				btnTable.setPreferredSize(new Dimension(150, 60));
				btnTable.setText("Bàn số " + table_Name + " " + table_Status );
				
				panel.add(btnTable);
				
				if(btnTable.getText().equals("Bàn số " + table_Name + " " + "Trống")) {
					btnTable.setBackground(Color.GREEN);
				}else {
					btnTable.setBackground(Color.PINK);
				}
			} 
		} catch (Exception ex) {
			System.out.println(ex);
		}
	
		return panel;
	}

	@Override
	public JPanel managerMainViewRight() {
		JPanel panel = new JPanel();
		
		JPanel managerCbb = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel managerAddFood = new JPanel();
		JPanel managerTableBill = new JPanel();
		JPanel managerChange = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel managerSale = new JPanel(new GridLayout(2, 1, 5, 5));
		JPanel managerTotalMoney = new JPanel();
		
		cbbListFoodCategory = new JComboBox();
		cbbListFoodCategory.setPreferredSize(new Dimension(300,20));
		cbbListFoodCategory.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				FormManagerMainDao kn = new FormManagerMainDao();
				ConnectionUntil con = new ConnectionUntil();
				try {
					rs = kn.getAllListFoodCategory();
					while(rs.next()) {
						cbbListFoodCategory.addItem(rs.getString("foodCategoryName"));
					}
				} catch (SQLException ex) {
					// TODO: handle exception
				} finally {
					con.closeConnection(conn);
					con.closeResultSet(rs);
					con.closeStatement(st);
				}
				
			}
		}); 
		

		cbbListFood = new JComboBox();
//		cbbListFood.addPropertyChangeListener(new PropertyChangeListener() {
//			
//			@Override
//			public void propertyChange(PropertyChangeEvent evt) {
//				FormManagerMainDao kn = new FormManagerMainDao();
//				ConnectionUntil con = new ConnectionUntil();
//				try {
////					String idFoodCategory = rs.getString("foodCategory");
////					rs = kn.getAllId_FoodCategory_Food(Integer.parseInt(idFoodCategory));
//					while(rs.next()) {
//						cbbListFood.addItem(rs.getString("foodName"));
//					}
//				} catch (SQLException ex) {
//					// TODO: handle exception
//				} finally {
//					con.closeConnection(conn);
//					con.closeResultSet(rs);
//					con.closeStatement(st);
//				}
//				
//			}
//		});
		cbbListFood.setPreferredSize(new Dimension(300,20));
		
		btnAddFood = new JButton("Thêm món");
		btnAddFood.addActionListener(this);
		btnAddFood.setPreferredSize(new Dimension(100, 50));
		
//		loadDataListFoodCategory();
//		loadDataListFood();
		
		getCombobox();
				
		SpinnerModel countFood = new SpinnerNumberModel(0, //giá trị ban đầu
	            0, //min
	            1000, //max
	            1);//step
		
		JSpinner count = new JSpinner(countFood);
		count.setPreferredSize(new Dimension(100, 30));
		
		tableBill = new JTable(model);
		tableBill.setPreferredSize(new Dimension(510, 800));
		
		// Đưa jtable vào trong thanh cuộn (khi dữ liệu quá nhiều dòng sẽ có thanh cuộn ngang và doc để xem dữ liệu)
		JScrollPane scroll =  new JScrollPane(tableBill);
		scroll.setPreferredSize(new Dimension(510, 580));

		btnChangeTable = new JButton("Chuyển bàn");
		btnChangeTable.addActionListener(this);
		
		btnSale = new JButton("Giảm giá");
		btnSale.addActionListener(this);
		
		txtTotalMoney = new JTextField(17);
		
		btnPay = new JButton("Thanh toán");
		btnPay.addActionListener(this);
		btnPay.setPreferredSize(new Dimension(100, 50));
		
		SpinnerModel countSale = new SpinnerNumberModel(0, //giá trị ban đầu
	            0, //min
	            30, //max
	            1);//step
		
		JSpinner sale = new JSpinner(countSale);
		sale.setPreferredSize(new Dimension(100, 30));
				
		JComboBox changeTable = new JComboBox();
		changeTable.setPreferredSize(new Dimension(50,30));
		
		managerCbb.add(cbbListFoodCategory);
		managerCbb.add(cbbListFood);
		managerAddFood.add(count);
		managerAddFood.add(btnAddFood);
		
		//Đưa thanh cuộn vào Frame (hiện thanh cuộn trên frame)
		managerTableBill.add(scroll, tableBill);
		managerChange.add(btnChangeTable);
		managerChange.add(changeTable);
		managerSale.add(btnSale);
		managerSale.add(sale);
		managerTotalMoney.add(txtTotalMoney);
		managerTotalMoney.add(btnPay);
	
		panel.add(managerCbb);
		panel.add(managerAddFood);
		panel.add(managerTableBill);
		panel.add(managerChange);
		panel.add(managerSale);
		panel.add(managerTotalMoney);
		
		return panel;
	}

	@Override
	public JSplitPane managerMainViewArea() {
		JSplitPane split = new JSplitPane();
		
		split.setRightComponent(managerMainViewRight());
		split.setLeftComponent(managerMainViewLeft());
		split.setDividerLocation(600);
		
		return split;
	}
	
	@Override
	public void loadDataListFoodCategory() {
		FormManagerMainDao kn = new FormManagerMainDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllListFoodCategory();
			while(rs.next()) {
				cbbListFoodCategory.addItem(rs.getString("foodCategoryName"));
				idFoodCategory = rs.getString("idFoodCategory");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
	}
	
	@Override
	public void loadDataListFood() {
		FormManagerMainDao kn = new FormManagerMainDao();
		ConnectionUntil con = new ConnectionUntil();
		try {
			rs = kn.getAllListFood();
			while(rs.next()) {
				cbbListFood.addItem(rs.getString("foodName"));
				idFoodCategory_Food = rs.getString("idFoodCategory");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
	}
	
	public void getCombobox() {
		idFoodCategory = idFoodCategory_Food;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mnAdminstrator) {
			FormAdminView admin = new FormAdminView();
			admin.display();
		}if(e.getSource() == mnThongTinCaNhan) {
			PersonalInfomationView pi = new PersonalInfomationView();
			pi.display();	
		}if(e.getSource() == mnDoiMatKhau) {
			ChangePasswordView cp = new ChangePasswordView();
			cp.display();
		}if(e.getSource() == mnDatLaiMatKhau) {
			ResetPasswordView rp = new ResetPasswordView();
			rp.display();
		}if(e.getSource() == mnDangXuat) {
			FormLoginView lg = new FormLoginView();
			lg.display();
			dispose();
		}if(e.getSource() == mnFileExit) {
			//icon mặc định, tiêu đề tùy chỉnh
			int n = JOptionPane.showConfirmDialog(formManagerMain, "Bạn thật sự muốn thoát ?", "Thông báo", JOptionPane.YES_NO_OPTION);
			//nếu n = 0 thì thoát chương trình ngươc lại n = 1 không thoát
			if(n!=1)
			//lệnh thoát chương trình
			System.exit(0);
		} 
			
	}

}
