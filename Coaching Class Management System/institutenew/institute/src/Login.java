 import java.awt.*;
 import java.awt.event.*;
 import javax.swing.*;



 class Login extends JFrame implements ActionListener
{
   JLabel l1,l2;
   JButton b1,b2;
   JTextField t1;
   JPasswordField t2;
   JOptionPane p1=new JOptionPane();
 Container contentPane=getContentPane();
   Login()
   {
    setTitle ("LOGIN"); 
    l1=new JLabel("User Name :"); l1.setForeground(Color.white); 
	  setLocation(400,400);
    l2=new JLabel("Password :"); l2.setForeground(Color.white);
    t1=new JTextField(20);
    t2=new JPasswordField(20);
    
    b1=new JButton("OK");
    contentPane.isOpaque();
    b2=new JButton("Cancel");
    l1.setFont(new Font("Tahoma", Font.BOLD, 16));
    l2.setFont(new Font("Tahoma", Font.BOLD, 16));
    b1.setFont(new Font("Tahoma", Font.BOLD, 16));
    b2.setFont(new Font("Tahoma", Font.BOLD, 16));
    contentPane.add(l1);
    contentPane.setBackground(Color.black);
    contentPane.setLayout(new FlowLayout(FlowLayout.LEFT));
    contentPane.add(l1); contentPane.add(t1); contentPane.add(l2); contentPane.add(t2);
    contentPane.add(b1); contentPane.add(b2); 
    b1.addActionListener(this);   
    b2.addActionListener(this);
    addWindowListener(new WLclass());
    setSize(300,200);
    show();
   }

   public static void main(String args[])
    {
     new Login();
    }

 public void actionPerformed(ActionEvent e)
  {
 String s1;
 String s2;
 if(e.getSource()==b1)
 {
 s1=t1.getText();
s2=t2.getText();

if(s1.equals(""))
{
 p1.showMessageDialog(this,"pls enter UserName");
t1.requestFocus();

}
else if(s2.equals(""))
{
 p1.showMessageDialog(this,"pls enter  Password");
t2.requestFocus();

}
else if(s1.equalsIgnoreCase("admin")==true && s2.equals("admin")==true)
{
dispose();
 MainMenu menu1=new MainMenu();
 menu1.LaunchFrame();	
 menu1.Check_Event();	
 }
 else
{
 p1.showMessageDialog(this,"Invalid login");
t1.setText("");
t2.setText("");
t1.requestFocus();


 }

}
 if(e.getSource()==b2)
 {
 System.exit(0); 
}

}

 class WLclass extends WindowAdapter
 {
  public void windowClosing(WindowEvent e)
   {
    System.exit(0);
   }
 }

}




	    
	  