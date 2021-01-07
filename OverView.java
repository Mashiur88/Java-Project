import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
  
public class OverView extends JFrame implements ActionListener {  
     JTable j;
     JLabel jl1;
     JScrollPane sp;
     JButton jB1;
     Font f;
     Container c;
     Object[] columnNames = {"Day","Sale","Cost","Profit"};
      Object[][] data=new Object[50][4];
      DataAccess da1=new DataAccess();
       ResultSet rs=null;
       
       public void actionPerformed(ActionEvent ae)
       {
           if(ae.getSource()==jB1)
           {
               this.dispose();
               new Account();
           }
       }
       OverView() 
    {    
         
        initComponents();
         showCost();
    }
       public void initComponents()
       {
        c=this.getContentPane();
        c.setBackground(Color.DARK_GRAY);
        c.setLayout(null);
        f=new Font("arial",Font.BOLD,20);
        this.setVisible(true);
        this.setBounds(50,40,700,500);
        this.setTitle("OverView"); 
        j = new JTable(data, columnNames);  
        //j.setEnabled(false);
        sp = new JScrollPane(j);
        sp.setBounds(50,80,600,150);
        jl1=new JLabel("OverView");
        jl1.setBounds(100,30,100,30);
        jl1.setForeground(Color.white);
        this.add(jl1);
        jl1.setFont(f);
        jB1=new JButton("Back");
        jB1.setBounds(450,240,80,30);
        this.add(jB1);
        this.add(sp);
        
        jB1.addActionListener(this);
       }
       public void showCost()
       {
           try {                                    
            String query = "SELECT Day,Sale,Cost,Sale-Cost FROM overview";           
            rs = da1.getData(query);             
            
            for(int i=0;rs.next();i++){
                data[i][0] =rs.getInt("Day");
                data[i][1] =rs.getInt("Sale");
                data[i][2] =rs.getInt("Cost");
                data[i][3] =rs.getInt("Sale-Cost");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Some Error Occured");
        }
       }
  
    public static void main(String[] args) 
    { 
        
         OverView o1=new OverView ();
    } 
}

