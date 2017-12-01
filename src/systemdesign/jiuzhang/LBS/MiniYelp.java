//package systemdesign.jiuzhang.LBS;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.concurrent.atomic.AtomicInteger;
//
///**
// * Definition of Location:
// * class Location {
// *     public double latitude, longitude;
// *     public static Location create(double lati, double longi) {
// *         // This will create a new location object
// *     }
// * };
// * Definition of Restaurant
// * class Restaurant {
// *     public int id;
// *     public String name;
// *     public Location location;
// *     public static Restaurant create(String name, Location location) {
// *         // This will create a new restaurant object,
// *         // and auto fill id
// *     }
// * };
// * Definition of Helper
// * class Helper {
// *     public static get_distance(Location location1, Location location2) {
// *         // return distance between location1 and location2.
// *     }
// * };
// * class GeoHash {
// *     public static String encode(Location location) {
// *         // return convert location to a GeoHash string
// *     }
// *     public static Location decode(String hashcode) {
// *          // return convert a GeoHash string to location
// *     }
// * };
// */
//
//
//
//class Location {
//    public double latitude, longitude;
//    public static Location create(double lati, double longi) {
//        // This will create a new location object
//      return null;
//    }
//
//};
//
//class Restaurant {
//    public int id;
//    public String name;
//    public Location location;
//    public static Restaurant create(String name, Location location) {
//        // This will create a new restaurant object,
//        // and auto fill id
//      return null;
//    }
//};
//
//class Helper {
//    public static double get_distance(Location location1, Location location2) {
//        // return distance between location1 and location2.
//
//    }
//};
//class GeoHash {
//    public static String encode(Location location) {
//        // return convert location to a GeoHash string
//    }
//    public static Location decode(String hashcode) {
//         // return convert a GeoHash string to location
//    }
//};
//class GeoHashMap {
//
//
//  private Map<String,Set<Restaurant>>[] multiLevelMap = null;
//
//  private int geoHashMaxLength;
//
//  private double[] errorMapping = new double[] {Double.MAX_VALUE,2500,630,78,20,2.4,0.61,0.076,0.019};
//
//  @SuppressWarnings("unchecked")
//  public GeoHashMap(int geoHashMaxLength) {
//    this.geoHashMaxLength = geoHashMaxLength;
//
//    multiLevelMap =  (Map<String,Set<Restaurant>>[])new Map[geoHashMaxLength + 1];
//    for(int i = 0; i <= geoHashMaxLength; i++ ) {
//      multiLevelMap[i] = new HashMap<String,Set<Restaurant>>();
//    }
//  }
//  public void add(Location location, Restaurant value) {
//    String geoHash = GeoHash.encode(location);
//    for(int i = 0; i <= geoHashMaxLength; i++) {
//      String geoHashSubstring = geoHash.substring(0, i);
//      Set<Restaurant> restSet = multiLevelMap[i].get(geoHashSubstring);
//      if(restSet == null) {
//        restSet = new HashSet<Restaurant>();
//      }
//      restSet.add(value);
//    }
//  }
//
//  public void remove(Location location, Restaurant value) {
//    String geoHash = GeoHash.encode(location);
//    for(int i = 0; i <= geoHashMaxLength; i++) {
//      String geoHashSubstring = geoHash.substring(0, i);
//      Set<Restaurant> restSet = multiLevelMap[i].get(geoHashSubstring);
//      if(restSet != null) {
//        restSet.remove(value);
//      }
//    }
//  }
//
//  public List<Restaurant> nearBy(Location location, double range) {
//    int subStringLen =  errorMapping.length - 1;
//    for(; subStringLen >=0 && errorMapping[subStringLen] < range; subStringLen--) {
//
//    }
//    if(subStringLen < 0) {
//      subStringLen = 0;
//    }
//    String geoHash = GeoHash.encode(location);
//    String subGeoHash = geoHash.substring(0, subStringLen);
//    List<Restaurant> nearBy = new ArrayList<>();
//    Set<Restaurant> geoHashSet = multiLevelMap[subStringLen].get(subGeoHash);
//    if(geoHashSet == null) {
//      return nearBy;
//    }
//    for(Restaurant item: geoHashSet) {
//      if(Helper.get_distance(item.location,location) < range) {
//        nearBy.add(item);
//      }
//    }
//    return nearBy;
//  }
//
//}
//public class MiniYelp {
//  public MiniYelp() {
//    // initialize your data structure here.
//  }
//  private HashMap<Integer,Restaurant> restaurantIdIndex = new HashMap<Integer, Restaurant>();
//
//  private GeoHashMap restaurantMap = new GeoHashMap(8);
//
//  AtomicInteger serialNumber = new AtomicInteger();
//  // @param name a string
//  // @param location a Location
//  // @return an integer, restaurant's id
//  public int addRestaurant(String name, Location location) {
//    // Write your code here
//    String geoHash = GeoHash.encode(location);
//
//    int id = serialNumber.getAndIncrement();
//    Restaurant restaurant = Restaurant.create(name,location);
//    restaurant.id = id;
//
//    restaurantIdIndex.put(id, restaurant);
//    restaurantMap.add(location,restaurant);
//    return id;
//
//  }
//
//  // @param restaurant_id an integer
//  public void removeRestaurant(int restaurant_id) {
//    // Write your code here
//    Restaurant restaurant = restaurantIdIndex.get(restaurant_id);
//    if(restaurant == null) {
//      return;
//    }
//    String geoHash = GeoHash.encode(restaurant.location);
//
//    restaurantMap.remove(restaurant.location,restaurant);
//
//    restaurantIdIndex.remove(restaurant_id);
//  }
//
//  // @param location a Location
//  // @param k an integer, distance smaller than k miles
//  // @return a list of restaurant's name and sort by
//  // distance from near to far.
//  public List<String> neighbors(Location location, double k) {
//    // Write your code here
//    List<Restaurant> neighborRest = restaurantMap.nearBy(location,k);
//    List<String> neighborName = new ArrayList<>();
//    for(Restaurant rest : neighborRest) {
//      neighborName.add(rest.name);
//    }
//    Collections.sort(neighborName,Collections.<String>reverseOrder());
//    return neighborName;
//  }
//};