# Head of a Gang

## 题目描述

```
One way that the police finds the head of a gang is to check people's phone calls. If there is a phone call between A and B, we say that A and B is related. The weight of a relation is defined to be the total time length of all the phone calls made between the two persons. A "Gang" is a cluster of more than 2 persons who are related to each other with total relation weight being greater than a given threthold K. In each gang, the one with maximum total weight is the head. Now given a list of phone calls, you are supposed to find the gangs and the heads.
```



## 输入描述:

```
For each case, the first line contains two positive numbers N and K (both less than or equal to 1000), the number of phone calls and the weight threthold, respectively. Then N lines follow, each in the following format:
Name1 Name2 Time
where Name1 and Name2 are the names of people at the two ends of the call, and Time is the length of the call. A name is a string of three capital letters chosen from A-Z. A time length is a positive integer which is no more than 1000 minutes.
```

## 输出描述:

```
For each test case, first print in a line the total number of gangs. Then for each gang, print in a line the name of the head and the total number of the members. It is guaranteed that the head is unique for each gang. The output must be sorted according to the alphabetical order of the names of the heads.
```

## 示例1

#### 输入

```
8 59
AAA BBB 10
BBB AAA 20
AAA CCC 40
DDD EEE 5
EEE DDD 70
FFF GGG 30
GGG HHH 20
HHH FFF 10
8 70
AAA BBB 10
BBB AAA 20
AAA CCC 40
DDD EEE 5
EEE DDD 70
FFF GGG 30
GGG HHH 20
HHH FFF 10
```

#### 输出

```
2
AAA 3
GGG 3
0
```

## 解答

```c++
#include <iostream>
#include <map>
#include <vector>
#include <string>
using namespace std;

struct Edge {
	string end;
	int weight;
	Edge(string e, int w) :end(e), weight(w){}
};

map<string, vector<Edge>> Adj;
map<string, bool> visit;
map<string, int> memberList;
map<string, int> result;
int totalWeight = 0, threshold;
string head;
int num = 0;
void DFS(string n, int depth) {
	visit.find(n)->second = true;
	//
	totalWeight += memberList[n];
	if (memberList[n] > memberList[head]) head = n;
	num++;
	if (Adj.find(n) == Adj.end()) return;
	vector<Edge> list = Adj.find(n)->second;
	for (auto it = list.begin();it != list.end();it++) {
		if (!visit[it->end]) DFS(it->end, depth + 1);
	}
}

void DFSTrave() {
	for (auto it = visit.begin(); it != visit.end();it++) {
		if (!it->second) {
			num = 0;
			totalWeight = 0;
			head = it->first;
			DFS(it->first, 1);
		}
		if (totalWeight > threshold*2 && num > 2) {
			result[head] = num;
		}
	}
}

int main() {
	int M;
	string begin, end;
	int value;
	while (cin >> M >> threshold) {

		while (M-- > 0) {
			cin >> begin >> end >> value;
			// 修改memberList
			if (memberList.find(begin) == memberList.end()) memberList[begin] = value;
			else memberList[begin] += value;
			if (memberList.find(end) == memberList.end()) memberList[end] = value;
			else memberList[end] += value;
			// 图
			//if (Adj.find(begin) == Adj.end()) 
			Adj[begin].push_back(Edge(end, value));
			Adj[end].push_back(Edge(begin, value));
			visit[begin] = false;
			visit[end] = false;
		}
		// 判断总边权是否超过阈值 人数是否大于2
		DFSTrave();
		cout << result.size() << endl;
		for (auto it = result.begin();it != result.end();it++) {
			cout << it->first << ' ' << it->second << endl;
		}
		result.clear();
		memberList.clear();
		Adj.clear();
		visit.clear();
	}
	return 0;
}
```

