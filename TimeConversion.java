import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.concurrent.TimeUnit;
import javax.swing.*;
import net.porteanit.sql.DbUtils;

public class main
{
	public static calss ex
	{
		public static int day = 0;
	}

	public static void main(String args[])
	{	
		login():
		//create();
	}
}


public static void login()
{
	JFrame f = new JFrame("Login");
	JLabel l1,l2;
	l1 = new JLabel("Username");
	l1.setBounds(30,15,100,30);

	l2 = new JLabel("Password");
	l2.setBounds(30,50,100,30);

	JTextField F_user = new JTextField();
	F_user.setBounds(110,15,200,30);

	JPasswordField F_pass = new JPasswordField();
	F_pass.setBounds(110,50,200,20);

	JButton login_but = new JButton("Login");
	login_but.setBounds(130,90,80,25);
	login_but.addActionListener(new ActionListener()
	{
		// Perform action
		public void actionPerformed(ActionEvent e)
		{
			String username = F_user.getText();
			String password = F_pass.getText();
			
			if(username.equals(" "))
			{
				JOptionPane.showMessegeDialog(null,"Please enter username");
			}
			else if(password.equals(" "))
			{

				JOptionPane.showMessageDialog(null,"Please enter Password");
			}
			else {
				Connectin connection = connect();

			}
		}
	})
	f.add(F_pass);
	f.add(login_but);
	f.add(F_user);
	f.add(l1);
	f.add(l2);

	f.setSize(400,180);
	f.setLayout(null);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
}

public static Connection connect()
{
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		// System.out.println("Loaded driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/mysql?
		user = root & password = Admin");
		// System.out.println("Connected to MySQL");
		return con;
	}
	catch (Exception ex)
	{
		ex.printStackTrace();
	}
	return null;
}
public static void user_menu(String UID)
{
	JFramen f = new JFrame("User Function");
	// f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JButton view_but = new JButton("View Books");
	view_but.setBounds(20,20,120,25);
	view_but.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame f = new JFrame("Books Available");
			//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			Connection connection = connect();
			String sql = "select * form books";
			try{
				Statement stmt = connection.creatStatement();
				stmt.executeUpdate("Use Library");
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				JTable book_list = new JTable();
		

                                JScrollPane scrollPane = new JScrollPane(book_list);
				f.add(scrollPane);
				f.setSize(800,400);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
			catch (SQLException e1){
					JOptionPane.showMessageDialog(null, e1);
			}
		}
	});
	JButton my_book = new JButton("My Books");
	my_book.setBounds(150,20,120,25);
	my_book.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			JFrame f = new JFrame("My Books");
			int UID_int = Interger.parseInt(UID);
			Connection connection = connect();
			String sql = "select distinct issued.*, books.bname,books.genre, book.price from issued,books"+" 			"where((issued.uid = UID_int + ") and (books.bid in (select bid from issued where issued.uid="+ 			UID_int;
			try{
				Statement stmt = connection.createStatement();
				//use database
				stmt.executeUpdate("Use Library");
				stmt = connection.createStatement();
				//store in array
				ArrayList books_list = new ArrayList();

				ResultSet rs = stmt.executeQuery(sql);
				JTable book_list = new JTable();
				book_list.setModel(DdUtils.resultSetToTableModel(rs));
				JScrollPane scrollPane = new JScrollPane(book_list);

				f.add(scrollPane);
				f.setSize(800,400);
				f.setVisible(true);
				f.setLocationRelativeTo(null);
			}
			catch (SQLException e1){
				JOptionPane.showMessageDialog(null, e1);
			}
		}
	});
	f.add(my_book);
	f.add(view_but);
	f.setSize(300,100);
	f.setLayout(null);
	f.setVisible(true);
	f.setLocationRelativeTo(null);
}


		       			

	
			