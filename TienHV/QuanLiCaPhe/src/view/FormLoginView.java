package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import common.database.ConnectionUntil;
import model.dao.FormLoginDao;

public class FormLoginView extends JFrame implements IFormLoginView, ActionListener  {

	private JLabel lblTitle;
	private JLabel lblUsername;
	private JTextField username;
	private JLabel lblPassword;
	private JPasswordField password;
	private JLabel lblCheckBox;
	private JCheckBox checkBox;
	private JButton btnLogin;
	private JButton btnExit;
	private FormLoginView login;
	
	//Sql
	private ResultSet rs;
	private Statement st;
	private Connection conn;
	
	@Override
	public void display() {
		add(ManagerFormLogin());
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(500, 200);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Thêm hàm bảng điều khiển.
		//Tắt chế độ phóng to màn hình
		setResizable(false);
	}

	@Override
	public JPanel ManagerFormLogin() {
		JPanel panel = new JPanel();
		
		JPanel top = new JPanel();
		JPanel bot = new JPanel(new GridLayout(4, 2, 5, 5));
		
		lblTitle = new JLabel("Đăng nhập");
		lblUsername = new JLabel("Tài khoản");
		username = new JTextField(20);
		lblPassword = new  JLabel("Mật khẩu");
		password = new JPasswordField(20);
		lblCheckBox = new JLabel("Hiện mật khẩu");
		checkBox = new JCheckBox();
		checkBox.addActionListener(this);
		btnLogin = new JButton("Đăng nhập");
		btnLogin.addActionListener(this);
		btnExit = new JButton("Thoát");
		btnExit.addActionListener(this);
		
		top.add(lblTitle);
		bot.add(lblUsername);
		bot.add(username);
		bot.add(lblPassword);
		bot.add(password);
		bot.add(lblCheckBox);
		bot.add(checkBox);
		bot.add(btnLogin);
		bot.add(btnExit);
		panel.add(top);
		panel.add(bot);
		
		return panel;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnLogin) {
			if(username.getText().equals("") || password.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu");
			}else {//Ngược lại, tài khoản và mật khẩu nhập vào.
				FormLoginDao lg = new FormLoginDao();
				ConnectionUntil con = new ConnectionUntil();
				try {
					lg.checkLogin(username.getText(), password.getText());
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					FormManagerMainView mainView = new FormManagerMainView();
					mainView.display();
					dispose();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Đăng nhập thất bại");
					ex.printStackTrace();
				} finally {
					con.closeConnection(conn);
					con.closeResultSet(rs);
					con.closeStatement(st);
				}
				 
			}
		} if(e.getSource() == btnExit) {
			//icon mặc định, tiêu đề tùy chỉnh
			int n = JOptionPane.showConfirmDialog(login, "Bạn thật sự muốn thoát ?", "Thông báo", JOptionPane.YES_NO_OPTION);
			//nếu n = 0 thì thoát chương trình ngươc lại n = 1 không thoát
			if(n!=1)
			//lệnh thoát chương trình
			System.exit(0);
		 } if(checkBox.isSelected()) {
			lblCheckBox.setText("Ẩn mật khẩu");
			password.setEchoChar((char)0);
		 } else {
			lblCheckBox.setText("Hiện mật khẩu");
			password.setEchoChar('*');
		 }
	}
}
