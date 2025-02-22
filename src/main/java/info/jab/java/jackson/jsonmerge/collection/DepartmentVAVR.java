package info.jab.java.jackson.jsonmerge.collection;

import com.fasterxml.jackson.annotation.JsonMerge;

import io.vavr.collection.List;

import java.util.Objects;

public class DepartmentVAVR {
    private String name;
    
    @JsonMerge
    private List<Employee> employees = List.empty();
    
    // Constructors
    public DepartmentVAVR() {}
    
    public DepartmentVAVR(String name) {
        this.name = name;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + getEmployees() +
                                '}';
                    }
                
    public List<Employee> getEmployees() {
        return employees;
    }
                
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentVAVR that = (DepartmentVAVR) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, employees);
    }

    public void addEmployee(Employee employee) {
        employees = employees.append(employee);
    }
} 