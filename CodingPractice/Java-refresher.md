## Java Basics Refresher

Here's a compact Java refresher for the built-in methods you'll use most often. This guide covers essential data structures and utility classes with examples, including their outputs.

### Arrays

Arrays in Java are fixed-size containers for elements of the same type. They are objects, and their length is a property, not a method.

Import:

```java
import java.util.Arrays;
```

Common methods:

```java
int[] arr = {3, 1, 2};

System.out.println(arr.length);                     // Output: 3
Arrays.sort(arr);                                   // arr becomes [1, 2, 3]
System.out.println(Arrays.toString(arr));           // Output: "[1, 2, 3]"
int[] copied = Arrays.copyOf(arr, 5);               // Output: [1, 2, 3, 0, 0]
int[] range = Arrays.copyOfRange(arr, 0, 2);        // Output: [1, 2]
Arrays.fill(arr, 7);                                // arr becomes [7, 7, 7]
System.out.println(Arrays.equals(new int[]{1,2}, new int[]{1,2})); // Output: true
Arrays.sort(arr);                                   // arr is [1,2,3] again
System.out.println(Arrays.binarySearch(arr, 2));    // Output: 1 (index of 2)
```

For 2D arrays:

```java
int[][] grid = {{1, 2}, {3, 4}};

System.out.println(Arrays.deepToString(grid));      // Output: "[[1, 2], [3, 4]]"
System.out.println(Arrays.deepEquals(new int[][]{{1,2}}, new int[][]{{1,2}})); // Output: true
```

### Strings

Strings in Java are immutable sequences of characters. All operations that modify a string return a new string object.

```java
String s = "Hello Java";

System.out.println(s.length());             // Output: 10
System.out.println(s.charAt(0));            // Output: 'H'
System.out.println(s.substring(0, 5));      // Output: "Hello"
System.out.println(s.indexOf("Java"));      // Output: 6
System.out.println(s.lastIndexOf("a"));     // Output: 9
System.out.println(s.contains("llo"));      // Output: true
System.out.println(s.startsWith("He"));     // Output: true
System.out.println(s.endsWith("va"));       // Output: true
System.out.println(s.equals("Hello Java")); // Output: true
System.out.println(s.equalsIgnoreCase("hello java")); // Output: true
```

More useful methods:

```java
System.out.println(s.toLowerCase());        // Output: "hello java"
System.out.println(s.toUpperCase());        // Output: "HELLO JAVA"
System.out.println("  hello  ".trim());     // Output: "hello"
System.out.println("  hello  ".strip());    // Output: "hello" (Java 11+)
System.out.println(s.replace("Java", "DSA")); // Output: "Hello DSA"
System.out.println(Arrays.toString(s.split(" "))); // Output: "[Hello, Java]"
System.out.println("".isEmpty());            // Output: true
System.out.println("   ".isBlank());         // Output: true (Java 11+)
```

Conversions:

```java
System.out.println(String.valueOf(123));    // Output: "123"
System.out.println(Integer.parseInt("42")); // Output: 42
System.out.println(Arrays.toString(s.toCharArray())); // Output: "[H, e, l, l, o,  , J, a, v, a]"
```

### StringBuilder

StringBuilder is used for mutable strings, efficient for concatenations and modifications.

```java
StringBuilder sb = new StringBuilder();

sb.append("abc");                           // sb: "abc"
System.out.println(sb);                      // Output: "abc"
sb.append(123);                             // sb: "abc123"
System.out.println(sb);                      // Output: "abc123"
sb.insert(1, "X");                          // sb: "aXbc123"
System.out.println(sb);                      // Output: "aXbc123"
sb.delete(1, 2);                            // sb: "abc123" (removes 'X')
System.out.println(sb);                      // Output: "abc123"
sb.deleteCharAt(0);                         // sb: "bc123"
System.out.println(sb);                      // Output: "bc123"
sb.setCharAt(0, 'Z');                       // sb: "Zc123"
System.out.println(sb);                      // Output: "Zc123"
sb.reverse();                                // sb: "321cZ"
System.out.println(sb);                      // Output: "321cZ"
System.out.println(sb.length());             // Output: 5
System.out.println(sb.charAt(0));            // Output: '3'
System.out.println(sb.substring(0, 2));      // Output: "32"
System.out.println(sb.toString());           // Output: "321cZ"
```

