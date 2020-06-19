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

    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()){
      return free;
    }
    int num_conflicts = conflicts.size(); 
    if (num_conflicts == 0) {
      free.add(TimeRange.WHOLE_DAY);
      return free;
    }

    int start = 0; int end;
    TimeRange start_interval;
    TimeRange end_interval;
    TimeRange interval;

    // make events in conflicts disjoint
    while (start < conflicts.size()) {
      start_interval = conflicts.get(start);
      end = start + 1;
      while (end < conflicts.size()){
        end_interval = conflicts.get(end);
        if (start_interval.contains(end_interval)) {
          conflicts.remove(end);
        }
        else if (start_interval.overlaps(end_interval)){
          start_interval = TimeRange.fromStartEnd(start_interval.start(), end_interval.end());
          conflicts.remove(end);
        }
        else {
          end++;
        }
      }
      start = end;
    }

    // add events between disjoint conflicts
    for (int i = 0; i < conflicts.size(); i++) {
      if (i == 0) {
        start = TimeRange.START_OF_DAY;
        end = conflicts.get(i).start();
      }
      else if (i == conflicts.size() - 1){
        start = conflicts.get(i).end();
        end = TimeRange.END_OF_DAY;
      }
      else {
        start = conflict.get(i).end();
        end = conflict.get(i+1).start();
      }
      interval = TimeRange.fromStartEnd(start, end, false);
      if (request.getDuration() <= interval.duration()) {
        free.add(interval);
      }
    }

    System.out.println("PRINTING RESULT: ");
    System.out.println(Arrays.toString(free.toArray()));
    return free;
  }
}
