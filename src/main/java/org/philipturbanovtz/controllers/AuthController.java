package org.philipturbanovtz.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.philipturbanovtz.common.HttpHelper;
import org.philipturbanovtz.dtos.api.auth.rq.LoginRqDto;
import org.philipturbanovtz.dtos.api.http.HttpResponseDto;
import org.philipturbanovtz.exceptions.auth.InvalidCredentialsException;
import org.philipturbanovtz.modules.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/auth")
@Tag(name = "Auth controller", description = "Methods for authentication")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("/login")
    @Operation(summary = "Login for users by credentials (email/password). Generates and returns access token")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "OK",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials"
        )
    })
    HttpResponseDto userLogin(@RequestBody LoginRqDto credentials) throws InvalidCredentialsException {
        logger.info("User login requested!");
        return HttpHelper.generateResponseOK(authService.login(credentials));
    }
}
