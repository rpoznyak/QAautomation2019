package api.models;

public class Labels {

    public Labels(String name, String color){
        this.name = name;
        this.color = color;
    }
    public Labels(){}

    public String id;
    public String name;
    public String color;

    @Override
    public String toString() {
        return "Labels{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
