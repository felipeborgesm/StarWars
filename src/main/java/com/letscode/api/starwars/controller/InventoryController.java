package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Trade;
import com.letscode.api.starwars.services.MarketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/market")
@Tag(name = "Inventory API", description = "A market API used for trading between rebels")
public class InventoryController {

  private final MarketService service;

  @PutMapping("/{id}")
  @Operation(
      method = "PUT",
      summary = "Trade",
      description = "Trade resources in a fair manner between rebels",
      responses = {
          @ApiResponse(responseCode = "202", description = "Success"),
          @ApiResponse(responseCode = "406", description = "Not Acceptable, You cannot trade more items than you have"),
          @ApiResponse(responseCode = "409", description = "Conflict, You cannot trade resources with self"),
          @ApiResponse(responseCode = "404", description = "Rebel not found"),
          @ApiResponse(responseCode = "412", description = "Cannot trade with traitor rebel")
      },
      requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
          description = "The trade object containing trade information",
          content = @Content(
              mediaType = MediaType.APPLICATION_JSON_VALUE,
              schema = @Schema(
                  implementation = Trade.class,
                  example = "{\"rebelId\":2,\"giving\":{\"water\":1,\"food\":0,\"ammo\":0,\"weapon\":0},\"receiving\":{\"water\":0,\"food\":0,\"ammo\":1,\"weapon\":0}}"
              )
          ),
          required = true
      )
  )
  public ResponseEntity<Void> tradeItem(
      @Parameter(name = "id", description = "Id of the rebel starting the trade")
      @PathVariable("id") Long id,

      @io.swagger.v3.oas.annotations.parameters.RequestBody
      @RequestBody Trade trade
  ) {
    service.trade(id, trade);
    return ResponseEntity.accepted().build();
  }
}
