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
    Collection<TimeRange> conflicts = new ArrayList<>();
    int num_attendees = request.getAttendees().size(); //check pseudo code 
    for (Event event : events) {
      for (int j = 0; j < num_attendees; j++) {
        if (event == request.getAttendees()[j]){
          conflicts.add(event.getWhen());
        }
      }
    }


    // sort events in O(nlogn) time
    Collections.sort(conflicts, TimeRange.ORDER_BY_START);

    // modify events so that all events are disjoint

    //find intervals between all disjoint events 

    //throw new UnsupportedOperationException("TODO: Implement this method.");
  }
}
