package com.searchJob.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.searchJob.entity.PostRecruiment;

@Repository
public interface PostRecruimentRepository extends JpaRepository<PostRecruiment, String>{
	
	@Query(value = "SELECT * FROM post_recruiment where id_deleted = 0 order by create_date desc", nativeQuery = true)
	List<PostRecruiment> getListAllPostRecruiments();
	
	@Query(value = "select p.id as id," + 
			" p.address as address," + 
			" p.create_date as createDate," + 
			" p.date_word as dateWord, " + 
			" p.deadline as deadline," + 
			" p.degree as degree," + 
			" p.description as description," + 
			" p.id_type_job as idTypeJob," + 
			" p.name_type_job as nameTypeJob," + 
			" p.salary as salary," + 
			" p.specialize as specialize," + 
			" p.status as status," + 
			" p.title as title" + 
			" from post_recruiment p " + 
			" inner join type_job t on t.id = p.id_type_job " + 
			" where t.id in(1,2,3,4,5) and p.id_deleted = 0  group by p.id_type_job ORDER BY p.create_date ASC", nativeQuery = true)
	List<Map<String, Object>> getListSlidePostRecruiment();
	
	@Query(value = "SELECT * FROM post_recruiment where id_deleted = 0 order by create_date desc limit :pageCurrent", nativeQuery = true)
	List<PostRecruiment> getListPostRecruimentLimit(@Param("pageCurrent") int pageCurrent);
	
	@Query(value = "SELECT * FROM searchjob.post_recruiment where " + 
			"					 case when(:nameTypeJob is not null ) then (name_type_job like :nameTypeJob)" + 
			"					 else (name_type_job like '%' or name_type_job is null) " + 
			"					 end  " + 
			"					 and case when(:address is not null) then (address like :address)  " + 
			"					 else (address like '%' or address is null)   " + 
			"					 end  " + 
			"					 and case when(:startSalary is not null or :endSalary is not null) then (salary >= :startSalary and salary <= :endSalary)  " + 
			"					 else (salary like '%' or salary is null)  " + 
			"					 end and id_deleted = 0 order by create_date desc ", nativeQuery = true)
	List<PostRecruiment> getListSeachPostRecruiment(@Param("nameTypeJob") String nameTypeJob,
													@Param("address") String address,
													@Param("startSalary") float startSalary,
													@Param("endSalary") float endSalary);
	PostRecruiment findByIdContaining(String id);
	
	@Query(value = "SELECT * FROM post_recruiment where id_deleted = 1 order by create_date desc", nativeQuery = true)
	List<PostRecruiment> getListHistoryDeletePostRecruiment();
	
	
}
