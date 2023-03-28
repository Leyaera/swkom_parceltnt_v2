package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface WarehouseService {

    Warehouse exportWarehouses() throws BLException;
    Hop getHop(String code) throws BLException;
    void importWarehouses(Warehouse warehouse);
}
