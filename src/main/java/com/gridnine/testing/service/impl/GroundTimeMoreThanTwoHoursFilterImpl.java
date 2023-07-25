package com.gridnine.testing.service.impl;

import com.gridnine.testing.entity.Segment;
import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.entity.Flight;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Riyaz Karimullin
 */
public class GroundTimeMoreThanTwoHoursFilterImpl implements FlightFilter {
    private static final long MAX_GROUND_TIME_HOURS = 2;

    /**
     * Filters flights where the total ground time between segments is less than or equal to the maximum allowed ground time
     *
     * @param flights the list of flights to be filtered
     * @return
     */
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> checkTotalTimeOnEarthExceedsTwoHours(flight.getSegments()))
                .collect(Collectors.toList());
    }

    /**
     * Checks if the total time spent on the ground between segments does not exceed two hours.
     *
     * @param segments
     * @return
     */
    private boolean checkTotalTimeOnEarthExceedsTwoHours(List<Segment> segments){
        Duration totalGroundTime = Duration.ZERO;
        for (int i =1; i < segments.size(); i++) {
            Duration groundTime = Duration.between(segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate());
            if (groundTime.isNegative()) {
                return false;
            }
            totalGroundTime = totalGroundTime.plus(groundTime);
        }
        return totalGroundTime.toHours() <= MAX_GROUND_TIME_HOURS;
    }
}
