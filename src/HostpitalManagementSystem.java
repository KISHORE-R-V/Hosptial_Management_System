import java.awt.print.Book;
import java.sql.*;
import java.util.Scanner;

public class HostpitalManagementSystem {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        try(Connection conn= com.jts.hms.MySqlConnector.getConnection()){
            Patient patient=new Patient(conn);
            Doctor doctor=new Doctor(conn);
            BookAppointment appointment=new BookAppointment(conn,patient,doctor);

            while(true){
                System.out.println("---HOSTPITAL MANAGEMENT SYSTEM---");
                System.out.println("1.Add Patients");
                System.out.println("2.Add Doctors");
                System.out.println("3.View Patients");
                System.out.println("4.View Doctors");
                System.out.println("5.Book Appointment");
                System.out.println("6.Exit");

                System.out.print("Enter your choice: ");

                int choice=sc.nextInt();

                if(choice==1){
                    patient.Add_Patient();
                }
                else if(choice==2){
                    doctor.Add_Doctor();
                }
                else if(choice==3) {
                    patient.View_Patient();
                }
                else if(choice==4) {
                    doctor.View_Doctor();
                }
                else if(choice==5){
                    appointment.bookAppointment();
                }
                else if(choice==6){
                    System.out.println("Thank For Using the Application");
                }
                else{
                    System.out.println("Please enter the valid Choice.");
                }

            }
        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
