#include <iostream>
#include "elemType.h";
#include "seqList.h";

// 动态分配
void SeqListInit(SeqList &L) {
	L.data = new ElemType[InitSize];
	L.length = 0;
	L.Maxsize = InitSize;
	// L.data = (ElemType*)malloc(sizeof(ElemType)*InitSize);
}

bool SeqListInsert(SeqList &L, int i, ElemType e) {   // i指位序
	if (i<1 || i>L.length + 1)         // 判断i的范围是否有效
		return false;
	if (L.length >= L.Maxsize)         // 判断储存空间
		return false;
	for (int j = L.length;j >= i;j--)  // 将第i个元素及之后的后移
		L.data[j] = L.data[j - 1];
	L.data[i - 1] = e;                  // 在i处放入s
	L.length++;                        // 长度加1
	return true;
}

bool SeqListDelete(SeqList &L, int i, ElemType &e) {
	if (i<1 || i>L.length + 1)         // 判断i的范围是否有效
		return false;
	e = L.data[i - 1];                 // 被删除的元素赋给e
	for (int j = i;j < L.length;j++) 
		L.data[j - 1] = L.data[j];
	L.length--;
	return true;
}

int LocateElem(SeqList L, ElemType e) {
	int i;
	for (i = 0;i < L.length;i++)
		if (L.data[i].Id == e.Id)
			return i + 1;
	return 0;
}

void PrintSeqList(SeqList L) {
	cout << "Id\tname\tgender\tage\tgrade1\tgrade2\tgrade3" << endl;
	string gender = "male";
	for (int i = 0;i < L.length;i++) {
		if (L.data[i].male)
			gender = "male";
		else
			gender = "female";
		cout << L.data[i].Id << "\t" << L.data[i].name << "\t" << gender << "\t" << L.data[i].age << "\t" << L.data->grade[0] << "\t" << L.data->grade[1] << "\t" << L.data->grade[2] << endl;
	}
}
bool Empty(SeqList L) {
	if(L.length == 0)
		return true;
	else 
		return false;
}
ElemType GetElem(SeqList L, int i) {
	return L.data[i];
}
void DestorySeqList(SeqList &L) {
	delete[] L.data;
	L.length = 0;
}