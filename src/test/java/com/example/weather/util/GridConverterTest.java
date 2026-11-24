package com.example.weather.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridConverterTest {

    @Test
    void 서울시청_좌표_변환() {
        GridConverter.Grid result =
                GridConverter.toGrid(37.5665, 126.9780);

        assertEquals(60, result.nx());
        assertEquals(127, result.ny());
    }

    @Test
    void 기준점_좌표_변환() {
        GridConverter.Grid result =
                GridConverter.toGrid(38.0, 126.0);

        assertEquals(43, result.nx());
        assertEquals(136, result.ny());
    }

    @Test
    void 기상청_가이드_예제_확인() {
        GridConverter.Grid result =
                GridConverter.toGrid(37.488201, 126.929810);

        assertEquals(59, result.nx());
        assertEquals(125, result.ny());
    }

    @Test
    void 잘못된_위도는_예외발생() {
        assertThrows(
                IllegalArgumentException.class,
                () -> GridConverter.toGrid(100.0, 126.0)
        );
    }
}
