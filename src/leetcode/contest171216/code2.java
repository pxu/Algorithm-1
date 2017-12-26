package leetcode.contest171216;

public class code2 {
  public String shortestCompletingWord(String licensePlate, String[] words) {
    int[] plateMap = new int[26];
    convertToMap(plateMap, licensePlate);
    int[] dictMap = new int[26];
    int minLen = Integer.MAX_VALUE;
    int minIdx = -1;
    for (int i = 0; i < words.length; i++) {
      convertToMap(dictMap, words[i]);
      int len = match(plateMap,words[i],dictMap);
      if (minLen > len) {
        minLen = len;
        minIdx = i;
      }
    }
    return words[minIdx];
  }
  public void convertToMap(int[] map, String word) {
    for(int i = 0; i < map.length; i++) {
      map[i] = 0;
    }
    for(int i = 0; i < word.length(); i++) {
      char ch = word.charAt(i);
      if (ch >= 'a' && ch <= 'z') {
        map[ch - 'a']++;
      } else if (ch >= 'A' && ch <= 'Z') {
        map[ch - 'A']++;
      }
    }
  }
  public int match(int[] plateMap, String dict, int[] dictMap) {
    for (int i = 0; i < plateMap.length; i++) {
      if (dictMap[i] < plateMap[i]) {
        return Integer.MAX_VALUE;
      }
    }
    return dict.length();
  }
}
