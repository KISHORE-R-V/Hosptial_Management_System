import java.sql.*;

public class HostpitalManagementSystem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(Sysetm.in);

        try(Connection conn= com.jts.hms.MySqlConnector.getConnection()){
            while(true){
                System.out.println("---HOSTPITAL MANAGEMENT SYSTEM---");
                System.out.println("1.Add Patients");
                System.out.println("2.Add Doctors");
                System.out.println("3.View Patients");
                System.out.println("4.View Doctors");
                System.out.println("5.Book Appointment");
                System.out.println("6.Exit");

                System.out.println("Enter your choice: ");

                int choice=sc.nextInt();

                if(choice==1){

                }
                else if(choice==2){

                }
                else if(choice==3) {

                }
                else if(choice==4) {

                }
                else if(choice==5){

                }
                else if(choice==6){
                    System.out.println("Thank For Using the Application");
                }
                else{
                    System.out.println("Please enter the valid Choice.");
                }

            }
        }

        sc.close();
    }
}
