package com.dib.z.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name="usertokenentity")
public class UserTokenEntity {

	@Id
	int userId;
	
	String token;
	
}
