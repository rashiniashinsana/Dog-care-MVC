package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.OwnerDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel {

    private AppointmentDto dto;

    public boolean saveAppointment(AppointmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO appointment VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAppointmentID());
        pstm.setString(2, dto.getOwnerID());
        pstm.setString(3, dto.getEmployeeID());
        pstm.setString(4, dto.getDate());
        pstm.setString(5, dto.getTime());
        pstm.setString(6, dto.getPurpose());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;

    }

    public boolean deleteAppointment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM record WHERE Appointment_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;


    }



    public List<AppointmentDto> getAllAppointment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<AppointmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String Appointment_ID = resultSet.getString(1);
            String Owner_ID = resultSet.getString(2);
            String Employee_ID = resultSet.getString(3);
            String date = resultSet.getString(4);
            String time = resultSet.getString(5);
            String purpose = resultSet.getString(6);


            var dto = new AppointmentDto(Appointment_ID, Owner_ID, Employee_ID, date, time, purpose);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateAppointment(AppointmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE appointment SET  O_ID = ?, Emp_ID =?, DATE = ?, TIME = ?, purpose = ? WHERE Appointment_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOwnerID());
        pstm.setString(2, dto.getEmployeeID());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getTime());
        pstm.setString(5, dto.getPurpose());
        pstm.setString(6, dto.getAppointmentID());
        return pstm.executeUpdate() > 0;
    }


    public AppointmentDto searchAppointment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointment WHERE Appointment_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();


        if (resultSet.next()) {
            String Appointment_ID = resultSet.getString(1);
            String O_ID = resultSet.getString(2);
            String Emp_ID = resultSet.getString(3);
            String Date = resultSet.getString(4);
            String Time = resultSet.getString(5);
            String purpose = resultSet.getString(6);



            dto = new AppointmentDto(Appointment_ID,O_ID,Emp_ID,Date,Time,purpose);

        }
        return dto;

    }
}