package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.StudentsDoc;

@Repository
public interface StudentsDocRepository extends JpaRepository<StudentsDoc, Long> {

}
