import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum ActivityType {
    BASIC_COURSE, MODULE_COURSE, BASIC_PROJECT, MODULE_PROJECT, BACHELOR_PROJECT
}

abstract class StudyActivity {
    ActivityType type;
    String name;
    int ects;

    public StudyActivity(ActivityType type, int ects, String name){
        this.type = type;
        this.ects = ects;
        this.name = name;
    }
    public int getEcts() { return ects; }
    public ActivityType getType() { return type; }
    public String getName() { return name; }
}

class Course extends StudyActivity {
    public Course(ActivityType type, int ects, String name) {
        super(type, ects, name);
    }
}

class Project extends StudyActivity {
    public Project(ActivityType type, String name) {
        super(type, 15, name);
    }
}

class BachelorProgram {
    private List<StudyActivity> studyActivities;

    public BachelorProgram() {
        studyActivities = new ArrayList<>();
    }

    public void addActivity(StudyActivity activity) {
        studyActivities.add(activity);
    }

    public boolean isValid() {
        Map<String, Integer> activityCount = new HashMap<>();
        int basicCourseEcts = 0, moduleCourseEcts = 0, basicProjectCount = 0,
                moduleProjectCount = 0, bachelorProjectCount = 0;

        for (StudyActivity studyActivity : studyActivities) {
            // Check if activity is unique
            if (activityCount.containsKey(studyActivity.getName())) {
                System.out.println(studyActivity.getName() + " appears more than once.");
                return false;
            } else {
                activityCount.put(studyActivity.getName(), 1);
            }

            // Count ECTS and project occurrences according to types
            switch (studyActivity.getType()) {
                case BASIC_COURSE:
                    basicCourseEcts += studyActivity.getEcts();
                    break;
                case MODULE_COURSE:
                    moduleCourseEcts += studyActivity.getEcts();
                    break;
                case BASIC_PROJECT:
                    basicProjectCount++;
                    break;
                case MODULE_PROJECT:
                    moduleProjectCount++;
                    break;
                case BACHELOR_PROJECT:
                    bachelorProjectCount++;
                    break;
            }
        }

        System.out.println("moduleProjectCount: " + moduleProjectCount);
        System.out.println("basicProjectCount: " + basicProjectCount);
        System.out.println("bachelorProjectCount: " + bachelorProjectCount);
        System.out.println("basicCourseEcts: " + basicCourseEcts);
        System.out.println("moduleCourseEcts: " + moduleCourseEcts);

        boolean result = moduleProjectCount == 2 && basicProjectCount == 3 && bachelorProjectCount == 1 && basicCourseEcts >= 40 && moduleCourseEcts >= 50;
        System.out.println("is valid: " + result);

        return result;
    }

    public int totalEcts() {
        int totalEcts = 0;
        for (StudyActivity studyActivity : studyActivities) {
            totalEcts += studyActivity.getEcts();
        }
        return totalEcts;
    }
}

public class Main {
    public static void main(String[] args) {
        BachelorProgram bachelorProgram = new BachelorProgram();
        //My courses:
        bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basisprojekt 1"));
        bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basisprojekt 2"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Empiriske data"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Eksperimentelle metoder"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Calculus"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Logic and discrete mathematics"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Scientific computing"));
        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Unavngivet basiskursus"));
        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Unavngivet valgkursus"));
        //F2024:
        bachelorProgram.addActivity(new Project(ActivityType.MODULE_PROJECT, "Module B Computer Science Project"));
        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 10, "Module B Software development"));
        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module B Interactive digitale systemer"));
//        //Courses I need:
//        bachelorProgram.addActivity(new Project(ActivityType.BASIC_PROJECT, "Basisprojekt 3"));
//        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "Naturvidenskabsteori og metoder i naturvidenskab"));
//        bachelorProgram.addActivity(new Course(ActivityType.BASIC_COURSE, 5, "BK4-8"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module B course 3"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module A course 1"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module A course 2"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module A course 3"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Module A course 4"));
//        bachelorProgram.addActivity(new Project(ActivityType.MODULE_PROJECT, "Module A Project"));
//        bachelorProgram.addActivity(new Course(ActivityType.MODULE_COURSE, 5, "Valgkursus"));
//        bachelorProgram.addActivity(new Project(ActivityType.BACHELOR_PROJECT, "Bachelor Project"));
        System.out.println("Is valid: " + bachelorProgram.isValid());
        System.out.println("Total ECTS: " + bachelorProgram.totalEcts());
    }
}