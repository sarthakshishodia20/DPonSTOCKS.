class Solution {
    // Variation of best time to buy and sell part II
    public int maxProfit(int[] prices, int fee) {
        int n=prices.length;
        int[][] dp=new int[n][2];
        for(int i=0;i<n;i++){
            dp[i][0]=-1;
            dp[i][1]=-1;
        }
        return func(0,1,prices,dp,fee);
    }
    public static int func(int index,int buy,int[] prices,int[][] dp,int fee){
        // if prices khtm toh profit 0
        if(index==prices.length){
            return 0;
        }
        // if overlapping subproblem return it
        if(dp[index][buy]!=-1){
            return dp[index][buy];
        }
        int profit;
        if(buy==1){
            // if i am buying i will not able to buy in future and thus buy=0 now 
            int iWillBuy=-prices[index]+func(index+1,0,prices,dp,fee);
            // if i have not buy till now in future i will buy it thus buy=1;
            int iWillNotBuy=func(index+1,1,prices,dp,fee);
            // maximum from both of them
            profit=Math.max(iWillBuy,iWillNotBuy);
        }
        else{
            // if i am selling right now a nominal charge or fee is implied to me and i am not able to neglect this thus i have to minus this fee from the entrie sell after each transaction.
            int iWillSell=+prices[index]-fee+func(index+1,1,prices,dp,fee);
            // if i am not selling right now then i have to sell it in future thus buy will remain 0
            int iWillNotSell=func(index+1,0,prices,dp,fee);
            // maximum of both above profits
            profit=Math.max(iWillSell,iWillNotSell);
        }
        // store and return max Profit for all
        dp[index][buy]=profit;
        // return ans
        return dp[index][buy];
    }
}
