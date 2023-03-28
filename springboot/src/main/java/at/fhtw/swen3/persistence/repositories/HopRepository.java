package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HopRepository extends JpaRepository<HopEntity, Long> {
    @Transactional
    @Query(value ="SELECT hop_type FROM hop WHERE code = :code", nativeQuery = true)
    String findHopTypeByCode(@Param("code") String code);

    HopEntity findHopEntityByCode(String code);
}