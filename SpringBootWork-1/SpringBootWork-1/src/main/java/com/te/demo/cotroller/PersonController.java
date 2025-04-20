package com.te.demo.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.te.demo.entity.Person;
import com.te.demo.response.ResponseMessage;
import com.te.demo.service.PersonServiceImpl;

@RestController
public class PersonController {

	@Autowired
	private PersonServiceImpl personServiceImpl;

	@PostMapping("person")
	public void person(@RequestPart(required = false, name = "data") String data,
			@RequestPart(name = "photo", required = false) MultipartFile photo,
			@RequestPart(name = "doc", required = false) List<MultipartFile> doc) {
		personServiceImpl.person(data, photo, doc);
	}

	@GetMapping(path = "/person/{personId}")
	public ResponseEntity<ResponseMessage> getPerson(@PathVariable("personId") Integer personId) {
		Person savePerson = personServiceImpl.getPerson(personId);
		if (savePerson != null) {
			return ResponseEntity.ok(new ResponseMessage(false, "person fetched successfully", savePerson));
		}
		return null;
	}

	@DeleteMapping(path = "/person/{personId}")
	public ResponseEntity<ResponseMessage> deletePerson(@PathVariable("personId") int personId) {
		Person deletedPerson = personServiceImpl.deletePerson(personId);
		if (deletedPerson != null) {
			return ResponseEntity.ok(new ResponseMessage(false, "person deleted successfully", null));
		}
		return null;

	}

	@GetMapping(path = "/persons")
	public ResponseEntity<ResponseMessage> getAllPerson() {
		List<Person> persons = personServiceImpl.getAllPerson();
		if (!persons.isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(false, "All persons fetched  successfully", persons));

		}
		return null;

	}

	@PutMapping("update")
	public void updatePerson(@RequestPart(required = false, name = "data") String data,
			@RequestPart(name = "photo", required = false) MultipartFile photo,
			@RequestPart(name = "doc", required = false) List<MultipartFile> doc) {
		personServiceImpl.updatePerson(data, photo, doc);
	}

}
