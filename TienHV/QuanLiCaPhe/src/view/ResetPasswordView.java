package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.bean.Employees;
import model.bean.User;
import model.dao.EmployeesDao;
import model.dao.UserDao;

public class ResetPasswordView extends JFrame implements IResetPasswordView, ActionListener{
	private JLabel lblUsername;
	private JTextField username;
	private JLabel lblFullName;
	private JTextField fullName;
	private JLabel lblIdentityCard;
	private JTextField identityCard;
	private JButton btnResetPass;
	private JButton btnExit;
	
	@Override
	public void display() {
		add(formMain());
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(400, 250);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(0);
		//Tiêu đề
		setTitle("Đặt lại mật khẩu");
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
	public JPanel formMain() {
		JPanel panel = new JPanel();
		JPanel manager = new JPanel(new GridLayout(7, 1, 5, 5));
		JPanel managerButton = new JPanel(new GridLayout(1, 2, 5, 5));
		
		lblUsername = new JLabel("Tài khoản");
		username = new JTextField(30);
		lblFullName = new JLabel("Họ và tên");
		fullName = new JTextField(30);
		lblIdentityCard = new JLabel("CMND");
		identityCard = new JTextField(30);
		
		JLabel lblSpace = new JLabel(">--------------~~~~Mật khẩu mặc định là: 123456~~~~--------------<");
		
		btnResetPass = new JButton("Đặt lại mật khẩu");
		btnResetPass.addActionListener(this);
		btnExit = new JButton("Thoát");
		btnExit.addActionListener(this);
		
		manager.add(lblUsername);
		manager.add(username);
		manager.add(lblFullName);
		manager.add(fullName);
		manager.add(lblIdentityCard);
		manager.add(identityCard);
		manager.add(lblSpace);
		managerButton.add(btnResetPass);
		managerButton.add(btnExit);
		
		
		panel.add(manager);
		panel.add(managerButton);
		
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnResetPass) {
			UserDao userDao = new UserDao();
			EmployeesDao employeesDao = new EmployeesDao();
			List<User> listUser = userDao.getAllUser();
			List<Employees> listEmployees = employeesDao.getAllEmployees();
			for(User user : listUser) {
				for(Employees employees : listEmployees) {
					String identityCard_Employees = String.valueOf(employees.getIdentityCard());
					if(username.getText().equals(user.getUsername())&&fullName.getText().equals(employees.getFullName()) && identityCard.getText().equals(identityCard_Employees)) {
						userDao.resetPassword(user);
					}
				}	
			}
		} if(e.getSource() == btnExit) {
			dispose();
		}
	}
}
