package catagory.DFS;

import java.util.*;

/**
 * Created by zhahua on 11/20/17.
 */
public class WordLadder2 {
    /*
    * @param start: a string
    * @param end: a string
    * @param dict: a set of string
    * @return: a list of lists of string
    */
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<>();
        if(start.equals(end)) {
            result.add(new ArrayList(){{this.add(start);}});
            return result;
        }
        dict.add(start);
        dict.add(end);

        Map<String,List<String>> cache = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        BFS(start, end, cache, dict, level);
        List<String> stack = new ArrayList<String>();
        stack.add(end);//forgot to add
        DFS(end, start, stack, cache, level, result);

        return result;
    }
    private void DFS(String current,String start,List<String> stack, Map<String,List<String>> cache, Map<String, Integer> level, List<List<String>> result) {
        if(current.equals(start)) {
            List<String> path = new ArrayList<>(stack);
            Collections.reverse(path);
            result.add(path);
            return;
        }
        for(String next : cache.get(current)) {
            if(level.get(next) == level.get(current) - 1) {
                stack.add(next);
                DFS(next,start, stack, cache, level, result);
                stack.remove(stack.size() - 1);
            }
        }

    }
    private void BFS(String start, String end, Map<String,List<String>> cache, Set<String> dict, Map<String, Integer> level) {

        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        level.put(start, 0);
        cache.put(start, new ArrayList());
        while(!queue.isEmpty()) { //?? 终止条件？
            String head = queue.poll();
            int headLevel = level.get(head);
            for(String nextWord : nextWords(head, dict)) {

                if(cache.containsKey(nextWord)) {
                    cache.get(nextWord).add(head); //****
                    continue;
                }
                queue.offer(nextWord);
                cache.computeIfAbsent(nextWord, (k) -> new ArrayList<String>()).add(head);
                level.put(nextWord, headLevel + 1);

            }
        }

    }
    private List<String> nextWords(String str, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        for(int i = 0; i < str.length(); i++) {
            char[] chars = str.toCharArray();
            for(char ch = 'a'; ch <= 'z'; ch++) {
                if(ch == str.charAt(i)) {
                    continue;
                }
                chars[i] = ch;
                String newStr = new String(chars);
                if(dict.contains(newStr)) {
                    result.add(newStr);
                }
            }
        }
        return result;

    }
}
