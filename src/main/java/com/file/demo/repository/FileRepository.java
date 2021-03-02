package com.file.demo.repository;

import com.file.demo.domain.FileDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileDomain, Integer> {
}
