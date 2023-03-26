package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.BLValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class WarehouseLogic {
    private final WarehouseRepository warehouseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public WarehouseLogic(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public boolean importWarehouses(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        if (BLValidator.INSTANCE.validate(warehouseEntity)) {
            //clear the existing DB
            warehouseRepository.deleteAll();

            //save to the DB
            warehouseRepository.save(warehouseEntity);
            return true;
        }
        return false;
    }
}
