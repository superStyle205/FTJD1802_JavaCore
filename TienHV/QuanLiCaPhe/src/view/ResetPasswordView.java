package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import common.database.ConnectionUntil;
import model.dao.FormLoginDao;
import model.dao.IResetPassworDao;
import model.dao.ResetPasswordDao;

public class ResetPasswordView extends JFrame implements IResetPasswordView, ActionListener {
	
	private JLabel lblUsername;
	private JTextField username;
	private JLabel lblOldPassword;
	private JPasswordField password;
	private JLabel lblNewPassword;
	private JPasswordField txtNewPassword;
	private JLabel lblEnterANewPassword;
	private JPasswordField txtEnterANewPassword;
	private JButton btnResetPass;
	private JButton btnExit;
	
	//SQL
	private ResultSet rs;
	private Statement st;
	private Connection conn;
	
	@Override
	public void display() {
		add(formMain());
		//Hiển thị cửa số ra. Nếu false hiển thị mặc định 3 ổ cửa sổ X + -
		setVisible(true);
		//Chỉnh kích thước rộng dài của cửa sổ
		setSize(400, 300);
		//Hiển thị cửa sổ ở giữa màn hình. Nếu không null nó sẽ xuất hiện ở góc màn hình.
		setLocationRelativeTo(null);
		//Khi click vào X đỏ thì sẽ tắt cửa sổ. Nếu không có hàm này thì sẽ không tắt được.
		setDefaultCloseOperation(0);
		//Tiêu đề
		setTitle("Đổi mật khẩu");
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
		JPanel manager = new JPanel(new GridLayout(9, 1, 5, 5));
		JPanel managerButton = new JPanel(new GridLayout(1, 2, 5, 5));
		
		lblUsername = new JLabel("Tài khoản");
		username = new JTextField(30);
		lblOldPassword = new JLabel("Mật khẩu cũ");
		password = new JPasswordField(30);
		lblNewPassword = new JLabel("Mật khẩu mới");
		txtNewPassword = new JPasswordField(30);
		lblEnterANewPassword = new JLabel("Nhập lại mật khẩu mới");
		txtEnterANewPassword = new JPasswordField(30);
		
		JLabel lblSpace = new JLabel("");
		
		btnResetPass = new JButton("Đổi mật khẩu");
		btnResetPass.addActionListener(this);
		btnExit = new JButton("Thoát");
		btnExit.addActionListener(this);
		
		manager.add(lblUsername);
		manager.add(username);
		manager.add(lblOldPassword);
		manager.add(password);
		manager.add(lblNewPassword);
		manager.add(txtNewPassword);
		manager.add(lblEnterANewPassword);
		manager.add(txtEnterANewPassword);
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
			if(txtNewPassword.getText().equals("") || txtEnterANewPassword.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mật khẩu");
			}else {//Ngược lại, tài khoản và mật khẩu nhập vào.
				ResetPasswordDao kn= new ResetPasswordDao();
				ConnectionUntil con = new ConnectionUntil();
				try {
					if(txtNewPassword.getText().equals(txtEnterANewPassword.getText())) {
						kn.updatePassword(username.getText(), txtNewPassword.getText());
						JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
					}else {
						JOptionPane.showMessageDialog(null, "Mật khẩu nhập lại không chính xác");
					}	
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Đổi mật khẩu thất bại");
					ex.printStackTrace();
				} finally {
					con.closeConnection(conn);
					con.closeResultSet(rs);
					con.closeStatement(st);
				}
			}
		} if(e.getSource() == btnExit) {
			dispose();
		}
	}
}

		
