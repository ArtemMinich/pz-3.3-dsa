package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Employee {

    private String name;

    private int height;

    private int salary;

    private IT position;

    private Employee right;

    private Employee left;

    public Employee(String name, int salary, IT position) {
        this.name = name;
        this.salary = salary;
        this.position = position;
    }


}
