package edu.ucsb.cs156.spring.backenddemo.controllers;

import edu.ucsb.cs156.spring.backenddemo.services.PublicHolidayQueryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Public Holiday info from https://date.nager.at/")
@Slf4j
@RestController
@RequestMapping("/api/publicholidays")
public class PublicHolidayController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    PublicHolidayQueryService publicHolidayQueryService;

    @Operation(summary="Get a list of public holidays by give year and countryCode", description ="Public Holidays information from https://date.nager.at/")
    @GetMapping("/get")
    public ResponseEntity<String> getPublicHolidays(
        @Parameter(name="year", example="2000") @RequestParam String year,
        @Parameter(name="countryCode", example="US") @RequestParam String countryCode
    ) throws JsonProcessingException {
        log.info("getPublicHolidays: year={} countryCode={}", year, countryCode);
        String result = publicHolidayQueryService.getJSON(year, countryCode);
        return ResponseEntity.ok().body(result);
    }

}
