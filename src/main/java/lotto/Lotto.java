package lotto;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    public static Map<Winner, Long> getStatistics(
            List<Integer> WinningNumbers,
            int bonusNumber,
            List<LottoBuyResponse> buyLotto
    ) {
        Map<Winner, Long> statistics = new EnumMap<>(Winner.class);

        for (Winner winner : Winner.values()) {
            statistics.put(winner, 0L);
        }

        for (LottoBuyResponse lotto : buyLotto) {
            int matchCount = countMatches(lotto.lotto(), WinningNumbers);
            boolean hasBonus = lotto.lotto().contains(bonusNumber);

            try {
                Winner winner = Winner.of(matchCount, hasBonus);
                statistics.put(winner, statistics.getOrDefault(winner, 0L) + 1);
            } catch (IllegalArgumentException ignored) {

            }
        }

        return statistics;
    }

    private static int countMatches(List<Integer> lottoNumbers, List<Integer> winningNumbers) {
        return (int) lottoNumbers.stream()
                .filter(winningNumbers::contains)
                .count();
    }
}
