package test.nz.ac.vuw.swen301.assignment1;

import nz.ac.vuw.swen301.assignment1.Degree;
import nz.ac.vuw.swen301.assignment1.Student;
import nz.ac.vuw.swen301.assignment1.StudentManager;
import nz.ac.vuw.swen301.studentmemdb.StudentDB;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class StudentManagerTest {

    // DO NOT REMOVE THE FOLLOWING -- THIS WILL ENSURE THAT THE DATABASE IS AVAILABLE
    // INCLUDE THIS AS A STATIC FIXTURE (annotated with @BeforeClass) IN ALL TESTS
    // AND THE APPLICATION CAN CONNECT TO IT WITH JDBC
    @BeforeClass
    public static void init () {
        StudentDB.init();
    }
    // DO NOT REMOVE BLOCK ENDS HERE

    @Test
    public void testReadStudent() throws Exception {
        Student student = new StudentManager().readStudent("id52");
        assertNotNull(student);
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student createdStu = StudentManager.createStudent("Test", "Test", StudentManager.readDegree("deg1"));
        Student student = StudentManager.readStudent("id10001");
        assertEquals(createdStu, student);
    }

    @Test
    public void testGetAllStudentIds() throws Exception {
        Collection<String> ids = StudentManager.getAllStudentIds();
        assertEquals(10001, ids.size());
    }

    @Test
    public void testGetAllDegreeIds() throws Exception {
        Iterable<String> ids = StudentManager.getAllDegreeIds();
        int counter = 0;
        for(String s : ids){
            counter++;
        }
        assertEquals(counter, 16);
    }

    @Test(timeout = 1000)
    public void testReadStudentSpeed() throws Exception {
        long start = System.currentTimeMillis();
        for(int i = 1; i < 1000; i++){
            Student student = StudentManager.readStudent("id"+i);
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
