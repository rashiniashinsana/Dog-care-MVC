package lk.ijse.dogCareClinic.dto;

public class communityDto {
    private String projectId;
    private String name;
    private String date;
    private String location;

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public communityDto(String projectId, String name,  String date,String location){

        this.projectId = projectId;
        this.name = name;
        this.location = location;
        this.date = date;
    }

}
