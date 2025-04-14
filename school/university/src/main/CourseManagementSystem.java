import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CourseManagementSystem extends JFrame {
    private final Database database;

    public CourseManagementSystem() {
        database = new Database(); // Initialize "database"
        setupUI();
    }

    private void setupUI() {
        setTitle("University Course Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu coursesMenu = new JMenu("Courses");
        JMenuItem addCourseItem = new JMenuItem("Add New Course");
        addCourseItem.addActionListener(this::addCourse);
        coursesMenu.add(addCourseItem);
        menuBar.add(coursesMenu);
        setJMenuBar(menuBar);

        // Main Panel
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Courses", createCoursesPanel());
        tabbedPane.addTab("Students", createStudentsPanel());
        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createCoursesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        DefaultListModel<Course> listModel = new DefaultListModel<>();
        JList<Course> courseList = new JList<>(listModel);
        database.getCourses().forEach(listModel::addElement);

        // Buttons
        JButton enrollButton = new JButton("Enroll Student");
        enrollButton.addActionListener(e -> showEnrollmentDialog());

        panel.add(new JScrollPane(courseList), BorderLayout.CENTER);
        panel.add(enrollButton, BorderLayout.SOUTH);
        return panel;
    }

    private void addCourse(ActionEvent e) {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField capacityField = new JTextField();

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Course ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Course Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Max Capacity:"));
        inputPanel.add(capacityField);

        int result = JOptionPane.showConfirmDialog(
            this, inputPanel, "Add Course", JOptionPane.OK_CANCEL_OPTION
        );

        if (result == JOptionPane.OK_OPTION) {
            // Create and save course (encapsulation)
            Course course = new Course(
                idField.getText(),
                nameField.getText(),
                Integer.parseInt(capacityField.getText())
            );
            database.addCourse(course);
        }
    }

    private void showEnrollmentDialog() {
        // Implementation for enrolling students
        // Uses JComboBox for selecting students/courses
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CourseManagementSystem().setVisible(true);
        });
    }
}