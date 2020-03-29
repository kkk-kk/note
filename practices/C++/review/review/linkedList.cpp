#include "elemType.h"
#include "linkedList.h"


LinkList listHeadInsert(LinkList &L) {
	LNode *s;  // 指向要添加的结点
	ElemType e;
	char c;
	L = (LinkList)malloc(sizeof(LNode));  // 头结点
	L->next = NULL;                       // 初始为空链表
	do {
		printf("Input # to add next one;");
		printf("Input:Id,name,male,age,grade[3]");
		scanf("%s,%s,%c,%d,%d,%d,%d", e.Id, e.name, c, e.age, e.grade[0], e.grade[1], e.grade[2]);
		if (c == 'm') e.male = true;
		else e.male = false;
		L = (LinkList)malloc(sizeof(LNode));  // 新结点
		s->data = e;   // 填入数据
		s->next = L->next;  
		L->next = s;
		printf("Input # to add next one;");
		scanf("%c", c);
	} while (c == '#');
	return L;
}

LNode *GetElem(LinkList L, int i) {
	int j = 1;    // 序号从1开始
	if (i == 0)
		return L;
	if (i < 1)
		return NULL;
	LNode* p = L->next;
	while (p && j < i) {
		p = p->next;
	}
	return p;
}

LNode *LocateElem(LinkList L, ElemType e) {
	LNode* p = L->next;
	while (p!=NULL && p->data.Id != e.Id) {
		p = p->next;
	}
	return p;
}