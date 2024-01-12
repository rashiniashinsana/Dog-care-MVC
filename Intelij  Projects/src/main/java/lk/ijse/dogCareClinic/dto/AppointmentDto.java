package lk.ijse.dogCareClinic.dto;

public class AppointmentDto {
    private String Appointment_ID;
    private String Owner_ID;
    private String Employee_ID;
    private String Date;
    private String Time;
    private String Purpose;

    public AppointmentDto(String Appointment_ID, String OwnerID, String EmployeeID, String Date, String Time, String Purpose) {

        this.Appointment_ID = Appointment_ID;
        this.Owner_ID = OwnerID;
        this.Employee_ID = EmployeeID;
        this.Date =  Date;
        this.Time = Time;
        this.Purpose = Purpose;

    }


    public String getAppointmentID() {
        return Appointment_ID;
    }
    public void setAppointment(String AppointmentID) {
        this.Appointment_ID = Appointment_ID;
    }

    public String getOwnerID() {
        return Owner_ID;
    }
    public void setOwnerID(String OwnerID) {
        this.Owner_ID = OwnerID;
    }

    public String getEmployeeID() {
        return Employee_ID;
    }
    public void setEmployeeID(String EmployeeId) {
        this.Employee_ID = Employee_ID;
    }
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }


}