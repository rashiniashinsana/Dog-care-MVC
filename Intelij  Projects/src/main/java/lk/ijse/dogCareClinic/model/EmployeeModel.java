package lk.ijse.dogCareClinic.model;

import lk.ijse.dogCareClinic.db.DbConnection;
import lk.ijse.dogCareClinic.dto.AppointmentDto;
import lk.ijse.dogCareClinic.dto.EmployeeDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {

        public boolean saveEmployee(EmployeeDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, dto.getEmployeeId());
            pstm.setString(2, dto.getEmployeeName());
            pstm.setString(3, dto.getNIC());
            pstm.setString(4, dto.getSex());
            pstm.setString(5, dto.getContact());
            pstm.setString(6, dto.getJobRole());

            boolean isSaved = pstm.executeUpdate() > 0;

            return isSaved;
        }

        public boolean deleteEmployee(String id) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "DELETE FROM employee WHERE Emp_ID = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            return pstm.executeUpdate() > 0;
        }

        public List<EmployeeDto> getAllEmployee() throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "SELECT * FROM employee";
            PreparedStatement pstm = connection.prepareStatement(sql);

            List<EmployeeDto> dtoList = new ArrayList<>();

            ResultSet resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                String Emp_ID = resultSet.getString(1);
                String Emp_Name = resultSet.getString(2);
                String NIC = resultSet.getString(3);
                String Sex = resultSet.getString(4);
                String Contact = resultSet.getString(5);
                String JobRole = resultSet.getString(6);

                var dto = new EmployeeDto(Emp_ID,Emp_Name,NIC,Sex,Contact,JobRole);
                dtoList.add(dto);
            }
            return dtoList;
        }

        public boolean update(EmployeeDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE employee SET  Emp_Name = ?, NIC = ?, Sex = ?, Contact = ?, JobRole =? WHERE Emp_ID = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, dto.getEmployeeName());
            pstm.setString(2, dto.getNIC());
            pstm.setString(3, dto.getSex());
            pstm.setString(4, dto.getContact());
            pstm.setString(5, dto.getJobRole());
            pstm.setString(6, dto.getEmployeeId());

            return pstm.executeUpdate() > 0;
        }
    public EmployeeDto searchEmployee(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM employee WHERE Emp_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        EmployeeDto dto = null;

        if (resultSet.next()) {
            String EmployeeID = resultSet.getString(1);
            String EmployeeName = resultSet.getString(2);
            String NIC = resultSet.getString(3);
            String Sex = resultSet.getString(4);
            String Contact = resultSet.getString(5);
            String JobRole = resultSet.getString(6);

            dto = new EmployeeDto(EmployeeID,EmployeeName,NIC,Sex,Contact,JobRole);

        }
        return dto;

    }

    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE employee SET  Emp_Name = ?,  NIC  =?, Sex= ?, Contact = ?, JobRole = ? WHERE Emp_ID = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEmployeeName());
        pstm.setString(2, dto.getEmployeeName());
        pstm.setString(3, dto.getNIC());
        pstm.setString(4, dto.getSex());
        pstm.setString(5, dto.getContact());
        pstm.setString(6, dto.getJobRole());
        return pstm.executeUpdate() > 0;

    }
}


