//Engineering digest
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List> map = new HashMap<>();
        for(String s : strs){ //tea
            int[] count = new int[26];
            for(char c : s.toCharArray()) {
                count[ c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i : count){
                sb.append("#");
                sb.append(i);
            }
            String key = sb.toString();  // hash1
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<String>());  // { hash1 : ["ate"]}
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}
