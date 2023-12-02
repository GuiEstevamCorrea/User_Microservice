package com.gc.microservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


public record UserRecordDTO(@NotBlank String name, @NotBlank @Email String email) {
}
