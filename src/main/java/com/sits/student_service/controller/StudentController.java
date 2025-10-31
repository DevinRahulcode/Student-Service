package com.sits.student_service.controller;


import com.sits.student_service.dto.request.StudentRequestDTO;
import com.sits.student_service.dto.response.StudentResponseDTO;
import com.sits.student_service.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/vi/student")
@Tag(name = "Student", description = "Endpoints for the do the crud operations")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all Students", description = "All the Registered Students will be shown here")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents(){
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{id}/student")
    @Operation(summary = "Get Student by Id", description = "Will Fetch student by id")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable UUID id){
        StudentResponseDTO student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/create-student")
    @Operation(summary = "Create new student", description = "Will create new Students")
    public ResponseEntity<StudentResponseDTO> createStudent(@Validated({Default.class}) @RequestBody StudentRequestDTO studentRequestDTO){
        StudentResponseDTO newStudent = studentService.createStudent(studentRequestDTO);
        return ResponseEntity.ok().body(newStudent);
    }

    @PutMapping("/{id}/update-student")
    @Operation(summary = "update students", description = "Students can be updated by the Id")
    public ResponseEntity<StudentResponseDTO> updateStudent(@Validated({Default.class}) @PathVariable UUID id, @RequestBody StudentRequestDTO studentRequestDTO){
        StudentResponseDTO updateStudent = studentService.updateStudent(id,studentRequestDTO);
        return ResponseEntity.ok().body(updateStudent);
    }

    @DeleteMapping("/{id}/delete-student")
    @Operation(summary = "Delete Student", description = "Students can be deleted by the id")
    public ResponseEntity<Void> deleteStudent(@PathVariable UUID id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
