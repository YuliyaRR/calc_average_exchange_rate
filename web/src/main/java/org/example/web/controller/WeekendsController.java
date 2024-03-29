package org.example.web.controller;

import org.example.service.core.dto.Weekend;
import org.example.service.service.api.IWeekendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/weekends")
@RestController
@RequiredArgsConstructor
public class WeekendsController {
private final IWeekendService weekendService;

    @GetMapping
    public ResponseEntity<List<Weekend>>findAll(){
        return new ResponseEntity<>(weekendService.findAll(), HttpStatus.OK);
    }
}
