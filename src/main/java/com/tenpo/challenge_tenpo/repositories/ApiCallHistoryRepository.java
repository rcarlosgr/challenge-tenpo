package com.tenpo.challenge_tenpo.repositories;

import com.tenpo.challenge_tenpo.entities.ApiCallHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiCallHistoryRepository extends JpaRepository<ApiCallHistory, Long> {
}
