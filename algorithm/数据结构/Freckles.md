# Freckles

## 题目描述

  In an episode of the Dick Van Dyke show, little Richie connects the freckles on his Dad's back to form a picture of the Liberty Bell. Alas, one of the freckles turns out to be a scar, so his Ripley's engagement falls through.   Consider Dick's back to be a plane with freckles at various (x,y) locations. Your job is to tell Richie how to connect the dots so as to minimize the amount of ink used. Richie connects the dots by drawing straight lines between pairs, possibly lifting the pen between lines. When Richie is done there must be a sequence of connected lines from any freckle to any other freckle.

## 输入描述:

```
    The first line contains 0 < n <= 100, the number of freckles on Dick's back. For each freckle, a line follows; each following line contains two real numbers indicating the (x,y) coordinates of the freckle.
```

## 输出描述:

```
    Your program prints a single real number to two decimal places: the minimum total length of ink lines that can connect all the freckles.
```

## 解答

```c++
#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <iomanip>
using namespace std;

int N,M; // 节点个数 边数
typedef struct Node {
	double x, y;
	Node(double _x,double _y):x(_x),y(_y){}
};

typedef struct Edge {
	double weight;
	int b, e;
	Edge(int _b, int _e, double w) :b(_b), e(_e), weight(w){}
};

int *father;
vector<Node> nodeList;
vector<Edge> edgeList;
vector<Edge> MST;  // 最小生成树

double Kruskal() {
	father = new int[N];
	for (int i = 0;i < N;i++) {
		father[i] = i;
	}
	double total = 0;
	for (auto it = edgeList.begin(); it != edgeList.end();it++) {
		if (FindFather(it->b) != FindFather(it->e)) {
			MST.push_back(Edge(it->b, it->e, it->weight));
			total += it->weight;
			Union(it->b, it->e);
			if (MST.size() == N - 1) break; // 边数 = 顶点数-1 退出
		}
	}
	return total;
}

bool cmp(Edge a, Edge b) {
	return a.weight < b.weight;
}

int main() {
	cin >> N;
	double x, y;
	
	for (int i = 0;i < N;i++) {
		cin >> x >> y;
		nodeList.push_back(Node(x, y));
	}
	double weight;
	M = 0;
	for (int i = 0;i < N;i++) {
		for (int j = i + 1;j < N;j++) {
			weight = sqrt(pow((nodeList[i].x - nodeList[j].x), 2) + pow((nodeList[i].y - nodeList[j].y), 2));
			edgeList.push_back(Edge(i, j, weight));
			M++;
		}
	}
	sort(edgeList.begin(), edgeList.end(), cmp);
	cout << setiosflags(ios::fixed) <<setprecision(2) << Kruskal() << endl;

}
```

+ 并查集

```
int FindFather(int x) {
	int a = x, z;
	while (x != father[x]) {
		x = father[x];
	}
	// 路径压缩
	// x此时存放根节点。下面把路径上的所有节点的father都改成根节点
	while (a != father[a]) {
		z = a;
		a = father[a]; // 回溯父亲节点
		father[z] = a; // 将原结点a的父亲改为根节点x
	}
	return x;
}

void Union(int a, int b) {
	int ra = FindFather(a), rb = FindFather(b);
	if (ra != rb) {
		father[rb] = ra;
	}
}
```

