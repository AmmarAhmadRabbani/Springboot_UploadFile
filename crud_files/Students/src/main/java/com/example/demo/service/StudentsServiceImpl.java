package com.example.demo.service;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.StudentsDto;
import com.example.demo.entity.Students;
import com.example.demo.entity.StudentsDoc;
import com.example.demo.repository.StudentsDocRepository;
import com.example.demo.repository.StudentsRepository;
import com.example.demo.utility.ImageUtils;

@Service
public class StudentsServiceImpl implements StudentsService {
	@Autowired
	private StudentsRepository studentsRepository;

	@Autowired
	private StudentsDocRepository studentsDocRepository;

	@Override
	public StudentsDto addStudents(StudentsDto studentsDto, MultipartFile multipartFile) throws IOException {

		if (studentsDto != null) {
			Students students = new Students();
			BeanUtils.copyProperties(studentsDto, students);
			studentsRepository.save(students);
			studentsDocRepository
					.save(StudentsDoc.builder().url(ImageUtils.compressImage(multipartFile.getBytes())).build());
			BeanUtils.copyProperties(students, studentsDto);
			return studentsDto;
		}

		return null;
	}

}
