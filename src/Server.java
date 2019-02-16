import java.util.ArrayList;
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

        System.out.println("1. View Profile");
        System.out.println("2. Create Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Registered Event");
        System.out.println("5. ");
        System.out.println("6. View Event upon interest ");
        System.out.println("7. View All Events");
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

}
