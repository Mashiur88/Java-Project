import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginFrame extends JFrame implements ActionListener {
    JTextField tf1;
    JPasswordField jPf1; 
   JLabel jl1,jl2;
   JButton jB1,jB2;
   private Font f;
   DataAccess da=new DataAccess();
   ResultSet rs=null;
    Container c;
    
 
   @Override
    public void actionPerformed(ActionEvent e){
              boolean flag=true;
		String userName=this.tf1.getText();
		String password=this.jPf1.getText();
		String s=e.getActionCommand();
		
		if(s.equals("Cancel")){
			System.exit(0);
		}
		else{
			if(userName.length()==0 || password.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide User name and Password");
				flag=false;
			}
			if(flag && s.equals("Login")){
				String sql="select * from user where username='"+userName+"' and password='"+password+"'";
				rs=da.getData(sql);
				try{
					if(rs.next()){
						this.dispose();
						new MainFrame();
					}
					else{
						JOptionPane.showMessageDialog(null,"You must need to provide Valid User name and Password");
					}
					da.close();
				}catch(SQLException ex){
					ex.printStackTrace();
				}
			}
		}		
	}
  LoginFrame()
{
   
 initComponents();

}
public void initComponents()
{     
       
     c = this.getContentPane();
     c.setLayout(null);
     c.setBackground(Color.DARK_GRAY);
     f=new Font("arial",Font.BOLD,18);
     
      this.setTitle("Login Frame");
       this.setLayout(null);
       this.setBounds(300,300,500,500);
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       tf1= new JTextField();
       tf1.setBounds(250, 150, 200, 50);
       jPf1= new JPasswordField();
       jPf1.setBounds(250, 250, 200, 50);
       jPf1.setEchoChar('*');
       
       jl1=new JLabel();
       jl1.setText("Enter your id");
       jl1.setBounds(50,150,150,50);
       jl1.setFont(f);
       jl1.setForeground(Color.WHITE);
       
       jl2=new JLabel();
       jl2.setText("Enter your Password");
       jl2.setBounds(50,250,200,50);
       jl2.setFont(f);
       jl2.setForeground(Color.WHITE);
       
       jB1=new JButton("Login");
       jB1.setBounds(90,350,150,50);
       
       jB2=new JButton("Cancel");
       jB2.setBounds(250,350,150,50);
       
       jB1.addActionListener(this);
       jB2.addActionListener(this);
       
       c.add(tf1);
       c.add(jPf1);
       c.add(jl1);
       c.add(jl2);
       c.add(jB1);
       c.add(jB2);
       
      
       
}
   

    public static void main(String[] args) {
        LoginFrame lf1=new LoginFrame();
        lf1.setVisible(true);
        
        
        
    }
}
    

