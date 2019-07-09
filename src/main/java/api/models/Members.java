package api.models;

public class Members {

    public String id;
    public String fullName;
    public String username;

    @Override
    public String toString() {
        return "Members{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
