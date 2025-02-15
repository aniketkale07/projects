import java.util.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.event.*;

import javax.swing.plaf.*;

import javax.swing.table.*;

import java.sql.*;

import java.sql.ResultSet;

class Exam extends JFrame implements ActionListener


{

JLabel studid,studnm,examnm,type,examfee,examdate,examdetails,ladm;
JTextField tid,tfee,tdt,tstudnm;
JButton bsave,bclear,ball,bdelete,bopen;	
JPanel top;



	Font fo=new Font("verdana" ,Font.BOLD,20);

	

	Connection con;

	Statement st;

	ResultSet rs;

        JComboBox enm,ct;


DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);



      Exam()
      
       {
                setSize(630,570);

                setLayout(null);

	    	setTitle("EXAM DETAILS");

	    	setResizable(false);

	      	setLocation(300,160);



		ladm=new JLabel("EXAM DETAILS");

               ladm.setBounds(230,30,380,30);

		ladm.setFont(fo);

		ladm.setForeground(Color.red);

		add(ladm);


               studid=new JLabel("Student Id :");

	    	studid.setBounds(30,90,100,30);

	    	tid=new JTextField(30);

	    	tid.setBounds(130,90,400,25);


               studnm=new JLabel("Stud Name:");

	    	studnm.setBounds(30,130,100,25);

	    	tstudnm=new JTextField(150);

	    	tstudnm.setBounds(130,130,400,25);



               examnm=new JLabel("Exam Name:");

	    	examnm.setBounds(30,170,100,25);

	    	 enm=new JComboBox ();

             enm.addItem("Java");

             enm.addItem("PHP");
             enm.addItem("TCS");
             enm.addItem(".Net");
             enm.addItem("RDBMS");
             enm.addItem("Syspro");
             enm.addItem("Adv Java");

          	enm.setBounds(130,170,400,25);



				type=new JLabel("Type :");

	   	type.setBounds(30,210,100,25);

	    	 ct=new JComboBox ();

              ct.addItem("Fresher");

            ct.addItem("Backlog");

           ct.setBounds(130,210,400,25);



			 examfee=new JLabel("Exam Fee :");

	    	examfee.setBounds(30,250,100,25);

	    	tfee=new JTextField();

	    	tfee.setBounds(130,250,400,25);



	       examdate=new JLabel("Exam date :");

	    	examdate.setBounds(30,290,100,30);

	    	tdt=new JTextField();

	    	tdt.setBounds(130,290,400,25);


                bsave=new JButton("Save");

	        bsave.setBounds(20,340,90,25);

	        bsave.addActionListener(this);



	        bclear=new JButton("Clear");

	    	bclear.setBounds(145,340,90,25);

	    	bclear.addActionListener(this);



                bdelete=new JButton("Delete");

	    	bdelete.setBounds(265,340,90,25);

	    	bdelete.addActionListener(this);


	        ball=new JButton("All");

	    	ball.setBounds(385,340,90,25);

	    	ball.addActionListener(this);

                bopen=new JButton("Open");
                bopen.setBounds(510,340,90,25);
                bopen.addActionListener(this);



                add(studid);

		add(tid);

                add(studnm);

                add(tstudnm);

	    	add(examnm);

	    	add(enm);

	    	add(type);

                add(ct);

	    	add(examfee);

	    	add(tfee);

	    	add(examdate);

	    	add(tdt);

                add(ladm);
                 
                add(bsave);
               
                add(bclear);

                add(ball);
            
                add(bdelete);

                add(bopen);
			setVisible(true);



tdt .addKeyListener(new KeyAdapter() {
    public void keyTyped(KeyEvent e) {
      char c = e.getKeyChar();
      if (!((c >= '0') && (c <= '9') ||
         (c == KeyEvent.VK_BACK_SPACE) ||
         (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH)))        
      {
        JOptionPane.showMessageDialog(null, "Please Enter Valid Date");
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


tfee.addKeyListener(new KeyAdapter()
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


tstudnm.addKeyListener(new KeyAdapter()
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



 scrlPane.setBounds(0,400,627,200);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));

    
    model.addColumn("S_ID");
    model.addColumn("S_NAME");
    model.addColumn("E_NAME");
    model.addColumn("S_TYPE");
    model.addColumn("E_FEE");
    model.addColumn("E_DATE");

		
		setVisible(true);


		}


public void actionPerformed(ActionEvent e)

	{

			if(bsave==e.getSource())

		{

          try

	{
               Class.forName("com.mysql.jdbc.Driver");
    				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
    				
    		        PreparedStatement pst = con.prepareStatement("INSERT INTO Exam VALUES(?,?,?,?,?,?)");
		               
		        int tid1 =Integer.parseInt(tid.getText());
		                
	                 String tstudnm1=tstudnm.getText();

	                 String enm1=enm.getSelectedItem().toString();

                         String ct1=ct.getSelectedItem().toString();

	                 String tfee1=tfee.getText();


			 String tdt1 =tdt.getText();
                            pst.setInt(1,tid1);
	                    pst.setString(2,tstudnm1);
	                    pst.setString(3,enm1);
			    pst.setString(4,ct1);
                            pst.setString(5,tfee1);    
			    pst.setString(6,tdt1);
                	    pst.executeUpdate();
                	    JOptionPane.showMessageDialog(null, "Your Record is Submitted");
                	     con.close();

                          tid.setText("");
	    	  	  tstudnm.setText("");
	    	  	  tfee.setText("");
	    	  	  tdt.setText(""); 
             
    				}
              catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
 

		              
		         

	    }

	    	  if(e.getSource()==bclear)
	    	  {
	    	  	  tid.setText(" ");
	    	  	  tstudnm.setText(" ");
	    	  	  tfee.setText(" ");
	    	  	  tdt.setText(" "); 
	    	  }

              if(e.getSource()==ball)
 			{
			   int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from Exam" );
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6) });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
			}


     if(e.getSource()==bdelete)
	    		  {
	                     try
    	       	{
    		      Class.forName("com.mysql.jdbc.Driver");
    		      con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
    		   PreparedStatement pst1=con.prepareStatement("Delete from Exam where s_id=?");
    	
    		      String tid1=tid.getText();
      	          pst1.setString(1,tid1);
      
                  JOptionPane.showMessageDialog(null,"Record Is Deleted ");
                  pst1.executeUpdate();
                 	 tid.setText("");
           	 tstudnm.setText(" ");
	    	  	  tfee.setText(" ");
	    	  	  tdt.setText(" "); 
                   con.close();
                  }
                  catch(Exception ex)
                  {
      	            System.out.println("Error occured :"+ex);
                  }
			}


if(e.getSource()==bopen)
	      {
	                     try
	               	{
	               	     Class.forName("com.mysql.jdbc.Driver");
	                     con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
	                     st= con.createStatement();
	                     String id=tid.getText();
	                     
                         String qry= "select * from Admission where stu_id="+id+"";
	                     rs = st.executeQuery(qry);
	                     while(rs.next())
                         {
                           tstudnm.setText(rs.getString(4));
                          
			

				/*tsname.setEnabled(true);
				tsadd.setEnabled(true);
				tspno.setEnabled(true);
				tbat.setEnabled(true);
				tgen.setEnabled(true);
				tcou.setEnabled(true);*/
				
                         }
       
                        con.close();
	               			
	               	}
	               	 catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
		  			 
	    
		}



        }

     public static void main (String args[])

               	    	{

					new Exam();

		          }

}

 
