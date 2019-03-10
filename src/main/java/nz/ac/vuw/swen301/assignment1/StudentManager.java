package nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.studentmemdb.StudentDB;
import java.util.Collection;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read operation for instanbces of Degree.
 * @author jens dietrich
 */
public class StudentManager {

    //  DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    // THE FOLLOWING METHODS MUST IMPLEMENTED :

    /**
     * Return a student instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     * @param id
     * @return
     */
    public static Student readStudent(String id) {
        return null;
    }

    /**
     * Return a degree instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     * @param id
     * @return
     */
    public static Degree readDegree(String id) {
        return null;
    }

    /**
     * Delete a student instance from the database.
     * I.e., after this, trying to read a student with this id will return null.
     * @param student
     */
    public static void delete(Student student) {}

    /**
     * Update (synchronize) a student instance with the database.
     * The id will not be changed, but the respective database record  may have changed names, first names or degree.
     * Note that names and first names can only be max 1o characters long.
     * @param student
     */
    public static void update(Student student) {}


    /**
     * Create a new student with the values provided, and save it to the database.
     * The student must have a new id that is not been used by any other Student instance or STUDENTS record (row).
     * @param name
     * @param firstName
     * @param degree
     * @return a freshly created student instance
     */
    public static Student createStudent(String name,String firstName,Degree degree) {
        return null;
    }

    /**
     * Get all student ids currently being used in the database.
     * @return
     */
    public static Collection<String> getAllStudentIds() {
        return null;
    }

    /**
     * Get all degree ids currently being used in the database.
     * @return
     */
    public static Iterable<String> getAllDegreeIds() {
        return null;
    }


}
