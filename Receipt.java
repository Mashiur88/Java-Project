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

  
public class Receipt extends JFrame implements ActionListener {  
     JTable j;
     JLabel jl1,jl2;
     JScrollPane sp;
     JButton jB1,jB2;
     Font f;
     Container c;
     static String s;
     public int cost;
     public int mCost;
     Object[] columnNames = {"Item Name", "Quantity", "Price","Total Price"};
     Object[][] data=new Object[50][4];
      DataAccess da=new DataAccess();
      DataAccess da2=new DataAccess();
       ResultSet rs=null;
       
       public void actionPerformed(ActionEvent ae)
       {
           if(ae.getSource()==jB1)
           {
               this.dispose();
               new MainFrame();
               cost=0;
           }
           if(ae.getSource()==jB2)
           {
               calculateCost();
               JOptionPane.showMessageDialog(null,"Total Bill    "+cost);
           }
       }
       Receipt() 
    {    
         //this.cost=cost;
        initComponents();
         addItem();
         
       }
       public void updateCost()
       {
           String sql="INSERT INTO daily (OrderCost) VALUES ('"+this.cost+"')";
		   da2.updateDB(sql);
       }
       
       public void calculateCost()
       {
            //System.out.println(j.getRowCount());
           int x,y;
           y=(int)j.getRowCount();
           for(int i=0;i!=y;i++)
           {
               
               if(j.getValueAt(i,3)==null)
               {
               break;
               }
               x=(int)j.getValueAt(i,3);
               System.out.println(x);
               this.cost+=x;
               System.out.println(this.cost);
               
           }
           //System.out.println(this.cost);
          s=Integer.toString(cost);
          updateCost();
        //System.out.println(s);
        
            
      }
       public void initComponents()
       {
        c=this.getContentPane();
        c.setBackground(Color.DARK_GRAY);
        c.setLayout(null);
        f=new Font("arial",Font.BOLD,20);
        //this.setVisible(true);
        this.setBounds(50,40,700,500);
        this.setTitle("Receipt"); 
        j = new JTable(data, columnNames);  
        j.setEnabled(false);
        sp = new JScrollPane(j);
        sp.setBounds(50,80,600,150);
        jl1=new JLabel("Receipt");
        jl1.setBounds(100,30,100,30);
        jl1.setForeground(Color.white);
        this.add(jl1);
        jl1.setFont(f);
        jB1=new JButton("Back");
        jB1.setBounds(320,250,70,30);
        this.add(jB1);
         jB2=new JButton("Confirm");
        jB2.setBounds(220,250,90,30);
        this.add(jB2);
        this.add(sp);
        
        jB1.addActionListener(this);
        jB2.addActionListener(this);
       }
       public void addItem()
       {
           try {                                    
            String query = "SELECT Item_Name,Quantity,Price,Price*Quantity FROM menu where check_box=1";           
            rs = da.getData(query);             
            
            for(int i=0;rs.next();i++){
                data[i][0] = rs.getString("Item_Name");
                data[i][1] = rs.getInt("Quantity");
                data[i][2] = rs.getInt("Price");
                data[i][3]= rs.getInt("Price*Quantity");
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Some Error Occured");
        }
       }
       
  
    public static void main(String[] args) 
    { 
        Receipt r1 =new Receipt();
        
         
         //r1.setCost(0);
    } 
}

