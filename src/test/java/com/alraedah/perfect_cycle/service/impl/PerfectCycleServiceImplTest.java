package com.alraedah.perfect_cycle.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

class PerfectCycleServiceImplTest {

    private PerfectCycleServiceImpl perfectCycleService;
    private PerfectCycleServiceImpl perfectCycleServiceSpy;

    @BeforeEach
    void init() {
        perfectCycleService = new PerfectCycleServiceImpl();
        perfectCycleServiceSpy = Mockito.spy(perfectCycleService);
    }

    @Test
    void isAllPerfectCycleElementVisited_assertAllElementAreVisited_returnTrue() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        perfectCycleListElementVisitedMap.put(1, 1);
        perfectCycleListElementVisitedMap.put(2, 2);

        boolean result = perfectCycleServiceSpy.isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);

        assertTrue(result);
    }

    @Test
    void isAllPerfectCycleElementVisited_assertAllElementAreNotVisited_returnFalse() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        perfectCycleListElementVisitedMap.put(1, 0);
        perfectCycleListElementVisitedMap.put(2, 2);

        boolean result = perfectCycleServiceSpy.isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);

        assertFalse(result);
    }

    @Test
    void isAllPerfectCycleElementVisited_assertAllElementMapAreEmpty_returnTrue() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();

        boolean result = perfectCycleServiceSpy.isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);

        assertTrue(result);
    }

    @Test
    void isAllPerfectCycleElementVisited_assertAllElementMapIsNull_returnTrue() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = null;

        boolean result = perfectCycleServiceSpy.isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);

        assertTrue(result);
    }

    @Test
    void initializePerfectCycleListElementVisitedMap_assertPerfectListHasElements_returnVisitedMapWithZerosValues() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{1, 2, 3});

        Map<Integer, Integer> result =
                perfectCycleServiceSpy.initializePerfectCycleListElementVisitedMap(perfectCycleList);

        assertEquals(3, result.size());
        for (int index = 0; index < perfectCycleList.size(); index++) {
            assertEquals(0, result.get(index));
        }
    }

    @Test
    void initializePerfectCycleListElementVisitedMap_assertPerfectListIsEmpty_returnEmptyVisitedMap() {
        List<Integer> perfectCycleList = new ArrayList<>();

        Map<Integer, Integer> result =
                perfectCycleServiceSpy.initializePerfectCycleListElementVisitedMap(perfectCycleList);

        assertEquals(0, result.size());
    }

    @Test
    void initializePerfectCycleListElementVisitedMap_assertPerfectListIsNull_returnEmptyVisitedMap() {
        List<Integer> perfectCycleList = null;

        Map<Integer, Integer> result =
                perfectCycleServiceSpy.initializePerfectCycleListElementVisitedMap(perfectCycleList);

        assertEquals(0, result.size());
    }

    @Test
    void incrementPerfectCycleListVisitedMapElements_assertPerfectMapIsNull_doNothing() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = null;
        int lastVisitedIndex = 0;

        perfectCycleServiceSpy.incrementPerfectCycleListVisitedMapElements(perfectCycleListElementVisitedMap, lastVisitedIndex);

        assertNull(perfectCycleListElementVisitedMap);
    }

    @Test
    void incrementPerfectCycleListVisitedMapElements_assertPerfectMapContainTheIndex_incrementTheIndexValue() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        perfectCycleListElementVisitedMap.put(0, 3);
        int lastVisitedIndex = 0;

        perfectCycleServiceSpy.incrementPerfectCycleListVisitedMapElements(perfectCycleListElementVisitedMap, lastVisitedIndex);

        assertEquals(4, perfectCycleListElementVisitedMap.get(lastVisitedIndex));
        assertEquals(1, perfectCycleListElementVisitedMap.size());
    }

    @Test
    void incrementPerfectCycleListVisitedMapElements_assertPerfectMapNotContainTheIndex_doNothing() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        perfectCycleListElementVisitedMap.put(0, 3);
        int lastVisitedIndex = 1;

        perfectCycleServiceSpy.incrementPerfectCycleListVisitedMapElements(perfectCycleListElementVisitedMap, lastVisitedIndex);

        assertEquals(3, perfectCycleListElementVisitedMap.get(0));
        assertEquals(1, perfectCycleListElementVisitedMap.size());
    }

    @Test
    void getTheNextElementToVisit_assertPerfectListContainIndex_returnCorrespondingValue() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{1, 2, 3});
        int lastVisitedIndex = 1;

        int result =
                perfectCycleServiceSpy.getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);

        assertEquals(2, result);
    }

    @Test
    void getTheNextElementToVisit_assertPerfectListNotContainIndex_returnNegativeOne() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{1, 2, 3});
        int lastVisitedIndex = 5;

        int result =
                perfectCycleServiceSpy.getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);

        assertEquals(-1, result);
    }

    @Test
    void getTheNextElementToVisit_assertPerfectListIsEmpty_returnNegativeOne() {
        List<Integer> perfectCycleList = new ArrayList<>();
        int lastVisitedIndex = 5;

        int result =
                perfectCycleServiceSpy.getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);

        assertEquals(-1, result);
    }

    @Test
    void getTheNextElementToVisit_assertPerfectListIsNull_returnNegativeOne() {
        List<Integer> perfectCycleList = null;
        int lastVisitedIndex = 5;

        int result =
                perfectCycleServiceSpy.getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);

        assertEquals(-1, result);
    }

    @Test
    void areIndexesWithinListRange_assertAllIndexesWithinRange_returnTrue() {
        int perfectCycleListSize = 1;
        int elementsCounter = 0;
        int lastVisitedIndex = 0;

        boolean result =
                perfectCycleServiceSpy.areIndexesWithinListRange(perfectCycleListSize, elementsCounter,
                        lastVisitedIndex);

        assertTrue(result);
    }

    private static Stream<Arguments> InvalidPerfectCycleIndexes() {
        return Stream.of(
                arguments(1, 0),
                arguments(0, 1),
                arguments(1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("InvalidPerfectCycleIndexes")
    void areIndexesWithinListRange_assertIndexesNotWithinRange_returnFalse(int elementsCounter, int lastVisitedIndex) {
        int perfectCycleListSize = 1;

        boolean result =
                perfectCycleServiceSpy.areIndexesWithinListRange(perfectCycleListSize, elementsCounter,
                        lastVisitedIndex);

        assertFalse(result);
    }

    @Test
    void areIndexesWithinListRange_assertLastVisitedIndexIsNegative_returnFalse() {
        int perfectCycleListSize = 1;
        int elementsCounter = 0;
        int lastVisitedIndex = -1;

        boolean result =
                perfectCycleServiceSpy.areIndexesWithinListRange(perfectCycleListSize, elementsCounter,
                        lastVisitedIndex);

        assertFalse(result);
    }

    @Test
    void isListPerfectCycle_assertAllElementAreVisitedAndLastIndexIsZero_returnTrue() {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        int lastVisitedIndex = 0;

        doReturn(true).when(perfectCycleServiceSpy).isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);
        boolean result = perfectCycleServiceSpy.isListPerfectCycle(perfectCycleListElementVisitedMap, lastVisitedIndex);

        assertTrue(result);
    }

    private static Stream<Arguments> InvalidPerfectCycle() {
        return Stream.of(
                arguments(1, true),
                arguments(1, false),
                arguments(0, false)
        );
    }

    @ParameterizedTest
    @MethodSource("InvalidPerfectCycle")
    void isListPerfectCycle_assertListIsNotPerfect_returnFalse(int lastVisitedIndex,
                                                               boolean isAllPerfectCycleElementVisited) {
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();

        doReturn(isAllPerfectCycleElementVisited).when(perfectCycleServiceSpy).isAllPerfectCycleElementVisited(perfectCycleListElementVisitedMap);
        boolean result = perfectCycleServiceSpy.isListPerfectCycle(perfectCycleListElementVisitedMap, lastVisitedIndex);

        assertFalse(result);
    }

    @Test
    void trackOccurrenceOfPerfectCycleList_assertListIsPerfect_returnTrue() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{0, 1, 2});
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        int lastVisitedIndex = 0;

        doReturn(perfectCycleListElementVisitedMap).when(perfectCycleServiceSpy).initializePerfectCycleListElementVisitedMap(perfectCycleList);
        doReturn(lastVisitedIndex).when(perfectCycleServiceSpy).populatePerfectCycleListElementVisitedMap(perfectCycleList, perfectCycleListElementVisitedMap);
        doReturn(true).when(perfectCycleServiceSpy).isListPerfectCycle(perfectCycleListElementVisitedMap, lastVisitedIndex);
        boolean result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleList(perfectCycleList);

        assertTrue(result);
    }

    @Test
    void trackOccurrenceOfPerfectCycleList_assertListIsNotPerfect_returnFalse() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{0, 1, 2});
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        int lastVisitedIndex = 0;

        doReturn(perfectCycleListElementVisitedMap).when(perfectCycleServiceSpy).initializePerfectCycleListElementVisitedMap(perfectCycleList);
        doReturn(lastVisitedIndex).when(perfectCycleServiceSpy).populatePerfectCycleListElementVisitedMap(perfectCycleList, perfectCycleListElementVisitedMap);
        doReturn(false).when(perfectCycleServiceSpy).isListPerfectCycle(perfectCycleListElementVisitedMap,
                lastVisitedIndex);
        boolean result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleList(perfectCycleList);

        assertFalse(result);
    }

    @Test
    void addPerfectCycleListStatus_assertListIsPerfect_populateTrueStatus() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{0, 1, 2});
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();
        String perfectCycleListName = "list1";

        doReturn(true).when(perfectCycleServiceSpy).trackOccurrenceOfPerfectCycleList(perfectCycleList);
        perfectCycleServiceSpy.addPerfectCycleListStatus(statusMapForPerfectCycleList, perfectCycleListName,
                perfectCycleList);

        assertTrue(statusMapForPerfectCycleList.get(perfectCycleListName));
        assertEquals(1, statusMapForPerfectCycleList.size());
    }

    @Test
    void addPerfectCycleListStatus_assertListIsNotPerfect_populateFalseStatus() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{0, 1, 2});
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();
        String perfectCycleListName = "list1";

        doReturn(false).when(perfectCycleServiceSpy).trackOccurrenceOfPerfectCycleList(perfectCycleList);
        perfectCycleServiceSpy.addPerfectCycleListStatus(statusMapForPerfectCycleList, perfectCycleListName,
                perfectCycleList);

        assertFalse(statusMapForPerfectCycleList.get(perfectCycleListName));
        assertEquals(1, statusMapForPerfectCycleList.size());
    }

    @Test
    void trackOccurrenceOfPerfectCycleLists_assertListsArePerfect_populateTrueStatus() {
        Map<String, List<Integer>> perfectCycleLists = new HashMap<>();
        List<Integer> perfectCycleList1 = Arrays.asList(new Integer[]{0, 1, 2});
        List<Integer> perfectCycleList2 = Arrays.asList(new Integer[]{0, 1, 2});
        String perfectCycleListName1 = "list1";
        String perfectCycleListName2 = "list2";
        perfectCycleLists.put(perfectCycleListName1, perfectCycleList1);
        perfectCycleLists.put(perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();
        statusMapForPerfectCycleList.put(perfectCycleListName1, true);
        statusMapForPerfectCycleList.put(perfectCycleListName2, true);

        doReturn(statusMapForPerfectCycleList).when(perfectCycleServiceSpy).getStatusMapMap();
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName1, perfectCycleList1);
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleLists(perfectCycleLists);

        assertEquals(statusMapForPerfectCycleList, result);
        assertEquals(2, result.size());
    }

    @Test
    void trackOccurrenceOfPerfectCycleLists_assertListsAreNotPerfect_populateFalseStatus() {
        Map<String, List<Integer>> perfectCycleLists = new HashMap<>();
        List<Integer> perfectCycleList1 = Arrays.asList(new Integer[]{0, 1, 2});
        List<Integer> perfectCycleList2 = Arrays.asList(new Integer[]{0, 1, 2});
        String perfectCycleListName1 = "list1";
        String perfectCycleListName2 = "list2";
        perfectCycleLists.put(perfectCycleListName1, perfectCycleList1);
        perfectCycleLists.put(perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();
        statusMapForPerfectCycleList.put(perfectCycleListName1, false);
        statusMapForPerfectCycleList.put(perfectCycleListName2, false);

        doReturn(statusMapForPerfectCycleList).when(perfectCycleServiceSpy).getStatusMapMap();
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName1, perfectCycleList1);
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleLists(perfectCycleLists);

        assertEquals(statusMapForPerfectCycleList, result);
        assertEquals(2, result.size());
    }

    @Test
    void trackOccurrenceOfPerfectCycleLists_assertListsAreNotPerfectAndPerfect_populateFalseAndTrueStatus() {
        Map<String, List<Integer>> perfectCycleLists = new HashMap<>();
        List<Integer> perfectCycleList1 = Arrays.asList(new Integer[]{0, 1, 2});
        List<Integer> perfectCycleList2 = Arrays.asList(new Integer[]{0, 1, 2});
        String perfectCycleListName1 = "list1";
        String perfectCycleListName2 = "list2";
        perfectCycleLists.put(perfectCycleListName1, perfectCycleList1);
        perfectCycleLists.put(perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();
        statusMapForPerfectCycleList.put(perfectCycleListName1, true);
        statusMapForPerfectCycleList.put(perfectCycleListName2, false);

        doReturn(statusMapForPerfectCycleList).when(perfectCycleServiceSpy).getStatusMapMap();
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName1, perfectCycleList1);
        doNothing().when(perfectCycleServiceSpy).addPerfectCycleListStatus(statusMapForPerfectCycleList,
                perfectCycleListName2, perfectCycleList2);
        Map<String, Boolean> result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleLists(perfectCycleLists);

        assertEquals(statusMapForPerfectCycleList, result);
        assertEquals(2, result.size());
    }

    @Test
    void trackOccurrenceOfPerfectCycleLists_assertListsAreEmpty_populateEmptyStatus() {
        Map<String, List<Integer>> perfectCycleLists = new HashMap<>();
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();

        doReturn(statusMapForPerfectCycleList).when(perfectCycleServiceSpy).getStatusMapMap();
        Map<String, Boolean> result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleLists(perfectCycleLists);

        assertEquals(statusMapForPerfectCycleList, result);
        assertEquals(0, result.size());
    }

    @Test
    void trackOccurrenceOfPerfectCycleLists_assertListsIsNull_populateEmptyStatus() {
        Map<String, List<Integer>> perfectCycleLists = null;
        Map<String, Boolean> statusMapForPerfectCycleList = new HashMap<>();

        doReturn(statusMapForPerfectCycleList).when(perfectCycleServiceSpy).getStatusMapMap();
        Map<String, Boolean> result = perfectCycleServiceSpy.trackOccurrenceOfPerfectCycleLists(perfectCycleLists);

        assertEquals(statusMapForPerfectCycleList, result);
        assertEquals(0, result.size());
    }

    @Test
    void populatePerfectCycleListElementVisitedMap_assertPerfectListIsValid_returnValidLastVisitedIndexAndPopulateVisitedMap() {
        List<Integer> perfectCycleList = Arrays.asList(new Integer[]{1, 0});
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        perfectCycleListElementVisitedMap.put(0, 0);
        perfectCycleListElementVisitedMap.put(1, 0);
        int elementsCounter = 0;
        int lastVisitedIndex = 0;

        doReturn(true).when(perfectCycleServiceSpy).areIndexesWithinListRange(perfectCycleList.size(),
                elementsCounter, lastVisitedIndex);
        doReturn(true).when(perfectCycleServiceSpy).areIndexesWithinListRange(perfectCycleList.size(),
                elementsCounter + 1, lastVisitedIndex);
        doReturn(false).when(perfectCycleServiceSpy).areIndexesWithinListRange(perfectCycleList.size(),
                elementsCounter + 2, lastVisitedIndex);
        doReturn(0).when(perfectCycleServiceSpy).getTheNextElementToVisit(perfectCycleList, lastVisitedIndex);
        doNothing().when(perfectCycleServiceSpy).incrementPerfectCycleListVisitedMapElements(perfectCycleListElementVisitedMap, lastVisitedIndex);
        int result = perfectCycleServiceSpy.populatePerfectCycleListElementVisitedMap(perfectCycleList,
                perfectCycleListElementVisitedMap);

        assertEquals(0, result);
        assertEquals(2, perfectCycleListElementVisitedMap.size());
    }

    @Test
    void populatePerfectCycleListElementVisitedMap_assertPerfectListIsEmpty_returnValidLastVisitedIndexAndEmptyVisitedMap() {
        List<Integer> perfectCycleList = new ArrayList<>();
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();
        int elementsCounter = 0;
        int lastVisitedIndex = 0;

        doReturn(false).when(perfectCycleServiceSpy).areIndexesWithinListRange(perfectCycleList.size(),
                elementsCounter, lastVisitedIndex);
        int result = perfectCycleServiceSpy.populatePerfectCycleListElementVisitedMap(perfectCycleList,
                perfectCycleListElementVisitedMap);

        assertEquals(0, result);
        assertEquals(0, perfectCycleListElementVisitedMap.size());
    }

    @Test
    void populatePerfectCycleListElementVisitedMap_assertPerfectListIsNull_returnValidLastVisitedIndexAndEmptyVisitedMap() {
        List<Integer> perfectCycleList = null;
        Map<Integer, Integer> perfectCycleListElementVisitedMap = new HashMap<>();

        int result = perfectCycleServiceSpy.populatePerfectCycleListElementVisitedMap(perfectCycleList,
                perfectCycleListElementVisitedMap);

        assertEquals(0, result);
        assertEquals(0, perfectCycleListElementVisitedMap.size());
    }
}
