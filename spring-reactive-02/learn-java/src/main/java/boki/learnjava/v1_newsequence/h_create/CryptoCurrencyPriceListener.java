package boki.learnjava.v1_newsequence.h_create;

import java.util.List;

public interface CryptoCurrencyPriceListener {
    void onPrice(List<Integer> priceList);

    void onComplete();
}
