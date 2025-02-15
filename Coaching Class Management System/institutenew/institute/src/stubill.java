import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

class stubill extends JFrame implements ActionListener {

	JLabel lrno, lsname, lsadd, lFeet, ldate, ladm, lbno, lPfee, lspno, lconm, lsgen, lfno, lcof, lemail;
	JTextField tdate, tsname, tFeet, tsadd, tconm, tbno, tPfee, trno, tspno, tfno, tgen, tcof, temail;
	JButton bnew, bprint, ball, bcle, bopen, bprint1;
	// JComboBox gender;

	Font fo = new Font("vERDANA", Font.BOLD, 20);
	Connection con;
	Statement st;
	ResultSet rs;
	String test, test1;

	Thread datimeThread;
	Date date;
	GregorianCalendar calendar;
	String strDate;

	// Table
	DefaultTableModel model = new DefaultTableModel();
	JTable tabGrid = new JTable(model);
	JScrollPane scrlPane = new JScrollPane(tabGrid);

	stubill() {
		setSize(590, 680);

		setLayout(null);
		setTitle("STUDENT RECEIPT");
		setResizable(true);
		setLocation(300, 160);

		ladm = new JLabel("RECEIPT");
		ladm.setFont(fo);
		ladm.setBounds(250, 30, 250, 50);
		ladm.setForeground(Color.red);

		lbno = new JLabel("RECEIPT No :");
		lbno.setBounds(30, 90, 100, 25);
		tbno = new JTextField(5);
		tbno.setBounds(130, 90, 100, 25);

		ldate = new JLabel("Date :");
		ldate.setBounds(320, 90, 45, 25);
		tdate = new JTextField(15);
		tdate.setBounds(370, 90, 130, 25);

		lrno = new JLabel("Student Id :");
		lrno.setBounds(30, 130, 100, 25);
		trno = new JTextField();
		trno.setBounds(130, 130, 100, 25);

		lfno = new JLabel("Form No :");
		lfno.setBounds(300, 130, 70, 25);
		tfno = new JTextField(15);
		tfno.setBounds(370, 130, 130, 25);

		lsname = new JLabel("Stud Name:");
		lsname.setBounds(30, 170, 90, 25);
		tsname = new JTextField(150);
		tsname.setBounds(130, 170, 370, 25);

		lemail = new JLabel("Email_Id:");
		lemail.setBounds(30, 210, 90, 25);
		temail = new JTextField(150);
		temail.setBounds(130, 210, 370, 25);

		lsadd = new JLabel("Stud Add:");
		lsadd.setBounds(30, 245, 90, 25);
		tsadd = new JTextField(150);
		tsadd.setBounds(130, 245, 370, 25);

		lsgen = new JLabel("Gender :");
		lsgen.setBounds(340, 280, 90, 25);

		tgen = new JTextField();
		tgen.setBounds(420, 280, 80, 25);

		lspno = new JLabel("Contact No :");
		lspno.setBounds(30, 280, 100, 25);
		tspno = new JTextField(20);
		tspno.setBounds(130, 280, 100, 25);

		lFeet = new JLabel("Fee Type:");
		lFeet.setBounds(340, 315, 105, 25);
		tFeet = new JTextField(30);
		tFeet.setBounds(420, 315, 80, 25);

		lconm = new JLabel("Course Name:");
		lconm.setBounds(30, 315, 100, 25);
		tconm = new JTextField(30);
		tconm.setBounds(130, 315, 100, 25);

		lcof = new JLabel("Course Fees :");
		lcof.setBounds(30, 355, 100, 25);
		tcof = new JTextField(15);
		tcof.setBounds(130, 355, 100, 25);

		lPfee = new JLabel("Paid Fee :");
		lPfee.setBounds(340, 355, 130, 25);
		tPfee = new JTextField(15);
		tPfee.setBounds(420, 355, 80, 25);

		bnew = new JButton("New");
		bnew.setBounds(25, 400, 90, 25);
		bnew.addActionListener(this);
		bopen = new JButton("Open");
		bopen.setBounds(135, 400, 90, 25);
		bopen.addActionListener(this);
		bcle = new JButton("Clear");
		bcle.setBounds(245, 400, 90, 25);
		bcle.addActionListener(this);
		bprint = new JButton("Submit");
		bprint.setBounds(355, 400, 90, 25);
		bprint.addActionListener(this);
		bprint = new JButton("Submit");
		bprint.setBounds(355, 400, 90, 25);
		bprint.addActionListener(this);

		ball = new JButton("ALL");
		ball.setBounds(465, 400, 90, 25);
		ball.addActionListener(this);

		bprint1 = new JButton("Print");
		bprint1.setBounds(575, 400, 90, 25);
		bprint1.addActionListener(this);

		add(lbno);
		add(tbno);
		add(lrno);
		add(trno);
		add(ladm);
		add(ldate);
		add(lfno);
		add(tfno);
		add(tdate);
		add(lPfee);
		add(tPfee);
		add(lsname);
		add(tsname);
		add(lsadd);
		add(tsadd);
		add(lFeet);
		add(tFeet);
		add(lconm);
		add(tconm);
		add(lspno);
		add(tspno);
		add(lspno);
		add(lsgen);
		add(tgen);
		add(lcof);
		add(tcof);
		add(bnew);
		add(bcle);
		add(bprint);
		add(bprint1);

		add(ball);
		add(lemail);
		add(temail);
		add(bopen);

		tspno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});

		trno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});

		tsname.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((Character.isDigit(c))))
				// if(!((c>=65 && c<=90) || (c>=97 && c<=122)))
				{
					JOptionPane.showMessageDialog(null, "Please enter Alphabets");
					e.consume();
				}
			}
		});

		tgen.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((Character.isDigit(c))))
				// if(!((c>=65 && c<=90) || (c>=97 && c<=122)))
				{
					JOptionPane.showMessageDialog(null, "Please enter Alphabets");
					e.consume();
				}
			}
		});
		tcof.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});

		tPfee.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});
		tconm.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((Character.isDigit(c))))
				// if(!((c>=65 && c<=90) || (c>=97 && c<=122)))
				{
					JOptionPane.showMessageDialog(null, "Please enter Alphabets");
					e.consume();
				}
			}
		});
		tfno.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					JOptionPane.showMessageDialog(null, "Please enter a numerical value");
					e.consume();
				}
			}
		});
		tFeet.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((Character.isDigit(c))))
				// if(!((c>=65 && c<=90) || (c>=97 && c<=122)))
				{
					JOptionPane.showMessageDialog(null, "Please enter Alphabets");
					e.consume();
				}
			}
		});
		// Table
		scrlPane.setBounds(0, 440, 590, 200);
		add(scrlPane);
		tabGrid.setFont(new Font("Verdana", 0, 13));

		model.addColumn("R_NO");
		model.addColumn("A_NO");
		model.addColumn("S_ID");
		model.addColumn("S_NAME");
		model.addColumn("E_ID");
		model.addColumn("S_ADD");
		model.addColumn("S_GENDER");
		model.addColumn("S_PHONE");
		model.addColumn("C_NAME");
		model.addColumn("F_TYPE");
		model.addColumn("R_FEE");

		tbno.setEnabled(false);

		setVisible(true);

		date = new Date();
		calendar = new GregorianCalendar();
		calendar.setTime(date);
		strDate = calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.YEAR);
		tdate.setText(strDate);

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == bnew) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "root");

				st = con.createStatement();

				rs = st.executeQuery("select b_no from bill");
				int i = 1;

				while (rs.next()) {
					i = Integer.parseInt(rs.getString("b_no"));
				}
				i++;
				tbno.setText("" + i);
				tbno.setEnabled(false);

				rs.close();
			} catch (Exception e) {
				System.out.println("error" + e);
			}
		}

		if (ae.getSource() == bcle) {

			tPfee.setText("  ");
			// tdate.setText("In Integer Only ");
			tdate.setText(strDate);
			trno.setText(" ");
			tFeet.setText(" ");
			tcof.setText(" ");
			tfno.setText(" ");
			tgen.setText(" ");
			tsname.setText(" ");
			tsadd.setText(" ");
			tconm.setText("");
			tspno.setText("");
			temail.setText("");
			tPfee.setText("");

		}
		if (ae.getSource() == bprint) {

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
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "root");

				PreparedStatement pst = con.prepareStatement("INSERT INTO bill VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

				int tbno1 = Integer.parseInt(tbno.getText());

				String tdate1 = tdate.getText();

				int tfno1 = Integer.parseInt(tfno.getText());

				int trno1 = Integer.parseInt(trno.getText());

				String tsname1 = tsname.getText();

				String e1 = temail.getText();

				String tconm1 = tconm.getText();

				String tsadd1 = tsadd.getText();

				String tspno1 = tspno.getText();
				String tgen1 = tgen.getText();

				String tFeet1 = tFeet.getText();

				String tPfee1 = tPfee.getText();

				pst.setInt(1, tbno1);
				pst.setString(2, tdate1);
				pst.setInt(4, tfno1);
				pst.setInt(3, trno1);
				pst.setString(5, tsname1);
				pst.setString(7, tsadd1);
				pst.setString(8, tgen1);
				pst.setString(9, tspno1);
				pst.setString(10, tconm1);
				pst.setString(12, tPfee1);
				pst.setString(6, e1);
				pst.setString(11, tFeet1);

				pst.executeUpdate();

				Statement pst1 = con.createStatement();
				pst1.executeUpdate("update Admission set fee=fee-'" + tPfee1 + "' where stu_id='" + trno1 + "'");

				JOptionPane.showMessageDialog(null, "Your Record is Submitted");
				con.close();

				trno.setText("  ");
				tdate.setText(strDate);
				tsname.setText(" ");
				tspno.setText(" ");
				tfno.setText(" ");
				tsadd.setText(" ");
				tbno.setText(" ");
				tfno.setText(" ");
				tgen.setText(" ");
				tFeet.setText(" ");
				tPfee.setText(" ");
				tconm.setText("");
				tcof.setText("");
				temail.setText("");

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
			}

		}

		if (ae.getSource() == ball) {
			int r = 0;

			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root",
						"root");
				Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = stmt.executeQuery("SELECT * from bill");

				while (rs.next()) {
					model.insertRow(r++,
							new Object[] { rs.getString(1), rs.getString(4), rs.getString(3), rs.getString(5),
									rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
									rs.getString(10), rs.getString(11), rs.getString(12) });
				}
			}

			catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
			}
		}

		if (ae.getSource() == bprint1) {
			PrintUtilities.printComponent(this);
		}
		if (ae.getSource() == bopen) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "root");
				st = con.createStatement();
				String id = trno.getText();

				String qry = "select * from Admission where stu_id=" + id + "";
				rs = st.executeQuery(qry);
				while (rs.next()) {
					tfno.setText(rs.getString(1));
					trno.setText(rs.getString(3));
					tsname.setText(rs.getString(4));
					temail.setText(rs.getString(5));
					tsadd.setText(rs.getString(6));
					tgen.setText(rs.getString(8));
					tspno.setText(rs.getString(7));
					tconm.setText(rs.getString(9));

					tFeet.setText(rs.getString(11));
					tcof.setText(rs.getString(12));

				}
				/*
				 * String id1=tFeet.getText();
				 * int j=Integer.parseInt(id1);
				 * 
				 * String qry1= "select * from Course where co_id="+j+"";
				 * rs = st.executeQuery(qry1);
				 * while(rs.next())
				 * {
				 * tconm.setText(rs.getString(2));
				 * tcof.setText(rs.getString(4));
				 * 
				 * }
				 */

				con.close();

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
			}

		}

	}

	public static void main(String args[]) {
		new stubill();
	}

}
