package startup.vn.pharmacyservice.event;

import java.time.Instant;

public record OrderEvent(
        String orderId,
        String medicineId,
        int quantity,
        Instant timestamp) {
}
