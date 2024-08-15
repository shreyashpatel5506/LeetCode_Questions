bool lemonadeChange(int* bills, int billsSize) {
    int five = 0;
    int ten = 0;
    
    for(int i = 0; i < billsSize; i++){
        if(bills[i] == 5){
            five++;
        } else if(bills[i] == 10){
            if(five < 1){ // We need a 5-dollar bill for a 10-dollar bill, return false if not available
                return false;
            } else {
                five--; // Decrement the number of 5-dollar bills
                ten++;  // Increment the number of 10-dollar bills
            }
        } else if(bills[i] == 20){ // For a 20-dollar bill
            if(ten > 0 && five > 0){ // Case 1: Use one 10-dollar bill and one 5-dollar bill
                ten--;  // Decrement the number of 10-dollar bills
                five--; // Decrement the number of 5-dollar bills
            } else if(five >= 3){ // Case 2: Use three 5-dollar bills
                five -= 3; // Decrement the number of 5-dollar bills by 3
            } else {
                return false; // If neither condition is met, return false
            }
        }
    }
    
    return true; // Return true if all transactions are possible
}

