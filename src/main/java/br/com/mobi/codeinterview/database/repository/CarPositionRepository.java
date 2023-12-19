package br.com.mobi.codeinterview.database.repository;

import br.com.mobi.codeinterview.database.entity.CarPosition;
import br.com.mobi.codeinterview.database.entity.CarPositionKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CarPositionRepository extends JpaRepository<CarPosition, CarPositionKey> {

    List<CarPosition> findByPositionDateBetween(LocalDateTime begin, LocalDateTime end);

    List<CarPosition> findByPlateAndPositionDateBetween(String plate, LocalDateTime begin, LocalDateTime end);

    List<CarPosition> findByPlate(String plate);
}
