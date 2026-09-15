package startup.vn.pharmacyservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SellMedicineRequest(
        @NotBlank String medicineId,
        @Min(1) int quantity) {
}
