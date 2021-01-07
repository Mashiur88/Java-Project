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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


class placeOrder extends JFrame implements ActionListener,ListSelectionListener
{
    JTable jt1;
    Container c;
    JLabel jl1,jl2;
    JScrollPane jS1;
    JButton jB1,jB2;
    private Font f;
    DataAccess da=new DataAccess();
    String s;
    ResultSet rs=null;
    ListSelectionModel lS1;
    DataAccess da2 = new DataAccess();
     Object [] cols={"Item Name","Price","Quantity","Check"};
        Object[][] data = new Object[50][4];
    
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        if(! lS1.isSelectionEmpty())
        {
            String b=JOptionPane.showInputDialog("select item :");
            int check=Integer.parseInt(b);
            System.out.println(check);
            String a=JOptionPane.showInputDialog("enter the Quantity :");
            int Quantity=Integer.parseInt(a);
            System.out.println(Quantity);
            int x=lS1.getMinSelectionIndex();
            System.out.println(x);
            s=(String) jt1.getValueAt(x,0);
            System.out.println(s);
            
            String sql="UPDATE menu SET Quantity='"+Quantity+"',check_box='"+check+"' WHERE Item_Name='"+s+"'";
            da2.updateDB(sql);
            this.addItem();
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==jB1)
        {
            this.dispose();
            new Receipt().setVisible(true);
        }
        if(ae.getSource()==jB2)
        {
            this.dispose();
            new MainFrame();
        }
    }
    placeOrder()
    {
        initComponents();
        addItem();
        
    }
   
    public void initComponents()
       {
           this.setVisible(true);
            c=this.getContentPane();
            c.setLayout(null);
            c.setBackground(Color.darkGray);
            this.setTitle("Place Order");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setBounds(200,200,700,450);
            jl1=new JLabel("Menu");
            jl1.setBounds(50,20,150,80);
            f=new Font("arial",Font.BOLD,20);
            jl1.setFont(f);
            jl1.setForeground(Color.WHITE);
            this.add(jl1);
            jt1=new JTable(data,cols);
            jt1.setRowSelectionAllowed(true);
            lS1=jt1.getSelectionModel();
            lS1.addListSelectionListener(this);
           // j.setCellSelectionEnabled(false);
            jS1=new JScrollPane(jt1);
            jS1.setBounds(50,80,600,150);
            jB1=new JButton("Receipt");
            jB1.setBounds(200,280,90,30);
            this.add(jB1);
            jB2=new JButton("Back");
            jB2.setBounds(320,280,90,30);
            this.add(jB2);
            
            jB1.addActionListener(this);
            jB2.addActionListener(this);
            
            
            
           // this.add(jt1);
            this.add(jS1);
       }
    
     public void addItem()
    {
       
        try {                                    
            String query = "SELECT Item_Name,Price,Quantity,check_box FROM menu";           
            rs = da.getData(query);             
            
            for(int i=0;rs.next();i++){
                data[i][0] = rs.getString("Item_Name");
                data[i][1] = rs.getInt("Price");
                data[i][2] = rs.getInt("Quantity");
                data[i][3] = rs.getInt("check_box");
                
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Some Error Occured");
        }
    }
    
    public static void main(String[] args) {
        placeOrder pO1=new placeOrder();
    }
}