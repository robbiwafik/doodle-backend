package com.doodle.application.order;

import com.doodle.application.validation.annotations.ValidOrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPartialDTO {
    @NotNull(message = "Status field is required.")
    @NotBlank(message = "Status field can't be empty.")
    @ValidOrderStatus
    private String status;

    public void setStatus(String status) {
        this.status = status.toUpperCase();
    }
}
