package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Qualifier("inMemoryRepository")
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntries;
    private long currentId;

    public InMemoryTimeEntryRepository() {
        timeEntries = new HashMap<Long, TimeEntry>();
        currentId = 0L;
    }

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(++currentId);
        timeEntries.put(currentId, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(Long id) {
        return timeEntries.get(id);
    }


    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntries.values());
    }

    public TimeEntry update(long id, TimeEntry toUpdate) {
        if(find(id) == null) return null;

        toUpdate.setId(id);
        timeEntries.put(id, toUpdate);
        return toUpdate;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }
}
