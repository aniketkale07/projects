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



class Studupdate extends JFrame implements ActionListener
{

	
	JLabel lrno,lsname,lsadd,lspno,lfno,lbat,ldate,ladm,lsgen,lconm,lcopd,lemail;
	JTextField trno,tdate,tsname,tspno,tfno,tsem,tsadd,tcopd,tgen,tbat,tcou,temail;
	JButton bopen,bmod,bcancel,bcle,bdel;
	JPanel top;

	Font fo=new Font("verdana" ,Font.BOLD,20);
	
	Connection con;
	PreparedStatement pst;
	Statement st;
	ResultSet rs;
	String test,test1;

           Thread datimeThread;
           Date date;
           GregorianCalendar calendar;
           String strDate;

       
//Table
    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);
					
	  Studupdate()
	    {
                setSize(580,580);
	    	
               setLayout(null);
	    	setTitle("STUDENT  RECORD");
	    	setResizable(false);
	      	setLocation(300,160);	


		ladm=new JLabel("UPDATED RECORD");
                ladm.setBounds(200,40,300,40);
		ladm.setFont(fo);
		ladm.setForeground(Color.red);	
		add(ladm);
		  	

               lrno=new JLabel("Student Id :");
	    	lrno.setBounds(30,100,100,30);
	    	trno=new JTextField();
	    	trno.setBounds(130,100,110,25);
	    	
	    	
		ldate=new JLabel("Date :");
	    	ldate.setBounds(330,100,45,30);
	    	tdate=new JTextField(15);
	    	tdate.setBounds(390,100,110,25);	
	    	
	    	lsname=new JLabel("Stud Name:");
	    	lsname.setBounds(30,150,120,25);
	    	tsname=new JTextField(150);
	    	tsname.setBounds(130,150,370,25);
	    	
	    	lsadd=new JLabel("Stud Add:");
	    	lsadd.setBounds(30,190,120,25);
	    	tsadd=new JTextField(150);
	    	tsadd.setBounds(130,190,370,25);

                lemail=new JLabel("Email_Id:");
	    	lemail.setBounds(30,230,120,25);
	    	temail=new JTextField(150);
	    	temail.setBounds(130,230,370,25);
	    	
	    	
	    	lsgen=new JLabel("Gender :");
	    	lsgen.setBounds(330,270,80,25);
	    	tgen=new JTextField(150);
        	 tgen.setBounds(390,270,110,25);
                         
	    	lspno=new JLabel("Contact No :");
	    	lspno.setBounds(30,270,90,25);
	    	tspno=new JTextField(20);
	    	tspno.setBounds(130,270,115,25);
	    	
	    		          	
                lconm=new JLabel("Course Name:");
	    	lconm.setBounds(30,310,100,25);
                tcou=new JTextField();
                          tcou.setBounds(130,310,115,25);
	    	
                    
                lbat=new JLabel("Batch id:");
	    	lbat.setBounds(330,305,180,35);
               tbat=new JTextField();
                           tbat.setBounds(390,310,110,25);
	    	

		bopen=new JButton("Search");
	    	bopen.setBounds(20,360,90,25);
		bopen.addActionListener(this);
	    	bmod=new JButton("Modify");
	    	bmod.setBounds(120,360,90,25);
                bmod.addActionListener(this) ;
	    	bcle=new JButton("Clear");
	    	bcle.setBounds(240,360,90,25);
	    	bcle.addActionListener(this);
	    	bcancel=new JButton("ALL");
                bcancel.addActionListener(this);
	    	bcancel.setBounds(350,360,90,25);
	       	
			bdel = new JButton("delete");
			bdel.setBounds(470,360,90,25);
					bdel.addActionListener(this);
			add(bdel);
		
		//add(lfno);
		//add(tfno);
                add(lrno);
                add(trno);
	    	add(ladm);
	    	add(lsname);
	    	add(tsname);
	    	add(lsadd);
	    	add(tsadd);
	    	add(lsgen);
                add(tgen);
                add(lspno);
                add(tspno);
		add(lconm);
		add(tcou);
                add(tbat);
                add(lbat);
		add(ldate);
		add(tdate);
                 add(bmod);
                 add(bcle);
	    	add(bcancel);
		add(bopen);
                add(lemail);
                add(temail);

 trno.addKeyListener(new KeyAdapter()
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

tgen.addKeyListener(new KeyAdapter()
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

tbat.addKeyListener(new KeyAdapter()
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

tsname.addKeyListener(new KeyAdapter()
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
tcou.addKeyListener(new KeyAdapter()
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
 scrlPane.setBounds(0,400,580,220);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));

  model.addColumn("S_ID");
    model.addColumn("S_NAME");
    model.addColumn("S_ADD");
    model.addColumn("E_ID");
    model.addColumn("S_PHONE");
    model.addColumn("S_GENDER");
    model.addColumn("C_NAME");
    model.addColumn("B_TIME");
		
		
  
   	
		setVisible(true);

					
					trno.setEnabled(true);
					/*tsname.setEnabled(false);
					tsadd.setEnabled(false);
					tspno.setEnabled(false);
					tbat.setEnabled(false);
					tgen.setEnabled(false);
					tcou.setEnabled(false);
					tdate.setEnabled(false);*/
 
                  date=new Date();
                  calendar=new GregorianCalendar();
                  calendar.setTime(date);
                  strDate=calendar.get(Calendar.DATE)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.YEAR);
                 tdate.setText(strDate);
                  
		           
	    }

	    public void actionPerformed(ActionEvent ae)
	    {
	    	if(ae.getSource()==bcle)
	    	{
	    	trno.setText("  ");
	    	tcou.setText("  ");
	    	tsname.setText(" ");
	    	tspno.setText(" ");
	    	tsadd.setText(" ");
	    	tgen.setText(" ");
	    	tbat.setText("  ");
                tdate.setText(strDate);
                temail.setText("  ");

		/*tsname.setEnabled(false);
		tsadd.setEnabled(false);
		tspno.setEnabled(false);
		tbat.setEnabled(false);
		tgen.setEnabled(false);
		tcou.setEnabled(false);
		tdate.setEnabled(false);*/
	    		
	    	}
                if(ae.getSource()==bcancel)
                {
                  int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from Admission" );
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getString(3),rs.getString(4),rs.getString(6),rs.getString(5),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10) });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
                }
		
		if(ae.getSource()==bmod)
                        {
                            
			String s3=tspno.getText();
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
    					
					int tfno1=Integer.parseInt(trno.getText());

    					int trno1=Integer.parseInt(trno.getText());
    					
					String tdate1=tdate.getText();

    					String tsname1=tsname.getText();

					String tsadd1=tsadd.getText();
                                       
                                        String e1=temail.getText();
    					
    					String tspno1=tspno.getText();

					String tgen1=tgen.getText();

					String tcou1=tcou.getText();

					String tbat1=tbat.getText();
    					    					
     String str1="UPDATE Admission SET Add_id ="+tfno1+",stu_name ='"+tsname1+"',stu_add='"+tsadd1+"',Email_id='"+e1+"',stu_phno='"+tspno1+"',stu_gender='"+tgen1+"'where Add_id="+tfno1+"";
    					st.executeUpdate(str1);
    					JOptionPane.showMessageDialog(null, "Record is Modified");
    					trno.setText("  ");
	    				tcou.setText("  ");
	    				tsname.setText(" ");
	   			 	tspno.setText(" ");
	 			   	tsadd.setText(" ");
				    	tgen.setText(" ");
				    	tbat.setText("  ");
                                        tdate.setText("");
                                        temail.setText("  ");

    					con.close();
				   				
    					
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
	                     String id=trno.getText();
	                     
                         String qry= "select * from Admission where stu_id="+id+"";
	                     rs = st.executeQuery(qry);
	                     while(rs.next())
                         {
                           tsname.setText(rs.getString(4));
                           tsadd.setText(rs.getString(6));
                           tspno.setText(rs.getString(7));
			    tgen.setText(rs.getString(8));
			tcou.setText(rs.getString(9));
			tbat.setText(rs.getString(10));
			tdate.setText(rs.getString(2));	
                        temail.setText(rs.getString(5));

				/*tsname.setEnabled(true);
				tsadd.setEnabled(true);
				tspno.setEnabled(true);
				tbat.setEnabled(true);
				tgen.setEnabled(true);
				tcou.setEnabled(true);
				tdate.setEnabled(true);*/
                         }
       
                        con.close();
	               			
	               	}
	               catch(Exception ex)
		                 {
			                System.out.println("Error:"+ex);
	                     }
		  			 
	    
		}
		// Delete Record 
		if(ae.getSource()==bdel)
			{

		 try
    	       	{
    		      Class.forName("com.mysql.jdbc.Driver");
    		      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","");
    		      pst=con.prepareStatement("Delete from Admission where stu_id=?");
    	
    		      String trno1=trno.getText();
      	          pst.setString(1,trno1);
      
                  JOptionPane.showMessageDialog(null,"Record Is Deleted ");
                  pst.executeUpdate();
                  trno.setText("");
           	     tsname.setText("");
           	     tsadd.setText("");
           	     tspno.setText("");
		    tgen.setText("");
           	    tcou.setText("");
           	     tbat.setText("");
                     temail.setText("");
           	     //tdate.setText(" ");
         
                   con.close();
		
					/*trno.setEnabled(true);
					tsname.setEnabled(false);
					tsadd.setEnabled(false);
					tspno.setEnabled(false);
					tbat.setEnabled(false);
					tgen.setEnabled(false);
					tcou.setEnabled(false);
		           		//tdate.setEnabled(false);*/

                  }
                  catch(Exception ex)
                  {
      	            System.out.println("Error occured :"+ex);
                  }
			

	    }
	}

	    public static void main (String args[]) 
	    	{
					new Studupdate();
						   }	
	    	
	    	
	    
		}
	
