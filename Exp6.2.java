import java.util.*;
import java.util.stream.Collectors;

class Student {
    String name;
    double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public void display() {
        System.out.println(name + " - Marks: " + marks);
    }
}

public class StudentFilterSort {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Alice", 80),
            new Student("Bob", 72),
            new Student("Charlie", 90),
            new Student("David", 65),
            new Student("Eve", 85),
            new Student("Frank", 65)
        );

        List<Student> filteredSortedStudents = students.stream()
                .filter(s -> s.marks > 75)  
                .sorted(Comparator.comparingDouble((Student s) -> s.marks).reversed()
                        .thenComparing(s -> s.name))  
                .collect(Collectors.toList());

        System.out.println("Students who scored above 75% (Sorted by Marks):");
        if (filteredSortedStudents.isEmpty()) {
            System.out.println("No students scored above 75%");
        } else {
            filteredSortedStudents.forEach(Student::display);
        }

        runTestCases();
    }

    public static void runTestCases() {
        System.out.println("\n===== Running Test Cases =====");
        System.out.println("\nTest Case 1: Normal Case");
        testFilterSort(Arrays.asList(
                new Student("Alice", 80),
                new Student("Bob", 72),
                new Student("Charlie", 90),
                new Student("David", 65),
                new Student("Eve", 85)
        ));

        System.out.println("\nTest Case 2: All Below 75%");
        testFilterSort(Arrays.asList(
                new Student("Bob", 70),
                new Student("David", 60),
                new Student("Frank", 65)
        ));
        System.out.println("\nTest Case 3: Same Marks");
        testFilterSort(Arrays.asList(
                new Student("Alice", 80),
                new Student("Bob", 80),
                new Student("Charlie", 85)
        ));

        System.out.println("\nTest Case 4: Single Student Above 75%");
        testFilterSort(Arrays.asList(
                new Student("Alice", 60),
                new Student("Bob", 50),
                new Student("Charlie", 90)
        ));
    }

    public static void testFilterSort(List<Student> students) {
        List<Student> result = students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble((Student s) -> s.marks).reversed()
                        .thenComparing(s -> s.name))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No students scored above 75%");
        } else {
            result.forEach(Student::display);
        }
    }
}

