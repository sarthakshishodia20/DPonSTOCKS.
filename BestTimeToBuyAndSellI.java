class Solution {
    public int maxProfit(int[] prices) {
        int buyprice=Integer.MAX_VALUE;
        int max=0;
        for(int i=0;i<prices.length;i++){
            if(buyprice<prices[i]){
                int profit=prices[i]-buyprice;
                max=Math.max(profit,max);
            }
            else{
                buyprice=prices[i];            }
        }
        return max;
    }
}
