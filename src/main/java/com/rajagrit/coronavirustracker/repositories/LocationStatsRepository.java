package com.rajagrit.coronavirustracker.repositories;

import com.rajagrit.coronavirustracker.models.LocationStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationStatsRepository extends JpaRepository<LocationStats,Long> {
}
