package com.couchtalks.service.impl;

/**
 * Created by ShkarupaN on 11.04.2017.
 */
import com.couchtalks.service.DateTimeService;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class CurrentTimeDateTimeService implements DateTimeService {

    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}
