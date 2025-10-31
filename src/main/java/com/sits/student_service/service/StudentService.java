package com.sits.student_service.service;

import com.sits.student_service.dto.request.StudentRequestDTO;
import com.sits.student_service.dto.response.StudentResponseDTO;

import java.util.List;
import java.util.UUID;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(UUID id);

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO updateStudent(UUID id, StudentRequestDTO studentRequestDTO);

    void deleteStudent(UUID id);
}
