public class Course {
    private String courseId;
    private String courseName;
    private int maxCapacity;

    public Course(String courseId, String courseName, int maxCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
    }

    // Getters and setters (encapsulation)
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() { return maxCapacity; }
}