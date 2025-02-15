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



class BatchRec extends JFrame implements ActionListener
{

	
        JLabel lbcod,lstrdt,lnos,lcoid,ltnm,ltime,ladm,lbtime;
        JTextField ttime,tbcod,ttnm,tstrdt,tnos;
JComboBox co_id; 
        JButton bopen,bmod,bdel,bnew,badd,bcan;
	Font fo=new Font("Verdana" ,Font.BOLD,20);
	JComboBox tbtime;
String test;
	Connection con;
	ResultSet rs;
	Statement stmt,st;
	PreparedStatement pst;	

        
           Thread datimeThread;
           Date date;
           GregorianCalendar calendar;
           String strDate;

	
//Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);	



	   BatchRec()
	    {
                setSize(480,530);
	    	
	    	setLayout(null);
	    	setTitle("BATCH RECORD");
	    	setResizable(true);
               setLocation(100,160);
	    	
                ladm=new JLabel("STANDARD RECORD");
		ladm.setFont(fo);
		ladm.setForeground(Color.red);		    	
	    	ladm.setBounds(140,30,250,30);
	    	
                lbcod=new JLabel("Batch Id :");
	    	lbcod.setBounds(15,90,100,30);
                tbcod=new JTextField(5);
	    	tbcod.setBounds(100,90,90,25);

	    	lcoid=new JLabel("Course:");
	    	lcoid.setBounds(230,90,90,30);
                co_id=new JComboBox();
	    	co_id.setBounds(320,90,95,25);

                lstrdt=new JLabel("Start Date:");
	    	lstrdt.setBounds(15,150,100,30);
                tstrdt=new JTextField(30);
	    	tstrdt.setBounds(100,150,90,25);


                lbtime=new JLabel("Batch Time:");
	    	lbtime.setBounds(230,150,100,30);
String s1[]={"9am to 10am","11am to 12am","10am to 11 am"};
                tbtime=new JComboBox(s1);
	    	tbtime.setBounds(320,150,95,25);



	    	             		    	
	    	
                bopen=new JButton("Open");
		bopen.addActionListener(this);
	    	bopen.setBounds(50,210,90,25);
                bmod=new JButton("Modify");
		bmod.addActionListener(this);
	    	bmod.setBounds(190,210,90,25);
                bdel=new JButton("Delete");
		bdel.addActionListener(this);
	    	bdel.setBounds(330,210,90,25);
                bnew=new JButton("New");
	    	bnew.addActionListener(this);
	    	bnew.setBounds(50,260,90,25);
                badd=new JButton("Add");
		badd.addActionListener(this);
	    	badd.setBounds(190,260,90,25);
                bcan=new JButton("ALL");
                bcan.addActionListener(this);
	    	bcan.setBounds(330,260,90,25);
	    	    	    	
	         add(ladm);
	        add(lbcod);
	        add(tbcod);
	        add(lstrdt);
	        add(tstrdt);
	        
	        add(lcoid);
	        add(co_id);
	       // add(ltime);
	       // add(ttime);
	           add(lbtime);add(tbtime);     
	    	add(bopen);
	    	add(bmod);
	    	add(bdel);
	    	add(bnew);
	    	add(badd);
	    	add(bcan);

tbcod.addKeyListener(new KeyAdapter()
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
    scrlPane.setBounds(50,300,380,200);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));

    
    model.addColumn("B_ID");
    model.addColumn("C_NAME");
    model.addColumn("S_DATE");
    model.addColumn("B_TIME");

		
		setVisible(true);


                  date=new Date();
                  calendar=new GregorianCalendar();
                  calendar.setTime(date);
                  strDate=calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
                  //tstrdt.setText(strDate);
                  


