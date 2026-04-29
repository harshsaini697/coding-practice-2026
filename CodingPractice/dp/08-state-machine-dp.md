# 08. State Machine DP

Use state machine DP when the answer depends on a mode.

Common modes:

```text
holding stock
not holding stock
cooldown
transaction count
buy/sell phase
```

## Stock With Unlimited Transactions

State:

```text
hold = max profit while holding a stock
cash = max profit while not holding a stock
```

Transition:

```text
newHold = max(hold, cash - price)
newCash = max(cash, hold + price)
```

Java:

```java
int maxProfit(int[] prices) {
    int hold = -prices[0];
    int cash = 0;

    for (int i = 1; i < prices.length; i++) {
        int price = prices[i];
        int nextHold = Math.max(hold, cash - price);
        int nextCash = Math.max(cash, hold + price);
        hold = nextHold;
        cash = nextCash;
    }

    return cash;
}
```

## With Cooldown

Modes:

```text
hold
sold today
rest
```

Transitions:

```text
newHold = max(hold, rest - price)
newSold = hold + price
newRest = max(rest, sold)
```

## With Transaction Limit

State:

```text
dp[day][transactionsUsed][holding]
```

This appears when the problem says:

```text
at most k transactions
```

## Interview Problems

- Best Time to Buy and Sell Stock II
- Best Time to Buy and Sell Stock III
- Best Time to Buy and Sell Stock IV
- Best Time to Buy and Sell Stock with Cooldown
- Best Time to Buy and Sell Stock with Transaction Fee

