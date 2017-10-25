/* RosterManager.java
 * 
 * ICS 45J - Lab 3
 *
 * Written by Ricky Shields and  for ICS 45J (Fall 2016)
 * rdshield@uci.edu, 72069422, Ricky Shields
 *  
 * 
 * This class holds an array of Courses and modifies said list through user input provided by ClassRosterUI. 
 * 
 */

/**
 * RosterManager.java
 * 
 * Represents an instance of RosterManager.
 * Modifies said list through user input provided by ClassRosterUI.
 * 
 */
public class RosterManager{
    
    /**Data structure storing the list of courses*/
    private Course[] courseList;        
  
    /**Integer value storing the number of courses*/
    private int courseCount;                
    /*Instance used to obtain information from user*/
    private ClassRosterUI input;            
  
    /**The RosterManager Constructor. courseList is instantiated and the courseCount is set to 0*/
    public RosterManager(){
        courseList = new Course[10];        
        courseCount = 0;                    
        input = new ClassRosterUI();        
    }
  
    /**Primary method for prompting user for information and menu functionality.
     * User input is parsed for correctness and the corresponding function is run.
     */
    public void run() {
        boolean done = false;
        System.out.println("Welcome to Class Roster Manager!");
        
        try {
            while(!done) {
                ClassRosterUI.printMenu();
                String choice = ClassRosterUI.getCommand();
                             
                if(choice.matches("ac")){ //Add a Course to the Course Array
                    addCourse(input.getCourseInfo());
                }
                else if(choice.matches("dc")){ //Remove a Course from the Course Array
                    deleteCourse(input.getCourseID());
                }
                else if(choice.matches("as")){ //Add a Student to the Student array for the noted course
                    addStudent(input.getCourseID(),input.getStudentInfo());
                }
                else if(choice.matches("ds")){ //Add a Student from the Student array for the noted course
                    deleteStudent(input.getCourseID(),input.getStudentID());
                }
                else if(choice.matches("p")){ //Print the list of Courses and enrolled Students
                    printRoster();
                }
                else if(choice.matches("q")){ //Close the program
                    done = true;
                }
            }
        }
        
        /**List of catch exceptions down below */
        catch (DuplicateCourseException e){ //Course exists in Course Array
            System.out.println("Error - Course already exists!");
        }
        
        catch (CourseLimitException e){ //Course Array is full
            System.out.println("Error - Course List is full!");
        }
        
        catch (CourseNotFoundException e){ //Course ID provided is not listed in Course Array
            System.out.println("Error - Course does not exist!");
        }
        
        catch (EmptyCourseListException e){ //No Courses in Course Array
            System.out.println("Error - Course list is empty!");
        }
        
        catch (EmptyStudentListException e){ //No Students in Student array For Course
            System.out.println("Error - Student list for course is empty!");
        }
        
        catch (StudentLimitException e){ //Student Array full for Course
            System.out.println("Error - Course is full!");
        }
        
        catch (StudentNotFoundException e){ //Student is not in Course in question
            System.out.println("Error - Student is not in this course!");
        }
        
        catch (DuplicateStudentException e){ // Student is already enrolled in the Course.
            System.out.println("Error - Student is already on course list!");
        }
        
        /**If errors are thrown, run is called again to restart the menu.*/
        finally {
            if(!done)
                run();
        }
    }
    
    /**Adds course to the courseList*/
    public void addCourse(Course c) throws DuplicateCourseException, CourseLimitException {
        /**Throw CourseLimitException is array is full*/
        if (courseCount >= 10){
            throw new CourseLimitException();
        }
        else {
            
            if(courseCount > 0){
                for (int i=0; i<courseCount;i++ ) {
                    if(courseList[i].getCourseCode().equals(c.getCourseCode())) {
                        throw new DuplicateCourseException();
                    }
                }
            }
        }
        courseList[courseCount] = c;
        courseCount++;
    }
    
    /**Deletes a course in the courseList
     * @param user-defined code for the course
     */
    public void deleteCourse(String courseCode) throws CourseNotFoundException, EmptyCourseListException {
        if(courseCount == 0)
            throw new EmptyCourseListException();
        
        int cPlacement = 0;
        boolean exists = false;
        for(Course c: courseList){
            if (c.getCourseCode().equals(courseCode)){
                exists = true;
                courseCount--;
                for(int i = cPlacement;i < courseCount; i++){
                    courseList[i] = courseList[i+1];
                }
                break;
            }
            cPlacement++;
        }
        
        if(!exists){
            throw new CourseNotFoundException();
        }
    }
    
    /**Adds a student to the studentList in the respective course given
     * @param user defined course code, and user defined student
     */
    public void addStudent(String courseCode, Student s) throws StudentLimitException, DuplicateStudentException, CourseNotFoundException {
        int courseLoc;
        for(courseLoc=0;courseLoc < courseCount; courseLoc++){
            if(courseList[courseLoc].getCourseCode().equals(courseCode)){
                break;
            }
        }
        
        if (courseLoc == courseCount){
                throw new CourseNotFoundException();
        }
        else if (courseList[courseLoc].getEnrollCount()>=10){
                throw new StudentLimitException();    
        }
        else {
            for(int sLoc = 0; sLoc < courseList[courseLoc].getEnrollCount();sLoc++) {
                if(courseList[courseLoc].getStudents()[sLoc].getStudentID() == s.getStudentID()) {
                    throw new DuplicateStudentException();
                }
            }
        }
        courseList[courseLoc].addStudent(s);
    }
  
    /**Removes a student from a course in the rosterManager
     * @param user-defined code for course, user-defined student id
     */
    public void deleteStudent(String courseCode,int studentID) throws EmptyStudentListException, StudentNotFoundException, CourseNotFoundException {
        boolean deleted = false;
        for(int c = 0; c < courseCount; c++){
          if(courseList[c].getCourseCode().equals(courseCode))
              courseList[c].removeStudent(studentID);
              deleted = true;
      }  
        
      if(!deleted){
          throw new CourseNotFoundException();
      }
    }
  
    /** Prints the UI (consists of courses and people enrolled in that course) */
    public void printRoster() {
        System.out.println("********************");
        if(courseCount > 0)
        {
            for(int i = 0; i < courseCount; i++){
                System.out.println(courseList[i].getCourseCode() + ": " + courseList[i].getCourseName());
                System.out.println("Enrolled: " + courseList[i].getEnrollCount());
                for(int s = 0; s < courseList[i].getEnrollCount(); s++){    
                        System.out.println("     " + courseList[i].getStudents()[s].getStudentID() + " | " 
                        + courseList[i].getStudents()[s].getLastName() + ", " + courseList[i].getStudents()[s].getFirstName());
                }
            } 
        }
        System.out.println("********************");
    }

    /**Gets the rosterManager's course array
     * @return courseList from the rosterManager instance
     */
    public Course[] getCourseList() {
        return courseList;
    }

    /**Sets this rosterManager's course array
     * to another user defined course array.
     * @param courseList <- user-defined course array 
     */
    public void setCourseList(Course[] courseList) {
        this.courseList = courseList; 
    }
    
    /**Gets this rosterManager's course count
     * @return the number of courses in the rosterManager
     */
    public int getCourseCount() {
        return courseCount;
    }
    
    /**Sets this rosterManager's course count
     * @return the number of courses in the rosterManager
     */
    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

}