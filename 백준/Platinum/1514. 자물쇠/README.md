# [Platinum III] 자물쇠 - 1514 

[문제 링크](https://www.acmicpc.net/problem/1514) 

### 성능 요약

메모리: 18800 KB, 시간: 156 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 4월 10일 18:14:19

### 문제 설명

<p>세준이는 노트북을 누가 가져갈까봐 자물쇠로 잠가놓는다. 자물쇠는 동그란 디스크 N개로 구성되어 있다. 각 디스크에는 숫자가 0부터 9까지 숫자가 표시되어 있다. 디스크는 원형이기 때문에, 0과 9는 인접해 있다.</p>

<p>세준이는 한 번 자물쇠를 돌릴 때, 최대 세 칸을 시계 방향 또는 반시계 방향으로 돌릴 수 있다. 또, 최대 세 개의 인접한 디스크를 한 번에 돌릴 수 있다.</p>

<p>현재 자물쇠의 상태와 세준이의 비밀번호가 주어질 때, 자물쇠를 최소 몇 번 돌려야 풀 수 있는지 구하는 프로그램을 작성하시오.</p>

<p>자물쇠의 상태가 555이고, 세준이의 비밀번호가 464인 경우에, 각 디스크를 따로 따로 돌리면 3번 돌려야 한다. 하지만, 디스크 3개를 동시에 돌려서 444로 만들고, 2번째 디스크를 6으로 돌리면 2번만에 돌릴 수 있다.</p>

### 입력 

 <p>첫째 줄에 세준이의 비밀번호의 길이 (자물쇠의 크기) N이 주어진다. N은 100보다 작거나 같다. 둘째 줄에 현재 자물쇠의 상태가 주어지고, 셋째 줄에 세준이의 비밀번호가 주어진다.</p>

### 출력 

 <p>첫째 줄에 최소 몇 번을 돌려야 풀 수 있는지 구하는 프로그램을 작성하시오.</p>

