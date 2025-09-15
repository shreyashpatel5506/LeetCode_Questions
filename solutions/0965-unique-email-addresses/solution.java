class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> unique = new HashSet<>();
        for(String email : emails){
           String[] parts = email.split("@");
           String local = parts[0];
           String domain = parts[1];

           int plusindex = local.indexOf("+");
           if(plusindex != -1){
           local = local.substring(0, plusindex);

           }
        local = local.replace(".", "");

          String normalized = local + "@" + domain;

            unique.add(normalized);
        }
        return unique.size();
    }
}
