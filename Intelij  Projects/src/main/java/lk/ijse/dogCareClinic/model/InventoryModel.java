package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.InventoryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryModel {


    public static boolean saveInventory(InventoryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO inventory VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getItem_ID());
        pstm.setString(2, dto.getItem_Name());
        pstm.setString(3, dto.getDescription());
        pstm.setString(4, dto.getUnit_Price());
        pstm.setString(5, String.valueOf(dto.getQuantity()));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deleteInventory(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM inventory WHERE Item_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<InventoryDto> getAllInventory() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM inventory";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<InventoryDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String Item_ID = resultSet.getString(1);
            String Item_Name = resultSet.getString(2);
            String Description = resultSet.getString(3);
            String Unit_Price = resultSet.getString(4);
            String Quantity = resultSet.getString(5);


            var dto = new InventoryDto(Item_ID,Item_Name,Description,Unit_Price,Quantity);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateInventory(InventoryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE inventory SET Item_Name = ?, Description = ?, Unit_Price = ?, Quantity = ?  WHERE Item_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getItem_Name());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getUnit_Price());
        pstm.setString(4, String.valueOf(dto.getQuantity()));
        pstm.setString(5,dto.getItem_ID());

        return pstm.executeUpdate() > 0;
    }

    public InventoryDto searchInventory(String id) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM inventory WHERE Item_ID = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            ResultSet resultSet = pstm.executeQuery();
            InventoryDto dto = null;

            if (resultSet.next()) {
                String Item_ID = resultSet.getString(1);
                String Item_Name = resultSet.getString(2);
                String Description = resultSet.getString(3);
                String Unit_Price = resultSet.getString(4);
                String Qunatity = resultSet.getString(5);

                dto = new InventoryDto(Item_ID,Item_Name,Description,Unit_Price, Qunatity);

            }
            return dto;

        }


}
