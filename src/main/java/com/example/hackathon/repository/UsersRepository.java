package com.example.hackathon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.hackathon.model.Users;
import java.util.List;


@Repository
public interface UsersRepository  extends MongoRepository<Users, String> {
	
	public List<Users> findAll();

}