package com.te.demo.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.demo.entity.Person;
import com.te.demo.entity.PersonDoc;
import com.te.demo.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Environment environment;
	private Path dirLocation;
	private String dir;

	private Path getPath(String fileName) {
		String dir = environment.getProperty("file.upload.location");
		this.dir = dir + "\\" + fileName;
		this.dirLocation = Paths.get(this.dir).toAbsolutePath().normalize();
		return this.dirLocation;
	}

	public void person(String data, MultipartFile photo, List<MultipartFile> docs) {

		Person person = null;

		try {
			person = mapper.readValue(data, Person.class);
			// System.out.println(person);
			this.dirLocation = getPath(person.getName());
			// System.out.println("ammar rabbani"+this.dirLocation);
			Files.createDirectories(this.dirLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<PersonDoc> personDocs = new ArrayList<>();
		for (MultipartFile multipartFile : docs) {
			try {
				String fileName = multipartFile.getOriginalFilename();
				// System.out.println("hiiiiiiii"+fileName);
				Path dfile = this.dirLocation.resolve(fileName);
				System.out.println("superrrrrrrrr" + dfile);
				Files.copy(multipartFile.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
				personDocs.add(PersonDoc.builder().fileName(multipartFile.getOriginalFilename()).url(dfile.toString())
						.build());
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
		person.setPersonDoc(personDocs);

		personRepository.save(person);

	}

	@Override
	public Person getPerson(Integer personId) {
		Person findById = personRepository.findById(personId).orElseThrow(null);
		System.out.println("fetched personnnnns" + findById);
		return findById;
	}

	@Override
	public Person deletePerson(int personId) {
		Person person = personRepository.findById(personId).orElseThrow();
		if (person != null) {
//			
			personRepository.delete(person);
			return person;
		}

		return null;
	}

	@Override
	public List<Person> getAllPerson() {
		return personRepository.findAll();
	}

	@Override
	public Person updatePerson(String data, MultipartFile photo, List<MultipartFile> docs) {
		try {
			Person person = mapper.readValue(data, Person.class);
			Person fetchedPerson = personRepository.findById(person.getPersonId()).orElseThrow();
			if (fetchedPerson != null) {
				fetchedPerson.setName(person.getName());
				fetchedPerson.setGender(person.getGender());
				fetchedPerson.setPersonId(person.getPersonId());
				fetchedPerson.setPersonDoc(person.getPersonDoc());
				fetchedPerson.setPhoto(person.getPhoto());
				personRepository.save(fetchedPerson);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
