package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

/**
 * @author Riyaz Karimullin
 */
public interface FlightFilter {
    /**
     * Filters a list of flights based on certain criteria.
     *
     * @param flights the list of flights to be filtered
     * @return the filtered list of flights
     */
    List<Flight> filter(List<Flight> flights);
}
