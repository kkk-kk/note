#pragma once
#include <string>
using namespace std;

// 线性表的顺序储存类型

#define MaxSize 50
typedef struct {                   // 静态分配
	ElemType data[MaxSize];
	int length;
}SqList;

#define InitSize 100
typedef struct {
	ElemType *data;
	int Maxsize, length;           // 最大容量 当前个数
}SeqList;

void SeqListInit(SeqList &L);
bool SeqListInsert(SeqList &L, int i, ElemType e);
bool SeqListDelete(SeqList &L, int i, ElemType &e);
int LocateElem(SeqList L, ElemType e);

void PrintSeqList(SeqList L);
bool Empty(SeqList L);
ElemType GetElem(SeqList L, int i);
void DestorySeqList(SeqList &L);
