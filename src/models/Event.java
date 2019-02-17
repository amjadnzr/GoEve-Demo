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
    private ArrayList<User> participants; // user who accpeted for event
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

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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

    public static ArrayList<Event> getEventList() {
        return eventList;
    }

    public static void setEventList(ArrayList<Event> eventList) {
        Event.eventList = eventList;
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

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", eventDate=" + eventDate +
                ", admin=" + admin +
                ", type='" + type + '\'' +
                '}';
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

    public static void acceptReg(User user){
        Scanner sc = new Scanner(System.in);
        ArrayList<Event> result=new ArrayList<>();
        System.out.println("List of events you are admin in:");

        if(eventList.size()==0){
            System.out.println("No Events available");
        }

        else{

            for (int i=0;i<eventList.size();i++){
                if(eventList.get(i).getAdmin()==user){
                    result.add(eventList.get(i));
                }
            }

            for (int i=0;i<eventList.size();i++){
                System.out.println((i+1)+". "+result.get(i));
            }

            int input=Integer.parseInt(sc.nextLine());

            while (input<0 && input>result.size()){
                System.err.println("Invalid input... Re-enter");
                input=Integer.parseInt(sc.nextLine());
            }

            Event thisEvent=eventList.get(input-1);

            System.out.println("---Registered participant List----");
            System.out.println("Select to confirm participant");
            try {

                if(thisEvent.regUsers.size()==0){
                    throw new NullPointerException();
                }

                for (int i = 0; i < thisEvent.regUsers.size();i++) {
                    System.out.println((i+1)+". "+thisEvent.regUsers.get(i));
                }
            }catch (NullPointerException e){
                System.out.println("No Registrations as yet");
            }

            // Taking input to confirm the user
            int inputNum=Integer.parseInt(sc.nextLine());


            while (0>inputNum || inputNum>thisEvent.regUsers.size()){
                System.err.println("Invalid input.. Re enter");
                inputNum=Integer.parseInt(sc.nextLine());
            }

            // Get the user from registration list and add to participation list
            User u=thisEvent.regUsers.get(inputNum-1);
            thisEvent.regUsers.remove(inputNum-1);

            //Decides whether the max partcipant count has reached or not
            boolean b=true;
            try{
                if(thisEvent.participants.size()==thisEvent.maxPart){
                    System.err.println("Maximum participants added");
                    b=false;
                }
            }catch (Exception e){
                //only exception will be null pointer there for we can consider this coz maxpart > 0
                b=true;
            }

            if(b) {
                try {
                    thisEvent.participants.add(u);
                } catch (Exception e) {
                    thisEvent.participants = new ArrayList<>();
                    thisEvent.participants.add(u);
                }

                //Adding to user confirmed list
                try{
                    user.getRegConfEvents().add(thisEvent);
                }catch(NullPointerException e){
                    user.setRegConfEvents(new ArrayList<>());
                    user.getRegConfEvents().add(thisEvent);
                }
            }




        }

        }

    }

