package lotto.controller;

import lotto.domain.Lotto;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoController {

    //1~45까지의 로또 번호 생성기
    public Lotto generateRandomLotto() {

        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }
}
