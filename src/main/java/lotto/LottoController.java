package lotto;

import java.util.List;

public class LottoController {
    private static LottoController instance;
    private final LottoService lottoService;

    private LottoController() {
        lottoService = new LottoService();
    }

    public static LottoController getInstance() {
        if (instance == null) {
            instance = new LottoController();
        }
        return instance;
    }

    public List<LottoDto> buyLotto(int amount) {
        return lottoService.buyLotto(amount);
    }
}
