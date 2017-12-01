package catagory.linkedlistnarray.region;

import java.util.TreeMap;

/**
 * Created by zhahua on 11/29/17.
 */
class MyCalendarTwo {
    TreeMap<Integer, Integer> events = new TreeMap<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        events.put(start, events.getOrDefault(start, 0) + 1);
        events.put(end, events.getOrDefault(end, 0) - 1);
        int delta = 0;
        for(int value : events.values()) { //*values()
            delta += value;
            if(delta >= 3) {
                events.put(start, events.get(start) - 1);
                events.put(end, events.get(end) + 1);
                return false;
            }
        }
        return true;
    }
}
