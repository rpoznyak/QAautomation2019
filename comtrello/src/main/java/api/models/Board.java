package api.models;

public class Board {
    public Board(){}

    public Board(String name){
        this.name = name;
    }

    public String id;
    public String name;
    public String desc;
    public String url;
    public String shortUrl;

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", url='" + url + '\'' +
                ", shortUrl='" + shortUrl + '\'' +
                '}';
    }
}