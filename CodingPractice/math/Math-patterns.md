# LeetCode Math Patterns - Java Boilerplate

## 1. Divisors Up To Square Root

Use when finding factors, divisor count, or divisor sum.

```java
int count = 0;
int sum = 0;

for (int d = 1; d <= num / d; d++) {
    if (num % d == 0) {
        int other = num / d;

        if (d == other) {
            count++;
            sum += d;
        } else {
            count += 2;
            sum += d + other;
        }
    }
}
```

Key idea:

```text
Divisors come in pairs:
d and num / d
```

## 2. Prime Check

```java
private boolean isPrime(int n) {
    if (n < 2) {
        return false;
    }

    for (int d = 2; d <= n / d; d++) {
        if (n % d == 0) {
            return false;
        }
    }

    return true;
}
```

## 3. Sieve Of Eratosthenes

Use when checking primes up to `n`.

```java
boolean[] isPrime = new boolean[n + 1];
Arrays.fill(isPrime, true);

if (n >= 0) {
    isPrime[0] = false;
}

if (n >= 1) {
    isPrime[1] = false;
}

for (int p = 2; p <= n / p; p++) {
    if (isPrime[p]) {
        for (int multiple = p * p; multiple <= n; multiple += p) {
            isPrime[multiple] = false;
        }
    }
}
```

## 4. GCD

Use Euclid's algorithm.

```java
private int gcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }

    return Math.abs(a);
}
```

## 5. LCM

Use GCD to avoid unnecessary overflow.

```java
private long lcm(long a, long b) {
    return a / gcd((int) a, (int) b) * b;
}
```

Safer long GCD:

```java
private long gcdLong(long a, long b) {
    while (b != 0) {
        long temp = a % b;
        a = b;
        b = temp;
    }

    return Math.abs(a);
}
```

## 6. Modular Arithmetic

Use when answers can be large.

```java
long MOD = 1_000_000_007L;

long add = (a + b) % MOD;
long sub = (a - b + MOD) % MOD;
long mul = (a * b) % MOD;
```

## 7. Fast Power

Use binary exponentiation.

```java
private long pow(long base, long exp, long mod) {
    long result = 1;
    base %= mod;

    while (exp > 0) {
        if ((exp & 1) == 1) {
            result = (result * base) % mod;
        }

        base = (base * base) % mod;
        exp >>= 1;
    }

    return result;
}
```

## 8. Digit Processing

Use when working with individual decimal digits.

```java
while (num > 0) {
    int digit = num % 10;
    num /= 10;
}
```

Reverse number:

```java
int rev = 0;

while (num > 0) {
    rev = rev * 10 + num % 10;
    num /= 10;
}
```

## 9. Count Digits

```java
private int countDigits(int num) {
    if (num == 0) {
        return 1;
    }

    int count = 0;

    while (num != 0) {
        count++;
        num /= 10;
    }

    return count;
}
```

## 10. Palindrome Number

Reverse half the number.

```java
private boolean isPalindrome(int x) {
    if (x < 0 || (x % 10 == 0 && x != 0)) {
        return false;
    }

    int reversedHalf = 0;

    while (x > reversedHalf) {
        reversedHalf = reversedHalf * 10 + x % 10;
        x /= 10;
    }

    return x == reversedHalf || x == reversedHalf / 10;
}
```

## 11. Overflow-Safe Integer Math

Use checks before multiplying or adding.

```java
if (num > Integer.MAX_VALUE / 10) {
    // would overflow if multiplied by 10
}
```

For reverse integer:

```java
if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
    return 0;
}
```

## 12. Combinations / Binomial Coefficient

Use when counting ways to choose `k` from `n`.

```java
private long nCk(int n, int k) {
    if (k > n - k) {
        k = n - k;
    }

    long res = 1;

    for (int i = 1; i <= k; i++) {
        res = res * (n - k + i) / i;
    }

    return res;
}
```

## 13. Pascal's Triangle

```java
List<List<Integer>> res = new ArrayList<>();

for (int i = 0; i < numRows; i++) {
    List<Integer> row = new ArrayList<>();

    for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
            row.add(1);
        } else {
            row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
        }
    }

    res.add(row);
}
```

## 14. Arithmetic Sequence

Use formulas instead of loops when possible.

```java
long sumOneToN = (long) n * (n + 1) / 2;
```

General arithmetic sum:

```java
long sum = (long) count * (first + last) / 2;
```

## 15. Geometric Growth / Doubling

Use when values double or grow exponentially.

```java
while (value < target) {
    value *= 2;
    steps++;
}
```

## 16. Coordinate Geometry

Distance squared avoids floating-point precision.

```java
private int distSq(int[] p) {
    return p[0] * p[0] + p[1] * p[1];
}
```

Slope comparison without division:

```java
// dy1 / dx1 == dy2 / dx2
dy1 * dx2 == dy2 * dx1
```

## 17. Random / Reservoir Sampling

Use when choosing uniformly from a stream.

```java
Random rand = new Random();
int chosen = -1;
int count = 0;

for (int x : nums) {
    count++;

    if (rand.nextInt(count) == 0) {
        chosen = x;
    }
}
```

