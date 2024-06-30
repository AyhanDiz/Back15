package htwberlin.webtech.web;

public class User {
    private String Name;
    private String Vorname;
    private String Email;

    public User(String Name, String Vorname, String Email){
        this.Name= Name;
        this.Vorname= Vorname;
        this.Email= Email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
