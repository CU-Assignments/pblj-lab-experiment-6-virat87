import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    int age;
    double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void display() {
        System.out.println(name + " - Age: " + age + ", Salary: " + salary);
    }
}

public class EmployeeSortLambda {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 30, 50000));
        employees.add(new Employee("Bob", 25, 60000));
        employees.add(new Employee("Charlie", 35, 55000));
        employees.add(new Employee("Alex", 28, 45000));
        employees.add(new Employee("Alex", 32, 47000));
        employees.add(new Employee("Alex", 25, 46000));
        employees.add(new Employee("David", 29, 50000));
        employees.add(new Employee("Eve", 31, 50000));
        employees.add(new Employee("Frank", 27, 50000));

        List<Employee> sortedByName = employees.stream()
                .sorted(Comparator.comparing(e -> e.name))
                .collect(Collectors.toList());
        System.out.println("Sorted by Name:");
        sortedByName.forEach(Employee::display);
        List<Employee> sortedByAge = employees.stream()
                .sorted(Comparator.comparingInt(e -> e.age))
                .collect(Collectors.toList());
        System.out.println("\nSorted by Age:");
        sortedByAge.forEach(Employee::display);
        List<Employee> sortedBySalary = employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.salary, e1.salary))
                .collect(Collectors.toList());
        System.out.println("\nSorted by Salary (Descending):");
        sortedBySalary.forEach(Employee::display);
        List<Employee> sortedByNameThenAge = employees.stream()
                .sorted(Comparator.comparing((Employee e) -> e.name)
                        .thenComparingInt(e -> e.age))
                .collect(Collectors.toList());
        System.out.println("\nSorted by Name, then Age:");
        sortedByNameThenAge.forEach(Employee::display);
        List<Employee> sortedBySalaryThenName = employees.stream()
                .sorted(Comparator.comparingDouble((Employee e) -> e.salary)
                        .thenComparing(e -> e.name))
                .collect(Collectors.toList());
        System.out.println("\nSorted by Salary, then Name:");
        sortedBySalaryThenName.forEach(Employee::display);
    }
}

