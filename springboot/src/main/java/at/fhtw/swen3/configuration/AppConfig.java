package at.fhtw.swen3.configuration;

import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ParcelService parcelService(ParcelLogic parcelLogic) {
        return new ParcelServiceImpl(parcelLogic);
    }

    @Bean
    public WarehouseService warehouseService(WarehouseLogic warehouseLogic) { return new WarehouseServiceImpl(warehouseLogic); }

    @Bean
    public WebhookManager webhookManager(WebhookManagerLogic webhookManagerLogic) { return new WebhookManagerImpl(webhookManagerLogic); }
}
