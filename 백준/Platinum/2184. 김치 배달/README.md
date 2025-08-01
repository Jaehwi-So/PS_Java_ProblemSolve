# [Platinum III] 김치 배달 - 2184 

[문제 링크](https://www.acmicpc.net/problem/2184) 

### 성능 요약

메모리: 45484 KB, 시간: 228 ms

### 분류

다이나믹 프로그래밍

### 제출 일자

2025년 7월 22일 07:22:06

### 문제 설명

<p>월드 식품에서는 김치를 만들어 여러 도시들에 배달 판매하는 일을 하고 있다. 각각의 도시들과 김치 공장은 1차원 직선상의 점에 위치해 있다. 각 도시는 정수 좌표로 나타난다.</p>

<p>배달을 할 때에는 공장에서 N(1 ≤ N ≤ 1,000)포기의 김치를 들고 시작한다. 그리고 1차원 직선을 따라 왼쪽이나 오른쪽으로 움직인다. 이동을 할 때에는 1초에 한 칸씩 움직일 수 있다. 또한 어떤 도시에 도착했을 때 김치는 0의 시간에 배달되는 것으로 생각한다. 즉 도시에 도착하기만 하면 배달이 완료되는 것으로 생각한다. 또한 김치를 배달하는 순서는 상관이 없다.</p>

<p>각각의 김치는 모두 t = 0 의 시각에 공장에서 출발된다. 각각의 김치는 1초에 1만큼씩 쉬게 되는데, 김치가 쉬게 될 경우 소비자가 불만을 토로할 수 있다. 따라서 월드 식품에서는 각 도시에 김치가 도착했을 때의 김치의 쉰 정도의 합을 최소로 하려 한다.</p>

<p>각 도시의 위치 및 김치 공장의 위치(x좌표)가 주어졌을 때, 모든 도시에 김치를 배달할 때의 김치의 쉰 정도의 합의 최솟값을 구하는 프로그램을 작성하시오.</p>

### 입력 

 <p>첫째 줄에 두 정수 N, L이 주어진다. L은 김치 공장의 x좌표이다. 다음 N개의 줄에는 김치를 배달할 도시의 x좌표가 주어진다. 모든 좌표는 1이상 1,000,000이하의 정수이다.</p>

### 출력 

 <p>첫째 줄에 답을 출력한다.</p>

