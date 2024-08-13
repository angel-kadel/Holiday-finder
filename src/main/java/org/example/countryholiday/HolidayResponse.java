package org.example.countryholiday;

import java.util.List;

public class HolidayResponse {
    private List<Holiday> holidays; // List of holidays in the response

    public List<Holiday> getHolidays() {
        return holidays; // Get the list of holidays
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays; // Set the list of holidays
    }
}
