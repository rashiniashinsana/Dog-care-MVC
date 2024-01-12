package lk.ijse.dogCareClinic.dto.tm;

public class AppointmentTm {
    private String AppointmentId;
    private String OwnerID;
    private String EmployeeID;
    private String date;
    private String time;
    private String purpose;


    public AppointmentTm(String AppointmentId, String OwnerID, String EmployeeID, String date,String time,String purpose){
        this.AppointmentId = AppointmentId;
        this.OwnerID = OwnerID;
        this.EmployeeID = EmployeeID;
        this.date = date;
        this.time = time;
        this.purpose = purpose;

    }

    public String getAppointmentId() {
        return AppointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        AppointmentId = appointmentId;
    }

    public String getOwnerID() {
        return OwnerID;
    }

    public void setOwnerID(String ownerID) {
        OwnerID = ownerID;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

