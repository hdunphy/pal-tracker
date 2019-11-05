package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.repository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry created = repository.create(timeEntryToCreate);
        return new ResponseEntity<TimeEntry>(created, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long id) {
        TimeEntry found = repository.find(id);
        HttpStatus status = found == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<TimeEntry>(found, status);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = repository.list();
        return new ResponseEntity<List<TimeEntry>>(list, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry toUpdate) {
        TimeEntry updated = repository.update(id, toUpdate);
        HttpStatus status = updated == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<TimeEntry>(updated, status);
    }

    @DeleteMapping("{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        repository.delete(timeEntryId);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
