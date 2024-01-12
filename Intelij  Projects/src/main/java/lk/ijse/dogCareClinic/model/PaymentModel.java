package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.PaymentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentModel {


    public boolean savePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO payment VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPaymentId());
        pstm.setString(2, dto.getAmount());
        pstm.setString(3, String.valueOf(dto.getDate()));

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deletePayment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM payment WHERE P_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<PaymentDto> getAllPayment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<PaymentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            String Amount = resultSet.getString(2);
            String Date = resultSet.getString(3);

            var dto = new PaymentDto(paymentId, Amount, Date);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updatePayment(PaymentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE payment SET Amount = ?, Date = ? WHERE P_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getAmount());
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getPaymentId());

        return pstm.executeUpdate() > 0;
    }




    public PaymentDto searchPayment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM payment WHERE P_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        PaymentDto dto = null;

        if (resultSet.next()) {
            String P_ID = resultSet.getString(1);
            String Amount = resultSet.getString(2);
            String Date = resultSet.getString(3);


            dto = new PaymentDto(P_ID,Amount,Date);

        }
        return dto;
    }
}
