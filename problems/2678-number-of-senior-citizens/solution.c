int countSeniors(char ** details, int detailsSize){
    int count=0;
     for(int i=0;i<detailsSize;i++){
        if(details[i][11]>='6' && details[i][12]>='0'){
            if(details[i][11]=='6' && details[i][12]=='0'){
                continue;
            }
            count++;
        }
   }
   return count;
}