package models;

import java.util.ArrayList;
import java.util.Scanner;

public class User {

    private String email;
    private String password;
    private String name;
    private int age;
    private ArrayList<String> interest;
    private ArrayList<Event> regEvents;
    private static ArrayList<User> userList= new ArrayList<>();

    public User(String email, String password, String name, int age, ArrayList<String> interest) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.interest = interest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getInterest() {
        return interest;
    }

    public void setInterest(ArrayList<String> interest) {
        this.interest = interest;
    }

    public ArrayList<Event> getRegEvents() {
        return regEvents;
    }

    public void setRegEvents(ArrayList<Event> regEvents) {
        this.regEvents = regEvents;
    }

    public static User signin(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter email:");
        String email=sc.nextLine();
        System.out.println("Enter password:");
        String pass=sc.nextLine();
        User logedUser=null;

        if(userList.size()!=0) {
            for (User user : userList) {

                // checking whether the emails match
                if (user.getEmail().equals(email)) {
                    // if email is equal checking whether the passwords are equal
                    if (user.getPassword().equals(pass)) {
                        logedUser = user;
                    }
                }
            }
        }else{
            System.err.println("Users not available in the system");
        }
        sc.close();
        return logedUser;
    }

    public static User signup(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Your Full Name:");
        String name=sc.nextLine();

        System.out.println("Enter your email Address");
        String email=sc.nextLine();

        System.out.println("Enter your Password");
        String pass=sc.nextLine();

        boolean isThere=false;

        do{
           for(User user:userList){
               if(user.getEmail().equals(email)) {
                   isThere = true;
                   System.err.println("Invalid email: email has already registered or invalid email");
                   System.out.println("Enter your email address");
                   email = sc.nextLine();
               }
           }


        }while(isThere);

        System.out.println("Enter age:");
        int age=Integer.parseInt(sc.nextLine());

        // validate age between 70 years
        while(age>70 || age<10){
            System.err.println("Age should be in between 0 and 70 years ");
        }

        int a;

        String interest[]={"Food Events","Tech Events","Music and Dance Events","Sport Events","Education Event"};
        ArrayList <String> userInt =new ArrayList<>();

        System.out.println(" Select Event type(Add zero at the end finish interests):");
        System.out.println("1. Food Events");
        System.out.println("2. Tech Events");
        System.out.println("3. Music and Dance Events");
        System.out.println("4. Sports Events");
        System.out.println("5. Educational Events");
        System.out.println("0. Done adding interest");

        do{
            System.out.println("Add:");
            a=Integer.parseInt(sc.nextLine());

            if(a>interest.length || a<0){
                System.err.println("Invalid input");
                continue;
            }
            boolean alreadyIn=false;

           // checking whether the interest is already added
            if(userInt.size()!=0 && a!=0) {
                for (String s : userInt) {
                    if (interest[a - 1].equals(s)) {
                        alreadyIn = true;
                    }
                }
            }

            if(a==0){
                alreadyIn = true;
            }

            if(!alreadyIn){
                    userInt.add(interest[a-1]);
            }

        } while (a != 0);

        // creating the user object
        User user=new User(email,pass,name,age,userInt);
        //Adding the user to the arrayList
        userList.add(user);
        return user;
    }


    public void userInfo(){
        System.out.println("Name: "+ this.name);
        System.out.println("Email: "+this.email);
        System.out.println("Age: " +this.age);

        System.out.println("Interests:");
        if(this.interest.size()!=0) {
            for (int i = 0; i < this.interest.size(); i++) {
                System.out.println((i + 1) + ". " + this.interest.get(i));
            }
        }else{
            System.out.println("None");
        }
        System.out.println();
        System.out.println("Registered Events:");

           try {
               for (int i = 0; i < this.regEvents.size(); i++) {
                   System.out.println((i + 1) + ". " + this.regEvents.get(i));
               }


           }catch (NullPointerException e){
               System.out.println("None");
           }

        System.out.println();
    }
}

