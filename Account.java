import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class Account extends JFrame implements ActionListener {
    Container c;
    JTextField jT1;
    JLabel jL1,jL2;
    JButton jB1,jB2,jB3,jB4;
    Font f;
    Receipt r1=new Receipt();
    int x;
    static int day;
    DataAccess da3=new DataAccess();
    DataAccess da2=new DataAccess();
    DataAccess da=new DataAccess();
    ResultSet rs=null;
    
     public void actionPerformed(ActionEvent e){
        System.out.println("Button Pressed");
        if(e.getSource()==jB1)
        {
            this.dispose();
            new MainFrame();
        }
     if(e.getSource()==jB2)
        {
           addCost();
        }
     if(e.getSource()==jB3)
        {
            this.dispose();
            new OverView();
        }
     
     
     }
    
    Account()
    {
        intiComponents();
        
    }
    public void intiComponents()
    {
        c=this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);
        this.setVisible(true);
           f=new Font("arial",Font.BOLD,18);
            this.setTitle("Account Frame");
            this.setLayout(null);
            this.setBounds(500,500,500,500);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           jL1=new JLabel();
           jL1.setText("Account");
           jL1.setBounds(200,10,150,50);
           c.add(jL1);
           jL1.setFont(f);
           jL1.setForeground(Color.white);
           jL2=new JLabel();
           jL2.setText("Cost");
           jL2.setBounds(30,60,150,50);
           jL2.setFont(f);
           jL2.setForeground(Color.white);
           c.add(jL2);
           jT1=new JTextField();
           jT1.setBounds(100,60,150,40);
           this.add(jT1);
           jB1=new JButton("Back");
           jB1.setBounds(360,350,100,50);
           c.add(jB1);
           jB2=new JButton("Ok");
           jB2.setBounds(180,120,70,20);
           c.add(jB2);
           jB3=new JButton("Overview");
           jB3.setBounds(100,190,100,50);
           c.add(jB3);
         
          jB1.addActionListener(this);
          jB2.addActionListener(this);
          jB3.addActionListener(this);
         
     }
    public int dailyCost()
        {
            
            try {                                    
            String query = "SELECT OrderCost FROM daily";           
            rs = da.getData(query);             
            
            for(int i=0;rs.next()!=false;++i){
               x+=rs.getInt("OrderCost");
                
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Some Error Occured");
        }
            System.out.println(x);
            return x;
        }
     public void addCost()
       {
            if(this.jT1.getText().length()==0){
                    JOptionPane.showMessageDialog(null,"You must provide the cost");
               }
            else {
                    int cost=Integer.parseInt(this.jT1.getText());
                    int Sale=dailyCost();//r1.dailyCost();
                    String sql="INSERT INTO overview (Day,Sale,Cost,Profit) VALUES ('"+day+"','"+Sale+"','"+cost+"',0)";
                    da3.updateDB(sql);
                  }
            try
            {
                String query = "Delete FROM daily";           
             da2.updateDB(query);
            }
              catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Some Error Occured");
        }
            day++;
	}
       
    
    public static void main(String[] args) {
        Account a1=new Account();
    }
}
