package aditya.SpringSecurity.JWT_Security6.Controller;

import aditya.SpringSecurity.JWT_Security6.Model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    List<Student> studentList = new ArrayList<>(List.of(new Student(1, "Aditya",35), new Student(2,"Atmiya", 75)));
    @GetMapping("/getAllStudentsList")
    public List<Student> getAllStudentsList(){
        return studentList;
    }

    @GetMapping("/getCsrf")
    public CsrfToken csrfToken(HttpServletRequest httpServletRequest){
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }
    @PostMapping("/addStudents")
    public List<Student> getAllStudents(@RequestBody List<Student> student){
        studentList.addAll(student);
        return student;
    }


}
