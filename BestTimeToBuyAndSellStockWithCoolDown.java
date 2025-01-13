class Solution {
    public int maxProfit(int[] prices) {
        // same as buy and sell stock part II
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            dp[i][0] = -1;
            dp[i][1] = -1;
        }
        return func(0, 1, prices, dp);
    }
    public int func(int index, int buy, int[] prices, int[][] dp) {
        // index >= isliye aaya kyuki +2 ki call lg rhi cooldown pr isliye out of bound error aa skta hai 
        if (index >= prices.length) {
            return 0;
        }
        // agr overlapping hai toh return krdo
        if (dp[index][buy] != -1) {
            return dp[index][buy];
        }
        int profit;
        if (buy == 1) {
            // i will buy this and for future use i will put buy to 0 as i can not buy before selling it
            int iWillBuy = -prices[index] + func(index + 1, 0, prices, dp);
            // i will not buy it but for future use i can put buy as 1 because in future i will buy it
            int iWillNotBuy = func(index + 1, 1, prices, dp);
            // dono ka maxprofit
            profit = Math.max(iWillBuy, iWillNotBuy);
        } else {
            // abhi sell kr rha and cooldown means next index pr break le rha isliye index+2 aaya hai 
            int iWillSell = prices[index] + func(index + 2, 1, prices, dp);
            // abhi sell nahi kr rha shyd agle index pr sell krun
            int iWillNotSell = func(index + 1, 0, prices, dp);
            // dono ka max profit
            profit = Math.max(iWillSell, iWillNotSell);
        }
        // store krke return krdo profit ko
        dp[index][buy] = profit;
        return dp[index][buy];
    }
}
