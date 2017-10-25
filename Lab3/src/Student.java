/* Student.java
 * 
 * ICS 45J - Lab 3
 *
 * Written by Ricky Shields and  for ICS 45J (Fall 2016)
 * rdshield@uci.edu, 72069422, Ricky Shields
 *  
 * 
 * This class is used to store Student information, including student identification number,
 * first name, and last name.
 * 
 */

/**
 * Student.java
 * 
 * Represents a student enrolled in the school.
 * A student can be enrolled in many courses.
 */
public class Student {
     
    /** Represents Student's individual school Identification number*/
    private int studentID;          
    
    /** Represents Student's First Name*/
    private String firstName;       
    
    /** Represents Student's Last Name*/
    private String lastName;
    
    /** Constructor for Student Class */
    public Student(int sID, String firstName, String lastName) {
        this.studentID = sID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    //Public Setters and Getters
    /** Public method for getting StudentID
     * @return this Student's ID number
     */
    public int      getStudentID()                  {return studentID;}
    
    /** Public method for setting StudentID
     * @param studentID this Student's ID number
     */
    public void     setStudentID(int studentID)     {this.studentID = studentID;}
    
    /** Public method for getting student's first name
     * @return this Student's first name
     */
    public String   getFirstName()                  {return firstName;}
    
    /** Public method for setting StudentID
     * @param firstName this Student's first name
     */
    public void     setFirstName(String firstName)  {this.firstName = firstName;}
    
    /** Public method for getting student's last name
     * @return this Student's last name
     */
    public String   getLastName()                   {return lastName;}
    
    /** Public method for setting StudentID
     * @param studentID this Student's last name
     */
    public void     setLastName(String lastName)    {this.lastName = lastName;}
}
