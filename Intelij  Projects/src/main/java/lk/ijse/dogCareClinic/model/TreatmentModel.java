package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.TreatmentDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TreatmentModel {

    public boolean saveTreatment(TreatmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO treatment VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getTreatmentID());
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getMedication());
        pstm.setString(4, dto.getDiagnosis());
        pstm.setString(5, dto.getPaymentID());
        pstm.setString(6, dto.getDogID());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public static boolean deleteTreatment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM treatment WHERE T_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<TreatmentDto> getAllTreatment() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM treatment";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<TreatmentDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String TreatmentID = resultSet.getString(1);
            String Date = resultSet.getString(2);
            String Medication = resultSet.getString(3);
            String Diagnosis = resultSet.getString(4);
            String DogID = resultSet.getString(5);
            String PaymentID = resultSet.getString(6);

            var dto = new TreatmentDto(TreatmentID, Date, Medication, Diagnosis, DogID, PaymentID);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateTreatment(TreatmentDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE treatment SET  Date = ?, Medication = ?, Diagnosis = ?, D_ID =?, p_ID = ? WHERE T_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getTreatmentID());
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getMedication());
        pstm.setString(4, dto.getDiagnosis());
        pstm.setString(5, dto.getDogID());
        pstm.setString(6, dto.getPaymentID());
        return pstm.executeUpdate() > 0;
    }

    public TreatmentDto searchTreatment(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM treatment WHERE T_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        TreatmentDto dto = null;

        if (resultSet.next()) {
            String T_ID = resultSet.getString(1);
            String Date = resultSet.getString(2);
            String Medication = resultSet.getString(3);
            String Diagnosis = resultSet.getString(4);
            String P_ID = resultSet.getString(5);
            String D_ID = resultSet.getString(6);


            dto = new TreatmentDto(T_ID, Date, Medication, Diagnosis, P_ID, D_ID);

        }
        return dto;

    }

}

