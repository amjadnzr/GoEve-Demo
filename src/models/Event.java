package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Event {

    private String title;
    private Date eventDate;
    private int maxPart;    //Will store the maximum number of partcipants
    private ArrayList<User> regUsers;
    private User admin;
    static ArrayList<Event>eventList= new ArrayList<>();


    public Event(String title, Date eventDate, int maxPart,User admin) {
        this.title = title;
        this.eventDate = eventDate;
        this.maxPart = maxPart;
        this.admin=admin;
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

    public static Event createEvent(String title,Date date,int maxPart,User admin){

        Event event= new Event(title,date,maxPart,admin);
        eventList.add(event);
        System.out.println("---Event Successfully created--");
        System.out.println();

        return event;
    }

    public static void delEvent(Event e){
        eventList.remove(e);
        System.out.println("Successfully Deleted Event");
    }
}
