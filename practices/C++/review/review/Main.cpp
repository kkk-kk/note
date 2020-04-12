#include "elemType.h"
#include "exercises.h"

int main() {
	int A[5] = { 1,4,5,10,11 };
	int B[5] = { 2,4,6,8,10 };
	int R[10];
	Merge(A, B, R, 5, 5, 10);
	system("pause");
	return 0;
}
/*
void seqList() {
	SeqList seqlist;
	SeqListInit(seqlist);
	ElemType e;
	ElemType e0 = { "0000","lll",true,20,{100,100,100} };
	ElemType e1 = { "0001","kkk",true,20,{100,100,100} };
	ElemType e2 = { "0002","xxx",true,20,{100,100,100} };
	SeqListInsert(seqlist, 1, e0);
	SeqListInsert(seqlist, 1, e1);
	SeqListInsert(seqlist, 1, e2);
	PrintSeqList(seqlist);
	SeqListDelete(seqlist, 2, e);
	PrintSeqList(seqlist);
	bool em = Empty(seqlist);
	e = GetElem(seqlist, 1);
	int i = LocateElem(seqlist, e0);
	DestorySeqList(seqlist);
}
*/