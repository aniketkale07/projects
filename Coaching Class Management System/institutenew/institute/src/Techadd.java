import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import java.sql.*;
import java.sql.ResultSet;

class Techadd extends JFrame implements ActionListener
{

	
	JLabel lidno,ltname,ltadd,ltpno,lfno,lbat,ldate,ladm,ltgen,lconm,lcopd,lemail;
	JTextField tid,tdate,ttname,ttpno,tfno,tsem,ttadd,tcopd,tbat,tcou,temail;
	JButton bnew,badd,bcancel,bcle;
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
					
					
	  Techadd()
	    {
                setSize(560,570);
	    	
               setLayout(null);
	    	setTitle("Teacher RECORD");
	    	setResizable(false);
	      	setLocation(300,160);	


		ladm=new JLabel("ADD RECORD");
                ladm.setBounds(230,30,280,30);
		ladm.setFont(fo);
		ladm.setForeground(Color.red);	
		add(ladm);
		
    	

               lidno=new JLabel("Teacher Id :");
	    	lidno.setBounds(30,110,100,30);
	    	tid=new JTextField();
	    	tid.setBounds(140,110,125,25);
	    	

		
	    	
	    	ltname=new JLabel("Teacher Name:");
	    	ltname.setBounds(30,160,120,25);
	    	ttname=new JTextField(150);
	    	ttname.setBounds(140,160,370,25);
	    	
	    	lemail=new JLabel("Email_Id:");
	    	lemail.setBounds(30,200,120,25);
	    	temail=new JTextField(150);
	    	temail.setBounds(140,200,370,25);

                ltadd=new JLabel("Teacher Add:");
	    	ltadd.setBounds(30,240,120,25);
	    	ttadd=new JTextField(150);
	    	ttadd.setBounds(140,240,370,25);
	    		    	

	    	ltgen=new JLabel("Gender :");
	    	ltgen.setBounds(340,280,100,25);
	    	 tgen=new JComboBox ();
                tgen.addItem("Male");
                tgen.addItem("Female");
        	   tgen.setBounds(410,280,100,25);
                         
	    	ltpno=new JLabel("Contact No :");
	    	ltpno.setBounds(30,280,90,25);
	    	ttpno=new JTextField(20);
	    	ttpno.setBounds(140,280,115,25);
	    	
	    		          	
                lconm=new JLabel("Course Id :");
	    	lconm.setBounds(30,330,90,25);
                co_id=new JComboBox ();
                co_id.setBounds(140,330,115,25);
                    
                lbat=new JLabel("Faculty :");
	    	lbat.setBounds(340,330,90,25);
                b_id=new JComboBox ();
                b_id.setBounds(410,330,100,25);
	    	

		bnew=new JButton("New");
	    	bnew.setBounds(40,385,90,25);
		bnew.addActionListener(this);
	    	badd=new JButton("Add");
	    	badd.setBounds(170,385,90,25);
                badd.addActionListener(this) ;
	    	bcle=new JButton("Clear");
	    	bcle.setBounds(300,385,90,25);
	    	bcle.addActionListener(this);
	    	bcancel=new JButton("ALL");
                bcancel.addActionListener(this);
	    	bcancel.setBounds(430,385,90,25);
	       	
		
		
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
                add(lemail);
                add(temail);
		
                add(b_id);
                add(lbat);
                 add(badd);
                 add(bcle);
	    	add(bcancel);
		add(bnew);
                

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

	// Table
    scrlPane.setBounds(0,430,557,200);
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

					
					tid.setEnabled(false);
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
    					
    					rs=st.executeQuery("select co_id from Course");
    				
    					while(rs.next())
    					{
    					    test=rs.getString(1);	
    					    co_id.addItem(test);
    				    }
    				    rs=st.executeQuery("select b_id from Batch");
    				
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
                temail.setText("");
	    	ttpno.setText(" ");
	    	ttadd.setText(" ");
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
                ResultSet rs = stmt.executeQuery("SELECT * from Teacher" );
				
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
		
		if(ae.getSource()==badd)
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
    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    				
    		PreparedStatement pst = con.prepareStatement("INSERT INTO Teacher VALUES(?,?,?,?,?,?,?,?)");
		               
		                int tid1 =Integer.parseInt(tid.getText());
		              	
	                    
	                     String ttname1=ttname.getText();
                              
                            
				 String ttadd1=ttadd.getText();
                                  String e1=temail.getText();
  
	                    	String ttpno1 =ttpno.getText();

				String g1=tgen.getSelectedItem().toString();
				
					String c1=co_id.getSelectedItem().toString();
					String b1=b_id.getSelectedItem().toString();
                                      
	                 	pst.setInt(1,tid1);
	                    pst.setString(2,ttname1);
	                    pst.setString(3,ttadd1);
                            pst.setString(4,e1);
				pst.setString(5,ttpno1);
				 pst.setString(6,g1);
				 pst.setString(7,c1);   
				pst.setString(8,b1);

			   
                	    pst.executeUpdate();
                	    JOptionPane.showMessageDialog(null, "Your Record is Submitted");
                	        con.close();
				
				tid.setText("  ");
	    	
	    	ttname.setText(" ");
	    	ttpno.setText(" ");
	    	ttadd.setText(" ");
                temail.setText(" ");
		              }
		              catch(NumberFormatException e)
		              {
		                JOptionPane.showMessageDialog(null, "Please Enter All Fields !");
		              }
		              
		             catch(Exception ex)
		             {
			            System.out.println("Error Occured");
			            System.out.println("Error:"+ex);
		              }

	    }

		if(ae.getSource()==bnew)
	      {
	               try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    					
    					st=con.createStatement();
    					
    					rs=st.executeQuery("select t_id from Teacher");
    					int i=1;
    					
    					while(rs.next())
    					{
    						i=Integer.parseInt(rs.getString("t_id"));
    					}
    					i++;
    					tid.setText(""+i);
							

					
					ttname.setText("");
					ttpno.setText("");
                                        temail.setText("");
					ttadd.setText("");
					tgen.setEnabled(true);
					
					ttname.setEnabled(true);
                                        temail.setEnabled(true);
					ttpno.setEnabled(true);
					b_id.setEnabled(true);
					ttadd.setEnabled(true);
					co_id.setEnabled(true);
					
    					
    					rs.close();
    				}
    				catch(Exception e)
	 	            {
	 		           System.out.println("error" +e);
	 	            }
			
		}
	}

	    public static void main (String args[]) 
	    	{
					new Techadd();
						   }	
	    	
	    	
	    
		}
	
