package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository= timeEntryRepository;
    }

    @RequestMapping(value = "/time-entries", method = POST)
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
      TimeEntry timeEntry= timeEntryRepository.create(timeEntryToCreate);
      return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/time-entries/{TIME_ENTRY_ID}")
    @ResponseBody
    public ResponseEntity<TimeEntry> read(@PathVariable("TIME_ENTRY_ID") long timeEntryId) {
        TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);
        if(timeEntry != null){
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntry= timeEntryRepository.list();
        return new ResponseEntity<List<TimeEntry>>(timeEntry, HttpStatus.OK);
    }

    @RequestMapping(value = "/time-entries/{TIME_ENTRY_ID}",method = PUT)
    public ResponseEntity<TimeEntry> update(@PathVariable("TIME_ENTRY_ID") long timeEntryId, @RequestBody TimeEntry timeEntryToUpdate) {
        TimeEntry timeEntry= timeEntryRepository.update(timeEntryId,timeEntryToUpdate);
        if(timeEntry != null) {
            return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
        }else{
            return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/time-entries/{TIME_ENTRY_ID}",method = DELETE)
    public ResponseEntity<Void> delete(@PathVariable("TIME_ENTRY_ID") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
