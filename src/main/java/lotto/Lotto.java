package lotto;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicates(numbers);
        validateRange(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복된 숫자가 있습니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            }
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
