import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import models.*;
public class Server {

      static Scanner sc = new Scanner(System.in);
      static User currentUser=null;

    //default serialVersion id
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {

        // Firstly have to load the files
        readFile();

        System.out.println("------Welcome to GoEve\uD83D\uDE03------");
        System.out.println("To continue");
        while(currentUser==null) {
            login();
            while (currentUser!=null){
                action();
            }
        }

    }

public static void action(){

    System.out.println("Welcoome: ---"+currentUser.getName()+" ---");
    int num;
    do {

        System.out.println("1. View Profile");
        System.out.println("2. Create Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Register for Event");
        System.out.println("5. View All Events");
        System.out.println("6. Accept Registration for events");
        System.out.println("7. Accept User to event");
        System.out.println("0. Log out");

        num=Integer.parseInt(sc.nextLine());

        while(num<0 || num>7){
            System.err.println("Invalid input!! Re-enter");
            num=Integer.parseInt(sc.nextLine());
        }

        switch(num){
            case 0:
                currentUser=null;
                break;
            case 1:
                currentUser.userInfo();
                break;
            case 2:
                createEvent();
                break;
            case 3:
                delEvent();
                break;
            case 4:
                registerEvent();
                break;
            case 5:
                allEvents();
                break;
            case 6:
                acceptReg();
                break;
            case 7:
                acceptUser();
                break;
        }
    }while(num!=0);

}

    public static void login(){
        System.out.println("---select action---");
        System.out.println("1. Sign Up");
        System.out.println("2. Sign In");
        System.out.println("0.Exit Application");
        int select=Integer.parseInt(sc.nextLine());

        while(select!=1 && select!=2 && select!=0){
            System.err.println("Invalid Input,Select action again");
            select=Integer.parseInt(sc.nextLine());
        }

        // decides whether the user has logged in or not


        if(select==1){
            signup();;
        }
        else if(select==0){
            System.out.println("---Thank you for using GoEve---");
            System.out.println("See you soon...\uD83D\uDCA9");
            System.out.println("Have a nice day\uD83D\uDE07");
            //Write file before exit
            writeFile();
            System.exit(0);
        }
        else{
            signin();
        }
    }

    private static void signup() {currentUser=User.signup();}

    private static void signin() {currentUser=User.signin();}

    private static void createEvent() {
        System.out.println("---Create Event");
        System.out.println("Select Event Type:");
        String interest[]={"Food Events","Tech Events","Music and Dance Events","Sport Events","Education Event"};
        System.out.println("1. Food Events");
        System.out.println("2. Tech Events");
        System.out.println("3. Music and Dance Events");
        System.out.println("4. Sports Events");
        System.out.println("5. Educational Events");

        int choice= Integer.parseInt(sc.nextLine());


        while (choice>5 || choice<0){
            System.err.println("Selection can only be done in the range of 1 to 5");
            System.out.println("Select Event Type");
            choice= Integer.parseInt(sc.nextLine());
        }
        String type=interest[choice-1];

        System.out.println("Enter Event title:");
        String title=sc.nextLine();

        System.out.println("Enter event date(DD/MM/YYYY)");
        String date[]= sc.nextLine().split("/");


        // to make sure numbers less than 10 are single digits
        if(date[0].charAt(0)=='0'){
            date[0]=String.valueOf(date[0].charAt(1));
        }

        if(date[1].charAt(0)=='0'){
            date[1]=String.valueOf(date[1].charAt(1));
        }


        int day=Integer.parseInt(date[0]);
        int month=Integer.parseInt(date[1]);
        boolean isDate=true;

        if(day>31 || day<1){
            isDate=false;
        }

        if(month>12 || month<1){
            isDate=false;
        }


        while (date.length!=3 || isDate==false){
            System.err.println("Invalid date input");
            date= sc.nextLine().split("/");

            // to make sure numbers less than 10 are single digits
            if(date[0].charAt(0)=='0'){
                date[0]=String.valueOf(date[0].charAt(1));
            }

            if(date[1].charAt(0)=='0'){
                date[1]=String.valueOf(date[1].charAt(1));
            }


             day=Integer.parseInt(date[0]);
            month=Integer.parseInt(date[1]);
             isDate=true;
            if(day>31 || day<1){
                isDate=false;
            }

            if(month>12 || month<1){
                isDate=false;
            }


        }


        System.out.println("Enter event Time(HH:MM)");
        String time[]= sc.nextLine().split(":");

        if(time[0].charAt(0)=='0'){
            time[0]=String.valueOf(time[0].charAt(1));
        }

        if(time[1].charAt(0)=='0'){
            time[1]=String.valueOf(time[1].charAt(1));
        }

        int h=Integer.parseInt(time[0]);
        int m=Integer.parseInt(time[1]);
        boolean isTime=true;

        if(h<0 || h>23){
            isTime=false;
        }

        if(m<0 || m>59){
            isTime=false;
        }


        while (time.length!=2 || isTime==false){
            System.err.println("Invalid time input");
            time= sc.nextLine().split(":");

            if(time[0].charAt(0)=='0'){
                time[0]=String.valueOf(time[0].charAt(1));
            }

            if(time[1].charAt(0)=='0'){
                time[1]=String.valueOf(time[1].charAt(1));
            }

             h=Integer.parseInt(time[0]);
             m=Integer.parseInt(time[1]);
             isTime=true;

            if(h<0 || h>23){
                isTime=false;
            }

            if(m<0 || m>59){
                isTime=false;
            }
        }

        Date d= new Date(Integer.parseInt(date[0]),
                         Integer.parseInt(date[1]),
                         Integer.parseInt(date[2]),
                         Integer.parseInt(time[0]),
                         Integer.parseInt(time[1]),
                         0 );

        System.out.println("Maximum seats:");
        int count=Integer.parseInt(sc.nextLine());

        while(count<0){
            System.err.println("Seat numbers can't take negative values");
            count=Integer.parseInt(sc.nextLine());
        }

        Event event=Event.createEvent(title,d,count,type,currentUser);
        currentUser.addAsEventAdmin(event);
    }

