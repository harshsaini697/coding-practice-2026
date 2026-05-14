# LeetCode Graph Patterns - Java Boilerplate

## 1. Build Adjacency List

Use when input is an edge list.

```java
List<List<Integer>> graph = new ArrayList<>();

for (int i = 0; i < n; i++) {
    graph.add(new ArrayList<>());
}

for (int[] edge : edges) {
    int u = edge[0];
    int v = edge[1];

    graph.get(u).add(v);
    graph.get(v).add(u); // omit for directed graph
}
```

## 2. DFS Traversal

Use for connected components, reachability, islands, and cycle checks.

```java
boolean[] visited = new boolean[n];

private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;

    for (int nei : graph.get(node)) {
        if (!visited[nei]) {
            dfs(nei, graph, visited);
        }
    }
}
```

## 3. BFS Traversal

Use for shortest path in unweighted graphs and level order expansion.

```java
Queue<Integer> q = new LinkedList<>();
boolean[] visited = new boolean[n];

q.offer(start);
visited[start] = true;

while (!q.isEmpty()) {
    int node = q.poll();

    for (int nei : graph.get(node)) {
        if (!visited[nei]) {
            visited[nei] = true;
            q.offer(nei);
        }
    }
}
```

## 4. Grid BFS / DFS

Use when a matrix acts like a graph.

```java
int[][] dirs = {
    {1, 0},
    {-1, 0},
    {0, 1},
    {0, -1}
};

for (int[] d : dirs) {
    int nr = r + d[0];
    int nc = c + d[1];

    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
        // valid neighbor
    }
}
```

## 5. Connected Components

Use when counting groups.

```java
int components = 0;
boolean[] visited = new boolean[n];

for (int i = 0; i < n; i++) {
    if (!visited[i]) {
        dfs(i, graph, visited);
        components++;
    }
}
```

## 6. Cycle Detection - Undirected

Track parent to avoid treating the previous node as a cycle.

```java
private boolean hasCycle(int node, int parent, List<List<Integer>> graph, boolean[] visited) {
    visited[node] = true;

    for (int nei : graph.get(node)) {
        if (!visited[nei]) {
            if (hasCycle(nei, node, graph, visited)) {
                return true;
            }
        } else if (nei != parent) {
            return true;
        }
    }

    return false;
}
```

## 7. Cycle Detection - Directed

Use three states.

```java
// 0 = unvisited, 1 = visiting, 2 = done
private boolean hasCycle(int node, List<List<Integer>> graph, int[] state) {
    if (state[node] == 1) {
        return true;
    }

    if (state[node] == 2) {
        return false;
    }

    state[node] = 1;

    for (int nei : graph.get(node)) {
        if (hasCycle(nei, graph, state)) {
            return true;
        }
    }

    state[node] = 2;
    return false;
}
```

## 8. Topological Sort - Kahn BFS

Use for prerequisites and dependency ordering.

```java
int[] indegree = new int[n];

for (int[] edge : edges) {
    int from = edge[0];
    int to = edge[1];
    graph.get(from).add(to);
    indegree[to]++;
}

Queue<Integer> q = new LinkedList<>();

for (int i = 0; i < n; i++) {
    if (indegree[i] == 0) {
        q.offer(i);
    }
}

List<Integer> order = new ArrayList<>();

while (!q.isEmpty()) {
    int node = q.poll();
    order.add(node);

    for (int nei : graph.get(node)) {
        indegree[nei]--;

        if (indegree[nei] == 0) {
            q.offer(nei);
        }
    }
}
```

## 9. Union Find

Use for connectivity, components, redundant edges, and Kruskal-style problems.

```java
class DSU {
    int[] parent;
    int[] rank;

    DSU(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa == pb) {
            return false;
        }

        if (rank[pa] < rank[pb]) {
            parent[pa] = pb;
        } else if (rank[pa] > rank[pb]) {
            parent[pb] = pa;
        } else {
            parent[pb] = pa;
            rank[pa]++;
        }

        return true;
    }
}
```

## 10. Dijkstra

Use for shortest path with non-negative edge weights.

