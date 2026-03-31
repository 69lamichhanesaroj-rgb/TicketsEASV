package dk.easv.ticketseasv.be;

public class User {
    private int id;
    private String name;
    private int roleId;
    private String email;
    private String password;
    private String role;

    public User(int id, String name, int roleId, String email, String password) {
        this.id = id;
        this.name = name;
        this.roleId = roleId;
        this.email = email;
        this.password = password;

    }

    public User(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
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

    public void  setRole(String role) {this.role = role;}
    public String getRole() {return role;}
}
