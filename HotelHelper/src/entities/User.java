package entities;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String aadharNo;
    private String name;
    private String age;
    private String location;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringBuffer("\n\nCustomer name: ")
                .append(this.getName()).append("\nAadhar Number: ")
                .append(this.getAadharNo()).append("\nAge :")
                .append(this.getAge()).append("\nLocation")
                .append(this.getLocation()).append("\nCustomer id :")
                .append(this.getId()).toString();
    }
}
