package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Event {

    private String title;
    private Date eventDate;
    private int maxPart;    //Will store the maximum number of partcipants
    private ArrayList<User> regUsers;
    private User admin;
    private String type;
    static ArrayList<Event>eventList= new ArrayList<>();


    public Event(String title, Date eventDate, int maxPart,String type,User admin) {
        this.title = title;
        this.eventDate = eventDate;
        this.maxPart = maxPart;
        this.admin=admin;
        this.type=type;
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

    public static Event createEvent(String title,Date date,int maxPart,String type,User admin){

        Event event= new Event(title,date,maxPart,type,admin);
        eventList.add(event);
        System.out.println("---Event Successfully created--");
        System.out.println();

        return event;
    }

    public static void delEvent(Event e){
        eventList.remove(e);
        System.out.println("Successfully Deleted Event");
    }

    public static void registerEvent(User user){
        Scanner sc = new Scanner(System.in);
        Event selectedEvent;
        System.out.println("---Select Event---");
        ArrayList<Event> result=new ArrayList<>();
        try {
            if(eventList.size()==0){
                throw new NullPointerException();
            }
            for (int i = 0; i < eventList.size(); i++) {
                for (int j =0; j < user.getInterest().size(); j++){
                     if(eventList.get(i).type.equals(user.getInterest().get(j))){
                         result.add(eventList.get(i));
                     }
                }
            }
        }catch (NullPointerException e){
            System.out.println("No Events with interest");
        }

        if(result.size()==0){
            System.out.println("No Events of your choice");
        }else{
            for (int i=0;i<result.size();i++){
                System.out.println((i+1)+". "+result.get(i).getTitle());
            }

            int val=Integer.parseInt(sc.nextLine());
            while(val<0 || val>result.size()){
                System.err.println("Invalid input.. re-enter");
                val=Integer.parseInt(sc.nextLine());
            }

             selectedEvent=result.get(val-1);

            // saving the user object in the event arraylist
            try {
                selectedEvent.getRegUsers().add(user);
            }catch (Exception e){
                selectedEvent.setRegUsers(new ArrayList<>());
                selectedEvent.getRegUsers().add(user);
            }

            //Saving the event object in the user list
            try {
                user.getRegEvents().add(selectedEvent);
            }catch (Exception e){
                user.setRegEvents(new ArrayList<>());
                user.getRegEvents().add(selectedEvent);
            }

            System.out.println("Successfully Registered");
        }
    }
}
