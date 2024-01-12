package lk.ijse.dogCareClinic.model;

import javafx.scene.control.Alert;
import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {

    public boolean saveUser(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES(?,?,?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getUsername());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3,dto.getConfirm_Password());
        pstm.setString(4, dto.getEmail());

        int i = pstm.executeUpdate();
        if (i > 0) return true;
        else return false;

    }

    public UserDto searchUser(String username) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE username=?");
        pstm.setString(1, username);
        ResultSet resultSet = pstm.executeQuery();

        UserDto dto = null;
        if (resultSet.next()) {
            dto = new UserDto(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        }
        return dto;
    }

}
