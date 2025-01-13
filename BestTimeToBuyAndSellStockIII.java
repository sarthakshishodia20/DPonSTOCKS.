class Solution {
    // Same Exact Copy of Part II
    public int maxProfit(int[] prices) {
        // prices array ki length
        int n=prices.length;
        // 3 paramter are changing isliye 3d dp bnaenge 
        int[][][] dp=new int[n][2][3];
        // initialisation of dp with -1;
        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        // return kro index=0 buy=1 prices dp and capacity=2 allowed according to question
        return func(0,1,prices,dp,2);
    }
    public static int func(int index,int buy,int[] prices,int[][][] dp,int capacity){
        // ya toh prices khtm ya capacity khtm tb return krdo 0 profit ki ab iske baad kch nah ho skta
        if(index==prices.length || capacity==0){
            return 0;
        }
        // agr overlapping problem hai toh return kro
        if(dp[index][buy][capacity]!=-1){
            return dp[index][buy][capacity];
        }
        int profit=0;
        if(buy==1){
            // i am buying right now and in future case ke liye buy ke liye 0 bhej rha or capacity - isliye nahi ki kyuki jb tk sell nahi hoga ek transaction complete nahi mana jaega
            int iWillBuy=-prices[index]+func(index+1,0,prices,dp,capacity);
            // i am not buying right now thus in future case buy ke liye parameter 1 bheja hai func ke andr
            int iWillNotBuy=0+func(index+1,1,prices,dp,capacity);
            // dono ka maximum
            profit=Math.max(iWillBuy,iWillNotBuy);
        }
        else{
            // i am selling right now and ek transaction complete hua tha capacity mein se 1 minus hua and in future case buy kr skte hai isliye 1  kia hai buy ki value ko
            int iWillSell=prices[index]+func(index+1,1,prices,dp,capacity-1);
            // i am not selling right now thun in future case buy nahi kr skte isliye buy ka value 0 or capacity abhi bhi utni hi rhengi kyuki abhi tk sell hua hi nahi hai
            int iWillNotSell=func(index+1,0,prices,dp,capacity);
            // dono cases ka maximum aaega
            profit=Math.max(iWillNotSell,iWillSell);
        }
        dp[index][buy][capacity]=profit;
        return dp[index][buy][capacity];
    }
}