## 18. Matrix Math

Use matrix multiplication for linear transitions.

```java
private long[][] multiply(long[][] a, long[][] b) {
    int n = a.length;
    long[][] res = new long[n][n];

    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < n; j++) {
                res[i][j] += a[i][k] * b[k][j];
            }
        }
    }

    return res;
}
```

## 19. Pigeonhole Principle

Use when constraints force duplicates or collisions.

```text
If n + 1 numbers are placed into n buckets,
at least one bucket has more than one number.
```

Common clue:

```text
array length n + 1
values from 1 to n
find duplicate
```

## 20. Inclusion / Exclusion

Use when counts overlap.

```text
count(A or B) = count(A) + count(B) - count(A and B)
```

For multiples:

```java
long countDivisibleByAorB = n / a + n / b - n / lcm(a, b);
```

# Mental Shortcuts

## Factors / Divisors

Think:

```text
loop to sqrt(n), count divisor pairs
```

## Prime Questions

Think:

```text
trial division / sieve
```

## Huge Powers

Think:

```text
fast exponentiation + modulo
```

## Digits

Think:

```text
num % 10 and num / 10
```

## Counting Ways

Think:

```text
combinations / Pascal / DP
```

## Geometry

Think:

```text
avoid floating point; compare squared distances or cross products
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Divisors Up To Square Root | [1390. Four Divisors](https://leetcode.com/problems/four-divisors/) |
| 2 | Prime Check | [204. Count Primes](https://leetcode.com/problems/count-primes/) |
| 3 | Sieve Of Eratosthenes | [204. Count Primes](https://leetcode.com/problems/count-primes/) |
| 4 | GCD | [1979. Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) |
| 5 | LCM | [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/) |
| 6 | Modular Arithmetic | [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) |
| 7 | Fast Power | [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) |
| 8 | Digit Processing | [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/) |
| 9 | Count Digits | [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) |
| 10 | Palindrome Number | [9. Palindrome Number](https://leetcode.com/problems/palindrome-number/) |
| 11 | Overflow-Safe Integer Math | [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/) |
| 12 | Combinations | [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) |
| 13 | Pascal's Triangle | [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) |
| 14 | Arithmetic Sequence | [268. Missing Number](https://leetcode.com/problems/missing-number/) |
| 15 | Geometric Growth / Doubling | [69. Sqrt(x)](https://leetcode.com/problems/sqrtx/) |
| 16 | Coordinate Geometry | [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) |
| 17 | Reservoir Sampling | [398. Random Pick Index](https://leetcode.com/problems/random-pick-index/) |
| 18 | Matrix Math | [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/) |
| 19 | Pigeonhole Principle | [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) |
| 20 | Inclusion / Exclusion | [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/) |

# Recommended Study Order

```text
8, 9, 10, 1, 2, 3, 4, 7, 6, 14, 12, 13, 16, 19, 20
```

Expanded:

| Order | Pattern | Practice Problem |
|---|---|---|
| 8 | [Digit Processing](#8-digit-processing) | [7. Reverse Integer](https://leetcode.com/problems/reverse-integer/) |
| 9 | [Count Digits](#9-count-digits) | [1295. Find Numbers with Even Number of Digits](https://leetcode.com/problems/find-numbers-with-even-number-of-digits/) |
| 10 | [Palindrome Number](#10-palindrome-number) | [9. Palindrome Number](https://leetcode.com/problems/palindrome-number/) |
| 1 | [Divisors Up To Square Root](#1-divisors-up-to-square-root) | [1390. Four Divisors](https://leetcode.com/problems/four-divisors/) |
| 2 | [Prime Check](#2-prime-check) | [204. Count Primes](https://leetcode.com/problems/count-primes/) |
| 3 | [Sieve Of Eratosthenes](#3-sieve-of-eratosthenes) | [204. Count Primes](https://leetcode.com/problems/count-primes/) |
| 4 | [GCD](#4-gcd) | [1979. Find Greatest Common Divisor of Array](https://leetcode.com/problems/find-greatest-common-divisor-of-array/) |
| 7 | [Fast Power](#7-fast-power) | [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) |
| 6 | [Modular Arithmetic](#6-modular-arithmetic) | [50. Pow(x, n)](https://leetcode.com/problems/powx-n/) |
| 14 | [Arithmetic Sequence](#14-arithmetic-sequence) | [268. Missing Number](https://leetcode.com/problems/missing-number/) |
| 12 | [Combinations](#12-combinations--binomial-coefficient) | [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) |
| 13 | [Pascal's Triangle](#13-pascals-triangle) | [118. Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/) |
| 16 | [Coordinate Geometry](#16-coordinate-geometry) | [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/) |
| 19 | [Pigeonhole Principle](#19-pigeonhole-principle) | [287. Find the Duplicate Number](https://leetcode.com/problems/find-the-duplicate-number/) |
| 20 | [Inclusion / Exclusion](#20-inclusion--exclusion) | [1201. Ugly Number III](https://leetcode.com/problems/ugly-number-iii/) |
