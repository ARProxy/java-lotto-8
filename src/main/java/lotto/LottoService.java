package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public LottoStaticsResponse staticsLotto(String winningNumber, int bonusNumber) {
        var splitWinningNumber = splitNumbers(winningNumber);
        return null;
    }

    private List<Integer> splitNumbers(String winningNumber) {
        return Arrays.stream(winningNumber.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }
}
