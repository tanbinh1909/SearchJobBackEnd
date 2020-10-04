package com.searchJob.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchJob.entity.Recruiment;

@Repository
public interface RecruimentRepository extends JpaRepository<Recruiment, String> {
	
	@Query(value  = "select * from recruiment where id_post_recruiment = :idPostRecruiment and id_customer = :idCustomer", nativeQuery = true)
	List<Recruiment> getListRecruitmentByIddPostRecruitmentAndIdCustomer(@Param("idPostRecruiment")String idPostRecruiment,@Param("idCustomer") String idCustomer);
	
	@Query(value  = "select * from recruiment where id_customer = :idCustomer", nativeQuery = true)
	List<Recruiment> getListRecruitmentByIdCustomer(@Param("idCustomer") String idCustomer);
	
	List<Recruiment> findByIdPostRecruiment(String idPostRecruiment);

}
