package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.persistence.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ParcelRepository extends JpaRepository<ParcelEntity, Long> {

    ParcelEntity findByTrackingId(String trackingId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE parcel  SET state = :state WHERE tracking_id = :trackingid",nativeQuery = true)
    void setStateToDifferentState(@Param("state") int state, @Param("trackingid") String trackingId);
}