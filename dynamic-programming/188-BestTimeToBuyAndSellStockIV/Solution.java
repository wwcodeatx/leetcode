class Solution {
    public int maxProfit(int k, int[] prices) {
        int numberDays = prices.length;
        
        // Special cases
        // If we don't have any prices or we aren't allowed to make any transactions, return a profit of 0
        if (numberDays <= 0 || k <= 0) {
            return 0;
        }
        
        // A transaction = 1 buy + 1 sell
        // If we don't have enough prices for the number of transactions, we want to use all positive values
        if (2*k > numberDays) {
            int result = 0;
            for (int day = 1; day < numberDays; day++) {
                // If we can't make a profit on the buy and sell, just don't add anything
                result += Math.max(0, prices[day] - prices[day - 1]);
            }
            
            return result;
        }
        
        // Create an array of profits for the given day, transaction # used, and whether held or not
        // profits [day] [transaction # (including 0)] [holding stocks? 1 means you have it, 0 means you don't]
        int[][][] profits = new int[numberDays][k+1][2];
        
        // Initialize the array
        for (int day = 0; day < numberDays; day++) {
            for (int transaction = 0; transaction <= k; transaction++) {
                // Max value for price is 1000 so we could end up with 0-1000=-1000
                // This is lower than that
                profits[day][transaction][0] = -1001;
                profits[day][transaction][1] = -1001;
            }
        }
        
        // Set the first day's profits
        // First day, no transactions yet because we aren't buying, don't have stocks
        profits[0][0][0] = 0;
        // First day, we do have our first transaction, do have stocks
        profits[0][1][1] = -prices[0];
        
        // For each day
        for (int day = 1; day < numberDays; day++) {
            // For each transaction number
            for (int transaction = 0; transaction <= k; transaction++) {
                // Evaluate and set "not holding"
                // (1) We didn't have anything yesterday and we didn't buy anything today
                // Profit = yesterday's profit + 0
                int noHoldingNoChange = profits[day - 1][transaction][0];
                // (2) We had something yesterday and today we sold it
                int noHoldingBecauseWeSold = profits[day - 1][transaction][1] + prices[day];
                
                int maxNoHolding = Math.max(noHoldingNoChange, noHoldingBecauseWeSold);
                
                profits[day][transaction][0] = maxNoHolding;
                
                // If we've already had a transaction, we can look at holding. Otherwise, it would be impossible
                // to hold a stock without an increment in the transaction number
                if (transaction > 0) {
                    // Evaluate and set "holding"
                    // (1) We had something yesterday and did nothing
                    int holdingNoChange = profits[day - 1][transaction][1];
                    // (2) We didn't have something yesterday and today we're buying
                    int holdingBecauseBought =  profits[day - 1][transaction - 1][0] - prices[day];

                    int maxHolding = Math.max(holdingNoChange, holdingBecauseBought);

                    profits[day][transaction][1] = maxHolding;
                }
            }
                
        }
        
        // Run through the last day's profits for all transaction #s, and return the max profit
        int maxProfit = 0;
        for (int transaction = 0; transaction <= k; transaction++) {
            maxProfit = Math.max(profits[numberDays - 1][transaction][0], maxProfit);
        }
        
        return maxProfit;  
    }
}