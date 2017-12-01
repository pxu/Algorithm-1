package catagory.linkedlistnarray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhahua on 11/28/17.
 */
// https://leetcode.com/problems/my-calendar-i/description/
public class MyCalendar {
    public MyCalendar() {

    }
    private List<int[]> events = new ArrayList<>();

    public boolean book(int start, int end) {
        for (int[] event : events) {
            int minEnd = Math.min(end, event[1]);
            int maxStart = Math.max(start, event[0]);
            //System.out.println("" + minEnd + " "+ maxStart);
            if (minEnd > maxStart) { //*
                return false;
            }
        }
        events.add(new int[]{start, end});
        return true;
    }
}
