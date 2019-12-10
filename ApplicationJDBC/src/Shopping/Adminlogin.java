package Shopping;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Statement;

public class Adminlogin {

	//Connection con=new Connection();
	public void Admin()throws SQLException,ClassNotFoundException {
		// TODO Auto-generated method stub
		java.sql.Connection con=null;//Connection object
		//2)create a Connection
		int f=0;
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping","root","");
		Scanner s= new Scanner(System.in);
		int m;
		System.out.println("Enter Your Username:");
		String username=s.next();
		System.out.println("Enter your password:");
		String password=s.next();
		Statement smt=(Statement) con.createStatement();
		ResultSet rr=smt.executeQuery("Select * from adminlogin");
		
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
				System.out.println("1.Add Product\n2.Display\n3.Remove Product\n4.Update Product\n5.Logout");
				System.out.println("Enter the choice:");
				m=s.nextInt();
			
				switch(m)
				{
				case 1:
					System.out.println("Enter Your productid:");
					int pid=s.nextInt();
					System.out.println("Enter Your produc Name:");
					String pname=s.next();
					System.out.println("Enter Your minsellquantity:");
					int min=s.nextInt();
					System.out.println("Enter Your price:");
					double price=s.nextDouble();
					PreparedStatement ps=con.prepareStatement("insert into addproduct(productid,productname,minsellquantity,price)values(?,?,?,?);");
					ps.setInt(1,pid);//
					ps.setString(2,pname);
					ps.setInt(3,min);
					ps.setDouble(4,price);
					ps.executeUpdate();
					System.out.println("Product added Successfully");
					break;
				case 2:
					Statement smt1=(Statement) con.createStatement();
					ResultSet rs=smt1.executeQuery("Select * from addproduct");
					System.out.println("###########***********#############");
					while(rs.next())
					{
						System.out.println("Product ID ->"+rs.getInt(1)+"\n"+"Product Name ->"+rs.getString(2)+"\n"+"Quantity ->"+rs.getInt(3)+"\n"+"Price ->"+rs.getDouble(4));
					}
					System.out.println("###########***********#############");
					break;
					
				case 3:
					Statement smt2=(Statement) con.createStatement();
					ResultSet rs2=smt2.executeQuery("Select * from addproduct");
					System.out.println("###########***********#############");
					while(rs2.next())
					{
						System.out.println("Product ID ->"+rs2.getInt(1)+"\n"+"Product Name ->"+rs2.getString(2));
					}
					System.out.println("###########***********#############");
					System.out.println("Enter Product id:");
					int id=s.nextInt();
					PreparedStatement pp=con.prepareStatement("delete from addproduct where productid=?;");
					pp.setInt(1,id);
					pp.executeUpdate();
					System.out.println("Successfully Removed...");
					break;
				case 4:
					Statement smt3=(Statement) con.createStatement();
					ResultSet rs3=smt3.executeQuery("Select * from addproduct");
					
					while(rs3.next())
					{    
						System.out.println("###########***********#############");
						System.out.println("Product ID ->"+rs3.getInt(1)+"\n"+"Product Name ->"+rs3.getString(2));
					System.out.println("###########***********#############");
					
					}
					int qua=0;
					System.out.println("Enter Product Id:");
					int proid=s.nextInt();
					System.out.println("Enter new minsell Quantity:");
					int promin=s.nextInt();
					Statement smt4=(Statement) con.createStatement();
					ResultSet rs4=smt4.executeQuery("Select * from addproduct");
					
					while(rs4.next())
					{    
						int idd = rs4.getInt(1);
						if(idd==proid) {
							int q= rs4.getInt(3);
							qua = q+promin;
					
									}
					}
						
							PreparedStatement pss=con.prepareStatement("update addproduct set minsellquantity=? where productid=?");
							pss.setInt(1,qua);
							pss.setInt(2,proid);
							pss.executeUpdate();
							System.out.println("Successfully Updated...");
						
					
					break;
				case 5:
					
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


