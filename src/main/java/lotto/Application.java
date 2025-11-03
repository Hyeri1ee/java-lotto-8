package lotto;

import lotto.constants.Command;
import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.view.Input;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        Input input = new Input();
        LottoController controller = new LottoController();

        System.out.println(Command.COMMAND_BUY_PRICE.getMessage());
        int purchaseAmount = input.readPurchaseAmount();
        
        int lottoCount = controller.calculateLottoCount(purchaseAmount);
        List<Lotto> lottos = controller.generateLottos(lottoCount);
        
        System.out.println(lottoCount + Command.COMMAND_BOUGHT.getMessage());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
    }
}
