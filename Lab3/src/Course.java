/* Course.java
 * 
 * ICS 45J - Lab 3
 *
 * Written by Ricky Shields and  for ICS 45J (Fall 2016)
 * rdshield@uci.edu, 72069422, Ricky Shields
 *  
 * 
 * This class is used to represent a course in the RosterManager, including
 * an identifying code, the number of students enrolled, and a list/array of said Students.
 * 
 */

/**
 * Course.java
 * 
 * Represents a Course of study in the RosterManager, including
 * an identifying code, the number of students enrolled, and a list/array of said Students.
 * 
 */
public class Course
{
    /**Identifying Code for this course*/
    private String courseCode;
        
    /**Name of Course*/
    private String courseName;
        
    /**Count of Students enrolled*/
    private int enrollCount;
        
    /**Array of Students enrolled in this course*/
    private Student[] students;
   
    /**Default Constructor for Course Class*/
    public Course(){
        courseCode = "0";
        courseName = "";
        enrollCount = 0;
        students = new Student[50];
    }
    
    /**Constructs a new Course Object with user-provided ID and class name*/
    public Course(String classCode, String className){
        courseCode = classCode;
        courseName = className;
        enrollCount = 0;
        students = new Student[50];
    }
    
    /**Returns this course's ID code
     * @return course's ID Code
     */
    public String getCourseCode(){return courseCode;}
    
    /**Sets this course's ID Code
     * @param courseCode user-defined code for this Course
     */
    public void setCourseCode(String courseCode)   {this.courseCode = courseCode;}
    
    /**Returns this course's name
     * @return course's name
     */
    public String getCourseName(){return courseName;}

    /**Sets this course's name
     * @param courseCode user-defined name for this Course
     */
    public void setCourseName(String courseName){this.courseName = courseName;}
    
    /**Returns this course's enroll count
     * @return course's count
     */
    public int getEnrollCount(){return enrollCount;}
    
    /**Sets this course's enroll count
     * @param courseCode user-defined enroll count
     */
    public void setEnrollCount(int enrollCount){this.enrollCount = enrollCount;}
    
    /**Returns this course's array of students enrolled
     * @return course's student array
     */
    public Student[] getStudents(){return students;}
    
    /**Sets this course's student array
     * @param courseCode user-defined code   for this Course
     */
    public void      setStudents(Student[] students)    {this.students = students;}
    
    /**Adds student to this course's array of enrolled students
     */
    public void addStudent(Student s){
        students[enrollCount] = s; 
        enrollCount++;
    }
    
    /**Removes student to this course's array of enrolled students
     */
    public void removeStudent(int studentID) throws EmptyStudentListException, StudentNotFoundException {
        if(enrollCount == 0)
            throw new EmptyStudentListException();
        
        int stack = 0;
        boolean studentRemoved = false;
        
        for(int i = 0; i < enrollCount; ++i ){
            boolean studentExists = (students[i] != null);
            if( (studentExists) && (students[i].getStudentID() == studentID)) {
                enrollCount--;
                studentRemoved = true; 
                for(int j = stack; j < enrollCount; ++j) {
                    students[j] = students[j+1];
                }
                
                break;
            }
            else
                stack++;
        }
        if (!studentRemoved) throw new StudentNotFoundException();
    }
}