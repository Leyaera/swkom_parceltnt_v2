package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.HopRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.BLValidator;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseLogic {
    private final WarehouseRepository warehouseRepository;
    private final HopRepository hopRepository;

    @Autowired
    public WarehouseLogic(WarehouseRepository warehouseRepository, HopRepository hopRepository) {
        this.warehouseRepository = warehouseRepository;
        this.hopRepository = hopRepository;
    }


    public Warehouse exportWarehouses() throws BLException {
        WarehouseEntity warehouseEntity = warehouseRepository.getWarehouseEntityByLevel(0);
        if(warehouseEntity == null) {
            log.error("No hierarchy loaded yet.");
            throw new BLDataNotFoundException(null, "No hierarchy loaded yet.");
        }

        try {
            BLValidator.INSTANCE.validate(warehouseEntity);
            log.info("WarehouseEntity is validated.");
        } catch (BLValidationException e) {
            log.error("BLValidation failed due to an error: {}", e.getMessage());
        }
        Warehouse warehouse = WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
        log.info("WarehouseEntity mapped to WarehouseDto.");
        return warehouse;
    }

    public Hop getHop(String code) throws BLException {
        HopEntity hopEntity = hopRepository.findHopEntityByCode(code);
        if(hopEntity == null) {
            log.error("No hop with the specified id could be found: " + code);
            throw new BLDataNotFoundException(null, "No hop with the specified id could be found: " + code);
        }

        try {
            BLValidator.INSTANCE.validate(hopEntity);
            log.info("HopEntity is validated.");
        } catch (BLValidationException e) {
            log.error("BLValidation failed due to an error: {}", e.getMessage());
        }

        Hop hop = HopMapper.INSTANCE.entityToDto(hopEntity);
        log.info("HopEntity mapped to HopDto.");
        return hop;
    }

    public void importWarehouses(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);
        try {
            BLValidator.INSTANCE.validate(warehouseEntity);
            log.info("WarehouseEntity is validated.");
        } catch (BLValidationException e) {
            log.error("BLValidation failed due to an error: {}", e.getMessage());
        }

        //clear the existing DB
        warehouseRepository.deleteAll();
        //save to the DB
        warehouseRepository.save(warehouseEntity);
    }
}
