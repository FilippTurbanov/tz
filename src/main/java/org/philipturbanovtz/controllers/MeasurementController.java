package org.philipturbanovtz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import org.philipturbanovtz.common.HttpHelper;
import org.philipturbanovtz.database.entities.user.User;
import org.philipturbanovtz.dtos.api.http.HttpResponseDto;
import org.philipturbanovtz.dtos.api.measurement.rq.SaveMeasurementsRqDto;
import org.philipturbanovtz.dtos.api.measurement.rs.MeasurementsRsDto;
import org.philipturbanovtz.exceptions.measurement.InvalidMeasurementDataException;
import org.philipturbanovtz.exceptions.measurement.MeasurementNotFoundException;
import org.philipturbanovtz.exceptions.measurement.SaveMeasurementException;
import org.philipturbanovtz.modules.measurement.MeasurementsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/measurements")
@Tag(name = "Measurements", description = "Controller for managing measurements for users")
public class MeasurementController {
    private final MeasurementsService measurementsService;
    private static final Logger logger = LoggerFactory.getLogger(MeasurementController.class);

    public MeasurementController(MeasurementsService measurementsService) {
        this.measurementsService = measurementsService;
    }

    @GetMapping("/")
    @Operation(summary = "Gets latest measurements for current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "0", description = "OK", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MeasurementsRsDto.class))),
        @ApiResponse(responseCode = "1", description = "Error. See details in message"),
        @ApiResponse(responseCode = "103", description = "Current user hasn't saved measurements")
    })
    HttpResponseDto home() throws MeasurementNotFoundException {
        logger.info("Get latest measurements requested!");
        MeasurementsRsDto data = measurementsService.getLatestForUser(1);
        return HttpHelper.generateResponseOK(data);
    }

    @PostMapping("/")
    @Operation(summary = "Save measurements for current user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "0", description = "OK"),
        @ApiResponse(responseCode = "1", description = "Error. See details in message"),
        @ApiResponse(responseCode = "101", description = "Invalid incoming data"),
        @ApiResponse(responseCode = "102", description = "Error while saving measurements for user")
    })
    HttpResponseDto saveMeasurements(@Valid @RequestBody SaveMeasurementsRqDto data) throws SaveMeasurementException, InvalidMeasurementDataException {
        logger.info("Save measurements requested!");
        User user = new User("abc", "abc");
        user.setId(1L);
        measurementsService.saveForUser(user, data);
        return HttpHelper.generateResponseOK(null);
    }
}
