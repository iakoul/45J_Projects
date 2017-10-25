/* Lab3Test.java
 * 
 * ICS 45J - Lab 3
 *
 * Written by Ricky Shields and  for ICS 45J (Fall 2016)
 * rdshield@uci.edu, 72069422, Ricky Shields
 *  
 * 
 * This class is used to run JUnit tests on the Roster Manager and all connected custom classes.
 * 
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * Represents a set of JUnit tests on RosterManager and attached/implemented classes. 
 */
public class Lab3Test {

   /** Instance of RosterManager class to run tests */
   private RosterManager rm;
   
  /** Recreating RosterManager for each test to insure errors are not caused by 
    * earlier steps
    */
   @Before //Executed before each test in this class
   public void testSetup(){
       System.out.println("Setting up for Test");
       rm = new RosterManager();
   }
   
   /**Testing AddCourse method to verify that Courses given are properly placed into Array*/
   @Test
   public void testAddCourse() {
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           assertEquals(1,rm.getCourseCount());
           assertEquals("ICS45J",rm.getCourseList()[0].getCourseCode());
           assertEquals("Intro to Java",rm.getCourseList()[0].getCourseName());
           rm.addCourse(new Course("ICS46","Discrete Structures"));
           assertEquals(2,rm.getCourseCount());
           assertEquals("ICS46",rm.getCourseList()[1].getCourseCode());
           assertEquals("Discrete Structures",rm.getCourseList()[1].getCourseName());
           
       }
       catch (Exception e) {}
   }
   
   /**Testing RemoveCourse method to verify that removing Course clears it from the array
   * and that the course count is incremented.
   */
   @Test
   public void testRemoveCourse() {
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.deleteCourse("ICS45J");
           assertEquals(0,rm.getCourseCount());
       }
       catch (Exception e){}
   }
   
  /** Testing AddStudent from RosterManager to verify that information is properly placed into
  * Course and Student arrays and all information is accessible.
  */
   @Test
   public void testAddStudent() {
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.getCourseList()[0].addStudent(new Student(1234567,"Ricky","Shields"));
           assertEquals(1,rm.getCourseList()[0].getEnrollCount());
           assertEquals(1234567,rm.getCourseList()[0].getStudents()[0].getStudentID());
           assertEquals("Ricky",rm.getCourseList()[0].getStudents()[0].getFirstName());
           assertEquals("Shields",rm.getCourseList()[0].getStudents()[0].getLastName());
           
           rm.getCourseList()[0].addStudent(new Student(2345678,"Adam","Smith"));
           assertEquals(2,rm.getCourseList()[0].getEnrollCount());
           assertEquals(2345678,rm.getCourseList()[0].getStudents()[1].getStudentID());
           assertEquals("Adam",rm.getCourseList()[0].getStudents()[1].getFirstName());
           assertEquals("Smith",rm.getCourseList()[0].getStudents()[1].getLastName());
       }
       catch (Exception e){}
   }
   
  /** Testing RemoveStudent method and verifying that Student is removed from the array and
  * that the array is compressed to the front.*/
   @Test
   public void testRemoveStudent() {
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.getCourseList()[0].addStudent(new Student(1234567,"Ricky","Shields"));
           rm.getCourseList()[0].addStudent(new Student(2345678,"Adam","Smith"));
           rm.deleteStudent("ICS45J", 1234567);
           
           assertEquals(2345678,rm.getCourseList()[0].getStudents()[0].getStudentID());
           assertEquals("Adam",rm.getCourseList()[0].getStudents()[0].getFirstName());
           assertEquals("Smith",rm.getCourseList()[0].getStudents()[0].getLastName());
       }
       
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when Course already exists in the Course Array*/
   @Test(expected = DuplicateCourseException.class)
   public void testDuplicateCourseException() throws DuplicateCourseException{
       try{
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.addCourse(new Course("ICS45J","Intro to Java"));
       }
       catch (DuplicateCourseException e){ throw e; }
       catch (Exception e){}
   }
   
  /** Testing Exception throwing for situation when Course Array is full (10)*/
   @Test(expected = CourseLimitException.class)
   public void testCourseLimitException()throws CourseLimitException{
       try {
           for(int i=0;i<=10;i++){
               rm.addCourse(new Course("ICS"+i,"Intro to Java"));
           }
       }
       catch (CourseLimitException e) { throw e; }
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when Course is not found in the Array*/
   @Test(expected = CourseNotFoundException.class)
   public void testCourseNotFoundException() throws CourseNotFoundException{
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.addStudent("ICS45C", new Student(1234,"Rick","Shields"));
       }
       catch (CourseNotFoundException e) { throw e; }
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when Course Array is empty when trying to delete a Course*/  
   @Test(expected = EmptyCourseListException.class)
   public void testEmptyCourseListException() throws EmptyCourseListException{
       try {
           rm.deleteCourse("ICS45J");
       }
       catch (EmptyCourseListException e) { throw e;}
       catch (Exception e) { }
   }

   /** Testing Exception throwing for situation when the enrolled number of students is reached and another student is added to a Course*/
   @Test(expected = StudentLimitException.class)
   public void testStudentLimitException() throws StudentLimitException{
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           for(int s=0; s <= 51; s++) {
               rm.addStudent("ICS45J",new Student(s,""+s,""));
           }
       }
       catch (StudentLimitException e) { throw e; }
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when the Course's Student Array is empty and a Student is requested to be deleted*/
   @Test(expected = EmptyStudentListException.class)
   public void testEmptyStudentListException() throws EmptyStudentListException{
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.deleteStudent("ICS45J", 1234);
       }
       catch (EmptyStudentListException e) { throw e; }
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when a student is requested to be deleted but is not found in the Course's Student array*/
   @Test(expected = StudentNotFoundException.class)
   public void testStudentNotFoundException() throws StudentNotFoundException{
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.addStudent("ICS45J",new Student(123,"",""));
           rm.deleteStudent("ICS45J", 125);
       }
       catch (StudentNotFoundException e) { throw e;}
       catch (Exception e) { }
   }
   
   /** Testing Exception throwing for situation when trying to add a student who is already in the Course's Student array*/
   @Test(expected = DuplicateStudentException.class)
   public void testDuplicateStudentException() throws DuplicateStudentException{
       try {
           rm.addCourse(new Course("ICS45J","Intro to Java"));
           rm.addStudent("ICS45J",new Student(123,"",""));
           rm.addStudent("ICS45J",new Student(123,"",""));
       }
       catch (DuplicateStudentException e) { throw e; }
       catch (Exception e) { }
   }
   
   /** Runs after each test to signify the test was completed */
   @After
   public void executeAfterTest() {
       rm = null;
       System.out.println("Test Completed");
   }
   
   /** Runs after all tests are completed*/
   @AfterClass
   public static void executeAfterAllTests() {
       System.out.println("All Tests Completed!");
   }
}
