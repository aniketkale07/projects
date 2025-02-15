import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MainMenu extends JFrame 
{
	 public JFrame frame1;
	 public JPanel top,lower;
	
	JLabel leli;	

	JMenuBar mbr=new JMenuBar();

	JMenu A_form=new JMenu("Admission");
	
	JMenu tea=new JMenu("Faculty");

		 JMenu cour=new JMenu("Course");
	 
	 JMenu stu=new JMenu("Student");
	 
	 JMenu batch=new JMenu("Batches");
         JMenu exam=new JMenu("Exam");

	
		  JMenu bill=new JMenu("Receipt");
	 
	JMenu exit=new JMenu("Logout");

   
	 
	 
	
	 
	 String temp;
	  public MainMenu()
	 {
	 	frame1=new JFrame();
	 	//addWindowListener(new WLclass());
        // setSize(1024,738);
          //show();
          
}
	 //}

 	
//student
        JMenuItem addts=new JMenuItem(" add ");
		JMenuItem search=new JMenuItem(" Search ");
		JMenuItem delete=new JMenuItem(" Delete ");
		JMenuItem modify=new JMenuItem(" Update ");

//teacher 
	 
    	JMenuItem opent=new JMenuItem(" Open ");
		JMenuItem modifyt=new JMenuItem("Update ");
		JMenuItem addt=new JMenuItem(" add ");
		JMenuItem deletet=new JMenuItem(" Delete ");
		  
	 
//Courses
    	JMenuItem addc=new JMenuItem(" Add");
		JMenuItem modc=new JMenuItem(" Update ");
		JMenuItem deletec=new JMenuItem(" Delete ");
		JMenuItem searchc=new JMenuItem(" Search ");
	 
//Courses
    	JMenuItem addb=new JMenuItem(" Add");
		




//bill

		JMenuItem openbl=new JMenuItem(" Open ");
		JMenuItem delbl=new JMenuItem(" Delete");
		JMenuItem printbl=new JMenuItem(" Add ");
 
	    
//admission form
		JMenuItem fresh=new JMenuItem("New Admission");
        JMenuItem fresh1=new JMenuItem("Enquiry");

//Exam
      //  JMenuItem exam1=new JMenuItem("Exam Details");
      //  JMenuItem exam2=new JMenuItem("Result");


//exit
		JMenuItem exit1=new JMenuItem("Exit");
		//show();

 public void LaunchFrame()	
 {
	 	frame1.setSize(1023,735);
	 	frame1.setLayout(null);
	 	frame1.setTitle("DAnce Academy System");
	    	frame1.setResizable(false);
	 	frame1.add(mbr);


		leli=new JLabel(new ImageIcon("44.jpg"));
	    	leli.setBounds(0,0,1023,1000);
	    frame1.add(leli);
               /*Font fo=new Font("Monotype Corsiva" ,Font.BOLD,70);
	    	leli.setFont(fo);
	    	leli.setForeground(Color.red);
	
		top=new JPanel();
		top.setLayout(null);
		top.setBorder(BorderFactory.createEtchedBorder(1));
	     top.setBackground(Color.pink);     
		top.setBounds(0,10,1023,100);
	    	frame1.add(top);
		top.add(leli);

		
		lower=new JPanel();
		lower.setLayout(null);
		lower.setBorder(BorderFactory.createEtchedBorder(1));
	     lower.setBackground(Color.gray);     
		lower.setBounds(0,105,1023,800);
	    	frame1.add(lower);*/

//admission form
		 mbr.add(A_form);
		A_form.add(fresh);
A_form.add(fresh1);
//teacher	 	 	
		mbr.add(tea);
		tea.add(opent);
		tea.add(modifyt);
		tea.add(addt);
		tea.add(deletet);

	 
//faculty
   		 mbr.add(cour);
		cour.add(modc);
		cour.add(addc);
		cour.add(deletec);
		cour.add(searchc);
	mbr.add(batch);
		
		batch.add(addb);
		//student
       		 mbr.add(stu);
		stu.add(search);
		stu.add(delete);
		stu.add(modify);
        

//bill
		   mbr.add(bill);
		   bill.add(printbl);
		bill.add(openbl);
		bill.add(delbl);
		

       mbr.add(exit);
		exit.add(exit1);

           
        	 	
	 	frame1.setJMenuBar(mbr);
	 	frame1.setVisible(true);
	// }
	 
}
 public void Check_Event()
 {
//faculty
     addc.addActionListener(new ActionListener()
		{
	   
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==addc)
	   	  	{
	   	  	   Course cr=new Course();
	   	  	}
	   	  }	   	  
	   });
	   

	 deletec.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==deletec)
			{
	   	         Course crd=new Course();
             
             		}
	   	  	
	   	  }   	  
	   });
	   
	   modc.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==modc)
	   	  	{
	   	  	 
	   	  	   Course crm=new Course();
             	   	}
            		   	  	
	   	  }   	  
	   });

		 searchc.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==searchc)
	   	  	{
	   	  	 
	   	  	   Course crm=new Course();
             	   	}
            		   	  	
	   	  }   	  
	   });	
