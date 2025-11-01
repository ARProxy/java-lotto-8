package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class LottoService {

    @SuppressWarnings("SpellCheckingInspection")
    public List<LottoDto> buyLotto(int amount) {
        int count = amount / 1000;
        List<LottoDto> lottos = new ArrayList<>();

        for (int i = 0; i <count; i++) {
            lottos.add(new LottoDto(
                    Randoms.pickUniqueNumbersInRange(1, 45, 6)
            ));
        }

        return lottos;
    }
}
