package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.InventoryDto;
import lk.ijse.dogCareClinic.dto.RecordDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordModel {


    public RecordDto searchRecord;

    public boolean saveRecord(RecordDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO record VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getRecordId());
        pstm.setString(2, dto.getDescription());
        pstm.setString(3, dto.getDate());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean deleteRecord(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM record WHERE Record_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public List<RecordDto> getAllRecords() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM record";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<RecordDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String record_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String date = resultSet.getString(3);

            var dto = new RecordDto(record_id, description, date);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateRecord(RecordDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE record SET Description = ?, Date = ? WHERE Record_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getDescription());
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getRecordId());

        return pstm.executeUpdate() > 0;
    }

    public RecordDto searchRecord(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM record WHERE Record_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        RecordDto dto = null;

        if (resultSet.next()) {
            String Record_ID = resultSet.getString(1);
            String Description = resultSet.getString(2);
            String Date = resultSet.getString(3);

            dto = new RecordDto(Record_ID, Description, Date);

        }
        return dto;

    }

}