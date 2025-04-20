package com.te.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.te.demo.entity.Person;

public interface PersonService {

	public void person(String data, MultipartFile photo, List<MultipartFile> docs);

	public Person getPerson(Integer personId);

	Person deletePerson(int personId);

	List<Person> getAllPerson();

	public Person updatePerson(String data, MultipartFile photo, List<MultipartFile> docs);

}
