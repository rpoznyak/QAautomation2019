package api.models;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by lolik on 11.06.2019
 */
public class Card {

    public String id;
    public String desc;
    public String idBoard;
    public String idList;
    public String name;
    public String url;
    public String[] idMembers;
    public String[] idLabels;
    public String[] idChecklists;
    public Date due;



    public Card(){}

    public Card(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", idBoard='" + idBoard + '\'' +
                ", idList='" + idList + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", idMembers=" + Arrays.toString(idMembers) +
                ", idLabels=" + Arrays.toString(idLabels) +
                ", idChecklists=" + Arrays.toString(idChecklists) +
                ", due=" + due +
                '}';
    }
}

