package lk.ijse.dogCareClinic.dto;

public class OwnerDto {
    private String OwnerId;
    private String OwnerName;
    private String Contacts;

    public OwnerDto(String OwnerId, String OwnerName, String Contacts) {
        this.OwnerId = OwnerId;
        this.OwnerName = OwnerName;
        this.Contacts = Contacts;
    }


    public String getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(String ownerId) {
        OwnerId = ownerId;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getContacts() {
        return Contacts;
    }

    public void setContacts(String contacts) {
        Contacts = contacts;
    }
}
