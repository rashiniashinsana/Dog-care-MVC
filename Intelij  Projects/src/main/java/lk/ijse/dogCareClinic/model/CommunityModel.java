package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.communityDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommunityModel {

    public boolean saveCustomer(final communityDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO community_project VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getProjectId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getDate());
        pstm.setString(4, dto.getLocation());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public List<communityDto> getAllCustomers() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM community_project";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<communityDto> dtoList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String date = resultSet.getString(3);
            String location = resultSet.getString(4);

            var dto = new communityDto(id, name, date, location);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public boolean updateProject(communityDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE community_project SET Project_Name = ?, Date = ?, Location = ? WHERE Project_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getDate());
        pstm.setString(3, dto.getLocation());
        pstm.setString(4, dto.getProjectId());

        return pstm.executeUpdate() > 0;
    }

    public boolean deleteCommunity(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM community_project WHERE Project_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);

        return pstm.executeUpdate() > 0;
    }

    public communityDto searchCommunity(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM community_project WHERE Project_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        communityDto dto = null;

        if (resultSet.next()) {
            String ProjectID = resultSet.getString(1);
            String ProjectName = resultSet.getString(2);
            String Date = resultSet.getString(3);
            String Location = resultSet.getString(4);


            dto = new communityDto(ProjectID, ProjectName, Date,Location);

        }
        return dto;

    }
}