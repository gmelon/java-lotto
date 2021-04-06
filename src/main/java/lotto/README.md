# Step2 - 로또(자동)

---

## 기능 요구사항

- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

```
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 힌트

- 로또 자동 생성은 Collections.shuffle() 메소드 활용한다.
- Collections.sort() 메소드를 활용해 정렬 가능하다.
- ArrayList의 contains() 메소드를 활용하면 어떤 값이 존재하는지 유무를 판단할 수 있다.

## 구현 기능 목록

### view

- InputView
    - 구입 금액을 입력받는다.
    - 당첨 번호를 입력받는다.
- ResultView
    - 로또 번호들을 출력한다.
    - 당첨 통계를 출력한다.
    - 총 수익률을 출력한다.

### domain

- 로또 번호
    - 1 ~ 45의 값을 가진다.
        - 그 외의 값을 생성하려고 할 경우, 예외를 던진다.
- 로또 티켓
    - 6개의 로또 번호를 가진다.
        - 각 번호는 중복되면 안 된다.
    - 로또 번호를 오름차순으로 반환한다
    - 당첨 번호를 입력받아 몇 개를 포함하는지 반환한다
- 로또 상점
    - 로또 티켓 1장의 가격을 가진다.
    - 입력한 금액에 맞는 로또 티켓들을 반환한다.
- 로또 판별기
    - 당첨 번호 6개를 갖는 로또 티켓을 가진다.
    - 로또 티켓을 입력받아 일치하는 번호의 개수를 반환한다.
- 로또 통계
    - 로또 판별기와 로또 티켓 리스트를 가진다.
    - 일치하는 개수별로 로또 티켓 개수를 반환한다.
    - 수익률을 계산해서 반환한다.

# Step3 - 로또(2등)

---

## 기능 요구사항

- 2등을 위해 추가 번호를 하나 더 추첨한다.
- 당첨 통계에 2등도 추가해야 한다.

```
[... 생략 ...]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

## 프로그래밍 요구사항

- java enum을 적용해 프로그래밍을 구현한다.
- 규칙 8: 일급 콜렉션을 쓴다.

## 힌트

- 일급 콜렉션을 쓴다.
    - 6개의 숫자 값을 가지는 java collection을 감싸는 객체를 추가해 구현해 본다.
- 하드 코딩을 하지 않기 위해 상수 값을 사용하면 많은 상수 값이 발생한다. 자바의 enum을 활용해 상수 값을 제거한다. 즉, enum을 활용해 일치하는 수를 로또 등수로 변경해 본다.

```
public enum Rank {
    FIRST(6, 2_000_000_000),
    SECOND(5, 30_000_000),
    THIRD(5, 1_500_000),
    FOURTH(4, 50_000),
    FIFTH(3, 5_000),
    MISS(0, 0);

    private int countOfMatch;
    private int winningMoney;

    private Rank(int countOfMatch, int winningMoney) {
        this.countOfMatch = countOfMatch;
        this.winningMoney = winningMoney;
    }

    public int getCountOfMatch() {
        return countOfMatch;
    }

    public int getWinningMoney() {
        return winningMoney;
    }
		
    public static Rank valueOf(int countOfMatch, boolean matchBonus) {
        // TODO 일치하는 수를 로또 등수로 변경한다. enum 값 목록은 "Rank[] ranks = values();"와 같이 가져올 수 있다.
        return null;
    }
}
```

## 구현 기능 목록 - 이전 단계 중복 제외

### view

- InputView
    - 보너스 번호를 입력받는다.
- ResultView
    - 당첨 통계를 출력한다.
        - 보너스 번호 관련 통계 추가

### domain

- 등수
    - 일치 개수와 상금을 갖는다.
- 로또 판별기
    - 보너스 번호를 가진다.
    - 로또 티켓을 입력받아 등수를 반환한다.
- 로또 통계
    - 등수별로 로또 티켓 개수를 반환한다.

# Step4 - 로또(수동)

---

## 기능 요구사항

- 현재 로또 생성기는 자동 생성 기능만 제공한다. 사용자가 수동으로 추첨 번호를 입력할 수 있도록 해야 한다.
- 입력한 금액, 자동 생성 숫자, 수동 생성 번호를 입력하도록 해야 한다.

```
구입금액을 입력해 주세요.
14000

수동으로 구매할 로또 수를 입력해 주세요.
3

수동으로 구매할 번호를 입력해 주세요.
8, 21, 23, 41, 42, 43
3, 5, 11, 16, 32, 38
7, 11, 16, 35, 36, 44

수동으로 3장, 자동으로 11개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6
보너스 볼을 입력해 주세요.
7

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
5개 일치, 보너스 볼 일치(30000000원) - 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

## 프로그래밍 요구사항

- 규칙 3: 모든 원시값과 문자열을 포장한다.
- 규칙 8: 일급 콜렉션을 쓴다.
- 예외 처리를 통해 에러가 발생하지 않도록 한다.

## 힌트

- 규칙 3: 모든 원시값과 문자열을 포장한다.
    - 로또 숫자 하나는 int 타입이다. 이 숫자 하나를 추상화한 LottoNo 객체를 추가해 구현한다.
- 예외 처리를 통해 에러가 발생하지 않도록 한다.
    - 사용자가 잘못된 값을 입력했을 때 java exception으로 에러 처리를 한다.
    - java8에 추가된 Optional을 적용해 NullPointerException이 발생하지 않도록 한다.

## 구현 기능 목록 - 이전 단계 중복 제외

### view

- InputView
    - 수동을 구매할 로또 수를 입력받는다.
    - 수동으로 구매할 번호들을 입력받는다.
- ResultView
    - 자동과 수동으로 산 로또 번호들을 함께 출력한다.

### domain

- 모든 로또 티켓들
    - 수동 로또 티켓들과 자동 로또 티켓들을 가진다.
    - 수동 로또 티켓의 개수를 반환한다.
    - 자동 로또 티켓의 개수를 반환한다.
    - 전체 티켓을 반환한다.
- 로또 상점
    - 구입 금액과 개수를 입력받아 로또를 구매할 수 있는지 확인한다.
    - 구입 금액과 수동 로또를 입력받아 모든 로또 티켓들을 반환한다.
- 로또 구매자
    - 구입 금액과 로또 상점을 가진다.
    - 로또 티켓을 1개라도 구매 가능한지 검증한다.
    - 수동으로 구매할 로또 개수를 입력받아 구매 가능한지 검증한다.
    - 수동 로또를 입력받아 모든 로또 티켓들을 반환한다.