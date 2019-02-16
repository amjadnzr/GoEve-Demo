package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String title;
    private Date eventDate;
    private int maxPart;    //Will store the maximum number of partcipants
    private ArrayList<User> regUsers;
    static ArrayList<Event>eventList= new ArrayList<>();


    public Event(String title, Date eventDate, int maxPart) {
        this.title = title;
        this.eventDate = eventDate;
        this.maxPart = maxPart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getMaxPart() {
        return maxPart;
    }

    public void setMaxPart(int maxPart) {
        this.maxPart = maxPart;
    }

    public ArrayList<User> getRegUsers() {
        return regUsers;
    }

    public void setRegUsers(ArrayList<User> regUsers) {
        this.regUsers = regUsers;
    }

    public static void createEvent(String title,Date date,int maxPart){

        Event event= new Event(title,date,maxPart);
        eventList.add(event);
        System.out.println("---Event Successfully created--");
        System.out.println();
    }
}