    private static void delEvent() {
        System.out.println("---Select Event to delete---");
        System.out.println("note:user can only delete events which he is admin");
        Event e=currentUser.deleteFromAdmin();
        if(e !=null){
            Event.delEvent(e);
        }


    }

    private static void registerEvent() {
        System.out.println("---Select Action---");
        System.out.println("1. View Event by user interest");
        System.out.println("2. Search for top events");
        int select=Integer.parseInt(sc.nextLine());

        while(select>2 || select<1){
            System.err.println("Input invalid re-enter");
            select=Integer.parseInt(sc.nextLine());
        }

        switch(select){
            case 1:
                Event.registerEvent(currentUser);
                break;
            case 2:
                Event.searchRegisterEvent(currentUser);
                break;
        }

    }

    private static void allEvents(){
         ArrayList<Event> events= Event.getEventList();


             if (events.size()==0){
                 System.out.println("No Events in the system");
             }else {

                 for (int i = 0; i < events.size(); i++) {
                     System.out.println(events.get(i));
                 }
             }

    }

    private static void acceptReg(){ Event.acceptReg(currentUser); }

    private static void  acceptUser(){ Event.acceptUser(currentUser);}

    private static void writeFile(){

        // Saving users to the file
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("E:\\amjad\\GoEveDemo\\user.txt"));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            ArrayList<User> userList= User.getUserList();
            System.out.println("All here"+userList.size());

            for (User user:userList) {
                objectOut.writeObject(user);
            }
            objectOut.close();
            fileOut.close();

            System.out.println("Users was successfully saved to the file");

        } catch (Exception ex) {
            System.out.println("Fatal error:User activities not save");
        }

        // Saving events to the file
        try {
            FileOutputStream fileOut = new FileOutputStream(new File("E:\\amjad\\GoEveDemo\\event.txt"));
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            ArrayList<Event> eventList= Event.getEventList();

            for (Event event:eventList) {
                objectOut.writeObject(event);
            }
            objectOut.close();
            fileOut.close();
            System.out.println("Events was successfully saved to the file");

        } catch (Exception ex) {
            System.out.println("Fatal error:Event activities not save");
        }
    }

    private static void readFile(){
        ArrayList<User> userList=User.getUserList();
        ArrayList<Event> eventList= Event.getEventList();

        // Read User from file
        try {

            FileInputStream fileIn = new FileInputStream(new File("E:\\amjad\\GoEveDemo\\user.txt"));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            while(fileIn.available() !=0) {
                User obj = (User) objectIn.readObject();
                userList.add(obj);
            }

            User.setUserList(userList);
            System.out.println("The Object has been read from the file");
            objectIn.close();
            fileIn.close();

        } catch (Exception ex) {
            System.out.println("Error in loading User file... please restart");

        }

        // Read Event from file
        try {

            FileInputStream fileIn = new FileInputStream(new File("E:\\amjad\\GoEveDemo\\event.txt"));
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            while(fileIn.available() !=0) {
                Event obj = (Event) objectIn.readObject();
                eventList.add(obj);
            }

           Event.setEventList(eventList);
            System.out.println("The Object has been read from the file");
            System.out.println(userList.size());
            objectIn.close();
            fileIn.close();

        } catch (Exception ex) {
            System.out.println("Error in loading User file... please restart");

        }
    }
}
