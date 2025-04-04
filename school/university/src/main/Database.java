import java.util.ArrayList;
import java.util.List;

public class Database {
    private final List<Course> courses = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();
    private final List<Enrollment> enrollments = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return new ArrayList<>(courses); // Return copy for encapsulation
    }

    // Similar methods for students and enrollments
}