package com.doodle.application.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidInputFieldsResponse {
    private Map<String, String> errors;
}
