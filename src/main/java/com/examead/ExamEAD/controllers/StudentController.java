package com.examead.ExamEAD.controllers;

import com.examead.ExamEAD.dtos.ResponseOjt;
import com.examead.ExamEAD.dtos.StudentDTO;
import com.examead.ExamEAD.dtos.StudentIfm;
import com.examead.ExamEAD.dtos.StudentScoreDTO;
import com.examead.ExamEAD.models.CreateScoreStudent;
import com.examead.ExamEAD.models.CreateStudent;
import com.examead.ExamEAD.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping("")
    ResponseEntity<ResponseOjt> getAll() {
        try {
            List<StudentIfm> list = studentService.getInfomationStudents();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseOjt(true, "ok", list)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseOjt(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("")
    ResponseEntity<ResponseOjt> insertStudent(@RequestBody CreateStudent createStudent) {
        try {
            StudentDTO studentDTO = studentService.create(createStudent);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseOjt(true, "ok", studentDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseOjt(false, e.getMessage(), "")
            );
        }
    }

    @PostMapping("/score")
    ResponseEntity<ResponseOjt> insertScoreStudent(@RequestBody CreateScoreStudent createScoreStudent) {
        try {
            StudentScoreDTO studentScoreDTO = studentService.insertScore(createScoreStudent);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseOjt(true, "ok", studentScoreDTO)
            );
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseOjt(false, e.getMessage(), "")
            );
        }
    }
}
