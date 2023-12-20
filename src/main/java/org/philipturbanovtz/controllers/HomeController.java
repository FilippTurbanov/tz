package org.philipturbanovtz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.philipturbanovtz.common.HttpHelper;
import org.philipturbanovtz.dtos.api.http.HttpResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test controller", description = "Test controller for health check")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    @Operation(summary = "Gets server time", tags = "/")
    @ApiResponses(value = {@ApiResponse(responseCode = "0", description = "OK")})
    HttpResponseDto home() {
        logger.info("Homepage requested!");
        long currentTime = System.currentTimeMillis();
        String response = "Application is running! Server time is: " + currentTime + " ms!";
        return HttpHelper.generateResponseOK(response);
    }
}
