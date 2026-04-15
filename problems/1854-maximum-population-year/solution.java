class Solution {
    public int maximumPopulation(int[][] logs) {
        HashMap<Integer, Integer> population = new HashMap<>();
       for(int[] people : logs){
        int birthyear = people[0];
        int deathyear = people[1];
        for(int year = birthyear; year < deathyear; year++){
        population.put(year, population.getOrDefault(year, 0) + 1);
        }
      
       }

       int maxPopluation =0 ; 
       int earlyyear = 1950;
       for(int i = 1950 ; i < 2050 ; i++){
        int currentPopulation = population.getOrDefault(i, 0);
        if (currentPopulation > maxPopluation) {
                maxPopluation = currentPopulation;
                earlyyear = i;
            }
       } 
       return earlyyear;
    }
}