package info.jab.java.jackson.jsonmerge.pojo;

import com.fasterxml.jackson.annotation.JsonMerge;
import java.util.Objects;

public class Employee {
    // Properties
    private String name;
    private String dept;
    private int salary;
    private String phone;
    @JsonMerge
    private Address address;

    // Constructors
    public Employee() {
    }

    public Employee(String name, String dept, int salary, String phone, Address address) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return salary == employee.salary &&
                Objects.equals(name, employee.name) &&
                Objects.equals(dept, employee.dept) &&
                Objects.equals(phone, employee.phone) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dept, salary, phone, address);
    }

    // toString
    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", dept='" + dept + '\'' +
                ", salary=" + salary +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
