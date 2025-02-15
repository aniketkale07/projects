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



class enqfrm extends JFrame implements ActionListener
{

	
	JLabel lrno,lsname,lsadd,lspno,lfno,lsem,ldate,ladm,lsgen,lconm,lcopd;
	JTextField trno,tdate,tsname,tspno,tfno,tsem,tsadd,tcopd,co_id;
	JButton bnew,bsub,bcancel,bcle;
	JPanel top;

	Font fo=new Font("verdana" ,Font.BOLD,20);
	
	Connection con;
	Statement st;
	ResultSet rs;
	String test,test1;
        JComboBox gender,b_id;

        
           Thread datimeThread;
           Date date;
           GregorianCalendar calendar;
           String strDate;




	//Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
     

					
	   enqfrm()
	    {
                setSize(630,570);
	    	
               setLayout(null);
	    	setTitle("ENQUIRY FORM");
	    	setResizable(false);
	      	setLocation(300,160);	


		ladm=new JLabel("ENQUIRY FORM");
                ladm.setBounds(240,30,380,40);
		ladm.setFont(fo);
		ladm.setForeground(Color.blue);	
		add(ladm);
		/*top=new JPanel();
		top.setLayout(null);
		top.setBorder(BorderFactory.createEtchedBorder(1));
	        top.setBounds(60,20,320,50);
		add(top);*/
		
    	
	    	
	    	

                lfno=new JLabel("Enquiry No :");
                lfno.setBounds(30,100,100,30);
	    	tfno=new JTextField(30);
                tfno.setBounds(130,100,130,25);

	    	ldate=new JLabel("Date :");
	    	ldate.setBounds(350,100,45,30);
	    	tdate=new JTextField(15);
	    	tdate.setBounds(420,100,120,25);

	    	
               lrno=new JLabel("Stud Name:");
	    	lrno.setBounds(30,140,120,30);
	    	trno=new JTextField();
	    	trno.setBounds(130,140,130,25);
	    	
	    		
	    	
	    	lsname=new JLabel("Email_Id:");
	    	lsname.setBounds(30,180,90,25);
	    	tsname=new JTextField(150);
	    	tsname.setBounds(130,180,410,25);
	    	
	    	lsadd=new JLabel("Stud Add:");
	    	lsadd.setBounds(30,220,90,25);
	    	tsadd=new JTextField(150);
	    	tsadd.setBounds(130,220,410,25);
	    	
	    	lsgen=new JLabel("Gender :");
	    	lsgen.setBounds(340,260,120,25);
	    	
            
                gender=new JComboBox ();
                gender.addItem("Male");
                gender.addItem("Female");
                gender.setBounds(430,260,110,25);
                         
	    	lspno=new JLabel("Contact No :");
	    	lspno.setBounds(30,260,100,25);
	    	tspno=new JTextField(20);
	    	tspno.setBounds(130,260,115,25);
	    	
	    		          	
                lconm=new JLabel("Course Name:");
	    	lconm.setBounds(30,300,100,25);
                co_id=new JTextField (20);
                          co_id.setBounds(130,300,115,25);
	    	
                    
                lsem=new JLabel("Batch Time :");
	    	lsem.setBounds(340,300,130,25);
               b_id=new JComboBox ();
                           b_id.setBounds(430,300,110,25);
	    	

		bnew=new JButton("New");
	    	bnew.setBounds(30,350,90,25);
		bnew.addActionListener(this);
	    	bsub=new JButton("Submit");
	    	bsub.setBounds(190,350,90,25);
                bsub.addActionListener(this) ;
	    	bcle=new JButton("Clear");
	    	bcle.setBounds(350,350,90,25);
	    	bcle.addActionListener(this);
	    	bcancel=new JButton("ALL");
                bcancel.addActionListener(this);
	    	bcancel.setBounds(500,350,90,25);
	       	
		
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


  	   tspno.addKeyListener(new KeyAdapter()
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

		tfno.addKeyListener(new KeyAdapter()
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

trno.addKeyListener(new KeyAdapter()
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
co_id.addKeyListener(new KeyAdapter()
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
    scrlPane.setBounds(0,400,627,200);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));
    
    model.addColumn("E_NO");
 
    model.addColumn("S_NAME");
    model.addColumn("E_ID");
    model.addColumn("S_ADD");
    model.addColumn("S_PHONE");
    model.addColumn("S_GENDER");
    model.addColumn("C_NAME");
    model.addColumn("S_BATCH");
 
     
		
		setVisible(true);

					/*tfno.setEnabled(false);
					trno.setEnabled(false);
					tdate.setEnabled(false);
					tsname.setEnabled(false);
					tsadd.setEnabled(false);
					tspno.setEnabled(false);
					b_id.setEnabled(false);
					gender.setEnabled(false);
					co_id.setEnabled(false);*/

                  date=new Date();
                  calendar=new GregorianCalendar();
                  calendar.setTime(date);
                  strDate=calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
                  tdate.setText(strDate);
                  

					
          try
		{
			Class.forName("com.mysql.jdbc.Driver");
    	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
            st=con.createStatement();
    					
    	//rs=st.executeQuery("select co_name from Course");
    				
    					//while(rs.next())
    					//{
    					   // test=rs.getString(1);	
    					   // co_id.addItem(test);
    				    //}
    				    rs=st.executeQuery("select time from Batch");
    				
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
	    	trno.setText("  ");
               tdate.setText(strDate);
	    	tsname.setText(" ");
	    	tspno.setText(" ");
	    	tfno.setText(" ");
	    	tsadd.setText(" ");
	    	co_id.setText("");
	    		
	    	}
                if(ae.getSource()==bcancel)
                {
                    int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection ("jdbc:mysql://localhost:3306/institute","root","");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from Enquiry" );
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)  });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
                }
		
