package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.OwnerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerModel {

    public boolean saveOwner(OwnerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO owner VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOwnerId());
        pstm.setString(2, dto.getOwnerName());
        pstm.setString(3, dto.getContacts());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deleteOwner(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM owner WHERE O_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<OwnerDto> getAllOwner() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM owner";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<OwnerDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String OwnerID = resultSet.getString(1);
            String OwnerName = resultSet.getString(2);
            String Contacts = resultSet.getString(3);

            var dto = new OwnerDto(OwnerID, OwnerName, Contacts);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateOwner(OwnerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE owner SET Owner_Name = ?, Contacts = ? WHERE O_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOwnerName());
        pstm.setString(2, dto.getContacts());
        pstm.setString(3, dto.getOwnerId());

        return pstm.executeUpdate() > 0;
    }


    public OwnerDto searchOwner(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM owner WHERE O_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        OwnerDto dto = null;

        if (resultSet.next()) {
            String O_ID = resultSet.getString(1);
            String Owner_Name = resultSet.getString(2);
            String Contacts = resultSet.getString(3);


            dto = new OwnerDto(O_ID, Owner_Name,Contacts);

        }
        return dto;

    }
}