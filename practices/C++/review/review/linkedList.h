#pragma once

// 单链表
typedef struct LNode {
	ElemType data;
	struct LNode* next;
}LNode, *LinkList;

LinkList listHeadInsert(LinkList &L);  
LNode *GetElem(LinkList L, int i);
LNode *LocateElem(LinkList L, ElemType e);

// 双链表
typedef struct DNode {
	ElemType data;
	struct DNode *next, *prior;
}DNode, *DLinkList;

// 静态链表
#define Maxsize 50
typedef struct {
	ElemType data;
	int next;          // 游标
} SLinkList[Maxsize];