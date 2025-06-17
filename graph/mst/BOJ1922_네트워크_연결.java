import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int computerCount, edgeCount;
    static List<Edge> edges;
    static int[] parents;

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        computerCount = Integer.parseInt(br.readLine());
        edgeCount = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        makeSet();
        Collections.sort(edges);

        int totalCost = 0;
        int useEdge = 0;
        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                if (++useEdge == computerCount - 1) break; // MST 완성
            }
        }

        System.out.println(totalCost);
    }

    static void makeSet() {
        parents = new int[computerCount + 1]; // 1-based index
        for (int i = 1; i <= computerCount; i++) {
            parents[i] = i;
        }
    }

    static int findSet(int x) {
        if (x == parents[x]) return x;
        return parents[x] = findSet(parents[x]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);

        if (rootA == rootB) return false;

        parents[rootB] = rootA;
        return true;
    }
}
