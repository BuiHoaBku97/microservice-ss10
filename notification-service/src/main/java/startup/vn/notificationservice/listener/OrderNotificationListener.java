package startup.vn.notificationservice.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import startup.vn.notificationservice.event.OrderEvent;
import startup.vn.notificationservice.service.NotificationService;

@Component
public class OrderNotificationListener {

    private final NotificationService notificationService;

    public OrderNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(
            topics = "${app.kafka.topics.medicine-stock-events}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "orderEventKafkaListenerContainerFactory")
    public void handleOrderEvent(OrderEvent event) {
        notificationService.sendInvoiceNotification(event);
    }
}
