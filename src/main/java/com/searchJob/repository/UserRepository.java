package com.searchJob.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchJob.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
	Optional<User> findByUsername(String username);
	
	@Query(value = "select * from user where username = :username", nativeQuery = true)
	User findByUsernames(@Param("username") String username);
	
	@Query(value = "select * from user where id = :id", nativeQuery = true)
	User findByIds(@Param("id") String id);

	User findByCode(String code);

	User findByPassword(String password);
	
	User findByUsernameAndStatus(String username, String status);
}
