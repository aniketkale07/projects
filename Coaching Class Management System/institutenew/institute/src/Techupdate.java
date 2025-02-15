import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;
import java.sql.ResultSet;



class Techupdate extends JFrame implements ActionListener
{

	
	JLabel lidno,ltname,ltadd,ltpno,lfno,lbat,ldate,ladm,ltgen,lconm,lcopd,lemail;
	JTextField tid,tdate,ttname,ttpno,ttadd,tcopd,temail;
	JButton bopen,bmod,bcancel,bcle;
	JPanel top;
	JComboBox tgen,b_id,co_id;
	Font fo=new Font("verdana" ,Font.BOLD,20);
	
	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	String test,test1;
       
//Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
					
	  Techupdate()
	    {
                setSize(580,550);
	    	
               setLayout(null);
	    	setTitle("TEACHER  RECORD");
	    	setResizable(false);
	      	setLocation(300,160);	


		ladm=new JLabel("MODIFY RECORD");
                ladm.setBounds(210,30,280,30);
		ladm.setFont(fo);
		ladm.setForeground(Color.red);	
		add(ladm);
		
    	

               lidno=new JLabel("Teacher id :");
	    	lidno.setBounds(30,100,100,30);
	    	tid=new JTextField();
	    	tid.setBounds(140,100,115,25);
	    	

		
	    	
	    	ltname=new JLabel("Teacher Name:");
	    	ltname.setBounds(30,140,120,25);
	    	ttname=new JTextField(150);
	    	ttname.setBounds(140,140,370,25);
	    	
	    	lemail=new JLabel("Email_Id:");
	    	lemail.setBounds(30,175,120,25);
	    	temail=new JTextField(150);
	    	temail.setBounds(140,175,370,25);

                ltadd=new JLabel("Teacher Add:");
	    	ltadd.setBounds(30,210,120,25);
	    	ttadd=new JTextField(150);
	    	ttadd.setBounds(140,210,370,25);
	    	
	    	
	    	ltgen=new JLabel("Gender :");
	    	ltgen.setBounds(340,250,80,25);
	    	 tgen=new JComboBox();
		tgen.addItem("Male");
                tgen.addItem("Female");
                 tgen.setBounds(410,250,100,25);
                         
	    	ltpno=new JLabel("Contact No :");
	    	ltpno.setBounds(30,250,90,25);
	    	ttpno=new JTextField(20);
	    	ttpno.setBounds(140,250,115,25);
	    	
	    		          	
                lconm=new JLabel("Course Id :");
	    	lconm.setBounds(30,300,90,25);
                co_id=new JComboBox();
                co_id.setBounds(140,300,115,25);
                    
                lbat=new JLabel("Faculty:");
	    	lbat.setBounds(340,300,90,25);
                b_id=new JComboBox ();
                b_id.setBounds(410,300,100,25);
	    	

		bopen=new JButton("Open");
	    	bopen.setBounds(50,370,90,25);
		bopen.addActionListener(this);
	    	bmod=new JButton("Modify");
	    	bmod.setBounds(180,370,90,25);
                bmod.addActionListener(this) ;
	    	bcle=new JButton("Clear");
	    	bcle.setBounds(310,370,90,25);
	    	bcle.addActionListener(this);
	    	bcancel=new JButton("ALL");
                bcancel.addActionListener(this);
	    	bcancel.setBounds(440,370,90,25);
	       	
		
		
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
		add(co_id);
		
                add(b_id);
                add(lbat);
                 add(bmod);
                 add(bcle);
	    	add(bcancel);
		add(bopen);
                add(lemail);
                add(temail);

 ttname.addKeyListener(new KeyAdapter()
    {
        public void keyTyped(KeyEvent e) {
    	char c = e.getKeyChar();
      		if (((Character.isDigit(c))))
      	//if(!((c>=65 && c<=90) || (c>=97 && c<=122)))
      		{
        	 JOptionPane.showMessageDialog(null, "Please enter Alphabets");
           	 e.consume();
      		}
    		}
  		    });
 ttpno.addKeyListener(new KeyAdapter()
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
    model.addColumn("E_ID");
    model.addColumn("T_PHONE");
    model.addColumn("T_GENDER");
    model.addColumn("C_ID");
    model.addColumn("T_FACULTY");
		
		setVisible(true);

					
					
					ttname.setEnabled(true);
					ttadd.setEnabled(true);
                                        temail.setEnabled(true);
					ttpno.setEnabled(true);
					b_id.setEnabled(true);
					tgen.setEnabled(true);
					co_id.setEnabled(true);

								
          try
		{
			Class.forName("com.mysql.jdbc.Driver");
    	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
            st=con.createStatement();
    					
    					rs=st.executeQuery("select co_id from course");
    				
    					while(rs.next())
    					{
    					    test=rs.getString(1);	
    					    co_id.addItem(test);
    				    }
    				    rs=st.executeQuery("select b_id from batch");
    				
    					while(rs.next())
    					{
    					    test=rs.getString(1);	
    					    b_id.addItem(test);
    				    }
    				    
		}		    
    				catch(Exception e)
	 	            {
	 		           System.out.println("error" +e);
	 	            }
							           

	    }

	  public void actionPerformed(ActionEvent ae)
	    {
	    	if(ae.getSource()==bcle)
	    	{
	    	tid.setText("  ");
	    	//co_id.setText("  ");
	    	ttname.setText(" ");
	    	ttpno.setText(" ");
	    	ttadd.setText(" ");
                 temail.setText(" ");
	    	//tgen.setText(" ");
	    	//b_id.setText("  ");
		

		
	    		
	    	}
                if(ae.getSource()==bcancel)
                {
                  int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
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
		
		if(ae.getSource()==bmod)
			{


			String s3=ttpno.getText();
                        String s7=temail.getText();
                          if(s3.length()!=10)
			{
				JOptionPane.showMessageDialog(this,"Check the Mobile Number","ADMIN",JOptionPane.ERROR_MESSAGE);
				return;
			}
 
                           if (!s7.contains("@") || !s7.contains("."))
		{
			JOptionPane.showMessageDialog(this,"Plz Enter Correct Email Id","Insert",JOptionPane.ERROR_MESSAGE);
			return;
		} 

    	
    		 try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    					st=con.createStatement();
    					
    					String tid1=tid.getText();
    				
    					String ttname1=ttname.getText();

					String ttadd1=ttadd.getText();
                                        String e1=temail.getText();
    					
					String ttpno1=ttpno.getText();
					
    					
				String g1=tgen.getSelectedItem().toString();
				String c1=co_id.getSelectedItem().toString();
					String b1=b_id.getSelectedItem().toString();
    					    					
    	 String str1="UPDATE teacher SET t_id ='"+tid1+"',t_name='"+ttname1+"',t_add='"+ttadd1+"',Email_id='"+e1+"',t_phno='"+ttpno1+"',t_gender='"+g1+"',co_id='"+c1+"',b_id='"+b1+"' where t_id LIKE '" +  tid1 +"' ";
    					st.executeUpdate(str1);
    					JOptionPane.showMessageDialog(null, "Record is Modified");
    					
				tid.setText("  ");
	    	//co_id.setText("  ");
	    	ttname.setText(" ");
	    	ttpno.setText(" ");
                temail.setText(" ");
	    	ttadd.setText(" ");
	    	//tgen.setText(" ");
	    	//b_id.setText("  ");

    					con.close();
				ttname.setEnabled(true);
				ttadd.setEnabled(true);
                                temail.setEnabled(true);
				ttpno.setEnabled(true);
				b_id.setEnabled(true);
				tgen.setEnabled(true);
				co_id.setEnabled(true);
				
    				
    					
    				}
    					catch(Exception ex)
		                 {
		                 	System.out.println("Error Occured");
			                System.out.println("Error:"+ex);
	                     }

	    }

		if(ae.getSource()==bopen)
	      {
	                     try
	               	{
	               	     Class.forName("com.mysql.jdbc.Driver");
	                     con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
	                     st= con.createStatement();
	                     String id=tid.getText();
	                     
	                     
                         String qry= "select * from teacher where t_id="+id+"";
	                     rs = st.executeQuery(qry);
	                     while(rs.next())
                         {
                           ttname.setText(rs.getString(2));
                           ttadd.setText(rs.getString(3));
                           temail.setText(rs.getString(4));
                           ttpno.setText(rs.getString(5));
                           
			    /*tgen.setText(rs.getString(5));
			co_id.setText(rs.getString(6));
			b_id.setText(rs.getString(7));*/
			
				ttname.setEnabled(true);
				ttadd.setEnabled(true);
                                temail.setEnabled(true);
				ttpno.setEnabled(true);
				b_id.setEnabled(true);
				tgen.setEnabled(true);
				co_id.setEnabled(true);
				
                         }
       
                        con.close();
	               			
	               	}
	               	catch(Exception ex)
		                 {
			                System.out.println("Error:"+ex);
	                     }
		  			 
	    
		}
	}

	    public static void main (String args[]) 
	    	{
					new Techupdate();
						   }	
	    	
	    	
	    
		}
	
