package dk.easv.ticketseasv.be;

public class User {
    private int id;
    private String role;
    private String Username;
    private String email;
    private String password;
    private String salt;


    public User(int id, String role, String Username, String email, String password, String salt) {
        this.id = id;
        this.role = role;
        this.Username = Username;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public User(String name, String role) {
        this.Username = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void  setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
