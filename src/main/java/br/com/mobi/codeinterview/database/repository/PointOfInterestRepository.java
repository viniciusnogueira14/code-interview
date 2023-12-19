package br.com.mobi.codeinterview.database.repository;

import br.com.mobi.codeinterview.database.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {
}
