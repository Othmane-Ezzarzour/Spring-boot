package com.brightcoding.springboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brightcoding.springboot.modeles.Users;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Integer>  {

	public Users findByEmail(String email);
	
	public Users findByUserId(String userId);
	
//	@Query(value = "select * from users", nativeQuery = true)
//	public Page<Users> findAllUsers(Pageable pageableRequest);
	
//	@Query(value = "select * from users u where (u.first_name = ?1 or u.last_name = ?1) and u.email_verification_status = ?2", nativeQuery = true)
//	public Page<Users> findAllUsersByCriteria(Pageable pageableRequest, String search, int status);

//	@Query(value = "select * from users u where (u.first_name = :search or u.last_name = :search) and u.email_verification_status = :status", nativeQuery = true)
//	public Page<Users> findAllUsersByCriteria(Pageable pageableRequest,@Param("search") String search,@Param("status") int status);
	
	@Query(value = "select * from users u where (u.first_name LIKE :search% or u.last_name LIKE :search%) and u.email_verification_status = :status", nativeQuery = true)
	public Page<Users> findAllUsersByCriteria(Pageable pageableRequest,@Param("search") String search,@Param("status") int status);

	public Page<Users> findAll(Pageable pageableRequest);
	
}
