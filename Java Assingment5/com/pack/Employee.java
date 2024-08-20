package com.pack;

public class Employee{
    private int empId;
    private String empName;
    private Address address;
    private String phoneNo;
    private String email;
    private String dept;


    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDept() {
        return dept;
    }

    public void setDept(int dept) {
        this.dept = String.valueOf(dept);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + empId;
        result = prime * result + ((empName == null)? 0 : empName.hashCode());
        result = prime * result + ((address == null)? 0 : address.hashCode());
        result = prime * result + ((phoneNo == null)? 0 : phoneNo.hashCode());
        result = prime * result + ((email == null)? 0 : email.hashCode());
        result = prime * result + ((dept == null)? 0 : dept.hashCode());
        return result;

    }
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (empId != other.empId)
            return false;
        return true;
    }


}
