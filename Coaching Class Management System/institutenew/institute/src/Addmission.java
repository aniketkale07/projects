import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Addmission extends JFrame implements ActionListener {
	JLabel lrno, lsname, lsadd, lspno, lfno, lsem, ldate, ladm, lsgen, lconm, lcopd, lemail, lfee, lfee1;
	JTextField trno, tdate, tsname, tspno, tfno, tsem, tsadd, tcopd, temail, Fee1;
	JButton bnew, bsub, bcancel, bcle, bprint, delAdd, update;
	JPanel top;

	Font fo = new Font("verdana", Font.BOLD, 20);

	Connection con;
	Statement st;
	ResultSet rs;
	String test, test1;
	JComboBox gender, b_id, co_id, Fee;

	Thread datimeThread;
	Date date;
	GregorianCalendar calendar;
	String strDate;

	// Table
	DefaultTableModel model = new DefaultTableModel();
	JTable tabGrid = new JTable(model);
	JScrollPane scrlPane = new JScrollPane(tabGrid);

	Addmission() {

		setSize(630, 770);
		setLayout(null);
		setTitle("ADMISSION FORM");
		setResizable(true);
		setLocation(100, 60);
isOpaque();

		ladm = new JLabel("ADMISSION FORM");
		ladm.setBounds(230, 20, 380, 40);
		ladm.setFont(fo);
		ladm.setForeground(Color.red);
		add(ladm);
		/*
		 * top=new JPanel();
		 * top.setLayout(null);
		 * top.setBorder(BorderFactory.createEtchedBorder(1));
		 * top.setBounds(60,20,320,50);
		 * add(top);
		 */

		lfno = new JLabel("Form No :");
		lfno.setBounds(30, 80, 100, 30);
		tfno = new JTextField(30);
		tfno.setBounds(130, 80, 130, 25);
		tfno.setEnabled(false);

		ldate = new JLabel("Date :");
		ldate.setBounds(340, 80, 120, 25);
		tdate = new JTextField(15);
		tdate.setBounds(420, 80, 120, 25);

		lrno = new JLabel("Student Id :");
		lrno.setBounds(30, 120, 100, 30);
		trno = new JTextField();
		trno.setBounds(130, 120, 130, 25);

		lsname = new JLabel("Stud Name:");
		lsname.setBounds(30, 160, 90, 25);
		tsname = new JTextField(150);
		tsname.setBounds(130, 160, 410, 25);

		lemail = new JLabel("Email_Id:");
		lemail.setBounds(30, 195, 90, 25);
		temail = new JTextField(150);
		temail.setBounds(130, 195, 410, 25);

		lsadd = new JLabel("Stud Add:");
		lsadd.setBounds(30, 230, 90, 25);
		tsadd = new JTextField(150);
		tsadd.setBounds(130, 230, 410, 25);

		lsgen = new JLabel("Gender :");
		lsgen.setBounds(340, 270, 120, 25);

		gender = new JComboBox();
		gender.addItem("Male");
		gender.addItem("Female");
		gender.setBounds(430, 270, 110, 25);

		lspno = new JLabel("Contact No :");
		lspno.setBounds(30, 270, 100, 25);
		tspno = new JTextField(20);
		tspno.setBounds(130, 270, 115, 25);

		lconm = new JLabel("Course Name:");
		lconm.setBounds(30, 315, 110, 25);
		co_id = new JComboBox();
		co_id.setBounds(130, 315, 115, 25);

		lsem = new JLabel("Batch Time :");
		lsem.setBounds(340, 315, 120, 25);
		b_id = new JComboBox();
		b_id.setBounds(430, 315, 110, 25);

		lfee = new JLabel("Fee Type:");
		lfee.setBounds(30, 355, 110, 25);
		Fee = new JComboBox();
		Fee.addItem("One Time");
		Fee.addItem("Installment");
		Fee.setBounds(130, 355, 115, 25);

		lfee1 = new JLabel("Total Fee");
		lfee1.setBounds(350, 355, 110, 25);
		Fee1 = new JTextField();

		Fee1.setBounds(430, 355, 115, 25);

		// KeyListen kl = new KeyListen();
		co_id.addActionListener(this);
		Fee.addActionListener(this);

		bnew = new JButton("New");
		bnew.setBounds(60, 410, 90, 25);
		bnew.addActionListener(this);
		bsub = new JButton("Submit");
		bsub.setBounds(190, 410, 90, 25);
		bsub.addActionListener(this);
		bcle = new JButton("Clear");
		bcle.setBounds(310, 410, 90, 25);
		bcle.addActionListener(this);
		bcancel = new JButton("ALL");
		bcancel.addActionListener(this);
		bcancel.setBounds(430, 410, 90, 25);

		bprint = new JButton("Print");
		bprint.addActionListener(this);
		bprint.setBounds(380, 445, 90, 25);

		delAdd = new JButton("Delete");
		delAdd.setBounds(260, 445, 90, 25);
		delAdd.addActionListener(this);
		add(delAdd);

		update = new JButton("Update");
		update.setBounds(120,445,90,25);
		update.addActionListener(this);
		add(update);
		add(lfno);
		add(tfno);
		add(lrno);
		add(trno);
		add(ladm);
		add(ldate);
		add(tdate);
		add(lsname);
		add(tsname);
		add(lsadd);
		add(tsadd);
		add(lsgen);
		add(gender);
		add(lspno);
		add(tspno);
		add(lconm);
		add(co_id);
		add(b_id);
		add(lsem);
		add(bsub);
		add(bcle);
		add(bcancel);
		add(bnew);
		add(lemail);
		add(temail);
		add(lfee);
		add(Fee);

		add(lfee1);
		add(Fee1);
		add(bprint);

		tsname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// if (((Character.isDigit(c))))
				if (!((c >= 65 && c <= 90) || (c >= 97 && c <= 122))) {
					JOptionPane.showMessageDialog(null, "Please enter Alphabets");
					e.consume();
				}
			}
		});
		tspno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});

		// Table
		scrlPane.setBounds(0, 480, 627, 200);
		add(scrlPane);
		tabGrid.setFont(new Font("Verdana", 0, 13));

		model.addColumn("A_NO");

		model.addColumn("S_ID");
		model.addColumn("S_NAME");
		model.addColumn("E_ID");
		model.addColumn("S_Add");
		model.addColumn("S_PHONE");
		model.addColumn("S_GENDER");
		model.addColumn("C_Name");

		setVisible(true);

		tfno.setEnabled(false);
		trno.setEnabled(true);
		Fee1.setEnabled(false);
		/*
		 * tdate.setEnabled(false);
		 * tsname.setEnabled(false);
		 * tsadd.setEnabled(false);
		 * tspno.setEnabled(false);
		 * b_id.setEnabled(false);
		 * gender.setEnabled(false);
		 * co_id.setEnabled(false);
		 */

		date = new Date();
		calendar = new GregorianCalendar();
		calendar.setTime(date);
		strDate = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);
		tdate.setText(strDate);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");
			st = con.createStatement();

			rs = st.executeQuery("select co_name from Course");

			while (rs.next()) {
				test = rs.getString(1);
				co_id.addItem(test);
			}
			rs = st.executeQuery("select time from Batch");

			while (rs.next()) {
				test = rs.getString(1);
				b_id.addItem(test);
			}

		} catch (Exception e) {
			System.out.println("error" + e);
		}

	}

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == Fee) {
			try {

				Statement s11 = null;
				Class.forName("com.mysql.jdbc.Driver");
				Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");
				s11 = cnDriver.createStatement();

				String we = co_id.getSelectedItem().toString();

				ResultSet rs11 = s11.executeQuery("select * from Course where co_name='" + we + "'");
				String type = Fee.getSelectedItem().toString();
				// System.out.println(type);
				while (rs11.next()) {

					if (type.equals("One Time")) {
						Fee1.setText(rs11.getString("co_fee1"));
					} else {
						Fee1.setText(rs11.getString("co_fee"));

					}
				}
			} catch (Exception ex) {

				System.out.println("Error:" + ex);
			}
		}

		if (ae.getSource() == bprint) {
			PrintUtilities.printComponent(this);
		}

		if (ae.getSource() == bcle) {
			trno.setText("  ");
			tdate.setText(strDate);
			tsname.setText(" ");
			temail.setText(" ");
			tspno.setText(" ");
			tfno.setText(" ");
			tsadd.setText(" ");

		}
		if (ae.getSource() == bcancel) {
			int r = 0;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");
				Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery("SELECT * from Admission");

				while (rs.next()) {
					model.insertRow(r++, new Object[] { rs.getString(1), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9) });
				}

			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
			}
		}

		if (ae.getSource() == bsub) {

			String s3 = tspno.getText();
			String s7 = temail.getText();
			if (s3.length() != 10) {
				JOptionPane.showMessageDialog(this, "Check the Mobile Number", "ADMIN", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!s7.contains("@") || !s7.contains(".")) {
				JOptionPane.showMessageDialog(this, "Plz Enter Correct Email Id", "Insert", JOptionPane.ERROR_MESSAGE);
				return;
			}

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");

				PreparedStatement pst = con.prepareStatement("INSERT INTO Admission VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

				int fno1 = Integer.parseInt(tfno.getText());

				String tdate1 = tdate.getText();
				int trno1 = Integer.parseInt(trno.getText());
				String tsname1 = tsname.getText();
				String e1 = temail.getText();
				String tsadd1 = tsadd.getText();
				String g1 = gender.getSelectedItem().toString();
				String tspno1 = (tspno.getText());
				String c1 = co_id.getSelectedItem().toString();
				String b1 = b_id.getSelectedItem().toString();
				String fee = Fee.getSelectedItem().toString();


				pst.setInt(1, fno1);
				pst.setString(2, tdate1);
				pst.setInt(3, trno1);
				pst.setString(4, tsname1);
				pst.setString(5, e1);
				pst.setString(6, tsadd1);
				pst.setString(7, tspno1);
				pst.setString(8, g1);
				pst.setString(9, c1);
				pst.setString(10, b1);
				pst.setString(11, fee);

				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Your Record is Submitted");
				con.close();

				trno.setText("  ");
				tdate.setText(strDate);
				tsname.setText(" ");
				tspno.setText(" ");
				tfno.setText(" ");
				tsadd.setText(" ");
				temail.setText("  ");
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please Enter All Fields Correctely !");
			}

			catch (Exception ex) {
				System.out.println("Error Occured");
				System.out.println("Error:" + ex);
			}

		}

		if (ae.getSource() == delAdd) {
			String a = (lrno.getText());
			String s = "delete from Admission where stud_id =" + a;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");

				st = con.createStatement();
				JOptionPane.showConfirmDialog(null, "Delete Record");
				st.executeUpdate(s);
				JOptionPane.showMessageDialog(null, "Record Deleted Successfully");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}

			if (ae.getSource() == bnew) {
				Fee.setBackground(Color.CYAN);
			
				try {
					Fee.setBackground(Color.CYAN);
			
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");

					st = con.createStatement();

					rs = st.executeQuery("select max(Add_id) from Admission");
					int i = 1;

					while (rs.next()) {
						i = Integer.parseInt(rs.getString(1));
					}
					i++;
					tfno.setText("" + i);

					rs = st.executeQuery("select stu_id from Admission");
					int j = 1;

					while (rs.next()) {
						j = Integer.parseInt(rs.getString("stu_id"));
					}
					j++;
					trno.setText("" + j);

					// trno.setText("");
					tdate.setText(strDate);
					tsname.setText("");
					temail.setText("");
					tspno.setText("");
					tsadd.setText("");

					/*
					 * gender.setEnabled(true);
					 * 
					 * tdate.setEnabled(true);
					 * tsname.setEnabled(true);
					 * tspno.setEnabled(true);
					 * b_id.setEnabled(true);
					 * tsadd.setEnabled(true);
					 * co_id.setEnabled(true);
					 */

					rs.close();
				} catch (Exception e) {
					System.out.println("error" + e);
				}
			}
	/*		if(ae.getSource()==update){
				int i = Integer.parseInt(lrno.getText());

				String tsname1 = tsname.getText();
				String e1 = temail.getText();
				String tsadd1 = tsadd.getText();
				String g1 = gender.getSelectedItem().toString();
				String tspno1 = (tspno.getText());
				String fee = Fee.getSelectedItem().toString();

// update query 
				try {
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");
						st = con.createStatement();
						String sa = "update admission set stu_name=?,Email_id=?,stu_add=?,stu_phno=?,stu_gender=?,feetype=? where stu_id=" +i;
						java.sql.PreparedStatement st=con.prepareStatement(sa);
						st.setString(1, tsname1);
						st.setString(2, e1);
						st.setString(3, tsadd1);
						st.setString(4, tspno1);
						st.setString(5, g1);
						st.setString(6, fee);
		
						st.executeUpdate();
						JOptionPane.showMessageDialog(null, "updated");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}




			} */
		}
	}

	public static void main(String args[]) {
		new Addmission();
	}

}
