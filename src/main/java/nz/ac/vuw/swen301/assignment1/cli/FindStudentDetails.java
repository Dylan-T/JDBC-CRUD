package nz.ac.vuw.swen301.assignment1.cli;

import nz.ac.vuw.swen301.assignment1.StudentManager;

import java.sql.SQLException;

public class FindStudentDetails {

    // THE FOLLOWING METHOD MUST IMPLEMENTED
    /**
     * Executable: the user will provide a student id as single argument, and if the details are found,
     * the respective details are printed to the console.
     * E.g. a user will invoke this by running "java -cp <someclasspath> nz.ac.vuw.swen301.assignment1.cli.FindStudentDetails id42"
     * @param arg
     */
    public static void main (String[] arg) throws SQLException {
        if(arg.length > 0) {
            Object student = StudentManager.readStudent(arg[0]);
            System.out.println(student.toString());
        }
    }
}
