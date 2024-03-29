package at.fhtw.swen3.persistence.repositories;

import at.fhtw.swen3.persistence.entities.TransferwarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferwarehouseRepository extends JpaRepository<TransferwarehouseEntity, Long> {
    TransferwarehouseEntity getTransferwarehouseEntityByCode(String code);
}