
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection conn;
    Scanner sc=new Scanner(System.in);

    public Doctor(Connection conn){
        this.conn=conn;
    }

    public void Add_Doctor() throws SQLException{
        System.out.println("Enter the DoctorName: ");
        String Name=sc.nextLine();
        System.out.println("Enter the Age:");
        int Age=sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the Gender:");
        String Gender=sc.nextLine();
        System.out.println("Enter the Specialization:");
        String Specialist=sc.nextLine();

        java.lang.String query="Insert into Doctors(Name,Age,Gender,Specialist) values (?,?,?,?)";

        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setString(1,Name);
            ps.setInt(2,Age);
            ps.setString(3,Gender);
            ps.setString(4,Specialist);
            int rowsInserted = ps.executeUpdate();
            if(rowsInserted>0){
                System.out.println("Doctors Added.");
            }else{
                System.out.println("Doctors not Added");
            }
        }
    }

    public void View_Doctor() throws SQLException {
        String query="select * from Doctors";
        try(PreparedStatement ps=conn.prepareStatement(query)) {
            try(ResultSet rs=ps.executeQuery()){
                System.out.println("Doctor Details:");

                while(rs.next()){
                    int id=rs.getInt("id");
                    String Name=rs.getString("Name");
                    int age=rs.getInt("Age");
                    String Gender=rs.getString("Gender");
                    String Specialist=rs.getString("Specialist");

                    System.out.println("Doctor id:"+id);
                    System.out.println("Doctor Name:"+Name);
                    System.out.println("Doctor Age:"+age);
                    System.out.println("Doctor Gender:"+Gender);
                    System.out.println("Doctor Specialized in:"+Specialist);

                }
            }
        }
    }

    public boolean Get_Doctor_By_Id(int id) throws SQLException{
        String query="Select * from Doctors where id=?";

        try(PreparedStatement ps=conn.prepareStatement(query)){
            ps.setInt(1,id);

            try(ResultSet rs=ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
