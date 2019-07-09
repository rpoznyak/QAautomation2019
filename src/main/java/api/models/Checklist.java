package api.models;

public class Checklist {

    public Checklist(){}

    public String id;
    public String name;

    @Override
    public String toString() {
        return "Checklist{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
