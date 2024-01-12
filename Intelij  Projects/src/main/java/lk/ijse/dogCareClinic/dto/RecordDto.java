package lk.ijse.dogCareClinic.dto;

public class RecordDto {
    private String recordId;
    private String description;
    private String date;

    public RecordDto(String recordId, String description, String date) {
        this.recordId = recordId;
        this.description = description;
        this.date = date;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
