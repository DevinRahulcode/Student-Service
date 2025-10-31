package com.sits.student_service.service.impl;

import com.sits.student_service.dto.request.StudentRequestDTO;
import com.sits.student_service.dto.response.StudentResponseDTO;
import com.sits.student_service.entity.Student;
import com.sits.student_service.mapper.StudentMapper;
import com.sits.student_service.repository.StudentRepository;
import com.sits.student_service.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        logger.info("Fetching all students from the database");
        List<StudentResponseDTO> students = studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
        logger.debug("Retrieved {} students", students.size());
        return students;
    }

    @Override
    public StudentResponseDTO getStudentById(UUID id) {
        logger.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new RuntimeException("Student not found with ID: " + id);
                });
        logger.debug("Student found: {}", student);
        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        logger.info("Creating new student with data: {}", studentRequestDTO);
        Student student = StudentMapper.toModel(studentRequestDTO);
        student = studentRepository.save(student);
        logger.debug("Student created with ID: {}", student.getId());
        return StudentMapper.toDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(UUID id, StudentRequestDTO studentRequestDTO) {
        logger.info("Updating student with ID: {}", id);
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Student not found with ID: {}", id);
                    return new RuntimeException("Student not found with ID: " + id);
                });

        existingStudent.setStudent_id(studentRequestDTO.getStudent_id());
        existingStudent.setName(studentRequestDTO.getName());
        existingStudent.setEmail(studentRequestDTO.getEmail());
        existingStudent.setFaculty(studentRequestDTO.getFaculty());
        existingStudent.setAddress(studentRequestDTO.getAddress());

        existingStudent = studentRepository.save(existingStudent);
        logger.debug("Student updated: {}", existingStudent);
        return StudentMapper.toDTO(existingStudent);
    }

    @Override
    public void deleteStudent(UUID id) {
        logger.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            logger.error("Student not found with ID: {}", id);
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        logger.debug("Student with ID {} deleted successfully", id);
    }
}
