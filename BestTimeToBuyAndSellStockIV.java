class Solution {
    // Same as Part III bs isme capacity mein 3 ki jagah k aa gya hai vrna ek dm same hai
    public int maxProfit(int k, int[] prices) {
        int n=prices.length;
        // now n means prices length 2 means 0 or 1 for buy and capacity is k+1 obviously means [k+1] for 0 based indexing
        int[][][] dp=new int[n][2][k+1];
        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++)
            {
                // Initialisation with -1
                Arrays.fill(dp[i][j],-1);
            }
        }
        return func(0,1,prices,dp,k);
    }
    public static int func(int index,int buy,int[] prices,int[][][] dp,int capacity){
        // agr capacity 0 ho jaye ya prices khtm ho jaye toh profit 0 hi bnega
        if(index==prices.length|| capacity==0){
            return 0;
        }
        // agr overlapping subproblem hai toh return kro yahin se
        if(dp[index][buy][capacity]!=-1){
            return dp[index][buy][capacity];
        }
        int profit;
        if(buy==1){
            // agr buy kr skte hai toh buy krunga or future use ke liye 0 ho jaega buy because sell se phle nahi kr skte dusra buy or capacity abhi isliye minus nahi hui because transaction complete selling pr hota hai
            int iWillBuy=-prices[index]+func(index+1,0,prices,dp,capacity);
            // agr buy nahi krna toh future mein krunga or buy ko 1 rehne dia for its permission in future
            int iWillNotBuy=0+func(index+1,1,prices,dp,capacity);
            // dono ka maximum aaega 
            profit=Math.max(iWillBuy,iWillNotBuy);
        }
        else{
            // agr mein sell kr skta hun toh sell krke capacity - means 1 transaction completed  and future use mein buy kr skta hun isliye buy ka value 1 hua
            int iWillSell=+prices[index]+func(index+1,1,prices,dp,capacity-1);
            // agr mein abhi sell nhi krna toh future use ke liye buy ko 0 rehne dia kyuki sell se phle buy nahi kr skte or capacity abhi bhi utni hi rhegi because transaction pura nai hua hai
            int iWillNotSell=func(index+1,0,prices,dp,capacity);
            // dono ka maximum store hoga
            profit=Math.max(iWillSell,iWillNotSell);
        }
        dp[index][buy][capacity]=profit;
        return dp[index][buy][capacity];
    }
}
