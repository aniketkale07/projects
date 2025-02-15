import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;
import java.sql.ResultSet;



class Techerod extends JFrame implements ActionListener
{
	

	
	JLabel lidno,ltname,ltadd,ltpno,lfno,lbat,ldate,ladm,ltgen,lconm,lcopd,lemail;
	JTextField tdate,ttname,ttpno,ttadd,tcopd,tbat,tcou,tgen,temail;
	JButton bopen,bdel,bcancel,bcle,delete;
	JPanel top;
	JComboBox tid;
	//JComboBox tgen,b_id,co_id;
	Font fo=new Font("verdana" ,Font.BOLD,20);
	
	
	PreparedStatement pst;
	Connection con;
	Statement st;
	ResultSet rs;
	String test,test1;

	
       
//Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
					
	  Techerod()
	    {
                setSize(580,550);
	    	
               setLayout(null);
	    	setTitle("TEACHER  RECORD");
	    	setResizable(false);
	      	setLocation(300,160);	


		ladm=new JLabel("Teacher RECORD");
                ladm.setBounds(170,30,320,30);
		ladm.setFont(fo);
		ladm.setForeground(Color.red);	
		add(ladm);
		
    	

               lidno=new JLabel("Teacher Id :");
	    	lidno.setBounds(30,100,100,30);
	    	tid= new JComboBox<>();
			//teacher id Combo box
			try{
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
				java.sql.Statement st= con.createStatement();
				String s = "SELECT * FROM teacher";
				java.sql.ResultSet rs = st.executeQuery(s);

				while(rs.next()){
					tid.addItem(rs.getString(1));
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}


	    	tid.setBounds(140,100,115,25);
	    	

		
	    	
	    	ltname=new JLabel("Teacher Name:");
	    	ltname.setBounds(30,140,120,25);
	    	ttname=new JTextField(150);
	    	ttname.setBounds(140,140,370,25);
	    	
	    	lemail=new JLabel("Email_Id:");
	    	lemail.setBounds(30,178,120,25);
	    	temail=new JTextField(150);
	    	temail.setBounds(140,178,370,25);

                ltadd=new JLabel("Teacher Add:");
	    	ltadd.setBounds(30,215,120,25);
	    	ttadd=new JTextField(150);
	    	ttadd.setBounds(140,215,370,25);
	    	
	    	ltgen=new JLabel("Gender :");
	    	ltgen.setBounds(340,260,80,25);
	    	 tgen=new JTextField();
                 tgen.setBounds(410,260,100,25);
                         
	    	ltpno=new JLabel("Contact No :");
	    	ltpno.setBounds(30,260,90,25);
	    	ttpno=new JTextField(20);
	    	ttpno.setBounds(140,260,115,25);
	    	
	    		          	
                lconm=new JLabel("Course Id :");
	    	lconm.setBounds(30,300,90,25);
                tcou=new JTextField();
                tcou.setBounds(140,300,115,25);
                    
                lbat=new JLabel("Faculty :");
	    	lbat.setBounds(340,300,90,25);
                tbat=new JTextField ();
                tbat.setBounds(410,300,100,25);
	    	

		bopen=new JButton("Open");
	    	bopen.setBounds(20,360,90,25);
		bopen.addActionListener(this);
	    	
	    	bcle=new JButton("Clear");
	    	bcle.setBounds(140,360,90,25);
	    	bcle.addActionListener(this);
	    	bcancel=new JButton("ALL");
                bcancel.addActionListener(this);
	    	bcancel.setBounds(260,360,90,25);
	    //    delete teacher Record
			delete = new JButton("Delete"); delete.addActionListener(this);
			delete.setBounds(370,360,90,25); 	add(delete);
		
		
                add(lidno);
                add(tid);
	    	add(ladm);
	    	add(ltname);
	    	add(ttname);
	    	add(ltadd);
	    	add(ttadd);
	    	add(ltgen);
                add(tgen);
                add(ltpno);
                add(ttpno);
			add(lconm);
			add(tcou);
		
                add(tbat);
                add(lbat);
                 
                 add(bcle);
	    	add(bcancel);
		add(bopen);
                add(lemail);
                add(temail);
tid.addKeyListener(new KeyAdapter()
    {
        public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
      		if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))))
      		{
        	 JOptionPane.showMessageDialog(null, "Please enter a numerical value");
           	 e.consume();
      		}
    		}
  		    });

// Table
    scrlPane.setBounds(0,410,577,200);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));

  model.addColumn("T_ID");
    model.addColumn("T_NAME");
    model.addColumn("T_ADD");
    model.addColumn("EL_ID");
    model.addColumn("T_PHONE");
    model.addColumn("T_GENDER");
    model.addColumn("C_ID");
    model.addColumn("T_FACULTY");
		
		setVisible(true);

					
					
					ttname.setEnabled(false);
					ttadd.setEnabled(false);
                                        temail.setEnabled(false);
					ttpno.setEnabled(false);
					tbat.setEnabled(false);
					tgen.setEnabled(false);
					tcou.setEnabled(false);
                                        temail.setEnabled(false);

								
     
							           

	    }

	  public void actionPerformed(ActionEvent ae)
	    {
	    	if(ae.getSource()==bcle)
	    	{
	    	tcou.setText("  ");
	    	ttname.setText(" ");
	    	ttpno.setText(" ");
	    	ttadd.setText(" ");
	    	tgen.setText(" ");
	    	tbat.setText("  ");
                temail.setText("  ");
		

		ttname.setEnabled(false);
		ttadd.setEnabled(false);
                temail.setEnabled(false);
		ttpno.setEnabled(false);
		tbat.setEnabled(false);
		tgen.setEnabled(false);
		tcou.setEnabled(false);
		
	    		
	    	}
                if(ae.getSource()==bcancel)
                {
                  int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver =DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from teacher" );
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8) });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
                }
		
		
		if(ae.getSource()==bopen)
	      {
	                     try
	               	{
	               	     Class.forName("com.mysql.jdbc.Driver");
	                     con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
	                     st= con.createStatement();
	                     String id=(String) tid.getSelectedItem();
	                     
                         String qry= "select * from teacher where t_id="+id+"";
	                     rs = st.executeQuery(qry);
	                     while(rs.next())
                         {
                           ttname.setText(rs.getString(2));
                           ttadd.setText(rs.getString(3));
                           temail.setText(rs.getString(4));
                           ttpno.setText(rs.getString(5));
			    tgen.setText(rs.getString(6));
			tcou.setText(rs.getString(7));
			tbat.setText(rs.getString(8));
			
				ttname.setEnabled(true);
				ttadd.setEnabled(true);
                                temail.setEnabled(true);
				ttpno.setEnabled(true);
				tbat.setEnabled(true);
				tgen.setEnabled(true);
				tcou.setEnabled(true);
				
                         }
       
                        con.close();
	               			
	               	}
	                catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
		  			 
	    
		}
		if(ae.getSource()==delete){
			String id = (String) tid.getSelectedItem();
			String s = "delete from teacher where t_id = " + id;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
			   
				st = con.createStatement();
				JOptionPane.showConfirmDialog(null, "Delete Record");
				int i=st.executeUpdate(s);
				if(i>0){
					JOptionPane.showMessageDialog(null, "Record deleted");
					
				}else{
					JOptionPane.showMessageDialog(null, "Record not Found");
				}

			} catch(Exception e){
				JOptionPane.showMessageDialog(null, e);
			}

		}
	}

	    public static void main (String args[]) 
	    	{
					new Techerod();
						   }	
	    	
	    	
	    
		}
	
