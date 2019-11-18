package com.lyz.springboot.controller;

import com.lyz.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李勇志
 * @create 2019-11-16 10:38
 */
@RestController
public class MyBatis2Controller {
    @Autowired
    private StudentService studentService;

    @GetMapping("/boot/students")
    public Object students(){
        return studentService.getAllStudent();
    }
}
