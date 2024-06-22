package com.mirkotik.livedatacapture.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mirkotik.livedatacapture.dto.ExcelDto;

@org.springframework.stereotype.Repository
public interface  RepositoryEntity extends JpaRepository<ExcelDto, String>{
}
