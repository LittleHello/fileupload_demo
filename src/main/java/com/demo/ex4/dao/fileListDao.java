package com.demo.ex4.dao;

import com.demo.ex4.entity.FilelistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface fileListDao extends JpaRepository<FilelistEntity,String> {

}
