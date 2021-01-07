import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame implements ActionListener{
    private ImageIcon icon;
    private Container p;
    private JButton user,user1,user2,user3,user4,user5;
    private JLabel jl1;
    private Font f;
   MainFrame(){
       
       initComponents();
    }  
   public void actionPerformed(ActionEvent ae) {
           
       if(ae.getSource()==user1)
       {
           this.dispose();
           new InsertItem();
       }
       else if(ae.getSource()==user3)
       {
           this.dispose();
           new DeleteItem();
       }
       else if(ae.getSource()==user4)
       {
           this.dispose();
           new LoginFrame().setVisible(true);
       }
       else if(ae.getSource()==user5)
       {
           this.dispose();
           new AddUser();
       }
       else if(ae.getSource()==user)
       {
           this.dispose();
           new placeOrder();
       }
       else if(ae.getSource()==user2)
       {
           this.dispose();
           new Account();
       }
            
    }
     
   public void initComponents(){
       p=this.getContentPane();
       p.setLayout(null);
      p.setBackground(Color.DARK_GRAY);
      
      this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(200, 200,600,400);
        this.setTitle("Main Frame");
        
        jl1=new JLabel();
        jl1.setText("Welcome TO Cafe Pomp");
        jl1.setBounds(150,1,250,50);
        p.add(jl1);
        f=new Font("arial",Font.BOLD,20);
        jl1.setFont(f);
        jl1.setForeground(Color.orange);
     
      user=new JButton("Place Order");
     user.setBounds(50,80,150,50);
     p.add(user);
     
     user1=new JButton("Insert Item");
     user1.setBounds(350,80,150,50);
     p.add(user1);
     
     user2=new JButton("Account");
     user2.setBounds(50,200,150,50);
     p.add(user2);
     
     user3=new JButton("Delete Item");
     user3.setBounds(350,200,150,50);
     p.add(user3);
     
     
     user4=new JButton("Log out");
     user4.setBounds(350,280,150,50);
     p.add(user4);
     
     user5=new JButton("Add User");
     user5.setBounds(200,140,150,50);
     p.add(user5);
     user1.addActionListener(this);
     user3.addActionListener(this);
     user4.addActionListener(this);
     user5.addActionListener(this);
     user.addActionListener(this);
     user2.addActionListener(this);
     
    //icon=new ImageIcon(getClass().getResource("88.jpg"));
        //this.setIconImage(icon.getImage());
    
}
    public static void main(String[] args) {
        MainFrame f1 =new MainFrame();

        
     
        
    }
}
