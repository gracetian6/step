// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    
    // find conflicts of all required attendees
    ArrayList<TimeRange> conflicts = new ArrayList<>();
    ArrayList<TimeRange> free = new ArrayList<>();

    for (Event event : events) {
      for (String attendee : request.getAttendees()) {
        if (event.getAttendees().contains(attendee)) {
          conflicts.add(event.getWhen());
        }
      }
    }


    // sort events in O(nlogn) time
    Collections.sort(conflicts, TimeRange.ORDER_BY_START);


    //find intervals between events 
      // pop next item 
      // keep searching until next event doesn't overlap
    int num_conflicts = conflicts.size(); // check 

    if (num_conflicts == 0) {
      free.add(TimeRange.WHOLE_DAY);
      return free;
    }

    int start = 0;
    TimeRange start_interval;
    TimeRange end_interval;
    TimeRange interval;


    while (start < num_conflicts){
      start_interval = conflicts.get(start);

      // add interval before start
      if (start == 0) {
        interval = TimeRange.fromStartEnd(TimeRange.START_OF_DAY, start_interval.start(), false);
        if (request.getDuration() <= interval.duration()) {
          free.add(interval);
        }
      }
      // keep searching until next event doesn't overlap
      for (int end = start + 1; end < num_conflicts; end++) {
        end_interval = conflicts.get(end);
        // if conflicts.get(end) doesn't overlap with conflicts.get(start):
        if (!end_interval.overlaps(start_interval)){
          // then add the interval of conflicts.get(end-1).end and conflicts.get(end).start
          interval = TimeRange.fromStartEnd(conflicts.get(end-1).end(), end_interval.start(), false); // change bool later
          if (request.getDuration() <= interval.duration()) {
            free.add(interval);
          }
          // and reset
          start = end;
        }
      }      
      start++;
    } 
    

    // add last interval 
    interval = TimeRange.fromStartEnd(conflicts.get(num_conflicts - 1).end(), TimeRange.END_OF_DAY + 1, false);
    if (request.getDuration() <= interval.duration()) {
      free.add(interval);
    } 
    

    System.out.println("PRINTING RESULT: ");
    System.out.println(Arrays.toString(free.toArray()));
    return free;
  }
}
