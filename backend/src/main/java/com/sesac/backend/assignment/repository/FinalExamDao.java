package com.sesac.backend.assignment.repository;

import com.sesac.backend.assignment.domain.FinalExam;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author dongjin
 * 기말고사 Repository
 * FinalExam 테이블 연결
 */
@Repository
public interface FinalExamDao extends JpaRepository<FinalExam, UUID> {

}
