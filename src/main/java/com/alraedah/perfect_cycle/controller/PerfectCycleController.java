package com.alraedah.perfect_cycle.controller;

import com.alraedah.perfect_cycle.service.PerfectCycleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/v1/perfect-cycle")
public class PerfectCycleController {

    private PerfectCycleService perfectCycleService;

    @Autowired
    public PerfectCycleController(PerfectCycleService perfectCycleService) {
        this.perfectCycleService = perfectCycleService;
    }

    /**
     * POST : track the occurrence of a perfect cycle in multiple lists.
     *
     * @param perfectCycleListBody the map of perfect cycle lists
     * @return the ResponseEntity with status 200 (OK) and the list of the tracking result for each provided list in
     * body
     */
    @ApiOperation(value = "track the occurrence of a perfect cycle in multiple lists")
    @PostMapping("track")
    public ResponseEntity<Map<String, Boolean>> trackPerfectCycle(@RequestBody Map<String, List<Integer>> perfectCycleListBody) {
        log.info("REST request to track the occurrence of a perfect cycle in multiple lists");

        Map<String, Boolean> result = perfectCycleService.trackOccurrenceOfPerfectCycleLists(perfectCycleListBody);
        return ResponseEntity.ok(result);
    }
}
