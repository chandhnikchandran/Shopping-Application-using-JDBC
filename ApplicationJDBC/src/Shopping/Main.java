package Shopping;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
		public static void main(String[] args)throws ClassNotFoundException,SQLException {
			// TODO Auto-generated method stub
			int n;
			Scanner s=new Scanner(System.in);
			do{
			System.out.println("1.Admin Login\n2.Agent Login\n3.Exit");
			System.out.println("Enter the choice:");
			n=s.nextInt();
			switch(n)
			{
			case 1:
				Adminlogin obj1=new Adminlogin();
				obj1.Admin();
				break;
			case 2:
				AgentLogin obj2=new AgentLogin();
				obj2.Login();
				break;
			case 3:
				return;
			}
				
			}while(n!=0);
		}

}
