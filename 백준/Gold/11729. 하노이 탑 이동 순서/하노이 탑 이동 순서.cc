#include<stdio.h>

int num;
void hanoi(int n, int a, int b, int tmp, bool pro) {
	if (pro == false) {
		num++;
	}
	if (n == 1) {
		if (pro == true) {
			printf("%d %d\n", a, b, pro);
		}
	}
	else {
		hanoi(n - 1, a, tmp, b, pro);
		if (pro == true) {
			printf("%d %d\n", a, b, pro);
		}
		hanoi(n - 1, tmp, b, a, pro);
	}
}
int main() {
	int n;
	scanf("%d", &n);
	num = 0;
	hanoi(n, 1, 3, 2, false);
	printf("%d\n", num);
	hanoi(n, 1, 3, 2, true);
	return 0;
}