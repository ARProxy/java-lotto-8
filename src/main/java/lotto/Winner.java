package lotto;

public enum Winner {
    FIRST(6, false, 2_000_000_000L),
    SECOND(5, true, 30_000_000L),
    THIRD(5, false, 1_500_000L),
    FOURTH(4, false, 50_000L),
    FIFTH(3, false, 5_000L);

    private final int matchCount;
    private final boolean matchBonus;
    private final long price;

    Winner(int matchCount, boolean matchBonus, long price) {
        this.matchCount = matchCount;
        this.matchBonus = matchBonus;
        this.price = price;
    }

    public static Winner of(int matchCount, boolean matchBonus) {
        for (Winner winner : Winner.values()) {
            if (winner.matchCount == matchCount && winner.matchBonus == matchBonus) return winner;
        }
        throw new IllegalArgumentException("[ERROR] 해당하는 당첨 등급이 없습니다.");
    }
}
