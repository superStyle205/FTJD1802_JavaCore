package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import model.bean.Employees;
import model.dao.EmployeesDao;

public class PersonalInfomationView extends JFrame implements IPersonalInfomationView, ActionListener {
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblFullName;
	private JTextField txtFullName;
	private JLabel lblAge;
	private JTextField txtAge;
	private JLabel lblAddress;
	private JTextField txtAddress;
	private JLabel lblPhoneNumber;
	private JTextField txtPhoneNUmber;
	private JLabel lblHomeTown;
	private JTextField txtHomeTown;
	private JLabel lblIdentityCard;
	private JTextField txtIdentityCard;
	private JButton btnExit;

	@Override
	public void display() {
		add(displayInformation());
		displayText();
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(400, 580);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(0);
		//Tiêu đề
		setTitle("Thông tin cá nhân");
		//Thêm hàm bảng điều khiển.
		//add(createMainPanel());
		setResizable(false);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}

	@Override
	public JPanel displayInformation() {
		JPanel main = new JPanel();
		JPanel panel = new JPanel(new GridLayout(17, 1, 5, 5)); 
	    
		JLabel title = new JLabel("-------------------------------Thông tin cá nhân--------------------------------");
		lblUsername = new JLabel("Tài khoản");
		txtUsername = new JTextField(30);
	    txtUsername.setEditable(false);
	    lblFullName = new JLabel("Họ và tên");
	    txtFullName = new JTextField(30);	
	    txtFullName.setEditable(false);//Khóa textField lại, không cho chỉnh sửa.
	    lblAge = new JLabel("Tuổi");
	    txtAge = new JTextField(30);	 
	    txtAge.setEditable(false);
	    lblAddress = new JLabel("Địa chỉ");
	    txtAddress = new JTextField(30);
	    txtAddress.setEditable(false);
	    lblPhoneNumber = new JLabel("Số điện thoại");
	    txtPhoneNUmber = new JTextField(30);
	    txtPhoneNUmber.setEditable(false);
	    lblHomeTown = new JLabel("Quê quán");
	    txtHomeTown = new JTextField(30);
	    txtHomeTown.setEditable(false);
	    lblIdentityCard = new JLabel("Chứng minh nhân dân");
	    txtIdentityCard = new JTextField(30);
	    txtIdentityCard.setEditable(false);
	    btnExit = new JButton("Thoát");
	    btnExit.addActionListener(this);
	    
	    JLabel lblSpace = new JLabel("");
	    
	    
	    panel.add(title);
	    panel.add(lblUsername);
	    panel.add(txtUsername);
	    panel.add(lblFullName);
	    panel.add(txtFullName);
	    panel.add(lblAge);
	    panel.add(txtAge);
	    panel.add(lblAddress);
	    panel.add(txtAddress);
	    panel.add(lblPhoneNumber);
	    panel.add(txtPhoneNUmber);
	    panel.add(lblHomeTown);
	    panel.add(txtHomeTown);
	    panel.add(lblIdentityCard);
	    panel.add(txtIdentityCard);
	    panel.add(lblSpace);
	    panel.add(btnExit);
	        
	    main.add(panel);
	    return main;
		
	}
	
	@Override
	public void displayText() {
		FormLoginView lg = new FormLoginView();	
		EmployeesDao employeesDao = new EmployeesDao();
		List<Employees> listEmployees = employeesDao.getAllEmployees();
		for(Employees employees : listEmployees) {
			if(employees.getUsername().equals(lg.username.getText())&&employees.getDeleteValue() == 1) {
				txtUsername.setText(employees.getUsername());
				txtFullName.setText(employees.getFullName());
				txtAge.setText(String.valueOf(employees.getAge()));
				txtAddress.setText(employees.getAddress());
				txtPhoneNUmber.setText(String.valueOf(employees.getPhoneNumber()));
				txtHomeTown.setText(employees.getHomeTown());
				txtIdentityCard.setText(String.valueOf(employees.getIdentityCard()));
			}
		}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExit) {
			dispose();
		}
		
	}




	
}
