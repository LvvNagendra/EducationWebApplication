package com.site.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.site.registration.FormModel;
@Repository
public interface FormRepository  extends JpaRepository<FormModel, Long> {
	FormModel getUserByfId(long fId);
	FormModel findByStudentEmail(String studentEmail);
	
	List<FormModel> findSingelUserByfId(long fId);

}
