package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.SupplierDto;
import lk.ijse.dogCareClinic.dto.communityDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupplierID());
        pstm.setString(2, dto.getSupplierName());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getSupplierment());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public static boolean deleteSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE Sup_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<SupplierDto> getAllSupplier() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<SupplierDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String SupplierID = resultSet.getString(1);
            String SupplierName = resultSet.getString(2);
            String Contact = resultSet.getString(3);
            String Supplierment = resultSet.getString(4);


            var dto = new SupplierDto(SupplierID,SupplierName ,Contact , Supplierment);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET  Sup_Name= ?,  Contact = ?, Supplierment = ? WHERE Sup_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getSupplierName());
        pstm.setString(2, dto.getContact());
        pstm.setString(3, dto.getSupplierment());
        pstm.setString(4, dto.getSupplierID());

        return pstm.executeUpdate() > 0;
    }

    public SupplierDto searchSupplier(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE Sup_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        SupplierDto dto = null;

        if (resultSet.next()) {
            String Sup_ID = resultSet.getString(1);
            String Sup_Name = resultSet.getString(2);
            String Contact = resultSet.getString(3);
            String Supplierment = resultSet.getString(4);


            dto = new SupplierDto(Sup_ID,Sup_Name,Contact,Supplierment);

        }
        return dto;

    }
}

