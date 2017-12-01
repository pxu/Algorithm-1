package catagory.linkedlistnarray.region;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

//https://leetcode.com/problems/my-calendar-i/description/
//
class MyCalendar {

    public NavigableMap<Integer, Integer> events = new TreeMap<Integer, Integer>();
    public MyCalendar() {
        
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> prev = events.floorEntry(start);
        Map.Entry<Integer, Integer> next = events.ceilingEntry(start); //* 是start 不是end
        
        if (prev != null && (prev.getValue() > start)) {
            return false;
        }
        if (next != null && (next.getKey() < end)) {
            return false;
        }
        events.put(start, end);
        return true;
    }
}