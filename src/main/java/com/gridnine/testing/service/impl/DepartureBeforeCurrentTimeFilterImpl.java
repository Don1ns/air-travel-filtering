package com.gridnine.testing.service.impl;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightFilter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Riyaz Karimullin
 */
public class DepartureBeforeCurrentTimeFilterImpl implements FlightFilter {

    /**
     * Filters flights where all segments have a departure date after the current time
     *
     * @param flights the list of flights to be filtered
     * @return
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
}
