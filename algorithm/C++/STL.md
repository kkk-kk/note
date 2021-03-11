# [STL](http://c.biancheng.net/stl/)

## [map](https://www.cnblogs.com/fnlingnzb-learner/p/5833051.html)

+ key只能出现一次

![img](image/ab94f358cc379299731b9aaa4814fd47.png)

#### 插入元素

```c++
map.insert(pair<int, string>(000,"zero"));  // 插入 pair 
map.insert(map<int, string>::value_type(001, "student_one")); // 插入 value_type数据
// 以上两种完全相同，当关键字存在时，insert插入失败，返回值为pair<iterator,bool>对象,第二个参数表示插入结果
map[000] = "zero"; // 可以覆盖以前的关键字
```

#### 查找元素

```c++
// 遍历
map<int, string>::iterator iter;  
for(iter = mapStudent.begin(); iter != mapStudent.end(); iter++)  
for (auto it = map.begin();it != map.end();it++)
// find 返回迭代器指向当前查找元素的位置否则返回map::end()位置
iter = mapStudent.find("123");
 
if(iter != mapStudent.end())
       cout<<"Find, the value is"<<iter->second<<endl;
else
   cout<<"Do not Find"<<endl;
```

#### 删除元素

```c++
// 迭代器删除
iter = map.find("000");
map.erase(iter);
//关键字删除
int n = map.erase("000"); // 删除成功返回1，否则返回0
//用迭代器范围刪除 : 把整个map清空
map.erase(map.begin(), map.end());
//全部删除
map.clear()
```

#### 其他

```
empty()
rbegin()        返回一个指向map尾部的逆向迭代器
rend()          返回一个指向map头部的逆向迭代器
size()          返回map中元素的个数
swap()          交换两个map
upper_bound()    返回键值>给定元素的第一个位置
```

#### [例题](https://www.nowcoder.com/practice/bcad754c91a54994be31a239996e7c11?tpId=40&tqId=21399&rp=1&ru=%2Fta%2Fkaoyan&qru=%2Fta%2Fkaoyan%2Fquestion-ranking&tab=answerKey)

```c++
/*
对每个字符串，输出它所有出现次数在1次以上的子串和这个子串出现的次数，输出按字典序排序。
*/
#include <iostream>
#include <map>
#include <string>
using namespace std; 

int main() {
	string str;
	while (cin >> str) {
		map<string, int> map;
		for (int i = 0;i <= str.size();i++) {
			for (int j = 0;j < i;j++) {
				map[str.substr(j, i - j)]++;
			}
		}
		for (auto it = map.begin();it != map.end();it++) {
			if (it->second > 1)
				cout << it->first << ' ' << it->second << endl;
		}
	}
}
```

```c++
// 将这输入的该组字符串按每个字符串的长度，由小到大排序，按排序结果输出字符串。
#include <iostream>
#include <map>
#include <string>
using namespace std; 

int main() {
	map<int,string> strmap;  // map映射有序，所以将字符串长度设置为first
	int i, c = 0,num;
	string s;
	while(cin >> num){
	    while (getline(cin, s)) {
		    if (s == "stop") break;
		    strmap[s.size()] = s;
	    }
        for (auto it = strmap.begin();it != strmap.end();it++) {
            if(it->second.size()!=0)
		        cout << it->second << endl;
	    }
        strmap.clear();
        c = 0;
    }
	return 0;
}
```

## set



## list

+ 双向链表

#### 函数

+ 构造函数

  + ```c++
    list<int> c0; //空链表
    list<int> c1(3); //建一个含三个默认值是0的元素的链表
    list<int> c2(5,2); //建一个含五个元素的链表，值都是2
    list<int> c4(c2); //建一个c2的copy链表
    list<int> c5(c1.begin(),c1.end()); ////c5含c1一个区域的元素
    ```

+ `c.rbegin()` 返回逆向链表的第一个元素,即c链表的最后一个数据。

  `c.rend()`返回逆向链表的最后一个元素的下一个位置,即c链表的第一个数据再往前的位置。

+ `c.assign(n,num)`   将n个num拷贝赋值给链表c。

  `c.assign(beg,end)`   将[beg,end)区间的元素拷贝赋值给链表c。

+ `c.front()`   返回链表c的第一个元素。

  `c.back()`   返回链表c的最后一个元素。

+ `c.insert(pos,num)`   在pos位置插入元素num。

  `c.insert(pos,n,num)`   在pos位置插入n个元素num。

  `c.insert(pos,beg,end)`   在pos位置插入区间为[beg,end)的元素。

+ `c.erase(pos)`　删除pos位置的元素。

