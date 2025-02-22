package info.jab.java.jackson.jsonmerge.list;

import com.fasterxml.jackson.annotation.JsonMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {
    // Properties
    private String name;
    
    @JsonMerge
    private List<Employee> employees = new ArrayList<>();
    
    // Constructors
    public Department() {}
    
    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
                    
    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Equals and HashCode
                
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    // toString
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + getEmployees() +
                                '}';
                    }
} 