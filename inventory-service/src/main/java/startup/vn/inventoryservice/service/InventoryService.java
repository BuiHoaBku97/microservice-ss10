package startup.vn.inventoryservice.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import startup.vn.inventoryservice.event.OrderEvent;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    private final Map<String, Integer> medicineStocks = new ConcurrentHashMap<>(Map.of(
            "MED-001", 100,
            "MED-002", 50,
            "MED-003", 75));

    public void decreaseStock(OrderEvent event) {
        int remainingStock = medicineStocks.compute(
                event.medicineId(),
                (medicineId, currentStock) -> Math.max((currentStock == null ? 0 : currentStock) - event.quantity(), 0));

        log.info(
                "Processed orderId={}, medicineId={}, quantity={}, remainingStock={}",
                event.orderId(),
                event.medicineId(),
                event.quantity(),
                remainingStock);
    }
}
