package lk.ijse.dogCareClinic.dto.tm;

public class DogTm {
    private String D_ID;
    private String D_Name;
    private String Gender;
    private String Breed;
    private String Age;
    private String O_ID;

    public DogTm(String D_ID, String D_Name, String Gender, String Breed , String Age,String O_ID){
        this.D_ID = D_ID;
        this.D_Name = D_Name;
        this.Gender = Gender;
        this.Breed = Breed;
        this.Age = Age;
        this.O_ID = O_ID;
    }

    public String getD_ID() {
        return D_ID;
    }

    public void setD_ID(String d_ID) {
        D_ID = d_ID;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String d_Name) {
        D_Name = d_Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getO_ID() {
        return O_ID;
    }

    public void setO_ID(String o_ID) {
        O_ID = o_ID;
    }
}
