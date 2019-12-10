package Shopping;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class AgentLogin {

	public void Login()throws SQLException,ClassNotFoundException {
		// TODO Auto-generated method stub
		java.sql.Connection con=null;//Connection object
		
		int f=0;
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","");
		Scanner s= new Scanner(System.in);
		int m;
		System.out.println("Enter Your Username:");
		String username=s.next();
		System.out.println("Enter your password:");
		String password=s.next();
		Statement smt=(Statement) con.createStatement();
		ResultSet rr=smt.executeQuery("Select * from agentlogin");
		
		while(rr.next())
		{
			if(username.equals(rr.getString(1))&&password.equals(rr.getString(2)))
		   {
				f=1;
		    }
		}
		if(f==1)
		{
		System.out.println("Successfully verified!");
		do
		{
			System.out.println("1.Buysell\n2.View product\n3.Logout");
			System.out.println("Enter the choice:");
			m=s.nextInt();
		
			switch(m)
			{
			case 1:
				System.out.println("Enter ProductId:");
				int pid=s.nextInt();
				System.out.println("Enter Quantity:");
				int qnty=s.nextInt();
				
				double price=0;
				int flag=0;
				Statement smt2=(Statement) con.createStatement();
				ResultSet rr2=smt2.executeQuery("Select *from addproduct");
				while(rr2.next())
				{
					if(pid==rr2.getInt(1))
					{
					 price=rr2.getDouble(4);
					 int quantity=rr2.getInt(3);
					 if(qnty<=quantity)
					 {
						 double sum=price*qnty;
						System.out.println("Cost is ->"+sum);
						flag=1;
					 }
					 else
					 {
						 System.out.println("Stack overflow");
						 flag=0;
					 }
					}
				}
				//double price=rr2.getDouble(4);
				//System.out.println(price);
				
				//Update quantity...
				if(flag==1)
				{
				Statement smt4=(Statement) con.createStatement();
				ResultSet rs4=smt4.executeQuery("Select * from addproduct");
				int qua=0;
				while(rs4.next())
				{    
					int idd = rs4.getInt(1);
					if(idd==pid) {
						int q= rs4.getInt(3);
						qua=q-qnty;
				
						}
				}
					
			PreparedStatement pss=con.prepareStatement("update addproduct set minsellquantity=? where productid=?");
			pss.setInt(1,qua);
			pss.setInt(2,pid);
			pss.executeUpdate();
			//System.out.println("Successfully Updated...");
				}
			break;

			case 2:
				Statement smt1=(Statement) con.createStatement();
				ResultSet rs=smt1.executeQuery("Select * from addproduct");
				System.out.println("~~~~~~~~~~List of Products~~~~~~~~~~~");
				System.out.println("###########***********#############");
				while(rs.next())
				{
					System.out.println("Product ID ->"+rs.getInt(1)+"\n"+"Product Name ->"+rs.getString(2)+"\n"+"Quantity ->"+rs.getInt(3)+"\n"+"Price ->"+rs.getDouble(4));
				}
				System.out.println("###########***********#############");
				break;
			case 3:
				return;
				
			}
		}while(m!=0);
		
		}
		else
		{
			System.out.println("Incorrect Username and Password.. ");
		}
		
	}

}
