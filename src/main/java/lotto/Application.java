package lotto;

import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {

        //로또 컨트롤러 생성후 런
        LottoController controller = new LottoController();
        controller.run();
    }
}
