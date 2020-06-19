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


    System.out.println("PRINTING COLLECTIONS: ");
    System.out.println(Arrays.toString(conflicts.toArray()));

    // sort events in O(nlogn) time
    Collections.sort(conflicts, TimeRange.ORDER_BY_START);

    // modify events so that all events are disjoint
    for (TimeRange conflict1 : conflicts) {
      for (TimeRange conflict2 : conflicts) {
        // pop first and then get end of next one that overlaps -- 
        // keeping going until no more overlap
      }
    }

    //find intervals between events 
      // pop next item 
      // keep searching until next event doesn't overlap
    num_conflicts = conflicts.size(); // check 
    int start = 0;
    while (start < num_conflicts){
      
      bolean overlap = 
      // keep searching until next event doesn't overlap
      for (int end = start + 1; end < num_conflicts; end++) {
        // if conflicts.get(end) doesn't overlap with conflicts.get(start):
          // then add the interval of conflicts.get(end-1).end and conflicts.get(end).start
          // and reset
            // start = end
      }
      

    } 

    //throw new UnsupportedOperationException("TODO: Implement this method.");

    return conflicts;
  }
}
