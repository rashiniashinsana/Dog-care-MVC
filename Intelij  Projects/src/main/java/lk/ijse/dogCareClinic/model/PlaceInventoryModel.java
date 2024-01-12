package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.InventoryDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceInventoryModel {
    private final InventoryModel InventoryModel = new InventoryModel();
    private final InventoryDetailModel InventoryDetailModel = new InventoryDetailModel();

    public boolean PlaceCustomerOrder(InventoryDto pDto) throws SQLException {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = lk.ijse.dogCareClinic.model.InventoryModel.saveInventory(pDto);
            if (isOrderSaved) {
                boolean isUpdated = lk.ijse.dogCareClinic.model.InventoryDetailModel.saveInventoryDetails(pDto.getItem_ID(),pDto.getUnit_Price(),pDto.getQuantity());
                if(isUpdated) {
                    connection.commit();
                    result = true;
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
