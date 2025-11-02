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

    private LottoStaticsResponse rateOfReturn(Map<Winner, Long> statics, int size) {
        long sum = 0L;
        int[] temp = new int[5];
        int index = 0;
        for (Winner winner : statics.keySet()) {
            long count = statics.get(winner);
            sum += winner.getPrice() * count;
            temp[index++] = (int) count;
        }
        double rate = (double) sum / (size * 1000) * 100;

        return new LottoStaticsResponse(
                temp[0],
                temp[1],
                temp[2],
                temp[3],
                temp[4],
                rate
        );
    }
}
