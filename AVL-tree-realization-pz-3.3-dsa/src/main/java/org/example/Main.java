package org.example;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Employee root = new Employee("root",2000,IT.BACKEND_DEV);
        Organization organization = new Organization(root);
        IntStream.range(1,10)
                .forEach(value -> {
                    Employee newEmployee = new Employee("E"+value,
                            new Random().nextInt(200,10000),
                            IT.values()[new Random().nextInt(0,3)]);
                    organization.add(newEmployee);
                }
                );
        Employee newEmployee = new Employee("E6", 6006,IT.FRONTEND_DEV);
        organization.add(newEmployee);
        System.out.print("All tree: ");
        organization.print();
        System.out.print("All employees with salary over 5000: ");
        organization.findEmployeeWithOverSalary(5000).stream()
                .map(Employee::getSalary)
                .forEach(es-> System.out.print(es + " "));
        System.out.println("\nFind employee (E6,6006,FRONTEND_DEV): " +
                organization.findEmployee(new Employee("E6", 6006,IT.FRONTEND_DEV)));
        System.out.print("Delete employee (E6,6006,FRONTEND_DEV): ");
        organization.delete(newEmployee);
        organization.print();
        organization.getFirstThreeLevels().stream()
                .map(Employee::getSalary)
                .forEach(es-> System.out.println(es + " "));
    }
}