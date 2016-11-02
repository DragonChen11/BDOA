package com.sky.kay.bdoa.model;

import java.util.ArrayList;

/**
 * Created by kay on 2016/8/1.
 */
public class Department {
    String departmentId;
    String department;
    ArrayList<Employee> members=new ArrayList<>();
    public Department(String departmentId,String department){
        this.departmentId=departmentId;
        this.department=department;
    }

}
