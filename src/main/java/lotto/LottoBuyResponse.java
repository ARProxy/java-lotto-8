package lotto;

import java.util.List;
import java.util.stream.Collectors;

public record LottoBuyResponse(
        List<Integer> lotto
) {
    @Override
    public String toString() {
        return lotto.stream()
                .sorted()
                .toList()
                .toString();
    }
}
