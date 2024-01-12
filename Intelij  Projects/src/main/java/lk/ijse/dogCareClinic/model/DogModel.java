package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.DogDto;
import lk.ijse.dogCareClinic.dto.communityDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DogModel {

    public boolean saveDog(DogDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO dog VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getD_ID());
        pstm.setString(2, dto.getD_Name());
        pstm.setString(3, dto.getGender());
        pstm.setString(4, dto.getBreed());
        pstm.setString(5, dto.getAge());
        pstm.setString(6, dto.getO_ID());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deleteDog(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM dog WHERE D_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<DogDto> getAllDog() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM dog";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<DogDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String D_ID = resultSet.getString(1);
            String D_Name = resultSet.getString(2);
            String Gender = resultSet.getString(3);
            String Breed = resultSet.getString(4);
            String Age = resultSet.getString(5);
            String O_ID = resultSet.getString(6);

            var dto = new DogDto(D_ID,D_Name,Gender,Breed,Age,O_ID);
            dtoList.add(dto);
        }
        return dtoList;
    }


    public AppointmentDto searchAppointment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM appointment WHERE Appointment_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        AppointmentDto dto = null;

        if (resultSet.next()) {
            String AppointmentID = resultSet.getString(1);
            String OwnerID = resultSet.getString(2);
            String EmployeeID = resultSet.getString(3);
            String Date = resultSet.getString(4);
            String Time = resultSet.getString(5);
            String Purpose = resultSet.getString(6);

            dto = new AppointmentDto(AppointmentID, OwnerID, EmployeeID, Date, Time, Purpose);

        }
        return dto;

    }
    public boolean updateDog(DogDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE dog SET D_Name = ?, Gender = ?, Breed = ?, Age = ?, O_ID = ? WHERE D_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getD_Name());
        pstm.setString(2, dto.getGender());
        pstm.setString(3, dto.getBreed());
        pstm.setString(4, dto.getAge());
        pstm.setString(5, dto.getO_ID());
        pstm.setString(6, dto.getD_ID());

        return pstm.executeUpdate() > 0;
    }

    public DogDto searchDog(String id) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM dog WHERE D_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        DogDto dto = null;

        if (resultSet.next()) {
            String D_ID = resultSet.getString(1);
            String D_Name = resultSet.getString(2);
            String Gender = resultSet.getString(3);
            String Breed = resultSet.getString(4);
            String Age = resultSet.getString(5);
            String O_ID = resultSet.getString(6);

            dto = new DogDto(D_ID,D_Name,Gender,Breed,Age,O_ID);

        }
        return dto;
    }
}


