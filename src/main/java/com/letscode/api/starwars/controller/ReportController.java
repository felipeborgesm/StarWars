package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Losses;
import com.letscode.api.starwars.domains.enums.InventoryItems;
import com.letscode.api.starwars.services.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Reports API", description = "API responsible gor generating multiple reports")
@RequestMapping("/api/reports")
public class ReportController {

  private final ReportService service;

  @GetMapping("/traitors")
  @Operation(
      method = "GET",
      summary = "Percentage of Traitors",
      description = "Count the percentage of traitors among us",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, examples = {@ExampleObject(value = "10.0")}))
      }
  )
  public ResponseEntity<Float> traitorPercentageReport() {
    return ResponseEntity.ok(service.getTraitorPercentage());
  }

  @GetMapping("/rebels")
  @Operation(
      method = "GET",
      summary = "Percentage of Rebels",
      description = "Count the percentage of rebels among us",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, examples = {@ExampleObject(value = "10.0")}))
      }
  )
  public ResponseEntity<Float> rebelsPercentageReport() {
    return ResponseEntity.ok(service.getRebelsPercentage());
  }

  @GetMapping("/resource/{resourceName}")
  @Operation(
      method = "GET",
      summary = "Average of Resources",
      description = "Gives an average of each resource type",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE, examples = {@ExampleObject(value = "10.0")}))
      }
  )
  public ResponseEntity<Float> resourcePercentage(
      @Parameter(name = "resourceName", description = "The resource being checked",schema = @Schema(description = "resource type",type = "string", allowableValues = {"WEAPON", "AMMO", "WATER", "FOOD"}))
      @PathVariable("resourceName") InventoryItems resourceName
  ) {
    return ResponseEntity.ok(service.getAverageResourcePerRebel(resourceName));
  }

  @GetMapping("/losses")
  @Operation(
      method = "GET",
      summary = "Losses to Traitors",
      description = "Gives a total in points of our losses to traitor rebels",
      responses = {
          @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,schema = @Schema(implementation = Losses.class), examples = {@ExampleObject(value = "{\"foodLost\": 0,\"waterLost\": 0,\"ammoLost\": 0,\"weaponLost\": 0,\"totalLost\": 0}")}))
      }
  )
  public ResponseEntity<Losses> lossesReport() {
    return ResponseEntity.ok(service.getLossesToTraitors());
  }


}