		if(ae.getSource()==bsub)
			{

			String s3=tspno.getText();
                        String s7=tsname.getText();
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
    				
    		PreparedStatement pst = con.prepareStatement("INSERT INTO Enquiry VALUES(?,?,?,?,?,?,?,?,?)");
		               
		        int fno1 =Integer.parseInt(tfno.getText());
		                
	            String tdate1=tdate.getText();
				
			 String trno1 =(trno.getText());
	                 String tsname1=tsname.getText();
				 String tsadd1=tsadd.getText();
			String g1=gender.getSelectedItem().toString();
			String tspno1 =tspno.getText();
			String c1=co_id.getText();
			String b1=b_id.getSelectedItem().toString();
                                      
	                 	pst.setInt(1,fno1);
	                    pst.setString(2,tdate1);
	                    pst.setString(3,trno1);
				 pst.setString(4,tsname1);
			 pst.setString(5,tsadd1);
			pst.setString(7,tspno1);	
	                  pst.setString(6,g1);
			 pst.setString(8,c1);                  	 
			 pst.setString(9,b1);

   
                	    pst.executeUpdate();
                	    JOptionPane.showMessageDialog(null, "Your Record is Submitted");
                	        con.close();
				
				trno.setText("  ");
	    tdate.setText(strDate);
	    	tsname.setText(" ");
	    	tspno.setText(" ");
	    	tfno.setText(" ");
	    	tsadd.setText(" ");
co_id.setText("");
		              }
		              catch(NumberFormatException e)
		              {
		                JOptionPane.showMessageDialog(null, "Please Enter All Fields Correctely !");
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
    					
    		rs=st.executeQuery("select Enq_id from Enquiry");
    					int i=1;
    					
    					while(rs.next())
    					{
    						i=Integer.parseInt(rs.getString("Enq_id"));
    					}
    					i++;
    					tfno.setText(""+i);
					
	//rs=st.executeQuery("select stu_id from Admission");
    					//int j=1;
    					
    					//while(rs.next())
    					//{
    						//j=Integer.parseInt(rs.getString("stu_id"));
    					//}
    					//j++;
    					//trno.setText(""+j);
					

					trno.setText(" ");
					tdate.setText(strDate);
					tsname.setText("");
					tspno.setText("");
					tsadd.setText("");
					


					/*gender.setEnabled(true);
					
					tdate.setEnabled(true);
					tsname.setEnabled(true);
					tspno.setEnabled(true);
					b_id.setEnabled(true);
					tsadd.setEnabled(true);
					co_id.setEnabled(true);*/
					
    					
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
					new enqfrm();
						   }	
	    	
	    	
	    
}
