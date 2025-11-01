package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public LottoStaticsResponse staticsLotto(String winningNumber, int bonusNumber, List<LottoBuyResponse> buyLotto) {
        var splitWinningNumber = splitNumbers(winningNumber);
        var statics = Lotto.getStatistics(splitWinningNumber, bonusNumber, buyLotto);
        System.out.println(statics);
        return null;
    }

    private List<Integer> splitNumbers(String winningNumber) {
        return Arrays.stream(winningNumber.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
}
