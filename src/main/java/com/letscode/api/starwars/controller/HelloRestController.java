package com.letscode.api.starwars.controller;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.usecases.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.print.attribute.standard.Media;

@RestController
@RequiredArgsConstructor
public class HelloRestController {

  private final CreateRebel createRebel;
  private final UpdateRebelLocation updateRebelLocation;
  private final FindRebelById findRebelById;
  private final ListRebels listRebels;
  private final ReportRebel reportRebel;

}
