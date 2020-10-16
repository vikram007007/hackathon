package com.example.hackathon.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Users {
  @Id
  public ObjectId _id;

  public String email;
  public String username;
  
  public String password;

public String get_id() {
	return _id.toHexString();
}

public void set_id(ObjectId _id) {
	this._id = _id;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

  

 
}