```java
long[] dist = new long[n];
Arrays.fill(dist, Long.MAX_VALUE / 4);
dist[start] = 0;

PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
pq.offer(new long[] {start, 0});

while (!pq.isEmpty()) {
    long[] curr = pq.poll();
    int node = (int) curr[0];
    long d = curr[1];

    if (d != dist[node]) {
        continue;
    }

    for (int[] edge : weightedGraph.get(node)) {
        int nei = edge[0];
        int weight = edge[1];

        if (dist[node] + weight < dist[nei]) {
            dist[nei] = dist[node] + weight;
            pq.offer(new long[] {nei, dist[nei]});
        }
    }
}
```

## 11. Bellman-Ford

Use when negative edges may exist.

```java
long[] dist = new long[n];
Arrays.fill(dist, Long.MAX_VALUE / 4);
dist[src] = 0;

for (int i = 0; i < n - 1; i++) {
    for (int[] edge : edges) {
        int u = edge[0];
        int v = edge[1];
        int w = edge[2];

        if (dist[u] + w < dist[v]) {
            dist[v] = dist[u] + w;
        }
    }
}
```

## 12. Floyd-Warshall

Use for all-pairs shortest paths when `n` is small.

```java
for (int mid = 0; mid < n; mid++) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            dist[i][j] = Math.min(dist[i][j], dist[i][mid] + dist[mid][j]);
        }
    }
}
```

## 13. Minimum Spanning Tree - Kruskal

Sort edges by weight, then union endpoints.

```java
Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

DSU dsu = new DSU(n);
int cost = 0;

for (int[] edge : edges) {
    if (dsu.union(edge[0], edge[1])) {
        cost += edge[2];
    }
}
```

## 14. Bipartite Check

Color neighboring nodes opposite colors.

```java
int[] color = new int[n]; // 0 = uncolored, 1 / -1 = colors

Queue<Integer> q = new LinkedList<>();
q.offer(start);
color[start] = 1;

while (!q.isEmpty()) {
    int node = q.poll();

    for (int nei : graph.get(node)) {
        if (color[nei] == 0) {
            color[nei] = -color[node];
            q.offer(nei);
        } else if (color[nei] == color[node]) {
            return false;
        }
    }
}
```

# LeetCode Practice Problems By Pattern

| # | Pattern | Practice Problem |
|---|---|---|
| 1 | Build Adjacency List | [1971. Find if Path Exists in Graph](https://leetcode.com/problems/find-if-path-exists-in-graph/) |
| 2 | DFS Traversal | [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/) |
| 3 | BFS Traversal | [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) |
| 4 | Grid BFS / DFS | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) |
| 5 | Connected Components | [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) |
| 6 | Cycle Detection - Undirected | [261. Graph Valid Tree](https://leetcode.com/problems/graph-valid-tree/) |
| 7 | Cycle Detection - Directed | [207. Course Schedule](https://leetcode.com/problems/course-schedule/) |
| 8 | Topological Sort | [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) |
| 9 | Union Find | [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/) |
| 10 | Dijkstra | [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/) |
| 11 | Bellman-Ford | [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/) |
| 12 | Floyd-Warshall | [1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance](https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/) |
| 13 | Minimum Spanning Tree | [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) |
| 14 | Bipartite Check | [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/) |

# Recommended Study Order

| Order | Pattern | Practice Problem |
|---|---|---|
| 4 | [Grid BFS / DFS](#4-grid-bfs--dfs) | [200. Number of Islands](https://leetcode.com/problems/number-of-islands/) |
| 3 | [BFS Traversal](#3-bfs-traversal) | [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/) |
| 2 | [DFS Traversal](#2-dfs-traversal) | [547. Number of Provinces](https://leetcode.com/problems/number-of-provinces/) |
| 5 | [Connected Components](#5-connected-components) | [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/) |
| 7 | [Cycle Detection - Directed](#7-cycle-detection---directed) | [207. Course Schedule](https://leetcode.com/problems/course-schedule/) |
| 8 | [Topological Sort](#8-topological-sort---kahn-bfs) | [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/) |
| 9 | [Union Find](#9-union-find) | [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/) |
| 14 | [Bipartite Check](#14-bipartite-check) | [785. Is Graph Bipartite?](https://leetcode.com/problems/is-graph-bipartite/) |
| 10 | [Dijkstra](#10-dijkstra) | [743. Network Delay Time](https://leetcode.com/problems/network-delay-time/) |
| 13 | [Minimum Spanning Tree](#13-minimum-spanning-tree---kruskal) | [1584. Min Cost to Connect All Points](https://leetcode.com/problems/min-cost-to-connect-all-points/) |