    try
		{
			Class.forName("com.mysql.jdbc.Driver");
    	    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
            st=con.createStatement();
    					
    					rs=st.executeQuery("select co_name from Course");
    				
    					while(rs.next())
    					{
    					    test=rs.getString(1);	
    					    co_id.addItem(test);
    				    }		
}    
    				catch(Exception e)
	 	            {
	 		           System.out.println("error" +e);
	 	            }
               

	    }
	    
	    public  void actionPerformed(ActionEvent ae)
	    {
	    	  if(ae.getSource()==bnew)
	    	  {
	    	  	 try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    					
    					stmt=con.createStatement();
    					
    					rs=stmt.executeQuery("select b_id from Batch");
    					int i=1;
    					
    					while(rs.next())
    					{
    						i=Integer.parseInt(rs.getString("b_id"));
    					}
    					i++;
					
    					tbcod.setText(""+i);
					
	    	  	 		//tbtime.setText(" ");
	    	  	 		 
	    	  	 	 	 tstrdt.setText(strDate); 


					/*//co_id.setEnabled(true);
					tconm.setEnabled(true);
					tcopd.setEnabled(true);
					
					tcof.setEnabled(true);*/
										
    					
    					rs.close();
    				}
    				catch(Exception e)
	 	            {
	 		           System.out.println("Error"+e);
	 	            }
	    	  	
	    	  }

			if(ae.getSource()==bcan)
 			{
  			  
			   int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from Batch");
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4) });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
			}

			if(ae.getSource()==badd)
			{
				
		   			
    			try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    				
    		PreparedStatement pst = con.prepareStatement("INSERT INTO Batch VALUES(?,?,?,?)");
		               
		  int tbcod1 =Integer.parseInt(tbcod.getText());
		String co_id1 =co_id.getSelectedItem().toString();

 		String tstrdt1=tstrdt.getText();
String ttime=tbtime.getSelectedItem().toString();

		
	                 	pst.setInt(1,tbcod1);
	                    pst.setString(2,co_id1);
	                    
			 pst.setString(3,tstrdt1);
			  pst.setString(4,ttime);

                	    pst.executeUpdate();
                	    JOptionPane.showMessageDialog(null, "Your Record is Submitted");
                	        con.close();
				
				tbcod.setText("  ");
	    			
                                tstrdt.setText(strDate);
	    			tstrdt.setText(" ");

					
	    	
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
	                     stmt= con.createStatement();
	                     String id=tbcod.getText();

	                     
                         String qry= "Select * from Batch where b_id="+id+"";
	                     rs = stmt.executeQuery(qry);
	                     while(rs.next())
                         {
                           
                           
                           tstrdt.setText(rs.getString(3));
					//tbtime.setText(rs.getString(4));
				
                                                      
                         }
       
                        con.close();
	               			
	               	}
	               catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
		  	}		 
	    
	
			if(ae.getSource()==bdel)
	    		  {
	                     try
    	       	{
    		      Class.forName("com.mysql.jdbc.Driver");
    		      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    		      pst=con.prepareStatement("Delete from Batch where b_id=?");
    	
    		      String bcod1=tbcod.getText();
      	          pst.setString(1,bcod1);
      
                  JOptionPane.showMessageDialog(null,"Record Is Deleted ");
                  pst.executeUpdate();
                 	 tbcod.setText("");
           	 //tbtime.setText("");
           	//co_id.setText("");
           	     tstrdt.setText("");
           	              
                   con.close();
                  }
                  catch(Exception ex)
                  {
      	            System.out.println("Error occured :"+ex);
                  }
			}


		if(ae.getSource()==bmod)
			{
    	
    			
    			 try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    					stmt=con.createStatement();
    					
    					String tbcod1=tbcod.getText();
    				   					
    					

					String co_id1=co_id.getSelectedItem().toString();


					String tstrdt1=tstrdt.getText();
                                        String ttime=tbtime.getSelectedItem().toString();
    					    					
    				   String str1="UPDATE Batch SET b_id ='"+tbcod1+"',co_name='"+co_id1+"',date1='"+tstrdt1+"',time='"+ttime+"' where b_id LIKE '" +  tbcod1 +"' ";
    					stmt.executeUpdate(str1);
    					JOptionPane.showMessageDialog(null, "Record is Modified");
    					tbcod.setText("");
		              
		              // tbtime.setText("");
                  tstrdt.setText(strDate);
    					con.close();
				
    				
    					
    				}
    					catch(Exception ex)
		                 {
		                 	System.out.println("Error Occured");
			                System.out.println("Error:"+ex);
	                     }
    		
		}



	    }
	    public static void main (String args[]) 
	    	{
					new BatchRec();
						   }	
	    	
	    	
	    
}
