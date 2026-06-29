import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList();
        if (strs == null || strs.length == 0) {
            return result;
        }

        Map<String, List<String>> map = new HashMap(); // Space Complexity - M

        // M is the length of array of strs
        // N length of max string
        for (String str : strs) {  // Time complexity: O(M * NlogN)
            // Convert to char array
            char[] chars = str.toCharArray();
            char[] freq = new char[26];
            for (int i = 0; i < chars.length; i++) {
                freq[chars[i] - 'a']++;
            }

            // Convert back to string
            String keyString = new String(freq);

            if (!map.containsKey(keyString)) {
                map.put(keyString, new ArrayList<String>());
            }
                
            map.get(keyString).add(str);
        }
        
        return new ArrayList<List<String>>(map.values());
    }
}

// Time Complexity: O(M * N) where M is the length of array of strs and N is the length of max string
// Space Complexity: O(M) where M is the length of array of strs
// The idea is to use a hashmap to group the anagrams together. 
// We can use the frequency of characters in the string as the key for the hashmap.
// For each string, we convert it to a character array and count the frequency of each character.
// We then convert the frequency array back to a string and use it as the key for the hashmap.
// We then add the original string to the list of anagrams for that key.
// Time Complexity is O(M * N) where M is the length of array of strs and N is the length of max string