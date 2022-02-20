package edu.gqq.ayuan.week4;

/**
 * File Name: Stock1.java Stock1 concrete class
 *
 *
 * To Compile: IntUtil.java RandomInt.java Stock1.java Stock1Base.java
 *
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Stock1 extends Stock1Base {

    //You can have any number of private data members here
    //You can have any number of private functions here
    Stock1() {
        //NOTHING CAN BE CHANGED HERE
        testBed();
    }

    @Override
    void NSquareTimeConstantSpace() {
        //NOTHING CAN BE CHANGED HERE
        nsquareTimeConstantSpace();
    }

    @Override
    void NlognTimeLognSpace() {
        //NOTHING CAN BE CHANGED HERE
        nlognTimelognSpace();
    }

//    @Override
//    void NTimeLognSpace() {
//        //NOTHING CAN BE CHANGED HERE
//        nTimelognSpace();
//    }

    @Override
    void NTimeLognSpace() {
        //NOTHING CAN BE CHANGED HERE
        nTimelognSpace();
    }

    /*
     * Time: O(n^2)
     * Space: THETA(1)
     * All your routine should match this answer
     * Nothing can be changed in this routine
     */
    private void nsquareTimeConstantSpace() {
        int gp = 0;
        int l = size();
        for (int buy = 0; buy < l - 1; ++buy) {
            for (int sell = buy + 1; sell < l; ++sell) {
                ++numConquer;
                int p = profit(sell, buy);
                if (p >= gp) { //So that I can make profit by keeping stock for less time
                    gp = p;
                    buyDay = buy;
                    sellDay = sell;
                }
            }
        }
    }

    /*
     * Time: O(nlogn)
     * Space: O(logn)
     */
    private void nlognTimelognSpace() {
        //must write the routine. UNTIL YOU WRITE UNCOMMENT BELOW
//        nsquareTimeConstantSpace();
    }

    /*
     * Time: O(n)
     * Space: O(logn)
     */
    private void nTimelognSpace() {
        //must write the routine. UNTIL YOU WRITE UNCOMMENT BELOW
        //nsquareTimeConstantSpace();
    }

    /*
     * Time: O(n)
     * Space: O(1)
     */
    public void NTimeConstantSpace() {
        //IF YOU CANNOT WRITE  USE THE ROUTINE BELOW
        //nsquareTimeConstantSpace();
    }

    public static void main(String[] args) {
        //NOTHING CAN BE CHANGED HERE
        System.out.println("Stock1 problem STARTS");
        Stock1 m = new Stock1();
        System.out.println("All Stock1 tests passed. Now you can pass interviews");
        System.out.println("Stock1 problem ENDS");
    }
}
