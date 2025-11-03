package lotto.domain;

import lotto.constants.Error;
import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers; //로또 번호에 중복된 숫자가 있으면 예외가 발생한다. 테스트코드에서 validate()을 먼저 호출하면 NullPointerException 오류
        validate();
    }

    //검증 모음 함수
    private void validate(){
        validateNumbersSize();
        validateIfDuplicate();
    }

    //로또 개수는 6개
    private void validateNumbersSize() {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException(Error.LOTTO_SIZE_6.getMessage());
        }
    }

    //로또 값은 중복 x
    private void validateIfDuplicate(){
        long distinctCount = numbers.stream()//왜 long밖에 안되지
                .distinct()
                .count();
        if (distinctCount != numbers.size()){
            throw new IllegalArgumentException(Error.LOTTO_NUMBERS_DUPLICATE.getMessage());
        }
    }

    public List<Integer> getNumbers(){
        return numbers;
    }


}
