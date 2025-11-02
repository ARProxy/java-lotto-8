package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LottoService {

    @SuppressWarnings("SpellCheckingInspection")
    public List<LottoBuyResponse> buyLotto(int amount) {
        int count = amount / 1000;
        List<LottoBuyResponse> lottos = new ArrayList<>();

        for (int i = 0; i <count; i++) {
            lottos.add(new LottoBuyResponse(
                    Randoms.pickUniqueNumbersInRange(1, 45, 6)
            ));
        }

        return lottos;
    }

    public LottoStaticsResponse staticsLotto(List<Integer> winningNumbers, int bonusNumber, List<LottoBuyResponse> buyLotto) {
        var statics = Lotto.getStatistics(winningNumbers, bonusNumber, buyLotto);
        return rateOfReturn(statics, buyLotto.size());
    }

    private LottoStaticsResponse rateOfReturn(Map<Winner, Long> statistics, int purchaseCount) {
        long totalPrize = calculateTotalPrize(statistics);
        double profitRate = calculateProfitRate(totalPrize, purchaseCount);

        return new LottoStaticsResponse(
                statistics.getOrDefault(Winner.FIRST, 0L),
                statistics.getOrDefault(Winner.SECOND, 0L),
                statistics.getOrDefault(Winner.THIRD, 0L),
                statistics.getOrDefault(Winner.FOURTH, 0L),
                statistics.getOrDefault(Winner.FIFTH, 0L),
                profitRate
        );
    }

    private long calculateTotalPrize(Map<Winner, Long> statistics) {
        return statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    private double calculateProfitRate(long totalPrize, int purchaseCount) {
        long totalCost = (long) purchaseCount * 1000;
        return (double) totalPrize / totalCost * 100;
    }
}
