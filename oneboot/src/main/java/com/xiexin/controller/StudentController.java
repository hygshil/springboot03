package com.xiexin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiexin.bean.Student;
import com.xiexin.bean.StudentExample;
import com.xiexin.respcode.Result;
import com.xiexin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    //全查
    // 注意不用map，公司都用一个类，好几个类组成的类，叫做统一响应类，每个公司不一样
    @RequestMapping("/selectAll")
    public Result selectAll(){
        List<Student> students = studentService.selectByExample(null);
        Result result = new Result(students);
        return result;
    }
    //带参数的分页查询
    @RequestMapping("/selectPageAll")
    public Result selectPageAll(Student student,@RequestParam(value = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(value = "limit",defaultValue = "10",required = true) Integer pageSize){
        System.out.println("student = " + student);
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        //使用pagehelper分页
        PageHelper.startPage(page,pageSize);
        if(null!=student.getStudentName()&&!"".equals(student.getStudentName())&&null!=student.getStudentSex()&&!"".equals(student.getStudentSex())){
            criteria.andStudentNameEqualTo(student.getStudentName());
            criteria.andStudentSexEqualTo(student.getStudentSex());
        }
        List<Student> students = studentService.selectByExample(example);
        PageInfo pageInfo = new PageInfo(students);
        Result result = new Result(pageInfo);
        return result;
    }
}
