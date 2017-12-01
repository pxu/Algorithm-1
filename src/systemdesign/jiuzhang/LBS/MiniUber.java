package systemdesign.jiuzhang.LBS;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Definition of Trip:
 * public class Trip {
 *     public int id; // trip's id, primary key
 *     public int driver_id, rider_id; // foreign key
 *     public double lat, lng; // pick up location
 *     public Trip(int rider_id, double lat, double lng);
 * }
 * Definition of Helper
 * class Helper {
 *     public static double get_distance(double lat1, double lng1,
 double lat2, double lng2) {
 *         // return distance between (lat1, lng1) and (lat2, lng2)
 *     }
 * };
 */
public class MiniUber {

 static class Helper {

     public static double get_distance(double lat1, double lng1,double lat2, double lng2) {

         // return distance between (lat1, lng1) and (lat2, lng2)
       return 0;
     }
 };
 static class Trip {
     public int id; // trip's id, primary key
     public int driver_id, rider_id; // foreign key
     public double lat, lng; // pick up location
     public Trip(int rider_id, double lat, double lng){
       this.rider_id = rider_id;
       this.lat = lat;
       this.lng = lng;

     }
 }
  static class Driver {
    public int driver_id;
    public double lat, lng; // pick up location
    public boolean isPicked;
    public int riderId;

    public Driver(int driver_id){
      this.driver_id = driver_id;
    }
    @Override
    public boolean equals(Object other) {
      if(!(other instanceof Trip)) {
        return false;
      }
      return this.driver_id == ((Driver)other).driver_id;
    }
    @Override
    public int hashCode() {
      return this.driver_id;
    }
  }

  static class Rider {
    public int rider_id;
    public double lat, lng; // pick up location
    public boolean isPicked;
    public int driver_id;
  }
  private Map<Integer, Driver> drivers = new HashMap<Integer, Driver>();

  private Map<Integer, Rider> riders = new HashMap<Integer, Rider>();

  public MiniUber() {
    // initialize your data structure here.
  }

  // @param driver_id an integer
  // @param lat, lng driver's location
  // return matched trip information if there have matched rider or null
  public Trip report(int driver_id, double lat, double lng) {
    // Write your code here
    Driver driver = drivers.get(driver_id);
    if(driver == null) {
      driver = new Driver(driver_id);
      drivers.put(driver_id, driver);
    }
    driver.lat = lat;
    driver.lng = lng;

    if(driver.isPicked) {
      Rider rider = riders.get(driver.riderId);
      Trip result =  new Trip(rider.rider_id, rider.lat, rider.lng);
      result.driver_id = driver_id;
      return result;
    }
    return null;
  }

  // @param rider_id an integer
  // @param lat, lng rider's location
  // return a trip
  public Trip request(int rider_id, double lat, double lng) {
    // Write your code here
    Rider rider = riders.get(rider_id);
    if(rider == null) {
      rider = new Rider();
      rider.rider_id = rider_id;
      riders.put(rider_id, rider);
    }
    rider.lat = lat;
    rider.lng = lng;
    double closestDist = Float.MAX_VALUE;
    Driver closestDriver = null;
    for(Entry<Integer,Driver > entry : drivers.entrySet()) {
      Driver driver = entry.getValue();
      if(driver.isPicked) {
        continue;
      }
      double dist = Helper.get_distance(driver.lat,driver.lng, rider.lat, rider.lng);
      if(closestDist > dist) {
        closestDist = dist;
        closestDriver = driver;
      }
    }
    if(closestDriver != null) {
      closestDriver.isPicked = true;
      closestDriver.riderId = rider.rider_id;

      Trip result = new Trip(rider.rider_id, rider.lat, rider.lng);
      result.driver_id = closestDriver.driver_id;
      return result;
    }
    return null;

  }
}
/**
 * Definition of Trip:
 * public class Trip {
 *     public int id; // trip's id, primary key
 *     public int driver_id, rider_id; // foreign key
 *     public double lat, lng; // pick up location
 *     public Trip(int rider_id, double lat, double lng);
 * }
 * Definition of Helper
 * class Helper {
 *     public static double get_distance(double lat1, double lng1,
 double lat2, double lng2) {
 *         // return distance between (lat1, lng1) and (lat2, lng2)
 *     }
 * };
 */