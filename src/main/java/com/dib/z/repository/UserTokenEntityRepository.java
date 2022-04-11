package com.dib.z.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dib.z.model.UserTokenEntity;

@Repository("userTokenEntityRepository")
public interface UserTokenEntityRepository extends JpaRepository<UserTokenEntity, Integer> {
	
	UserTokenEntity findByUserId(int userId);
	
}
