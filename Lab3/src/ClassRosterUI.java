import java.util.Arrays;
import java.util.Scanner;
/* ClassRosterUI.java
 * 
 * ICS 45J - Lab 3
 *
 * Written by Ricky Shields and  for ICS 45J (Fall 2016)
 * rdshield@uci.edu, 72069422, Ricky Shields
 *  
 * 
 * This class is used for reading and formatting input from the user,
 * including menus and character input.
 * 
 */

/**
 * ClassRosterUI.java
 * 
 * Represents a input/output for ClassRoster software. 
 *  
 */
public class ClassRosterUI {
   
    /**Scanner object for user input*/
    private static Scanner scan;
    
    /**Static list of accepted user-provided entries*/
    private static final String[] CHOICES = {"ac","dc","as","ds","p","q"};
    
    /**Creates a new ClassRosterUI object and initiate the Scanner for user input*/
    public ClassRosterUI(){
        scan = new Scanner(System.in);
    }
    
    /**Prints the Menu of options for user input*/
    public static void printMenu(){
        System.out.println("====================");
        System.out.println("Select an action based on the following menu:");
        System.out.println("----------");
        System.out.println("ac: Add Course");
        System.out.println("dc: Drop Course");
        System.out.println("as: Add Student");
        System.out.println("ds: Drop Student");
        System.out.println(" p: Print ClassRoster");
        System.out.println(" q: Quit Program");
        System.out.println("----------");
    }
    
    /**Retrieves the user's input from scanner, tests it against an array of accepted choices,
     * and either requests new entries or returns the choice in lower-case.
     * @return User's entry for command
     */
    public static String getCommand(){
        String entry = "";
        while(!Arrays.asList(CHOICES).contains(entry))
        {
            System.out.print("Enter Command:");
            entry = scan.nextLine().toLowerCase();
        }
        return entry;
    }
    
    /**Retrieves user input for course code and returns it in upper-case.
     * @return User's entry for course ID
     */
    public String getCourseID() {
        System.out.print("Enter Course Code:");
        return scan.nextLine().toUpperCase();
    }
    
    /**Retrieves user input for student ID, checks it to verify the entry is a number,
     * and returns the number if it is valid.
     * @return User's entry for student ID
     */
    public int getStudentID() {
        boolean idObtained = false;
        int studentID = 0;
        while(!idObtained){
            System.out.print("Enter Student ID:");
            if(scan.hasNextInt() ){
                studentID = scan.nextInt();
                if(studentID >= 0)
                    idObtained = true;
                scan.nextLine();
            }
            else {
                System.out.println("Invalid entry.");
                scan.nextLine();
            }
        }
        return studentID;
    }
    
    /**Retrieves user input for course ID/Course Name and returns a new Course object with that information.
     * @return Course object from user's input
     */
    public Course getCourseInfo(){
        String courseCode = getCourseID();
        System.out.print("Enter Course Name:");
        String courseName = scan.nextLine();
        return new Course(courseCode,courseName);
    }
    
    /**Retrieves user input for student ID, first/last name, and returns an object with that information.
     * @return Student object from user's input
     */
    public Student getStudentInfo() {
        int studentID = getStudentID();
        System.out.print("Enter First Name:");
        String firstName = scan.nextLine();
        System.out.print("Enter Last Name:");
        String lastName = scan.nextLine();
        return new Student(studentID,firstName,lastName);
    }
}