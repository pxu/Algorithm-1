package leetcode.contest171209;

import java.util.TreeMap;

public class code1 {
  public char nextGreatestLetter(char[] letters, char target) {
    TreeMap<Character,Character> map = new TreeMap<>();
    for(int i = 0; i < letters.length; i++) {
      map.put(letters[i], letters[i]);
    }
    Character higher = map.higherKey(target);
    if (higher == null) {
      return map.firstKey();
    }
    return higher;
  }
}
