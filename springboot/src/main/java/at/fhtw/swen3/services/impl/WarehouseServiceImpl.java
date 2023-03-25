package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseServiceImpl extends WarehouseService {

    @Autowired
    public WarehouseServiceImpl(WarehouseLogic warehouseLogic) {
        super(warehouseLogic);
    }

    public boolean importWarehouses(Warehouse warehouse) {
        return warehouseLogic.importWarehouses(warehouse);
    }
}
