# To Fill or Not to Fill

## 题目描述

With highways available, driving a car from Hangzhou to any other city is easy. But since the tank capacity of a car is limited, we have to find gas stations on the way from time to time. Different gas station may give different price. You are asked to carefully design the cheapest route to go.

## 输入描述:

```
For each case, the first line contains 4 positive numbers: Cmax (<= 100), the maximum capacity of the tank; D (<=30000), the distance between Hangzhou and the destination city; Davg (<=20), the average distance per unit gas that the car can run; and N (<= 500), the total number of gas stations. Then N lines follow, each contains a pair of non-negative numbers: Pi, the unit gas price, and Di (<=D), the distance between this station and Hangzhou, for i=1,...N. All the numbers in a line are separated by a space.
```

对于每种情况，第一行包含4个正数：Cmax（<=100），即油箱的最大容量；D（<=30000），
即杭州到目的地城市的距离；Davg（<=20），即汽车每单位汽油可行驶的平均距离；N（<=500），
即加油站总数。接下来是N行，每行包含一对非负数：Pi，煤气单价，Di（<=D），这个站到杭州的距离，i=1，…N。一行中的所有数字用空格隔开。

## 输出描述:

```
For each test case, print the cheapest price in a line, accurate up to 2 decimal places. It is assumed that the tank is empty at the beginning. If it is impossible to reach the destination, print "The maximum travel distance = X" where X is the maximum possible distance the car can run, accurate up to 2 decimal places.
```

对于每个测试用例，一行打印最便宜的价格，精确到小数点后2位。
假设开始时油箱是空的。如果无法到达目的地，请打印“最大行驶距离=X”，
其中X是车辆可以行驶的最大可能距离，精确到小数点后2位。

## 解答

采用贪心法，有两种解题方案

+ 模拟汽车前进的过程，在加油站时，如果可达的区域内有比当前油价便宜的油站，则只加油到可以到达那一站，如果没有则加满，同样到下一站
+ 设置region[D]表示i是否走过，找出最便宜的油站，计算能走的最大路程，将对应的region设置为走过，遍历所有的油站，一直到能走完。

采用第二种做法

```c++
#include <iostream>
#include <iomanip>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;
int Cmax, D, Davg, N;
typedef struct station {
	double price, pos;
	station(double p, double po) :price(p), pos(po) {};

}station;
bool cmp(station a, station b) {
	return a.price < b.price;
}
vector<station> s; // 不能用map，油价存在重复
bool *region;

int main() {
	while (cin >> Cmax) { // 油量
		cin >> D; // 总路程
		//D++;
		cin >> Davg; // 路程/油
		cin >> N; // 油站数
		int longestdis = Cmax * Davg; // 装满油箱可以走
		region = new bool[D];
		for (int i = 0;i < D;i++) region[i] = false;
		double dis;
		double price;
		for (int i = 0;i < N;i++) {
			cin >> price >> dis;
			s.push_back(station(price,dis));
		}
		sort(s.begin(), s.end(), cmp);
		double cost = 0; // 已消费
		bool flag = true; // 是否继续循环
		for (auto it = s.begin();it != s.end() && flag;it++) {
			int cnt = 0;// 
			for (int i = it->pos; i < it->pos + longestdis && i < D;i++) {
				if (!region[i]) {
					cnt++;
					region[i] = true;
				}
			}
			cost += it->price * cnt / (Davg * 1.0);
			flag = false;
			for (int i = 0;i < D;i++) {
				if (!region[i]) flag = true; // 还有没覆盖（region[i] == false）的，继续循环
			}
		}
		int i;
		if(!flag) cout << setiosflags(ios::fixed) << setprecision(2) << cost << endl;
		else {
			for (i = 0;i < D;i++) {
				if (!region[i])  break;
			}
			cout << "The maximum travel distance = " << i << ".00" << endl;
		}
		s.clear();
	}
}
```

