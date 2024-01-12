package lk.ijse.dogCareClinic.dto.tm;

public class CommunityTm {
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
    public CommunityTm(){

    }
    public CommunityTm(String projectId, String name, String date, String location){
        this.projectId = projectId;
        this.name = name;
        this.date = date;
        this.location = location;
    }
}
