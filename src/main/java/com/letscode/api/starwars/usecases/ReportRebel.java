package com.letscode.api.starwars.usecases;

import com.letscode.api.starwars.domains.Rebel;
import com.letscode.api.starwars.gateways.persistence.RebelPersistenceGateWay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportRebel {

  private final RebelPersistenceGateWay rebelPersistenceGateWay;
  private final Integer MIN_REPORT_NUMBER = 3;

  public Rebel execute(Rebel rebel) {
    if (rebel.getReportCounter() == null) {
      rebel.setReportCounter(0);
    }
    rebel.setReportCounter(rebel.getReportCounter() + 1);

    if (rebel.getReportCounter() >= MIN_REPORT_NUMBER) {
      rebel.setTraitor(true);
    }

    return rebelPersistenceGateWay.save(rebel);
  }
}
