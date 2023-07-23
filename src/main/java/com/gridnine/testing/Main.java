package com.gridnine.testing;

import com.gridnine.testing.service.FlightFilter;
import com.gridnine.testing.builder.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.impl.ArrivalBeforeDepartureFilterImpl;
import com.gridnine.testing.service.impl.DepartureBeforeCurrentTimeFilterImpl;
import com.gridnine.testing.service.impl.GroundTimeMoreThanTwoHoursFilterImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter departureBeforeCurrentTimeFilter = new DepartureBeforeCurrentTimeFilterImpl();
        FlightFilter arrivalBeforeDepartureFilter = new ArrivalBeforeDepartureFilterImpl();
        FlightFilter groundTimeMoreThanTwoHoursFilter = new GroundTimeMoreThanTwoHoursFilterImpl();

        List<Flight> filteredFlights = departureBeforeCurrentTimeFilter.filter(flights);
        System.out.println("Список перелетов с исключением перелетов," +
                " где вылет до текущего момента времени: " + filteredFlights);

        filteredFlights = arrivalBeforeDepartureFilter.filter(flights);
        System.out.println("Список перелетов с исключением перелетов," +
                " где имеются сегменты с датой прилёта раньше даты вылета: " + filteredFlights);

        filteredFlights = groundTimeMoreThanTwoHoursFilter.filter(flights);
        System.out.println("Список перелетов с исключением перелетов," +
                " где общее время, проведённое на земле превышает два часа " +
                "(время на земле — это интервал между прилётом одного сегмента и вылетом следующего за ним): " + filteredFlights);
    }
}
