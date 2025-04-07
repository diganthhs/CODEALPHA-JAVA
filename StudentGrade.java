import java.util.*;

class Student {
    String name;
    double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGrade {
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Display Grades");
            System.out.println("3. Calculate Statistics");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayGrades();
                    break;
                case 3:
                    calculateStatistics();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();
        students.add(new Student(name, grade));
        System.out.println("Student added successfully!");
    }

    private static void displayGrades() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("Student Grades:");
        for (Student student : students) {
            System.out.println(student.name + " - " + student.grade);
        }
    }

    private static void calculateStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students available to calculate statistics.");
            return;
        }

        double total = 0;
        double highest = Double.MIN_VALUE;
        double lowest = Double.MAX_VALUE;

        for (Student student : students) {
            total += student.grade;
            if (student.grade > highest) highest = student.grade;
            if (student.grade < lowest) lowest = student.grade;
        }

        double average = total / students.size();
        System.out.println("Average Grade: " + average);
        System.out.println("Highest Grade: " + highest);
        System.out.println("Lowest Grade: " + lowest);
    }
}
