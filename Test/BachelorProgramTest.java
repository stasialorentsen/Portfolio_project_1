import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BachelorProgramTest {
    BachelorProgram bachelorProgram;

    @Before
    public void setUp() {
        bachelorProgram = new BachelorProgram();
        // Basic courses
        for (int i = 1; i <= 8; i++) {
            bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Basic course " + i));
        }
        // Module courses
        for (int i = 1; i <= 10; i++) {
            bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module course " + i));
        }
        // Basic projects
        for (int i = 1; i <= 3; i++) {
            bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basic project " + i));
        }
        // Module projects
        for (int i = 1; i <= 2; i++) {
            bachelorProgram.addActivity(new Project(ActivityType.MODULE_PROJECT, "Module project " + i));
        }
        // Bachelor project
        bachelorProgram.addActivity(new Project(ActivityType.BACHELOR_PROJECT, "Bachelor project"));
    }

    @Test
    public void shouldBeValidProgram() {
        assertTrue(bachelorProgram.isValid());
    }

    @Test
    public void shouldNotBeValidIfHasDuplicateActivity() {
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Basic course 1"));
        assertFalse(bachelorProgram.isValid());
    }

    @Test
    public void shouldNotBeValidIfDoesNotHaveEnoughBasicCourses() {
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, -10, "Negative ECTS basic course"));
        assertFalse(bachelorProgram.isValid());
    }

    @Test
    public void shouldNotBeValidIfDoesNotHaveEnoughBasicProjects() {
        bachelorProgram = new BachelorProgram();
        // Basic courses
        for (int i = 1; i <= 8; i++) {
            bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Basic course " + i));
        }
        // Module courses
        for (int i = 1; i <= 10; i++) {
            bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module course " + i));
        }
        // Module projects
        for (int i = 1; i <= 2; i++) {
            bachelorProgram.addActivity(new Project(ActivityType.MODULE_PROJECT, "Module project " + i));
        }
        // Bachelor project
        bachelorProgram.addActivity(new Project(ActivityType.BACHELOR_PROJECT, "Bachelor project"));
        //Basic projects
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_PROJECT, 15,"First basic project"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_PROJECT, 15, "Second basic project"));
        assertTrue(bachelorProgram.isValid());
    }

    @Test
    public void testTotalEcts() {
        BachelorProgram bachelorProgram = new BachelorProgram();
        bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basisprojekt 1"));
        bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basisprojekt 2"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Empiriske data"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Eksperimentelle metoder"));

        int expectedEctsSum = 40;
        assertEquals(expectedEctsSum, bachelorProgram.totalEcts());
    }
}