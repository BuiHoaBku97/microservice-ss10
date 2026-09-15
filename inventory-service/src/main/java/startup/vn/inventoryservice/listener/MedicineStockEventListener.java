package startup.vn.inventoryservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import startup.vn.inventoryservice.event.OrderEvent;
import startup.vn.inventoryservice.service.InventoryService;

@Component
public class MedicineStockEventListener {

    private final InventoryService inventoryService;

    public MedicineStockEventListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "${app.kafka.topics.medicine-stock-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderEventKafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent event) {
        inventoryService.decreaseStock(event);
    }
}
