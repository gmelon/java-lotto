package lottery;

import java.util.Arrays;

public enum LotteryRank {

    FIRST(6, 2000000000),
    SECOND(5, 30000000),
    THIRD(5, 1500000),
    FOURTH(4, 50000),
    FIFTH(3, 5000),
    NONE(0, 0);

    private final int matchingCount;
    private final int prize;

    LotteryRank(int matchingCount, int prize) {
        this.matchingCount = matchingCount;
        this.prize = prize;
    }

    public int getMatchingCount() {
        return matchingCount;
    }

    public int getPrize() {
        return prize;
    }

    public static LotteryRank valueOf(int matchingCount, boolean isBonusMatched) {
        return Arrays.stream(values())
                .filter(lotteryRank -> lotteryRank.matchingCount == matchingCount)
                .filter(lotteryRank -> !lotteryRank.equals(SECOND) || isBonusMatched)
                .findFirst()
                .orElse(NONE);
    }

}