//admission form 	 

		 fresh.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==fresh)
	   	  	{
	   	  	 
	   	  	   Addmission afm=new Addmission();
             
             	
            	
            	
	   	  	}
	   	  }   	  
	   });
           
              fresh1.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==fresh1)
	   	  	{
	   	  	 
	   	  	   enqfrm afm=new enqfrm();
             
             	
            	
            	
	   	  	}
	   	  }   	  
	   });
//exit
 addb.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==addb)
	   	  	{
	   	  	 
	   	  	   BatchRec abt=new BatchRec();
             
             	
            	
            	
	   	  	}
	   	  }   	  
	   });

		exit1.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==exit1)
	   	  	{
	   	  	 
	   	  	   System.exit(0);
                                   	
	   	  	}
	   	  }   	  
	   });
//student
		modify.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==modify)
	   	  	{
	   	  	 
	   	  	   Studupdate srm=new Studupdate();
	   	  	}
	   	  }   	  
	   });

		delete.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==delete)
	   	  	{
	   	  	 
	   	  	  Studupdate srm=new Studupdate();
                                   	
	   	  	}
	   	  }   	  
	   });

	search.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==search)
	   	  	{
	   	  	 
	   	  	   Studupdate srm=new Studupdate();
	   	  	}
	   	  }   	  
	   });
//TEACHER

		addt.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==addt)
	   	  	{
	   	  	 
	   	  	   Techadd tadd=new Techadd();
	   	  	}
	   	  }   	  
	   });


	deletet.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==deletet)
	   	  	{
	   	  	 
	   	  	   Techerod tdel=new Techerod();
	   	  	}
	   	  }   	  
	   });

	modifyt.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==modifyt)
	   	  	{
	   	  	 
	   	  	   Techupdate tmod=new Techupdate();
			   tmod.show();
			   dispose();
	   	  	}
	   	  }   	  
	   });


	     opent.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==opent)
	   	  	{
	   	  	 
	   	  	   Techerod topen=new Techerod();
	   	  	}
	   	  }   	  
	   });


		/*deleteb.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==deleteb)
	   	  	{
	   	  	 
	   	  	   batrec op=new batrec();
	   	  	}
	   	  }   	  
	   });

	
		addb.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==addb)
	   	  	{
	   	  	 
	   	  	   batrec op=new batrec();
	   	  	}
	   	  }   	  
	   });*/



//bill

		printbl.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==printbl)
	   	  	{
	   	  	 
	   	  	   stubill print=new stubill();
	   	  	}
	   	  }   	  
	   });

		openbl.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==openbl)
	   	  	{
	   	  	 
	   	  	   try {
				stubillOpen openbill=new stubillOpen();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	  	}
	   	  }   	  
	   });

		delbl.addActionListener(new ActionListener()
		{
	   	  public void actionPerformed(ActionEvent ae)
	   	  {
	   	  	if(ae.getSource()==delbl)
	   	  	{
	   	  	 
	   	  	   stubilld delbill=new stubilld();
	   	  	}
	   	  }   	  
	   });


//Exam

 
	 }
public static void main(String args[])
{
    MainMenu menu1=new MainMenu();
menu1.LaunchFrame();	
	menu1.Check_Event();	

}
	 
}
