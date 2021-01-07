
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class DeleteItem extends JFrame implements ActionListener  {
    private JTextField tf1; 
    JLabel jl1;
    JButton jB1,jB2;
    private Font f;
    Container c;
    DataAccess da4=new DataAccess();
     public void actionPerformed(ActionEvent e){
              boolean flag=true;
		String ItemName=this.tf1.getText();
                String s=e.getActionCommand();
		
		if(s.equals("Back")){
			this.dispose();
                        new MainFrame();
		}
		else{
			if(ItemName.length()==0){
				JOptionPane.showMessageDialog(null,"You must provide ItemName ");
				flag=false;
			}
                        else if(flag && s.equals("Delete")){
				String sql="DELETE FROM menu WHERE Item_Name='"+ItemName+"'";
				da4.updateDB(sql);
                                JOptionPane.showMessageDialog(null,"Item is Deleted");
				
				}
			}
		}		
    DeleteItem()
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
            this.setTitle("DeleteItem Frame");
            this.setLayout(null);
            this.setBounds(500,500,500,400);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tf1= new JTextField();
            tf1.setBounds(250, 100, 200, 50);

            jl1=new JLabel();
            jl1.setText("Enter The Item Name");
            jl1.setBounds(50,100,200,50);
            jl1.setFont(f);
            jl1.setForeground(Color.WHITE);

          

            jB1=new JButton("Delete");
            jB1.setBounds(90,200,150,50);

            jB2=new JButton("Back");
            jB2.setBounds(250,200,150,50);

            jB1.addActionListener(this);
            jB2.addActionListener(this);

            this.add(tf1);
            this.add(jl1);
            this.add(jB1);
            this.add(jB2);
            
       
      
    }
    public static void main(String[] args) {
        DeleteItem dI1=new DeleteItem();
        
    }
    
}
