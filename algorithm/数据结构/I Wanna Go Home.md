# [I  Wanna Go Home](https://www.nowcoder.com/practice/0160bab3ce5d4ae0bb99dc605601e971?tpId=40&tqId=21359&rp=1&ru=%2Fta%2Fkaoyan&qru=%2Fta%2Fkaoyan%2Fquestion-ranking&tab=answerKey)

## 题目描述

  The country is facing a terrible civil war----cities in the country are divided into two parts supporting different leaders. As a merchant, Mr. M does not pay attention to politics but he actually knows the severe situation, and your task is to help him reach home as soon as possible.   "For the sake of safety,", said Mr.M, "your route should contain at most 1 road which connects two cities of different camp."   Would you please tell Mr. M at least how long will it take to reach his sweet home?

## 输入描述:

```
    The input contains multiple test cases.
    The first line of each case is an integer N (2<=N<=600), representing the number of cities in the country.
    The second line contains one integer M (0<=M<=10000), which is the number of roads.
    The following M lines are the information of the roads. Each line contains three integers A, B and T, which means the road between city A and city B will cost time T. T is in the range of [1,500].
    Next part contains N integers, which are either 1 or 2. The i-th integer shows the supporting leader of city i. 
    To simplify the problem, we assume that Mr. M starts from city 1 and his target is city 2. City 1 always supports leader 1 while city 2 is at the same side of leader 2. 
    Note that all roads are bidirectional and there is at most 1 road between two cities.
Input is ended with a case of N=0.
```

## 输出描述:

```
    For each test case, output one integer representing the minimum time to reach home.
    If it is impossible to reach home according to Mr. M's demands, output -1 instead.
```

## 解答（Dijkstra）

```c++
#include <iostream>
#include <vector>
using namespace std;

struct Edge {
	int end, time;
	Edge(int e, int t) :end(e), time(t){}
};

struct Input {
	int begin, end, time;
	Input(int b,int e,int t):begin(b),end(e),time(t){}
	Input(){}
};

vector<Edge> *Adj;
vector<Input> edgeList;
int *flag;
int N;

void Dijkstra(int s, int to) {
	int *d = new int[N]; // 起点到达各点的最短路径长度
	bool *vis = new bool[N]; // 标记数组
	for (int i = 1;i < N;i++) {
		vis[i] = false;
		d[i] = 0x7fffffff;
	}
	d[s] = 0;

	for (int i = 1;i < N;i++) {
		int u = -1, MIN = 0x7fffffff;
		for (int j = 1;j < N;j++) {
			if (!vis[j] && d[j] < MIN) {
				u = j;
				MIN = d[j];
			}
		}
		if (u == -1) break; // 剩下的顶点和起点s不连通
		vis[u] = true;
		for (int j = 0;j < Adj[u].size();j++) {
			int v = Adj[u][j].end; // v是终点 j是中间结点
			if(!(flag[u] == 2 && flag[v] == 1))  // 不能2->1
				if (!vis[v] && d[u] + Adj[u][j].time < d[v]) {
					d[v] = d[u] + Adj[u][j].time; // 优化d[v]
				}
		}
	}
	if (d[to] != 0x7fffffff) cout << d[to] << endl;
	else cout << -1 << endl;
}

int main() {
	int M; // n-结点 m-边
	int b, e, t;

	while (cin >> N && N != 0) {
		N++;
		Adj = new vector<Edge>[N]; // 动态分配vector数组，及销毁
		flag = new int[N];
		cin >> M;
		while (M-- > 0) {
			cin >> b >> e >> t;
			edgeList.push_back(Input(b, e, t));
		}
		for (int i = 1;i < N;i++) {
			cin >> flag[i];
		}
        // 根据题意：2不能到1，
		for (auto it = edgeList.begin();it != edgeList.end();it++) {
			if (flag[it->begin] == flag[it->end]) {  // 同一阵营
				Adj[it->begin].push_back(Edge(it->end, it->time));
				Adj[it->end].push_back(Edge(it->begin, it->time));
			}
			else if (flag[it->begin] == 1) { // begin - 1
				Adj[it->begin].push_back(Edge(it->end, it->time));
			}
			else { // end - 1
				Adj[it->end].push_back(Edge(it->begin, it->time));
			}
		}
		Dijkstra(1,2);
		for (int i = 0;i < N;i++) {  // 销毁vector数组
			Adj[i].clear();
		}
		//delete Adj;
		delete flag;
		edgeList.clear();
		Adj = NULL;
		flag = NULL;
	}
}
```

+ 对vector数组的动态分配及销毁
+ 题目要求只能有一条跨越1,2阵营的路，且起点在1，终点在2，所以只需要限制，只能从1到2即可

