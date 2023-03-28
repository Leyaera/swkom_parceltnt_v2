package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.controller.rest.WarehouseApi;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Warehouse;


import at.fhtw.swen3.services.exception.BLDataNotFoundException;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Slf4j
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-02-18T16:02:14.309709Z[Etc/UTC]")
@Controller
@RequestMapping("${openapi.parcelLogisticsService.base-path:}")
public class WarehouseApiController implements WarehouseApi {

    private final NativeWebRequest request;
    private final WarehouseServiceImpl warehouseService;

    @Autowired
    public WarehouseApiController(NativeWebRequest request, WarehouseServiceImpl warehouseService) {
        this.request = request;
        this.warehouseService = warehouseService;
    }


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Warehouse> exportWarehouses() {
        try {
            try {
                Warehouse warehouse = warehouseService.exportWarehouses();
                log.info("Successfully exported warehouses");
                return new ResponseEntity<Warehouse>(warehouse, HttpStatus.OK);
            } catch (BLDataNotFoundException e) {
                log.error(e.getMessage());
                return new ResponseEntity<Warehouse>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error.: {}", e.getMessage());
            return new ResponseEntity<Warehouse>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Hop> getWarehouse(String code) {
        try {
            try {
                Hop hop = warehouseService.getHop(code);
                log.info("Successfully returned hop with code: " + code);
                return new ResponseEntity<Hop>(hop, HttpStatus.OK);
            } catch (BLDataNotFoundException e) {
                log.error(e.getMessage());
                return new ResponseEntity<Hop>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("The operation failed due to an error.: {}", e.getMessage());
            return new ResponseEntity<Hop>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Void> importWarehouses(Warehouse warehouse) {
        try {
            warehouseService.importWarehouses(warehouse);
            log.info("Successfully loaded.");
            return new ResponseEntity<Void>( HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error during importing warehouses: {}", e.getMessage());
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