Capacity-related:

```java
StringBuilder sb2 = new StringBuilder(100);
System.out.println(sb2.capacity());          // Output: 100
sb2.ensureCapacity(200);
System.out.println(sb2.capacity());          // Output: 200 (or more)
```

### ArrayList

ArrayList is a dynamic array that can grow and shrink.

Import:

```java
import java.util.ArrayList;
import java.util.Collections;
```

Create:

```java
ArrayList<Integer> list = new ArrayList<>();
ArrayList<String> names = new ArrayList<>();
```

Common methods:

```java
list.add(10);                                // list: [10]
list.add(20);                                // list: [10, 20]
list.add(1, 15);                             // list: [10, 15, 20] (insert at index 1)

System.out.println(list.get(0));             // Output: 10
list.set(0, 99);                             // list: [99, 15, 20]
System.out.println(list);                     // Output: [99, 15, 20]
list.remove(1);                               // list: [99, 20] (remove by index)
list.remove(Integer.valueOf(20));            // list: [99] (remove by value)

System.out.println(list.size());             // Output: 1
System.out.println(list.isEmpty());          // Output: false
System.out.println(list.contains(99));       // Output: true
System.out.println(list.indexOf(99));        // Output: 0
list.clear();                                // list: []
```

Loop:

```java
list.addAll(Arrays.asList(1, 2, 3));
for (int x : list) {
    System.out.print(x + " ");               // Output: 1 2 3
}
System.out.println();
```

Sorting:

```java
Collections.sort(list);                      // list: [1, 2, 3]
System.out.println(list);                     // Output: [1, 2, 3]
Collections.reverse(list);                   // list: [3, 2, 1]
System.out.println(list);                     // Output: [3, 2, 1]
System.out.println(Collections.max(list));   // Output: 3
System.out.println(Collections.min(list));   // Output: 2
```

Convert between array and list:

```java
Integer[] arr = {1, 2, 3};
ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(arr)); // list2: [1, 2, 3]
Integer[] back = list2.toArray(new Integer[0]); // back: [1, 2, 3]
System.out.println(Arrays.toString(back));   // Output: "[1, 2, 3]"
```

Note: For primitive arrays like int[], Arrays.asList(arr) does not work as expected because primitives are not objects.

### HashMap

HashMap stores key-value pairs, allowing fast lookups.

Import:

```java
import java.util.HashMap;
```

```java
HashMap<String, Integer> map = new HashMap<>();

map.put("apple", 2);                         // map: {"apple"=2}
System.out.println(map.get("apple"));        // Output: 2
System.out.println(map.getOrDefault("banana", 0)); // Output: 0
System.out.println(map.containsKey("apple")); // Output: true
System.out.println(map.containsValue(2));    // Output: true
map.remove("apple");                         // map: {}
System.out.println(map.size());              // Output: 0
System.out.println(map.isEmpty());           // Output: true
```

Loop:

```java
map.put("apple", 2);
map.put("banana", 3);
for (String key : map.keySet()) {
    System.out.println(key + " " + map.get(key)); // Output: apple 2 \n banana 3
}

for (var entry : map.entrySet()) {
    System.out.println(entry.getKey() + " " + entry.getValue()); // Output: apple 2 \n banana 3
}
```

Frequency count example:

```java
String s = "banana";
HashMap<Character, Integer> freq = new HashMap<>();
for (char c : s.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
System.out.println(freq);                    // Output: {a=3, b=1, n=2}
```

### HashSet

HashSet stores unique elements, no duplicates.

Import:

```java
import java.util.HashSet;
```

```java
HashSet<Integer> set = new HashSet<>();

set.add(5);                                 // set: [5]
System.out.println(set.contains(5));        // Output: true
set.remove(5);                              // set: []
System.out.println(set.size());             // Output: 0
System.out.println(set.isEmpty());          // Output: true
set.clear();                                // set: []
```

### Queue

Queue is a FIFO data structure.

Import:

```java
import java.util.Queue;
import java.util.LinkedList;
```

```java
Queue<Integer> q = new LinkedList<>();

q.offer(10);                                // q: [10]
q.offer(20);                                // q: [10, 20]
System.out.println(q.poll());               // Output: 10, q: [20]
System.out.println(q.peek());               // Output: 20
System.out.println(q.isEmpty());            // Output: false
```

