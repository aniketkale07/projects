import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;

class stubillOpen extends JFrame implements ActionListener {

    JLabel lrno, lsname, lsadd, lFeet, ldate, ladm, lbno, lPfee, lspno, lconm, lsgen, lfno, lcof, lemail, issueL;
    JTextField tdate, tsname, tFeet, tsadd, tconm, tbno, tPfee, tspno, tfno, tgen, tcof, temail, issueTF;
    JButton bcle, ball, bopen, delete, update;
    // JComboBox gender;
    JComboBox studId;

    Font fo = new Font("vERDANA", Font.BOLD, 20);
    Connection con;
    Statement st;
    ResultSet rs;
    String test, test1;

    // Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

    stubillOpen() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute", "root", "");
            st = con.createStatement();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        setSize(590, 680);

        setLayout(null);
        setTitle("STUDENT RECEIPT");
        setResizable(false);
        setLocation(300, 160);

        ladm = new JLabel("OPEN RECEIPT");
        ladm.setFont(fo);
        ladm.setBounds(220, 30, 250, 50);
        ladm.setForeground(Color.red);

        lbno = new JLabel("Receipt No :");
        lbno.setBounds(30, 90, 100, 25);
        tbno = new JTextField(5);
        tbno.setBounds(130, 90, 100, 25);

        lrno = new JLabel("Student Id :");
        lrno.setBounds(30, 130, 100, 25);
        studId = new JComboBox();

        // combo box query
        try {
            String s = "select stu_id from bill";
            java.sql.ResultSet rs = st.executeQuery(s);

            while (rs.next()) {
                studId.addItem(rs.getInt(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //
        studId.setBounds(130, 130, 100, 25);

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

        lspno = new JLabel("Contact No :");
        lspno.setBounds(30, 280, 100, 25);
        tspno = new JTextField(20);
        tspno.setBounds(130, 280, 100, 25);

        lsgen = new JLabel("Gender :");
        lsgen.setBounds(340, 280, 90, 25);
        tgen = new JTextField();
        tgen.setBounds(420, 280, 80, 25);

        lconm = new JLabel("Course Name:");
        lconm.setBounds(30, 320, 100, 25);
        tconm = new JTextField(30);
        tconm.setBounds(130, 320, 100, 25);

        lFeet = new JLabel("Fee Type:");
        lFeet.setBounds(340, 320, 120, 25);
        tFeet = new JTextField(30);
        tFeet.setBounds(420, 320, 80, 25);

        lcof = new JLabel("Course Fees :");
        lcof.setBounds(30, 360, 100, 25);
        tcof = new JTextField(15);
        tcof.setBounds(130, 360, 100, 25);

        lPfee = new JLabel("Paid Fees:");
        lPfee.setBounds(340, 360, 115, 25);
        tPfee = new JTextField(15);
        tPfee.setBounds(420, 360, 80, 25);

        issueL = new JLabel("Date:");
        issueL.setBounds(30, 400, 115, 25);
        issueTF = new JTextField(15);
        issueTF.setBounds(130, 400, 100, 25);
        add(issueL);
        add(issueTF);

        bopen = new JButton("Open");
        bopen.setBounds(20, 450, 90, 25);
        bopen.addActionListener(this);
        bcle = new JButton("Clear");
        bcle.setBounds(130, 450, 90, 25);
        bcle.addActionListener(this);
        ball = new JButton("ALL");
        ball.setBounds(240, 450, 90, 25);
        ball.addActionListener(this);
        delete = new JButton("Delete ");
        delete.addActionListener(this);
        delete.setBounds(350, 450, 90, 25);
        update = new JButton("Update");
        update.addActionListener(this);
        update.setBounds(460, 450, 90, 25);

        add(delete);
        add(update);
        add(lbno);
        add(tbno);
        add(lrno);
        add(studId);
        add(ladm);
        add(lfno);
        add(tfno);

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
        add(lsgen);
        add(tspno);

        add(lspno);
        add(tgen);
        add(lcof);
        add(tcof);

        add(bopen);
        add(bcle);
        add(ball);

        add(lemail);
        add(temail);

        // Table
        scrlPane.setBounds(0, 500, 590, 200);
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

        setVisible(true);

        tbno.setEnabled(false);
        tfno.setEnabled(false);
        tPfee.setEnabled(false);
        tsname.setEnabled(false);
        tsadd.setEnabled(false);
        tFeet.setEnabled(false);
        tconm.setEnabled(false);
        tspno.setEnabled(false);
        tgen.setEnabled(false);
        tcof.setEnabled(false);
        temail.setEnabled(false);
        issueTF.setEnabled(false);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == delete) {
            Object id = studId.getSelectedItem();
            String s = "Delete from bill where stu_id= " + id + "";
            try {
                st = con.createStatement();

                JOptionPane.showMessageDialog(null, "Delete Record...");
                int i = st.executeUpdate(s);

            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }

        if (ae.getSource() == update) {
            Object id = studId.getSelectedItem();

            try {
                st = con.createStatement();
                String qry = "Select * from bill where stu_id=" + id + "";
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    tbno.setText(rs.getString("b_no"));
                    issueTF.setText(rs.getString("date1"));
                    tsname.setText(rs.getString("stu_name"));
                    temail.setText(rs.getString("Email_id"));
                    tsadd.setText(rs.getString("stu_add"));
                    tgen.setText(rs.getString("stu_gender"));
                    tspno.setText(rs.getString("stu_phno"));
                    tconm.setText(rs.getString("co_id"));

                    tFeet.setText(rs.getString("fee_t"));
                    tPfee.setText(rs.getString("paid_F"));

                    tbno.setEnabled(false);
                    tfno.setEnabled(true);
                    tPfee.setEnabled(true);
                    tsname.setEnabled(true);
                    tsadd.setEnabled(true);
                    tFeet.setEnabled(true);
                    tconm.setEnabled(true);
                    tspno.setEnabled(true);
                    tgen.setEnabled(true);
                    tcof.setEnabled(true);
                    temail.setEnabled(true);
                    issueTF.setEnabled(true);


                }

            } catch (SQLException e2) {
                System.out.println(e2);
            }
        }

        if (ae.getSource() == bopen) {
            try {
                st = con.createStatement();
                Object id = studId.getSelectedItem();
                String qry = "Select * from bill where stu_id=" + id + "";
                rs = st.executeQuery(qry);
                while (rs.next()) {
                    tbno.setText(rs.getString("b_no"));
                    issueTF.setText(rs.getString("date1"));
                    tsname.setText(rs.getString("stu_name"));
                    temail.setText(rs.getString("Email_id"));
                    tsadd.setText(rs.getString("stu_add"));
                    tgen.setText(rs.getString("stu_gender"));
                    tspno.setText(rs.getString("stu_phno"));
                    tconm.setText(rs.getString("co_id"));

                    tFeet.setText(rs.getString("fee_t"));
                    tPfee.setText(rs.getString("paid_F"));

                }

            }

            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
            }

        }
        if (ae.getSource() == bcle)

        {

            // tdate.setText("In Integer Only ");
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
            issueTF.setText("");

        }

        if (ae.getSource() == ball) {
            int r = 0;

            try {
                Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from bill");

                while (rs.next()) {
                    model.insertRow(r++,
                            new Object[] { rs.getString(1), rs.getString(4), rs.getString(3), rs.getString(5),
                                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
                                    rs.getString(10), rs.getString(11), rs.getString(12) });

                }
                con.close();
            }

            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error Occured : " + ex);
            }
        }
    }

    public static void main(String args[]) throws SQLException {
        try {
            new stubillOpen();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }
    }

}
