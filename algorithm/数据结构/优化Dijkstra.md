# [最短路径](https://www.nowcoder.com/practice/e372b623d0874ce2915c663d881a3ff2?tpId=40&tqId=21483&rp=1&ru=%2Fta%2Fkaoyan&qru=%2Fta%2Fkaoyan%2Fquestion-ranking&tab=answerKey)

## 题目描述

给你n个点，m条无向边，每条边都有长度d和花费p，给你起点s终点t，要求输出起点到终点的最短距离及其花费，如果最短距离有多条路线，则输出花费最少的。

## 输入描述:

```
输入n,m，点的编号是1~n,然后是m行，每行4个数 a,b,d,p，表示a和b之间有一条边，且其长度为d，花费为p。最后一行是两个数 s,t;起点s，终点t。n和m为0时输入结束。
(1<n<=1000, 0<m<100000, s != t)
```

## 输出描述:

```
输出 一行有两个数， 最短距离及其花费。
```

## 解答

```c++
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int M, N;
typedef struct Edge {
	int end;
	int dis, cost;
	Edge(int e, int d, int c):end(e),cost(c),dis(d){}
}Edge;
vector<Edge> *Adj;

typedef struct Node {
	int No;
	int dis,cost;
	Node(int n,int d, int c):No(n),dis(d),cost(c){}
	bool operator<(const Node &n) const {
		return dis > n.dis;
	}
}Node;

void Dijkstra(int b, int e) { // 优化的Dijkstra算法
	priority_queue<Node> q; // 最小优先队列
	int *d = new int[N];
	int *c = new int[N];
	for (int i = 0;i < N;i++) {
		d[i] = 0x7fffffff;
		c[i] = 0x7fffffff;
	}
	d[b] = 0;
	c[b] = 0;
	q.push(Node(b, d[b], c[b]));
	while (!q.empty()) {
		int u = q.top().No;
		q.pop();
		for (int i = 0;i < Adj[u].size();i++) {
			int v = Adj[u][i].end;
			int dis = Adj[u][i].dis;
			int cost = Adj[u][i].cost;
			if (dis + d[u] < d[v] || (dis + d[u] == d[v] && cost + c[u] < c[v])) {
				d[v] = dis + d[u];
				c[v] = cost + c[u];
				q.push(Node(v, d[v],c[v]));
			}
		}
	}
	cout << d[e] << ' ' << c[e] << endl;
}

int main() {
	int dis, cost, begin, end;
	while (cin >> N >> M && N != 0 && M != 0) {
		N++; // 0不用
		Adj = new vector<Edge>[N];
		for (int i = 0;i < M;i++) {
			cin >> begin >> end >> dis >> cost;
			vector<Edge>::iterator it;
            // 存在顶点相同距离、花费不同的边
			for (it = Adj[begin].begin(); it != Adj[begin].end(); it++) {
				if ((it->end == end) && (it->dis > dis || (it->dis == dis && it->cost > cost))) {
					it->dis = dis;
					it->cost = cost;
					break;
				}
			}
			if(it == Adj[begin].end()) Adj[begin].push_back(Edge(end, dis, cost));
			for (it = Adj[end].begin(); it != Adj[end].end(); it++) {
				if ((it->end == begin) && (it->dis > dis || (it->dis == dis && it->cost > cost ))) {
					it->dis = dis;
					it->cost = cost;
					break;
				}
			}
			if (it == Adj[end].end()) Adj[end].push_back(Edge(begin, dis, cost));
			
		}
		cin >> begin >> end;
		Dijkstra(begin, end);
		
	}
}
```