### Stack / Deque

Deque can be used as both stack and queue. ArrayDeque is preferred over Stack.

Import:

```java
import java.util.ArrayDeque;
```

```java
ArrayDeque<Integer> stack = new ArrayDeque<>();

stack.push(10);                             // stack: [10]
stack.push(20);                             // stack: [10, 20]
System.out.println(stack.peek());           // Output: 20
System.out.println(stack.pop());            // Output: 20, stack: [10]
System.out.println(stack.isEmpty());        // Output: false
```

Deque as queue:

```java
ArrayDeque<Integer> dq = new ArrayDeque<>();

dq.offerLast(1);                            // dq: [1]
dq.offerLast(2);                            // dq: [1, 2]
System.out.println(dq.pollFirst());         // Output: 1, dq: [2]
dq.offerFirst(0);                           // dq: [0, 2]
System.out.println(dq.pollLast());          // Output: 2, dq: [0]
```

### PriorityQueue

PriorityQueue is a heap, min-heap by default.

Import:

```java
import java.util.PriorityQueue;
```

```java
PriorityQueue<Integer> pq = new PriorityQueue<>();

pq.offer(3);                                // pq: [3]
pq.offer(1);                                // pq: [1, 3]
pq.offer(2);                                // pq: [1, 3, 2]

System.out.println(pq.peek());              // Output: 1
System.out.println(pq.poll());              // Output: 1, pq: [2, 3]
```

Max-heap:

```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
maxHeap.offer(1);
maxHeap.offer(3);
maxHeap.offer(2);
System.out.println(maxHeap.poll());         // Output: 3
```

Safer comparator:

```java
PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
maxHeap2.offer(1);
maxHeap2.offer(3);
maxHeap2.offer(2);
System.out.println(maxHeap2.poll());        // Output: 3
```

### Useful Math Functions

Math class provides mathematical functions.

```java
System.out.println(Math.max(5, 10));        // Output: 10
System.out.println(Math.min(5, 10));        // Output: 5
System.out.println(Math.abs(-5));           // Output: 5
System.out.println(Math.pow(2, 3));         // Output: 8.0
System.out.println(Math.sqrt(16));          // Output: 4.0
System.out.println(Math.ceil(3.2));         // Output: 4.0
System.out.println(Math.floor(3.8));        // Output: 3.0
System.out.println(Math.round(3.6));        // Output: 4
```

### Character Functions

Character class provides utilities for characters.

```java
char c = '5';
System.out.println(Character.isDigit(c));           // Output: true
System.out.println(Character.isLetter('a'));        // Output: true
System.out.println(Character.isLetterOrDigit('a')); // Output: true
System.out.println(Character.isUpperCase('A'));     // Output: true
System.out.println(Character.isLowerCase('a'));     // Output: true
System.out.println(Character.toUpperCase('a'));     // Output: 'A'
System.out.println(Character.toLowerCase('A'));     // Output: 'a'
```

### Quick DSA Cheats

Reverse a string:

```java
String s = "hello";
String rev = new StringBuilder(s).reverse().toString();
System.out.println(rev);                    // Output: "olleh"
```

Sort a string:

```java
String s = "cba";
char[] chars = s.toCharArray();
Arrays.sort(chars);
String sorted = new String(chars);
System.out.println(sorted);                 // Output: "abc"
```

ArrayList from array:

```java
String[] arr = {"a", "b", "c"};
ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));
System.out.println(list);                   // Output: [a, b, c]
```

Sort descending:

```java
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
list.sort(Collections.reverseOrder());
System.out.println(list);                   // Output: [3, 2, 1]
```

Custom sort:

```java
ArrayList<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2));
list.sort((a, b) -> a - b);                 // ascending
System.out.println(list);                   // Output: [1, 2, 3]
list.sort((a, b) -> b - a);                 // descending
System.out.println(list);                   // Output: [3, 2, 1]
```

For safer integer sorting:

```java
list.sort(Integer::compareTo);              // ascending
System.out.println(list);                   // Output: [1, 2, 3]
list.sort((a, b) -> Integer.compare(b, a)); // descending
System.out.println(list);                   // Output: [3, 2, 1]
```