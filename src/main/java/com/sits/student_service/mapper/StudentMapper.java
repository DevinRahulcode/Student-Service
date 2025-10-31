package com.sits.student_service.mapper;

import com.sits.student_service.dto.request.StudentRequestDTO;
import com.sits.student_service.dto.response.StudentResponseDTO;
import com.sits.student_service.entity.Student;

public class StudentMapper {

    public static StudentResponseDTO toDTO(Student student){
        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setId(student.getId().toString());
        studentResponseDTO.setStudent_id(student.getStudent_id());
        studentResponseDTO.setName(student.getName());
        studentResponseDTO.setEmail(student.getEmail());
        studentResponseDTO.setFaculty(student.getFaculty());
        studentResponseDTO.setAddress(student.getAddress());

        return studentResponseDTO;
    }


    public static Student toModel(StudentRequestDTO studentRequestDTO){
        Student student = new Student();

        student.setStudent_id(studentRequestDTO.getStudent_id());
        student.setName(studentRequestDTO.getName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setFaculty(studentRequestDTO.getFaculty());
        student.setAddress(studentRequestDTO.getAddress());

        return student;
    }
}
