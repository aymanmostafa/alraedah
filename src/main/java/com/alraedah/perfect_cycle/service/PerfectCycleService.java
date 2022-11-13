package com.alraedah.perfect_cycle.service;

import java.util.List;
import java.util.Map;

public interface PerfectCycleService {

    /**
     * track the occurrence of a perfect cycle in multiple lists.
     *
     * @param perfectCycleLists map of multiple perfect cycle lists
     * @return a map of status for each perfect cycle list
     */
    Map<String, Boolean> trackOccurrenceOfPerfectCycleLists(Map<String, List<Integer>> perfectCycleLists);
}