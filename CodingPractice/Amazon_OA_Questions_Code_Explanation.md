# Amazon OA Questions, Approaches & Code (Java)

> This file contains the coding questions we discussed, the core idea
> behind each solution, and a Java template.

------------------------------------------------------------------------

# 1. Car Pooling (LeetCode 1094)

## Pattern

-   Difference Array
-   Prefix Sum
-   Sweep Line

## Idea

Instead of simulating every passenger, record only where passengers
enter and leave.

    diff[start] += passengers
    diff[end]   -= passengers

Taking the prefix sum reconstructs the number of passengers at every
point.

## Code

``` java
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] diff = new int[1002];

        for (int[] trip : trips) {
            diff[trip[1]] += trip[0];
            diff[trip[2]] -= trip[0];
        }

        int current = 0;

        for (int x : diff) {
            current += x;
            if (current > capacity)
                return false;
        }

        return true;
    }
}
```

Complexity

-   Time: O(N + MaxLocation)
-   Space: O(MaxLocation)

------------------------------------------------------------------------

# 2. Sliding Window Maximum (LeetCode 239)

## Pattern

Monotonic Deque

## Idea

Maintain indices in decreasing order of values.

-   Remove expired indices.
-   Remove smaller elements from the back.
-   Front always contains the maximum.

## Code

``` java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {

            while (!dq.isEmpty() && dq.peekFirst() <= i - k)
                dq.pollFirst();

            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i])
                dq.pollLast();

            dq.offerLast(i);

            if (i >= k - 1)
                ans[i - k + 1] = nums[dq.peekFirst()];
        }

        return ans;
    }
}
```

Complexity

-   Time: O(N)
-   Space: O(K)

------------------------------------------------------------------------

# 3. Top K Frequent Elements

## Pattern

HashMap + Bucket Sort

## Idea

Count frequencies, bucket numbers by frequency, then iterate buckets
backwards.

## Code

``` java
class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> freq = new HashMap<>();

        for(int n : nums)
            freq.put(n, freq.getOrDefault(n,0)+1);

        List<Integer>[] bucket = new ArrayList[nums.length+1];

        for(int key : freq.keySet()) {
            int f = freq.get(key);

            if(bucket[f]==null)
                bucket[f]=new ArrayList<>();

            bucket[f].add(key);
        }

        int[] ans = new int[k];
        int idx=0;

        for(int i=bucket.length-1;i>=0 && idx<k;i--){
            if(bucket[i]==null) continue;

            for(int val : bucket[i]){
                ans[idx++]=val;
                if(idx==k) break;
            }
        }

        return ans;
    }
}
```

Complexity

-   Time: O(N)
-   Space: O(N)

------------------------------------------------------------------------

# 4. Sum of Total Strength of Wizards

## Pattern

-   Monotonic Stack
-   Prefix Sum
-   Prefix of Prefix

## Idea

Each element contributes as the minimum exactly once.

Steps

1.  Prefix Sum
2.  Prefix of Prefix
3.  Previous Smaller
4.  Next Smaller
5.  Contribution formula

## Code

```
class Solution {
    private static final int MOD = 1_000_000_007;

    public int totalStrength(int[] strength) {
        int n = strength.length;

        // -------------------------------
        // Phase 1: Build prefix-of-prefix
        // -------------------------------

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = (prefix[i] + strength[i]) % MOD;
        }

        long[] prefixOfPrefix = new long[n + 2];
        for (int i = 0; i <= n; i++) {
            prefixOfPrefix[i + 1] = (prefixOfPrefix[i] + prefix[i]) % MOD;
        }

        // -------------------------------------------
        // Phase 2: Previous Smaller & Next Smaller
        // -------------------------------------------

        int[] previousSmaller = new int[n];
        int[] nextSmaller = new int[n];

        Arrays.fill(previousSmaller, -1);
        Arrays.fill(nextSmaller, n);

        Stack<Integer> stack = new Stack<>();

        // Previous Strictly Smaller
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() &&
                    strength[stack.peek()] >= strength[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                previousSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        stack.clear();

        // Next Smaller OR Equal
        // (Tie-breaking avoids double counting)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() &&
                    strength[stack.peek()] > strength[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                nextSmaller[i] = stack.peek();
            }

            stack.push(i);
        }

        // ---------------------------------------
        // Phase 3: Calculate contribution
        // ---------------------------------------

        long answer = 0;

        for (int i = 0; i < n; i++) {

            int left = previousSmaller[i];
            int right = nextSmaller[i];

            long leftChoices = i - left;
            long rightChoices = right - i;

            /*
             * Sum of all prefix sums on the right
             */
            long rightContribution =
                    (prefixOfPrefix[right + 1] - prefixOfPrefix[i + 1] + MOD) % MOD;

            /*
             * Sum of all prefix sums on the left
             */
            long leftContribution =
                    (prefixOfPrefix[i + 1] - prefixOfPrefix[left + 1] + MOD) % MOD;

            /*
             * Total of all subarray sums
             * where strength[i] is the minimum.
             */
            long totalSubarraySum =
                    (rightContribution * leftChoices
                    - leftContribution * rightChoices) % MOD;

            answer = (answer +
                    totalSubarraySum * strength[i]) % MOD;
        }

        return (int) ((answer + MOD) % MOD);
    }
}
```

Overall complexity

-   O(N)

------------------------------------------------------------------------

# 5. Maximum Coins from Segments

## Pattern

-   Difference Array
-   Coordinate Compression
-   Sweep Line
-   Sliding Window

## Idea

