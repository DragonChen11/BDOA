package com.sky.kay.bdoa.model;

/**
 * Created by kay on 2016/8/1.
 */
public class Employee {
    public String name;
    public String cellphone;
    public String phone;
    public String job;
    public String userId;
    public String emailAddress ;
    public String department  ;
    public Employee(String name,String cellphone,String phone,String job,String userId,String emailAddress,String department){
        this.name=name;
        this.cellphone=cellphone;
        this.phone=phone;
        this.job=job;
        this.userId=userId;
        this.emailAddress=emailAddress;
        this.department=department;
    }
}
