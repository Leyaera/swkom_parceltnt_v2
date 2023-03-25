package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.dto.Warehouse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
public class WarehouseService {
    protected final WarehouseLogic warehouseLogic;
}
