package com.gridnine.testing.service.impl;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GroundTimeMoreThanTwoHoursFilterImplTest {
    private GroundTimeMoreThanTwoHoursFilterImpl filter;

    @BeforeEach
    public void initTest() {
        filter = new GroundTimeMoreThanTwoHoursFilterImpl();
    }

    @Test
    public void testFilter() {
        // Создаем примеры полетов для тестирования
        List<Flight> flights = new ArrayList<>();

        // Полет 1: Общее время на земле превышает 2 часа
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments1.add(new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5)));
        flights.add(new Flight(segments1));

        // Полет 2: Общее время на земле меньше или равно 2 часам
        List<Segment> segments2 = new ArrayList<>();
        segments2.add(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        segments2.add(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3)));
        flights.add(new Flight(segments2));

        // Применяем фильтр
        List<Flight> filteredFlights = filter.filter(flights);

        // Проверяем, что только Полет 1 был отфильтрован
        assertEquals(1, filteredFlights.size());
        assertEquals(flights.get(1), filteredFlights.get(0));
    }
}