package com.gridnine.testing.service.impl;

import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.entity.Flight;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Riyaz Karimullin
 */
public class ArrivalBeforeDepartureFilterImpl implements FlightFilter {
    /**
     * Filters flights where all segments have an arrival date after the corresponding departure date
     *
     * @param flights the list of flights to be filtered
     * @return
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments()
                        .stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}
