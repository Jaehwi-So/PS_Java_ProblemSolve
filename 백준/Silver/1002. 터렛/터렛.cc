#include<stdio.h>
#include<math.h>
#define swap(X,Y,T) T=X; X=Y; Y=T
double get_dist(int x1, int y1, int x2, int y2) {
	return sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
}
int main() {
	int t;
	scanf("%d", &t);
	//조규현, 백승환 : 터렛 근무원 & 류재명 : 마린
	for (int i = 0; i < t; i++) {
		int x1, y1, r1, x2, y2, r2, point;
		scanf("%d %d %d %d %d %d", &x1, &y1, &r1, &x2, &y2, &r2);
		int tmp;

		if (r2 > r1) {	//반지름이 큰 원을 x1, y1, r1으로 한다.
			swap(x1, x2, tmp);
			swap(y1, y2, tmp);
			swap(r1, r2, tmp);
		}
		double dist = get_dist(x1, y1, x2, y2); //(조규현과 백승환의 거리)^2
		if (dist == 0 && r1 == r2) {
			point = -1;
		}
		else if (dist > (r1 + r2) || dist + r2 < r1) {
			point = 0;
		}
		else if (dist == (r1 + r2) || dist + r2 == r1) {
			point = 1;
		}
		else if (dist < (r1 + r2) ) {	//외접할 경우
			point = 2;
		}
		printf("%d\n", point);
	}
	return 0;
}