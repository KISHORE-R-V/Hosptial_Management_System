import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookAppointment {
    private Scanner sc = new Scanner(System.in);
    private Connection connection;
    private Patient patient;
    private Doctor doctor;

    public BookAppointment(Connection connection, Patient patient, Doctor doctor) {
        this.connection = connection;
        this.patient = patient;
        this.doctor = doctor;
    }

    public void bookAppointment() throws SQLException {
        System.out.print("Enter Patient id: ");
        int patientId = sc.nextInt();
        System.out.print("Enter Doctor id: ");
        int doctorId = sc.nextInt();
        System.out.print("Enter the Appointment Date (yyyy-mm-dd): ");
        String appointmentDate = sc.next();

        if (!patient.Get_Patient_By_Id(patientId)) {
            System.out.println("Please provide a valid patient_id.");
            return;
        }

        if (!doctor.Get_Doctor_By_Id(doctorId)) {
            System.out.println("Please provide a valid doctor_id.");
            return;
        }

        if (!checkAvailability(connection, doctorId, appointmentDate)) {
            System.out.println("Doctor not available.");
            return;
        }

        String query = "INSERT INTO Appointments (patient_id, doctor_id, Appointment_Date) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, appointmentDate);

            if (preparedStatement.executeUpdate() > 0) {
                System.out.println("Appointment Booked Successfully.");
            } else {
                System.out.println("Appointment Not Booked.");
            }
        }
    }

    public boolean checkAvailability(Connection connection, int doctorId, String appointmentDate) throws SQLException {
        String query = "SELECT COUNT(1) FROM Appointments WHERE doctor_id=? AND Appointment_Date=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, appointmentDate);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0; // available if count=0
                }
            }
        }
        return false; // default not available
    }
}
