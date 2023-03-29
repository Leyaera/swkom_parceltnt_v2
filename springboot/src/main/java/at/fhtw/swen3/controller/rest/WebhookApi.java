package at.fhtw.swen3.controller.rest;

import at.fhtw.swen3.persistence.entities.WebhookEntity;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.Webhook;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;


@Validated
@RestController
public interface WebhookApi {
    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /webhook/{trackingId} : Register new Webhook(s) to a parcel.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @return Successfully registered webhook. (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or Parcel does not exist with this tracking ID.  (status code 404)
     */
    @Operation(
            operationId = "registerWebhook",
            summary = "Register new Webhook(s) to a parcel. ",
            tags = { "webhook" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully registered webhook."),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID. ")
            }
    )
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/webhook/{trackingId}",
            produces = { "application/json" }
    )
    default ResponseEntity<Void> registerWebhook(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true, in = ParameterIn.PATH) @PathVariable("trackingId") String trackingId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * GET /webhook/{trackingId} : Get list of all subscribers by tracking id.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @return Successfully retrieved list of subscribers. (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or Parcel does not exist with this tracking ID.  (status code 404)
     */
    @Operation(
            operationId = "getWebhook",
            summary = "Get list of all subscribers by tracking id. ",
            tags = { "webhook" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of subscribers."),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID. ")
            }
    )
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/webhook/{trackingId}",
            produces = { "application/json" }
    )
    default ResponseEntity<List<Webhook>> getWebhooks(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true, in = ParameterIn.PATH) @PathVariable("trackingId") String trackingId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

    /**
     * DELETE /webhook/{trackingId} : Delete webhook according to tracking id.
     *
     * @param trackingId The tracking ID of the parcel. E.g. PYJRB4HZ6  (required)
     * @return Successfully deleted webhook. (status code 200)
     *         or The operation failed due to an error. (status code 400)
     *         or Parcel does not exist with this tracking ID.  (status code 404)
     */
    @Operation(
            operationId = "deleteWebhook",
            summary = "Delete webhook according to tracking id. ",
            tags = { "webhook" },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted webhook."),
                    @ApiResponse(responseCode = "400", description = "The operation failed due to an error.", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Parcel does not exist with this tracking ID. ")
            }
    )
    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/webhook/{trackingId}",
            produces = { "application/json" }
    )
    default ResponseEntity<Void> deleteWebhook(
            @Pattern(regexp = "^[A-Z0-9]{9}$") @Parameter(name = "trackingId", description = "The tracking ID of the parcel. E.g. PYJRB4HZ6 ", required = true, in = ParameterIn.PATH) @PathVariable("trackingId") String trackingId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }
}
