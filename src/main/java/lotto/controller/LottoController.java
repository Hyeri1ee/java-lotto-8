package lotto.controller;

import lotto.constants.Command;
import lotto.domain.Lotto;
import lotto.view.Input;
import java.util.ArrayList;
import java.util.List;
import camp.nextstep.edu.missionutils.Randoms;

public class LottoController {
    private static final int LOTTO_PRICE = 1000;
    private List<Lotto> lottos = new ArrayList<>();

    //시작 함수
    public void run() {
        Input input = new Input();
        
        System.out.println(Command.COMMAND_BUY_PRICE.getMessage());
        int purchaseAmount = input.readPurchaseAmount();//가격
        
        int lottoCount = calculateLottoCount(purchaseAmount);
        generateLottos(lottoCount);
        printLottos(lottoCount);
    }

    private int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;//나머지는 버리기
    }

    //1~45 로또 번호 생성기
    private Lotto generateRandomLotto() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers);
    }

    //count개수만큼 로또 생성기
    private void generateLottos(int count) {
        for (int i = 0; i < count; i++) {
            lottos.add(generateRandomLotto());
        }
    }

//로또 출력 함수
    private void printLottos(int lottoCount) {
        System.out.println(lottoCount + Command.COMMAND_BOUGHT.getMessage());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getSortedNumbers());
        }
        //해당 부분 스트림으로 바꾸면 뭐가 더 나을까
        /*
        lottos.stream()
            .map(Lotto::getSortedNumbers)
            .forEach(System.out::println);
        */
    }
}
