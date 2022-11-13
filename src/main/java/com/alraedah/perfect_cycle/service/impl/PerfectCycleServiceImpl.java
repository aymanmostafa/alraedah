package com.alraedah.perfect_cycle.service.impl;

import com.alraedah.perfect_cycle.service.PerfectCycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PerfectCycleServiceImpl implements PerfectCycleService {

    /**
     * track the occurrence of a perfect cycle in multiple lists.
     *
     * @param perfectCycleLists map of multiple perfect cycle lists
     * @return a map of status for each perfect cycle list
     */
    @Override
    public Map<String, Boolean> trackOccurrenceOfPerfectCycleLists(Map<String, List<Integer>> perfectCycleLists) {

        Map<String, Boolean> statusMapForPerfectCycleList = getStatusMapMap();

        if (perfectCycleLists != null) {
            for (Map.Entry<String, List<Integer>> perfectCycleListsEntry : perfectCycleLists.entrySet()) {

                String perfectCycleListName = perfectCycleListsEntry.getKey();

                List<Integer> perfectCycleList = perfectCycleListsEntry.getValue();

                addPerfectCycleListStatus(statusMapForPerfectCycleList, perfectCycleListName, perfectCycleList);
            }
        }

        return statusMapForPerfectCycleList;
    }

    protected Map<String, Boolean> getStatusMapMap() {
        return new HashMap<>();
    }

    protected void addPerfectCycleListStatus(Map<String, Boolean> statusMapForPerfectCycleList,
                                             String perfectCycleListName, List<Integer> perfectCycleList) {

        Boolean perfectCycleListStatus = trackOccurrenceOfPerfectCycleList(perfectCycleList);

        statusMapForPerfectCycleList.put(perfectCycleListName, perfectCycleListStatus);
    }

    protected Boolean trackOccurrenceOfPerfectCycleList(List<Integer> perfectCycleList) {

        Map<Integer, Integer> perfectCycleListElementVisitedMap = initializePerfectCycleListElementVisitedMap(perfectCycleList);

        int lastVisitedIndex = populatePerfectCycleListElementVisitedMap(perfectCycleList,
                perfectCycleListElementVisitedMap);

        return isListPerfectCycle(perfectCycleListElementVisitedMap, lastVisitedIndex);
    }

    protected int populatePerfectCycleListElementVisitedMap(List<Integer> perfectCycleList,
                                                            Map<Integer, Integer> perfectCycleListElementVisitedMap) {

        int elementsCounter = 0;
        int lastVisitedIndex = 0;

        if (perfectCycleList != null) {
            while (areIndexesWithinListRange(perfectCycleList.size(), elementsCounter, lastVisitedIndex)) {

                incrementPerfectCycleListVisitedMapElements(perfectCycleListElementVisitedMap, lastVisitedIndex);

                lastVisitedIndex = getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);

                elementsCounter++;
            }
        }

        return lastVisitedIndex;
    }

    protected boolean isListPerfectCycle(Map<Integer, Integer> perfectCycleListElementVisitedMap, int lastVisitedIndex) {

        return lastVisitedIndex == 0 && isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);
    }

    protected boolean areIndexesWithinListRange(int perfectCycleListSize, int elementsCounter, int lastVisitedIndex) {

        return lastVisitedIndex < perfectCycleListSize && lastVisitedIndex >= 0 && elementsCounter < perfectCycleListSize;
    }

    protected int getTheNextElementToVisit(List<Integer> perfectCycleList, int lastVisitedIndex) {

        if (perfectCycleList != null && lastVisitedIndex < perfectCycleList.size()) {
            return perfectCycleList.get(lastVisitedIndex);
        }

        return -1;
    }

    protected void incrementPerfectCycleListVisitedMapElements(Map<Integer, Integer> perfectCycleListElementVisitedMap,
                                                               int lastVisitedIndex) {

        if (perfectCycleListElementVisitedMap != null && perfectCycleListElementVisitedMap.containsKey(lastVisitedIndex)) {

            int currentCounter = perfectCycleListElementVisitedMap.get(lastVisitedIndex);
            perfectCycleListElementVisitedMap.put(lastVisitedIndex, currentCounter + 1);
        }
    }

    protected Map<Integer, Integer> initializePerfectCycleListElementVisitedMap(List<Integer> perfectCycleList) {

        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();

        if (perfectCycleList != null) {
            for (int index = 0; index < perfectCycleList.size(); index++) {

                perfectCycleListElementVisitedMap.put(index, 0);
            }
        }

        return perfectCycleListElementVisitedMap;
    }

    protected boolean isAllPerfectCycleElementVisited(Map<Integer, Integer> perfectCycleListElementVisitedMap) {

        if (perfectCycleListElementVisitedMap != null) {
            for (Map.Entry<Integer, Integer> perfectCycleListsEntry : perfectCycleListElementVisitedMap.entrySet()) {
                if (perfectCycleListsEntry.getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}