import java.util.*;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.event.*;

import javax.swing.plaf.*;

import javax.swing.table.*;

import java.sql.*;

import java.sql.ResultSet;

class Result extends JFrame implements ActionListener

{

JLabel studid,studnm,examnm,ladm,result;
JTextField tid;
JButton bok,bcancel,ball,bnew;
JPanel top;


	Font fo=new Font("verdana" ,Font.BOLD,20);

	

	Connection con=null;

	Statement st=null;

	ResultSet rs;

        JComboBox snm,enm,res;


    DefaultTableModel model = new DefaultTableModel();
    JTable tabGrid = new JTable(model);
    JScrollPane scrlPane = new JScrollPane(tabGrid);

 Result()

  {
                setSize(630,570);

                setLayout(null);

	    	setTitle("RESULT DETAILS");

	    	setResizable(false);

	      	setLocation(300,160);



		ladm=new JLabel("RESULT");

               ladm.setBounds(270,30,380,30);

		ladm.setFont(fo);

		ladm.setForeground(Color.red);

		add(ladm);



               studid=new JLabel("Student Id :");

	    	studid.setBounds(30,90,100,30);

	    	tid=new JTextField(30);

	    	tid.setBounds(130,90,400,25);


               studnm=new JLabel("Stud Name:");

	    	studnm.setBounds(30,130,100,25);

                snm=new JComboBox ();

                   
          	snm.setBounds(130,130,400,25);


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


              result=new JLabel("Result :");

	   	result.setBounds(30,210,100,25);

	    	 res=new JComboBox ();

              res.addItem("Pass");

            res.addItem("Fail");
         
            res.addItem("Fail A.T.K.T");

           res.setBounds(130,210,400,25);


               bnew=new JButton("New");

	        bnew.setBounds(80,280,90,25);

               bok=new JButton("OK");

	        bok.setBounds(200,280,90,25);
bcancel=new JButton("Clear");

	    	bcancel.setBounds(320,280,90,25);

                ball=new JButton("ALL");
                ball.setBounds(440,280,90,25);



try{
  Class.forName("com.mysql.jdbc.Driver");
  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
    
   String sql="select stu_name from admission";
   st=con.createStatement();
   ResultSet rs=st.executeQuery(sql);
   while(rs.next())
    snm.addItem(rs.getString(1));
    rs.close();
   }catch(Exception x) {   }



	        bnew.addActionListener(this);
bok.addActionListener(this);
bcancel.addActionListener(this);

ball.addActionListener(this);



                add(studid);
               add(bcancel);
		add(tid);
               add(bok);add(bnew);

               add(studnm);
                add(result);
               add(examnm);
                add(ladm);
                add(snm);
                add(enm);
                 add(res);
                   add(ball);
               			setVisible(true);


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


scrlPane.setBounds(0,350,627,200);
   add(scrlPane);
    tabGrid.setFont(new Font ("Verdana",0,13));

    
    model.addColumn("S_ID");
    model.addColumn("S_NAME");
    model.addColumn("E_NAME");
    model.addColumn("S_RESULT");


		
		setVisible(true);


		}


public void actionPerformed(ActionEvent ae)
{

if(ae.getSource()==bnew)
	      {
	                    try
    				{
    					Class.forName("com.mysql.jdbc.Driver");
    					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
    					
    					st=con.createStatement();
    					
    	rs=st.executeQuery("select s_id from Exam");
    					int i=1;
    					
    					while(rs.next())
    					{
    						i=(rs.getInt("s_id"));
    					}
    					i++;
    					tid.setText(""+i);
					
										
    					
    					rs.close();
    				}
    				catch(Exception e1)
	 	            {
	 		           System.out.println("error" +e1);
	 	            }
			}


			if(ae.getSource()==ball)
                {
                    int r = 0;
        
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection cnDriver = DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
                Statement stmt = cnDriver.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery("SELECT * from Result");
				
                while(rs.next())
                {
                	model.insertRow(r++, new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4) });
                }
            }

            catch(Exception ex)
            {
                    JOptionPane.showMessageDialog(null,"Error Occured : " + ex);
            }
                }

if(ae.getSource()==bok)
                {


try
    				{
    				Class.forName("com.mysql.jdbc.Driver");
    				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/institute","root","root");
    				
    		        PreparedStatement pst = con.prepareStatement("INSERT INTO Result VALUES(?,?,?,?)");
		               
		        int fno1 =Integer.parseInt(tid.getText());
		                
	                 
			
	     String tsname1=snm.getSelectedItem().toString();
          String tsname11=enm.getSelectedItem().toString();
String tsname111=res.getSelectedItem().toString();

  pst.setInt(1,fno1);
	                    pst.setString(2,tsname1);
	                    pst.setString(3,tsname11);
			    pst.setString(4,tsname111);
pst.executeUpdate();
                	    JOptionPane.showMessageDialog(null, "Your Record is Submitted");
                	        con.close();
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




}












public static void main (String args[])

               	    	{

			new Result();

		        }
}








