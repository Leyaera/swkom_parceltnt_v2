package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface WarehouseNextHopsRepository extends JpaRepository<WarehouseNextHopsEntity, Long> {
    @Transactional
    @Query(value = "SELECT * FROM warehouse_next_hops WHERE fk_from_hop = :hopId", nativeQuery = true)
    List<WarehouseNextHopsEntity> findWarehouseNextHopsEntitiesByFkFromHop(@Param("hopId") int hopId);
}