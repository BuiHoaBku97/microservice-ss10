package startup.vn.pharmacyservice.controller;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import startup.vn.pharmacyservice.dto.SellMedicineRequest;
import startup.vn.pharmacyservice.event.OrderEvent;
import startup.vn.pharmacyservice.service.PharmacyOrderService;

@RestController
@RequestMapping("/api/orders")
public class PharmacyOrderController {

    private final PharmacyOrderService pharmacyOrderService;

    public PharmacyOrderController(PharmacyOrderService pharmacyOrderService) {
        this.pharmacyOrderService = pharmacyOrderService;
    }

    @PostMapping("/sell")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public OrderEvent sellMedicine(@Valid @RequestBody SellMedicineRequest request) {
        return pharmacyOrderService.sellMedicine(request);
    }
}
