package startup.vn.pharmacyservice.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import startup.vn.pharmacyservice.dto.SellMedicineRequest;
import startup.vn.pharmacyservice.event.OrderEvent;

@Service
public class PharmacyOrderService {

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    private final String medicineStockEventsTopic;

    public PharmacyOrderService(
            KafkaTemplate<String, OrderEvent> kafkaTemplate,
            @Value("${app.kafka.topics.medicine-stock-events}") String medicineStockEventsTopic) {
        this.kafkaTemplate = kafkaTemplate;
        this.medicineStockEventsTopic = medicineStockEventsTopic;
    }

    public OrderEvent sellMedicine(SellMedicineRequest request) {
        OrderEvent event = new OrderEvent(
                UUID.randomUUID().toString(),
                request.medicineId(),
                request.quantity(),
                Instant.now());

        kafkaTemplate.send(medicineStockEventsTopic, event.medicineId(), event);
        return event;
    }
}
