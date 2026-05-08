class Bank {
    long[] balance;
    public Bank(long[] balance) {
        this.balance = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) {
        if (validateAccount(account1) && 
            validateAccount(account2) && 
            hasEnoughBalance(account1, money)) {

            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }

        return false;
    }
    
    public boolean deposit(int account, long money) {
        if (validateAccount(account)) {
            balance[account - 1] += money;
            return true;
        }

        return false;  
    }
    
    public boolean withdraw(int account, long money) {
        if (validateAccount(account) && 
            hasEnoughBalance(account, money)) {
                balance[account - 1] -= money;
                return true;
        }

        return false;     
    }

    private boolean validateAccount(int account) {
        if (account > balance.length) {
            return false;
        }

        return true;
    }

    private boolean hasEnoughBalance(int account, long money) {
        long currbalance = balance[account - 1];
        if (currbalance >= money) {
            return true;
        }

        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */

// Leetcode: 2043. Simple Bank System
// Time Complexity: O(1) for each operation (transfer, deposit, withdraw)
// Space Complexity: O(n) where n is the number of accounts (size of the balance array)