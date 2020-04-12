#pragma warning(disable:4996)
#include "elemType.h"
#include "exercises.h"
// 习题

void Del_All_x(int L[], int x, int length) {
	int i = 0, j = 0;                     // 保留的元素的个数
	for (;i < length;i++) {
		if (L[i] != x) {
			L[j] = L[i];
			j++;
		}
	}
	length = j;
	for (i = 0;i < length;i++) {
		printf("%d,", L[i]);
	}
}

void Del_s_t(int L[], int s, int t, int length) {
	int i = 0, j = 0;                     // 保留的元素的个数
	for (;i < length;i++) {
		if (L[i] < s || L[i] > t) {
			L[j] = L[i];
			j++;
		}
	}
	length = j;
	for (i = 0;i < length;i++) {
		printf("%d,", L[i]);
	}
}

void Del_Repeat(int L[],int length) {
	int i, j = 0;
	for (i = 0;i < length;i++) {
		if (L[i] != L[i + 1]) {
			L[j] = L[i];
			j++;
		}
	}
	length = j;
	for (i = 0;i < length;i++) {
		printf("%d,", L[i]);
	}
}

bool Merge(int A[], int B[], int R[],int al,int bl,int rl) {
	if (al + bl > rl)
		return false;
	int a = 0, b = 0, r = 0;
	while (a < al && b < bl) {
		if (A[a] > B[b]) 
			R[r++] = B[b++];
		else 
			R[r++] = A[a++];
	}
	while (a < al) {
		R[r++] = A[a++];
	}
	while(b < bl) {
		R[r++] = B[b++];
	}
	rl = r;
	for (int i = 0;i < rl;i++) {
		printf("%d,", R[i]);
	}
	return true;
}