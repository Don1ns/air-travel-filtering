package com.gridnine.testing.service.impl;

import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArrivalBeforeDepartureFilterImplTest {
    private ArrivalBeforeDepartureFilterImpl filter;

    @BeforeEach
    public void initTest() {
        filter = new ArrivalBeforeDepartureFilterImpl();
    }

    @Test
    public void testFilter() {
        // Создаем примеры полетов для тестирования
        List<Flight> flights = new ArrayList<>();

        // Полет 1: Дата прилета раньше даты вылета
        List<Segment> segments1 = new ArrayList<>();
        segments1.add(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().minusHours(1)));
        flights.add(new Flight(segments1));

        // Полет 2: Дата прилета позже даты вылета
        List<Segment> segments2 = new ArrayList<>();
        segments2.add(new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3)));
        segments2.add(new Segment(LocalDateTime.now().plusHours(4), LocalDateTime.now().plusHours(5)));
        flights.add(new Flight(segments2));

        // Применяем фильтр
        List<Flight> filteredFlights = filter.filter(flights);

        // Проверяем, что только Полет 1 был отфильтрован
        assertEquals(1, filteredFlights.size());
        assertEquals(flights.get(1), filteredFlights.get(0));

        //Проверяем, что дата вылета раньше даты прилета
        for (Flight flight : filteredFlights) {
            for (Segment segment : flight.getSegments()) {
                assertTrue(segment.getDepartureDate().isBefore(segment.getArrivalDate()));
            }
        }
    }
}