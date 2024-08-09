package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

@AllArgsConstructor
@Getter
public class Organization {
    private Employee root;

    private Employee add(Employee parent, Employee newEmployee) {
        if (parent == null) {
            return newEmployee;
        } else if (parent.getSalary() > newEmployee.getSalary()) {
            parent.setLeft(add(parent.getLeft(), newEmployee));
            System.out.println("Inserted " + newEmployee.getSalary() +" to left  of " + parent.getSalary());
        } else if (parent.getSalary() < newEmployee.getSalary()) {
            parent.setRight(add(parent.getRight(), newEmployee));
            System.out.println("Inserted " + newEmployee.getSalary() +" to right  of " + parent.getSalary());
        }
        return rebalance(parent);
    }

    public void add(Employee newEmployee) {
        root = add(this.root, newEmployee);
    }

    private Employee rebalance(Employee employee) {
        updateHeight(employee);
        int balance = balanceFactor(employee);
        if (balance > 1) {
            if (height(employee.getRight().getRight()) > height(employee.getRight().getLeft())) {
                employee = rotateLeft(employee);
            } else {
                employee.setRight(rotateRight(employee.getRight()));
                employee = rotateLeft(employee);
            }
        } else if (balance < -1) {
            if (height(employee.getLeft().getLeft()) > height(employee.getLeft().getRight()))
                employee = rotateRight(employee);
            else {
                employee.setLeft(rotateLeft(employee.getLeft()));
                employee = rotateRight(employee);
            }
        }
        return employee;
    }

    private Employee rotateRight(Employee employee) {
        Employee leftChild = employee.getLeft();
        Employee leftRightGrandchild = leftChild.getRight();
        leftChild.setRight(employee);
        employee.setLeft(leftRightGrandchild);
        updateHeight(employee);
        updateHeight(leftChild);
        return leftChild;
    }

    private Employee rotateLeft(Employee employee) {
        Employee rightChild = employee.getRight();
        Employee rightLeftGrandchild = rightChild.getLeft();
        rightChild.setLeft(employee);
        employee.setRight(rightLeftGrandchild);
        updateHeight(employee);
        updateHeight(rightChild);
        return rightChild;
    }

    private int balanceFactor(Employee employee) {
        return (employee == null) ? 0 : height(employee.getRight()) - height(employee.getLeft());
    }

    private void updateHeight(Employee employee) {
        employee.setHeight(1 + Math.max(height(employee.getLeft()), height(employee.getRight())));
    }

    private int height(Employee employee) {
        return employee == null ? -1 : employee.getHeight();
    }


    private void findEmployeeWithOverSalary(List<Employee> result, Employee employee, int salary) {
        if (employee.getSalary() > salary) {
            result.add(employee);
            if (employee.getRight() != null) {
                findEmployeeWithOverSalary(result, employee.getRight(), salary);
            }
            if (employee.getLeft() != null) {
                findEmployeeWithOverSalary(result, employee.getLeft(), salary);
            }
        } else if (employee.getRight() != null) {
            findEmployeeWithOverSalary(result, employee.getRight(), salary);
        }
    }

    public List<Employee> findEmployeeWithOverSalary(int salary) {
        List<Employee> result = new LinkedList<>();
        findEmployeeWithOverSalary(result, root, salary);
        return result;
    }

    private void traverseInOrder(Employee employee, Consumer<Employee> action) {
        if (employee != null) {
            traverseInOrder(employee.getLeft(), action);
            action.accept(employee);
            traverseInOrder(employee.getRight(), action);
        }
    }

    public void print() {
        traverseInOrder(root, e -> System.out.print(e.getSalary() + " "));
        System.out.println();
    }

    public boolean findEmployee(Employee employee) {
        List<Employee> allEmployees = new LinkedList<>();
        traverseInOrder(root, allEmployees::add);
        return allEmployees.contains(employee);
    }

    private Employee delete(Employee parent, Employee deleteEmployee) {
        if (parent == null) return parent;
        if (deleteEmployee.getSalary() < parent.getSalary()) {
            parent.setLeft(delete(parent.getLeft(), deleteEmployee));
        } else if (deleteEmployee.getSalary() > parent.getSalary()) {
            parent.setRight(delete(parent.getRight(), deleteEmployee));
        } else {
            if ((parent.getLeft() == null) || (parent.getRight() == null)) {
                Employee temp = null;
                if (temp == parent.getLeft()) {
                    temp = parent.getRight();
                } else {
                    temp = parent.getLeft();
                }

                if (temp == null) {
                    temp = parent;
                    parent = null;
                } else {
                    parent = temp;
                }
            } else {
                Employee temp = minSalary(parent.getRight());
                parent.setSalary(temp.getSalary());
                parent.setRight(delete(parent.getRight(), temp));
            }
        }
        if (parent == null) return parent;
        parent = rebalance(parent);
        return parent;
    }

    private Employee minSalary(Employee employee) {
        Employee current = employee;
        while (current.getLeft() != null) current = current.getLeft();
        return current;
    }

    public Employee delete(Employee deleteEmployee) {
        return delete(root, deleteEmployee);
    }

    public List<Employee> getFirstThreeLevels() {
        Queue<Employee> queue = new LinkedList<>();
        LinkedList<Employee> result = new LinkedList<>();
        queue.add(root);
        for (int i = 0; i <= 7; i++) {
            Employee temp = queue.poll();
            result.add(temp);
            if (temp.getLeft() != null) {
                queue.add(temp.getLeft());
            }
            if (temp.getRight() != null) {
                queue.add(temp.getRight());
            }
        }
        result.addAll(queue);
        result.removeFirst();
        return result;
    }

}


