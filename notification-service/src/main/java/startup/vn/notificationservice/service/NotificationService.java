package startup.vn.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import startup.vn.notificationservice.event.OrderEvent;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void sendInvoiceNotification(OrderEvent event) {
        log.info("Hóa đơn cho đơn hàng [{}] đã được gửi tới khách hàng", event.orderId());
    }
}
