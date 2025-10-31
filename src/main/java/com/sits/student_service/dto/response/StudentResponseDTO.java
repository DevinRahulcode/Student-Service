package com.sits.student_service.dto.response;


import lombok.Data;

@Data
public class StudentResponseDTO {

    private String id;
    private String student_id;
    private String name;
    private String email;
    private String faculty;
    private String address;
}
