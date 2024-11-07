package com.example.repository;

import com.example.model.CepLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CepLogRepository extends JpaRepository<CepLog, Long> {
}