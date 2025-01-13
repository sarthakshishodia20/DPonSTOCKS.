class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // index denotes arr length
        // buy denotes possibilities to check sell and buy or ye do hi ho skte hai 1 means buy 0 means sell isliye iske liye 2 size ki dp array aai hai
        int[][] dp = new int[n][2];
        // initialisation of dp with -1;
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        // abhi ke liye buy kr skte hai isliye 1 aaya hai
        return func(0, 1, prices, dp);
    }

    public static int func(int index, int buy, int[] prices, int[][] dp) {
        if (index == prices.length) {
            // agr array khtm ho jata hai tb chahe buy hi kyu na kr rkha ho vahan se koi bhi profit nahi aaega nikal kr isliye 0 return krenge isme
            return 0;
        }
        if (dp[index][buy] != -1) {
            // agr phle se hai toh return krdo
            return dp[index][buy];
        }
        int profit = 0;
        if (buy == 1) {
            // choice hai mere paas buy krne ki ya na krne ki

            // agr m buy krta hun toh aage chlkr sell hi krna pdega or sell krne ke liye buy nahi kr skta isliye buy k liye 0 aaya hai
            int iWillBuy = -prices[index] + func(index + 1, 0, prices, dp);
            // agr m abhi buy nahi krta toh aage chlkr krna pdega isliye 1 aaya hai means m buy kr skta hun in future
            int iWillNotBuy = func(index + 1, 1, prices, dp);
            // dono mein se jo optimal max ho vo aaega
            profit = Math.max(iWillBuy, iWillNotBuy);
        } else {
            // agr mne sell krna hai toh uska profit add krlo or aage future use ke liye buy ko allowed krdo
            int iWillSell = prices[index] + func(index + 1, 1, prices, dp);
            // agr abhi nahi krna sell toh future mein kr skte hai but buy ko abhi allow mt kro kyuki isko phle sell krna hi pdega
            int iWillNotSell = func(index + 1, 0, prices, dp);
            // dono ka maximum
            profit = Math.max(iWillSell, iWillNotSell);
        }
        dp[index][buy] = profit;
        return profit;
    }
}
