package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.exception.BLException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService {

    protected final WarehouseLogic warehouseLogic;

    @Override
    public Warehouse exportWarehouses() throws BLException {
        return warehouseLogic.exportWarehouses();
    }

    @Override
    public Hop getHop(String code) throws BLException {
        return warehouseLogic.getHop(code);
    }

    @Override
    public void importWarehouses(Warehouse warehouse) {
        warehouseLogic.importWarehouses(warehouse);
    }
}
