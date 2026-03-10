package com.example.Passwordchecker.repository;

import com.example.Passwordchecker.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    /**
     * Returns all password history records ordered by most recent first.
     */
    List<PasswordHistory> findAllByOrderByCreatedAtDesc();
}