+ `c.push_back(num)`   在末尾增加一个元素。

  `c.pop_back()`   删除末尾的元素。

  `c.push_front(num)`   在开始位置增加一个元素。

  `c.pop_front()`   删除第一个元素。

+ `remove(num)`       删除链表中匹配num的元素。

+ `remove_if(comp)`    删除条件满足的元素,参数为自定义的回调函数。

+ `c.sort()`    将链表排序，默认升序

  `c.sort(comp)`    自定义回调函数实现自定义排序

+ `resize(n)`   从新定义链表的长度,超出原始长度部分用0代替,小于原始部分删除。

  `resize(n,num) `     从新定义链表的长度,超出原始长度部分用num代替。

+ `c1.swap(c2);`  或` swap(c1,c2); ` 将c1和c2交换。

+ `reverse() `   反转链表

+ `c1.merge(c2) `  合并2个有序的链表并使之有序,从新放到c1里,释放c2。

  `c1.merge(c2,comp)`   合并2个有序的链表并使之按照自定义规则排序之后从新放到c1中,释放c2。

+ `c1.splice(c1.beg,c2)`   将c2连接在c1的beg位置,释放c2

  `c1.splice(c1.beg,c2,c2.beg)`   将c2的beg位置的元素连接到c1的beg位置，并且在c2中施放掉beg位置的元素

  `c1.splice(c1.beg,c2,c2.beg,c2.end)`   将c2的[beg,end)位置的元素连接到c1的beg位置并且释放c2的[beg,end)位置的元素

+ `c.unique()`    删除相邻的元素

## stack

- `top()`：返回一个栈顶元素的引用，类型为 T&。如果栈为空，返回值未定义。
- `push(const T& obj)`：可以将对象副本压入栈顶。这是通过调用底层容器的 push_back() 函数完成的。
- `push(T&& obj)`：以移动对象的方式将对象压入栈顶。这是通过调用底层容器的有右值引用参数的 push_back() 函数完成的。
- `pop()`：弹出栈顶元素。
- `size()`：返回栈中元素的个数。
- `empty()`：在栈中没有元素的情况下返回 true。
- `emplace()`：用传入的参数调用构造函数，在栈顶生成对象。
- `swap(stack<T> & other_stack)`：将当前栈中的元素和参数中的元素交换。参数所包含元素的类型必须和当前栈的相同。对于 stack 对象有一个特例化的全局函数 swap() 可以使用。
- 没有迭代器

## queue

- `front()`：返回 queue 中第一个元素的引用。如果 queue 是常量，就返回一个常引用；如果 queue 为空，返回值是未定义的。
- `back()`：返回 queue 中最后一个元素的引用。如果 queue 是常量，就返回一个常引用；如果 queue 为空，返回值是未定义的。
- `push(const T& obj)`：在 queue 的尾部添加一个元素的副本。这是通过调用底层容器的成员函数 push_back() 来完成的。
- `push(T&& obj)`：以移动的方式在 queue 的尾部添加元素。这是通过调用底层容器的具有右值引用参数的成员函数 push_back() 来完成的。
- `pop()`：删除 queue 中的第一个元素。
- `size()`：返回 queue 中元素的个数。
- `empty()`：如果 queue 中没有元素的话，返回 true。
- `emplace()`：用传给 emplace() 的参数调用 T 的构造函数，在 queue 的尾部生成对象。
- `swap(queue<T> &other_q)`：将当前 queue 中的元素和参数 queue 中的元素交换。它们需要包含相同类型的元素。也可以调用全局函数模板 swap() 来完成同样的操作。
- 没有迭代器

#### priority_queue

将优先级高的排在前面，优先出队，用于优化Dijkstra算法。

```c++
struct Node{
	int No,weight;
    Node(int n,int w):No(n),weight(w){}
    bool operator<(const Node &n) const{  // 重载<运算符
        return weight > n.weight;  // 使weight更小的排在前面
    }
};

struct tmp{ // 通过重写仿函数实现小根堆
    bool operator()(Node a,Node b){
        return a.weight > b.weight; // 小根堆
    }
}

priority_queue<Node> Q; // 最小优先队列
priority_queue<int> Q_int_big; // 对于基础类型默认是大顶堆
priority_queue<int, vector<int>, less<int> > Q_int_big; // 大根堆
priority_queue<int,vector<int>,greater<int> > Q_int_small; // 小根堆
priority_queue<Node, vector<Node>, tmp> Q_Node_small;  // 小根堆

```

例题：[（Dijkstra）最短路径](../数据结构/优化Dijkstra.md)