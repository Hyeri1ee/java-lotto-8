package lotto;

import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외 발생한다.")
    @Test
    void Exception_lottoNumbers_exceed_6() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 숫자의 범위는 1~45이다.")
    @Test
    void 로또_번호_숫자범위가_1이상_45이하이면_정상이다() {
        //given
        Lotto lotto = new Lotto(List.of(1,4,5,2,45,44));
        //when
        List<Integer> numberList = lotto.getNumbers();
        boolean check = numberList.stream()
                .allMatch(num ->  num >= 1 &&  num <= 45);

        //then
        assertThat(check).isEqualTo(true);

    }


}
