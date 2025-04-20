package com.example.demo.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.StudentsDto;

public interface StudentsService {
	public StudentsDto addStudents(StudentsDto studentsDto, MultipartFile multipartFile) throws IOException;

}
