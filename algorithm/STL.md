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

