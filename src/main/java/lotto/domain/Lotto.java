package lotto.domain;

import lotto.constants.Error;
import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        this.numbers = new ArrayList<>(numbers);//해당 부분 방어적 복사 알아보기
        validate();
    }

    //검증 모음 함수
    private void validate(){
        validateNumbersSize();
        validateIfDuplicate();
        validateNumbersRange();
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

    //로또 값은 1~45
    private void validateNumbersRange() {
        boolean isOutOfRange = numbers.stream().anyMatch(num -> num < 1 || num > 45);

        if (isOutOfRange) {
            throw new IllegalArgumentException(Error.LOTTO_NUMBERS_RANGE.getMessage());
        }
    }

    public List<Integer> getNumbers(){
        return new ArrayList<>(numbers);
    }

    public List<Integer> getSortedNumbers() {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }
}
