package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

    public class InventoryDetailModel {
        public static boolean saveInventoryDetails(String ItemId, String UnitPrice, double Quantity) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO inventory_details VALUES(?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, ItemId);
            pstm.setString(2, UnitPrice);
            pstm.setDouble(3, Quantity);


            boolean isSaved = pstm.executeUpdate() > 0;

            return isSaved;
        }
    }

