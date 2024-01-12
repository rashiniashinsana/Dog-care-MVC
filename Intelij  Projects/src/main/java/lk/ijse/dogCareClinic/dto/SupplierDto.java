package lk.ijse.dogCareClinic.dto;

public class SupplierDto {
        private String SupplierID;
        private String SupplierName;
        private String Contact;
        private String Supplierment;



        public SupplierDto(String SupplierID, String SupplierName, String Contact, String Supplierment) {
            this.SupplierID = SupplierID;
            this.SupplierName = SupplierName;
            this.Contact = Contact;
            this.Supplierment = Supplierment;

        }

    public String getSupplierID() {
        return SupplierID;
    }

    public void setSupplierID(String supplierID) {
        SupplierID = supplierID;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getSupplierment() {
        return Supplierment;
    }

    public void setSupplierment(String supplierment) {
        Supplierment = supplierment;
    }
}
