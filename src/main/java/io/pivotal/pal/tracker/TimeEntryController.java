package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository repository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository, MeterRegistry registry) {
        this.repository = timeEntryRepository;
        this.timeEntrySummary = registry.summary("timeEntry.summary");
        this.actionCounter = registry.counter("timeEntry.actionCounter");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        actionCounter.increment();
        timeEntrySummary.record(repository.list().size());
        TimeEntry created = repository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(created, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        actionCounter.increment();
        TimeEntry found = repository.find(id);
        HttpStatus status = found == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<TimeEntry>(found, status);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        List<TimeEntry> list = repository.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry toUpdate) {
        actionCounter.increment();
        TimeEntry updated = repository.update(id, toUpdate);
        HttpStatus status = updated == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<TimeEntry>(updated, status);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        actionCounter.increment();
        timeEntrySummary.record(repository.list().size());
        repository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
