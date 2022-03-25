/**
 * Name: Merrick Qiu
 * Email: myqiu@ucsd.edu
 * Sources used: None
 * 
 * A Calendar event booker.
 * It books events if the events don't conflict
 */


 /**
  * A class for a calendar event booker
  It uses a TreeMap to store events
  */
public class MyCalendar {
    MyTreeMap<Integer, Integer> calendar;
    
    /**
     * MyCalendar constructor
     */
    public MyCalendar() {
        calendar = new MyTreeMap<>();
    }
    
    /**
     * Books events if it doesn't conflict with other events
     * @param start Start time
     * @param end End time
     * @return Booking success or not
     */
    public boolean book(int start, int end) {
        if (start < 0 || start >= end) {
            throw new IllegalArgumentException();
        }
        Integer ceilingStart = calendar.ceilingKey(start);
        Integer floorStart = calendar.floorKey(start);

        // Check for conflicts
        if (ceilingStart != null && ceilingStart < end || 
            floorStart != null && calendar.get(floorStart) > start) {
            return false;
        }
        // if (calendar.ceilingKey(start) != null) {
        //     if (calendar.ceilingKey(start) < end) {
        //       return false;
        //     }
        //   }
          
        //   if (calendar.floorKey(start) != null) {
        //     if (calendar.get(calendar.floorKey(start)) > start) {
        //        return false;
        //     }
        // }
        calendar.put(start, end);
        return true;
    }

    /**
     * Gets the TreeMap of the calendar
     * @return The TreeMap of the calendar
     */
    public MyTreeMap getCalendar(){
        return this.calendar;
    }
}