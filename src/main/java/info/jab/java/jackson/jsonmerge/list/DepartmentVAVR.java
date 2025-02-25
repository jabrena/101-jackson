package info.jab.java.jackson.jsonmerge.list;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import info.jab.java.jackson.jsonmerge.vavr.VavrJsonMergeListDeserializer;

import io.vavr.collection.List;

import java.util.Objects;

public class DepartmentVAVR {
    // Properties
    private String name;
    
    @JsonMerge
    @JsonDeserialize(using = EmployeeListDeserializer.class)
    private List<Employee> employees = List.empty();
    
    // Custom deserializer for employees list
    public static class EmployeeListDeserializer extends VavrJsonMergeListDeserializer<DepartmentVAVR, Employee> {
        public EmployeeListDeserializer() {
            super(DepartmentVAVR.class, Employee.class, DepartmentVAVR::getEmployees);
        }
    }
    
    // Constructors
    public DepartmentVAVR() {}
    
    public DepartmentVAVR(String name, List<Employee> employees) {
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
        employees = employees.append(employee);
    }

    // Equals and HashCode
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

    // toString
    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", employees=" + getEmployees() +
                                '}';
                    }
} 