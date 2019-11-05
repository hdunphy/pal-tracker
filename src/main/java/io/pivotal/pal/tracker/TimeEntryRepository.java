package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry any);

    public TimeEntry find(Long timeEntryId);

    public List<TimeEntry> list();

    public TimeEntry update(long id, TimeEntry toUpdate);

    public void delete(long timeEntryId);

}
