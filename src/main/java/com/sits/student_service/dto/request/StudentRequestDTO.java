package com.sits.student_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class StudentRequestDTO {


    @NotNull
    @Size(max = 10, message = "The ID Number should include 10 Characters")
    private String student_id;

    @NotNull
    @Size(max = 50, message = "The name should include 150 Characters")
    private String name;

    @NotNull
    @Email(message = "Please Enter a valid Email")
    private String email;

    @NotNull
    private String faculty;

    @NotNull
    private String address;
}
