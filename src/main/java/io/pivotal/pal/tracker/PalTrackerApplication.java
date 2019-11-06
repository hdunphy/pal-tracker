package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PalTrackerApplication {

    @Autowired
    @Qualifier("jdbcRepository")
    private TimeEntryRepository repository;

    public PalTrackerApplication(TimeEntryRepository repository) {

    }

    public static void main(String[] args) {
        SpringApplication.run(PalTrackerApplication.class, args);
    }
}
