package testing;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import common.database.ConnectionUntil;
import model.dao.TableFoodDao;

public class GetAllDatabase extends JFrame implements ActionListener, MouseListener {
	private JTable table;
	private ResultSet rs;
	private Statement st;
	private DefaultTableModel model;
	private Connection conn;
	private JButton them;
	private JButton sua;
	private JButton xoa;
	private JTextField id;
	private JTextField name;
	private JTextField status;
	
	public void hienThi() {
		add(panel());
		setVisible(true);
		setSize(600, 400);
		
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public JPanel panel() {
		JPanel panel = new JPanel();
		
		them = new JButton("Thêm");
		them.addActionListener(this);
		sua = new JButton("Sửa");
		sua.addActionListener(this);
		xoa = new JButton("Xóa");
		xoa.addActionListener(this);
		table = new JTable(model);
		table.addMouseListener(this);
		table.setPreferredSize(new Dimension(500, 600));
		JScrollPane sc = new JScrollPane(table);
		sc.setPreferredSize(new Dimension(450, 200));
		id = new JTextField(30);
		name = new JTextField(30);
		status = new JTextField(30);
		loadData();
		panel.add(them);
		panel.add(sua);
		panel.add(xoa);
		panel.add(sc, table);
		panel.add(id);
		panel.add(name);
		panel.add(status);
		
		return panel;
	}
	
	private void loadData() {
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
			// TODO: handle exception
		} finally {
			con.closeConnection(conn);
			con.closeResultSet(rs);
			con.closeStatement(st);
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == them) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.insertTableFood(Integer.parseInt(id.getText()), Integer.parseInt(name.getText()), status.getText());
				loadData();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Thêm thắt bại");
			} finally {
				con.closeConnection(conn);
				con.closeResultSet(rs);
				con.closeStatement(st);
			}
			
		} if(e.getSource() == sua) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.updateTableFood(Integer.parseInt(id.getText()), Integer.parseInt(name.getText()), status.getText());
				loadData();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Sửa thắt bại");
			} finally {
				con.closeConnection(conn);
				con.closeResultSet(rs);
				con.closeStatement(st);
			}
		} if(e.getSource() == xoa) {
			TableFoodDao kn = new TableFoodDao();
			ConnectionUntil con = new ConnectionUntil();
			try {
				kn.deleteTableFood(Integer.parseInt(id.getText()));
				loadData();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Xóa thắt bại");
			} finally {
				con.closeConnection(conn);
				con.closeResultSet(rs);
				con.closeStatement(st);
			}
			
		}
		
	}

	public static void main(String[] args) {
		GetAllDatabase ts = new GetAllDatabase();
		ts.hienThi();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int i = table.getSelectedRow();
		id.setText(table.getValueAt(i, 0).toString());
		name.setText(table.getValueAt(i, 1).toString());
		status.setText(table.getValueAt(i, 2).toString());	
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