Never expand every coordinate.

1.  Record events.
2.  Sort coordinates.
3.  Compute prefix values.
4.  Slide a window of size K.

Useful when coordinates are very large.

## Code

```
public static long maxMoney(int k, int[][] segments) {

    int maxIndex = 0;

    for (int[] s : segments)
        maxIndex = Math.max(maxIndex, s[1]);

    long[] diff = new long[maxIndex + 2];

    for (int[] s : segments) {

        diff[s[0]] += s[2];

        diff[s[1] + 1] -= s[2];
    }

    long[] bags = new long[maxIndex + 1];

    long running = 0;

    for (int i = 1; i <= maxIndex; i++) {
        running += diff[i];
        bags[i] = running;
    }

    long window = 0;

    for (int i = 1; i <= Math.min(k, maxIndex); i++)
        window += bags[i];

    long answer = window;

    for (int i = k + 1; i <= maxIndex; i++) {

        window += bags[i];
        window -= bags[i - k];

        answer = Math.max(answer, window);
    }

    return answer;
}
```

------------------------------------------------------------------------

# 6. Category Hierarchy Design

## Pattern

-   Composite Pattern
-   Strategy Pattern

## Core Classes

``` text
Category
 ├── children
 └── products

Product

Filter
 ├── NameFilter
 ├── BrandFilter
 └── PriceFilter

CategoryService
```

## Code

### Product

```
public class Product {

    private String id;
    private String name;
    private String brand;
    private double price;

    public Product(String id,
                   String name,
                   String brand,
                   double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }
}
```

### Category

```
import java.util.*;

public class Category {

    private String id;
    private String name;

    private List<Category> subCategories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addSubCategory(Category category) {
        subCategories.add(category);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getName() {
        return name;
    }
}
```

### Specification Interface

```
public interface ProductFilter {
    boolean matches(Product product);
}
```

### Name Filter

```
public class NameFilter implements ProductFilter {

    private String keyword;

    public NameFilter(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    @Override
    public boolean matches(Product product) {
        return product.getName()
                .toLowerCase()
                .contains(keyword);
    }
}
```

### Brand Filter

```
public class BrandFilter implements ProductFilter {

    private String brand;

    public BrandFilter(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean matches(Product product) {
        return product.getBrand()
                .equalsIgnoreCase(brand);
    }
}
```

### Price Range Filter

```
public class PriceRangeFilter implements ProductFilter {

    private double min;
    private double max;

    public PriceRangeFilter(double min,
                            double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean matches(Product product) {
        return product.getPrice() >= min
                && product.getPrice() <= max;
    }
}
```

### AND Filter

Allows combining filters.

```
import java.util.*;

public class AndFilter implements ProductFilter {

    private List<ProductFilter> filters;

    public AndFilter(List<ProductFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(Product product) {

        for(ProductFilter filter : filters) {
            if(!filter.matches(product)) {
                return false;
            }
        }

        return true;
    }
}
```

### OR Filter

```
import java.util.*;

public class OrFilter implements ProductFilter {

    private List<ProductFilter> filters;

    public OrFilter(List<ProductFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean matches(Product product) {

        for(ProductFilter filter : filters) {
            if(filter.matches(product)) {
                return true;
            }
        }

        return false;
    }
}
```

### Product Search Service

DFS traversal of category hierarchy.

```
import java.util.*;

public class ProductSearchService {

    public List<Product> getProducts(
            Category category,
            ProductFilter filter) {

        List<Product> result = new ArrayList<>();

        dfs(category, filter, result);

        return result;
    }

    private void dfs(Category category,
                     ProductFilter filter,
                     List<Product> result) {

        for(Product product : category.getProducts()) {

            if(filter == null ||
               filter.matches(product)) {

                result.add(product);
            }
        }

        for(Category child : category.getSubCategories()) {
            dfs(child, filter, result);
        }
    }
}
```

### Usage

```
Category electronics = new Category("1", "Electronics");

Category mobiles = new Category("2", "Mobiles");
Category laptops = new Category("3", "Laptops");

electronics.addSubCategory(mobiles);
electronics.addSubCategory(laptops);

mobiles.addProduct(
    new Product("P1",
                "iPhone 16",
                "Apple",
                1200));

mobiles.addProduct(
    new Product("P2",
                "Galaxy S25",
                "Samsung",
                1000));

ProductFilter filter =
    new AndFilter(
        List.of(
            new BrandFilter("Apple"),
            new PriceRangeFilter(1000, 1500)
        ));

ProductSearchService service =
        new ProductSearchService();

List<Product> products =
        service.getProducts(electronics, filter);

```

Why this design is extensible

Suppose tomorrow the interviewer asks:
```
Rating filter
Seller filter
Color filter
Inventory filter
Discount filter
```

You simply add:
```
class RatingFilter implements ProductFilter
class ColorFilter implements ProductFilter
class SellerFilter implements ProductFilter
```

Reason

Adding new filters never changes existing code.

------------------------------------------------------------------------

# General Interview Tips

## Difference Array

Use when updates affect ranges.

## Prefix Sum

Use for repeated range sum queries.

## Monotonic Stack

Use for previous/next greater or smaller element problems.

## Sliding Window

Use for contiguous subarray problems.

## Sweep Line

Convert intervals into events.

## Bucket Sort

Prefer when frequency \<= N.

## Common Amazon Follow-up Questions

-   Can you reduce the memory?
-   Can you make it O(N)?
-   What happens for duplicate values?
-   How would this work in a distributed system?
-   How would you test this?
-   What are the edge cases?
