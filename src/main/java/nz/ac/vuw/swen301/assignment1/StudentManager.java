package nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.studentmemdb.StudentDB;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read operation for instances of Degree.
 * @author jens dietrich
 */
public class StudentManager {
    static HashMap<String, Student> sCache = new HashMap<String, Student>();
    static HashMap<String, Degree> dCache = new HashMap<String, Degree>();

    //  DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    static {
        /**
         * DB STRUCTURE:
         * id    |first_name|name |degree
         * id0001|Alice     |Alpha|deg0
         * id0002|Bob       |Beta |deg1
         */
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
    public static Student readStudent(String id) throws SQLException {
        //Check sCache first
        Student cStu = sCache.get(id);
        if(cStu != null) {
            return cStu;
        }

        //Get data from StudentDB - STUDENTS
        Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM STUDENTS WHERE id='" + id + "'";
        ResultSet result = statement.executeQuery(sql);
        con.close();

        //Check a result was found
        if(!result.next()){
            return null;
        }

        Student stu = new Student(id, result.getString(2), result.getString(3), readDegree(result.getString(4)));
        sCache.put(id, stu);
        return stu;
    }

    /**
     * Return a degree instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     * @param id
     * @return
     */
    public static Degree readDegree(String id) throws SQLException {
        //Check dCache first
        Degree cDeg = dCache.get(id);
        if(cDeg != null) {
            return cDeg;
        }

        //Get data from studentDB - DEGREES
        Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");
        Statement statement = con.createStatement();
        String sql = "SELECT * FROM DEGREES WHERE id='" + id + "'";
        ResultSet result = statement.executeQuery(sql);
        con.close();

        //Check a result was found
        if(!result.next()){
            return null;
        }

        Degree deg = new Degree(id, result.getString(2));
        dCache.put(id, deg);
        return deg;
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

    //Fix this
    public Student findById(String id) {
        return null;
    }
}
