package com.jts.hms;
import java.sql.Connection;
import java.util.Scanner;
public class Patient {
    private Connection conn;
    Scanner sc=new Scanner(System.in);

    public Patient(Connection conn){
        this.conn=conn;
    }

    public void Add_Patient(){
        System.out.println("Enter the PatientName: ");
        String Name=sc.nextLine();
        System.out.println("Enter the Age:");
        int Age=sc.nextInt();
        System.out.println("Enter the Gender:");
        String Gender=sc.nextLine();

        java.lang.String query="Insert into Patients(Name,Age,Gender) values (?,?,?)";

        try(PreparedStatemnt ps=conn.prepareStatemnt(query)){
            ps.setString(1,Name);
            ps.setInt(2,Age);
            ps.setString(3,Gender);

            if(ps.execute()){
                System.out.println("Patients Added.");
            }else{
                System.out.println("Patients not Added");
            }
        }



    }
}
