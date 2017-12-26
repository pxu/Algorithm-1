package catagory.sort;

import java.util.Comparator;

public class Comparable {
  public static void main(String[] args) {

  }
  public static class Data implements java.lang.Comparable<Data> {
    int id;
    int age;
    public Data(int id, int age) {
      this.id = id;
      this.age = age;
    }
    public int compareTo(Data other) {
      return this.id - other.id;
    }
  }
  public static class ByName implements Comparator<Data> {

    public int compare(Data v, Data w) {
      return v.id - w.id;
    }

  }
}
