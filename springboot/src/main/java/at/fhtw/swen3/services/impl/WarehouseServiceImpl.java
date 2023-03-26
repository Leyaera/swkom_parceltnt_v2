package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exception.BLValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {

    protected final WarehouseLogic warehouseLogic;

    @Override
    public void importWarehouses(Warehouse warehouse) throws BLValidationException {
        warehouseLogic.importWarehouses(warehouse);
    }
}
