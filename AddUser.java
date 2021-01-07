import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class AddUser extends JFrame implements ActionListener{
    private JTextField tf1;
    private JPasswordField jPf1; 
    JLabel jl1,jl2;
    JButton jB1,jB2;
    private Font f;
    Container c;
     DataAccess da2=new DataAccess();
   ResultSet rs=null;
    public void actionPerformed(ActionEvent e){
              boolean flag=true;
		String userName=this.tf1.getText();
		String password=this.jPf1.getText();
		String s=e.getActionCommand();
		
		if(s.equals("Back")){
			this.dispose();
                        new MainFrame();
		}
		else{
			if(userName.length()==0 || password.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide User name and Password");
				flag=false;
			}
                        else if(flag && s.equals("Save")){
				String sql="INSERT INTO user (username, password) VALUES ('"+userName+"','"+password+"')";
				da2.updateDB(sql);
				
				}
			}
		}		
	
    AddUser()
    {
        
      intiComponents();
    }
    public void intiComponents()
    {
            this.setVisible(true);
            c = this.getContentPane();
            c.setLayout(null);
            c.setBackground(Color.DARK_GRAY);
            f=new Font("arial",Font.BOLD,18);
            this.setTitle("AddUser Frame");
            this.setLayout(null);
            this.setBounds(500,500,500,500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tf1= new JTextField();
            tf1.setBounds(250, 100, 200, 50);
            jPf1= new JPasswordField();
            jPf1.setBounds(250, 200, 200, 50);
            jPf1.setEchoChar('*');

            jl1=new JLabel();
            jl1.setText("Enter your id");
            jl1.setBounds(50,100,150,50);
            jl1.setFont(f);
            jl1.setForeground(Color.WHITE);

            jl2=new JLabel();
            jl2.setText("Enter your Password");
            jl2.setBounds(50,200,200,50);
            jl2.setFont(f);
            jl2.setForeground(Color.WHITE);

            jB1=new JButton("Save");
            jB1.setBounds(90,300,150,50);

            jB2=new JButton("Back");
            jB2.setBounds(250,300,150,50);
           
            jB1.addActionListener(this);
            jB2.addActionListener(this);
            

            this.add(tf1);
            this.add(jPf1);
            this.add(jl1);
            this.add(jl2);
            this.add(jB1);
            this.add(jB2);
       
      
    }
    
    public static void main(String[] args) {
        AddUser aU1=new AddUser();
       
    }
    
}
