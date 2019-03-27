package nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.studentmemdb.StudentDB;

import java.sql.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * A student managers providing basic CRUD operations for instances of Student, and a read operation for instances of Degree.
 * @author jens dietrich
 */
public class StudentManager {
    private static HashMap<String, Student> sCache = new HashMap<>();
    private static HashMap<String, Degree> dCache = new HashMap<>();

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
     * @param id of the student you wish to read
     * @return the student that matches the given id, null if no result found
     */
    public static Student readStudent(String id) {
        //Check sCache first
        Student cStu = sCache.get(id);
        if(cStu != null) {
            return cStu;
        }
        try {
            //open connection
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");
            //Get info from database
            String sql = "select * from STUDENTS where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            //Check a result was found
            if (!result.next()) {
                return null;
            }

            //Add to cache
            Student stu = new Student(id, result.getString(2), result.getString(3), readDegree(result.getString(4)));
            sCache.put(id, stu);

            statement.close();
            con.close();
            return stu;
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Return a degree instance with values from the row with the respective id in the database.
     * If an instance with this id already exists, return the existing instance and do not create a second one.
     * return null if there is no database record with this id.
     * @param id of the degree you with to read
     * @return the degree that matches the id, null if no result found
     */
    public static Degree readDegree(String id) {
        //Check dCache first
        Degree cDeg = dCache.get(id);
        if(cDeg != null) {
            return cDeg;
        }

        try {
            //open connection
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");

            //Get data from studentDB - DEGREES
            String sql = "select * from DEGREES where id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();

            //Check a result was found
            if (!result.next()) {
                return null;
            }

            //add result to cache
            Degree deg = new Degree(id, result.getString(2));
            dCache.put(id, deg);

            statement.close();
            con.close();
            return deg;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Delete a student instance from the database.
     * I.e., after this, trying to read a student with this id will return null.
     * @param student
     */
    public static void delete(Student student) {
        //Get data from studentDB - DEGREES
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");
            String sql = "DELETE FROM STUDENTS WHERE id=?";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,student.getId());
            int affected = statement.executeUpdate();

            //if something was removed also remove it from cache if possible
            if(affected > 0){
                sCache.remove(student.getId());
            }

            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Update (synchronize) a student instance with the database.
     * The id will not be changed, but the respective database record  may have changed names, first names or degree.
     * Note that names and first names can only be max 1o characters long.
     * @param student
     */
    public static void update(Student student){
        String sql= "UPDATE STUDENTS SET first_name=?, name=?, degree=? WHERE id=?";
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");

            //update database
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,student.getFirstName());
            statement.setString(2,student.getName());
            statement.setString(3,student.getDegree().getId());
            statement.setString(4,student.getId());
            statement.executeUpdate();

            //update cache
            sCache.put(student.getId(), student);

            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Create a new student with the values provided, and save it to the database.
     * The student must have a new id that is not been used by any other Student instance or STUDENTS record (row).
     * @param name of the student
     * @param firstName of the student
     * @param degree of the student
     * @return a freshly created student instance
     */
    public static Student createStudent(String name,String firstName,Degree degree) {
        try {
            //Create student
            String id = "id" + (getAllStudentIds().size() + 1); //TODO: fix id generation
            Student stu = new Student(id, name, firstName, degree);

            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");

            //Put into database
            String sql = "INSERT INTO STUDENTS (id, first_name, name, degree) VALUES (?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1,id);
            statement.setString(2,firstName);
            statement.setString(3,name);
            statement.setString(4,degree.getId());
            statement.executeUpdate();

            statement.close();
            con.close();

            //put in cache
            sCache.put(id, stu);

            return stu;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all student ids currently being used in the database.
     * @return
     */
    public static Collection<String> getAllStudentIds() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");

            String sql = "select ID from STUDENTS";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            HashSet<String> ids = new HashSet<>();
            while(result.next()){
                ids.add(result.getString("id"));
            }

            statement.close();
            con.close();
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Get all degree ids currently being used in the database.
     * @return
     */
    public static Iterable<String> getAllDegreeIds() {

        try {
            Connection con = DriverManager.getConnection("jdbc:derby:memory:student_records");
            String sql = "select ID from DEGREES";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            HashSet<String> ids = new HashSet<>();
            while(result.next()){
                ids.add(result.getString("id"));
            }

            statement.close();
            con.close();
            return ids;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


}
