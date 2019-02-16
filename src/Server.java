import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import models.*;
public class Server {

      static Scanner sc = new Scanner(System.in);
      static User currentUser=null;


    public static void main(String[] args) {
        System.out.println("------Welcome to GoEve------");
        System.out.println("To continue");
        while(currentUser==null) {
            login();
        }
        System.out.println("Welcoome: ---"+currentUser.getName()+" ---");
        int num=0;
        do {

            System.out.println("1. View Profile");
            System.out.println("2. Create Event");
            System.out.println("3. Delete Event");
            System.out.println("4. Registered Event");
            System.out.println("5. Scan Participant");
            System.out.println("6. View Event upon interest ");
            System.out.println("7. View All Events");
            System.out.println("0. Log out");

            num=Integer.parseInt(sc.nextLine());

            while(num<0 || num>7){
                System.err.println("Invalid input!! Re-enter");
                num=Integer.parseInt(sc.nextLine());
            }

            switch(num){
                case 0:
                    login();
                    break;
                case 1:
                    currentUser.userInfo();
                    break;
                case 2:
                    createEvent();
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
            System.out.println("See you soon...");
            System.out.println("Have a nice day:-)");
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

        System.out.println("Enter Event title:");
        String title=sc.nextLine();

        System.out.println("Enter event date(DD/MM/YYYY)");
        String date[]= sc.nextLine().split("/");

        while (date.length!=3){
            System.err.println("Invalid date input");
            date= sc.nextLine().split("/");
        }

        System.out.println("Enter event Time(HH:MM)");
        String time[]= sc.nextLine().split(":");

        while (time.length!=2){
            System.err.println("Invalid time input");
            time= sc.nextLine().split(":");
        }

        // to make sure numbers less than 10 are single digits
        if(date[0].charAt(0)=='0'){
            date[0]=String.valueOf(date[0].charAt(1));
        }

        if(date[1].charAt(0)=='0'){
            date[1]=String.valueOf(date[1].charAt(1));
        }

        if(time[0].charAt(0)=='0'){
            time[0]=String.valueOf(time[0].charAt(1));
        }

        if(time[1].charAt(0)=='0'){
            time[1]=String.valueOf(time[1].charAt(1));
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

        Event.createEvent(title,d,count);

    }

}
