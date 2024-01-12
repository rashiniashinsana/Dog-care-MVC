package lk.ijse.dogCareClinic.dto.tm;

public class EmployeeTm {
        private String employeeId;
        private String employeeName;
        private String NIC;
        private String Sex;
        private String Contact;
        private String JobRole;


        public EmployeeTm(String employeeId, String employeeName, String NIC,String Sex, String Contact,String JobRole) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.NIC = NIC;
            this.Sex = Sex;
            this.Contact = Contact;
            this.JobRole = JobRole;
        }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getJobRole() {
        return JobRole;
    }

    public void setJobRole(String jobRole) {
        JobRole = jobRole;
    }
}
