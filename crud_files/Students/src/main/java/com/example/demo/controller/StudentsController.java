package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.StudentsDto;
import com.example.demo.response.SuccessResponse;
import com.example.demo.service.StudentsService;

@RestController
public class StudentsController {

	@Autowired
	private StudentsService studentsService;

	@PostMapping("/addStudents")
	public ResponseEntity<SuccessResponse> addStudents(@RequestBody StudentsDto studentsDto,

			@RequestParam MultipartFile multipartFile) throws IOException {
		StudentsDto students = studentsService.addStudents(studentsDto, multipartFile);
		if (students != null) {
			return new ResponseEntity<>(new SuccessResponse(false, "Successfully", students), HttpStatus.OK);
		}
		return new ResponseEntity<>(new SuccessResponse(true, "Not Success", null), HttpStatus.BAD_REQUEST);

	}

}
