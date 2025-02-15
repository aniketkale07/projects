import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LaunchFrame implements ActionListener
{
JLabel leli;
JButton b1;
JFrame f1;

public  LaunchFrame()	
 {
	f1=new JFrame();
	 	f1.setLayout(null);
	 	
leli=new JLabel(new ImageIcon("44.jpg"));
	    leli.setBounds(0,0,500,500);
	    f1.add(leli);
		b1=new JButton("ok");
		f1.add(b1);
		b1.setBounds(100,100,100,50);
		b1.addActionListener(this);
		 f1.setSize(1023,735);
		f1.setTitle("DAnce Academy System");
		f1.setVisible(true);
	    	f1.setResizable(false);
			
		}
		public void actionPerformed(ActionEvent e)
		{
			if(b1==e.getSource())
			{
			leli=new JLabel(new ImageIcon("44.jpg"));
	    	leli.setBounds(0,0,500,500);
	    f1.add(leli);
			}
		}
		public static void main(String args[])
{
    LaunchFrame menu1=new LaunchFrame();
//menu1.LaunchFrame();	
	//menu1.Check_Event();	

}
}