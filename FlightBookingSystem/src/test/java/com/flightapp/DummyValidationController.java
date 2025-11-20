package com.flightapp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class DummyValidationController {

    static class DummyRequest {
        @NotBlank(message = "name must not be blank")
        public String name;
    }

    @PostMapping("/test/validate")
    public String validate(@Valid @RequestBody DummyRequest req) {
        return "OK";
    }
}
