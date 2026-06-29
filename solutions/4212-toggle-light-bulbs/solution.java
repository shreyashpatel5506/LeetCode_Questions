class Solution {
    public List<Integer> toggleLightBulbs(List<Integer> bulbs) {
        Set<Integer> onBulbs = new TreeSet<>();
          for (int bulb : bulbs) { 
            if (onBulbs.contains(bulb)) { 
                onBulbs.remove(bulb); 
            } else { 
                onBulbs.add(bulb); 
            } 
        } 
        
        return new ArrayList<>(onBulbs); 
    }
}
