package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface WarehouseService {
    public void importWarehouses(Warehouse warehouse) throws BLValidationException;
}
