package boki.learnjava.v1_newsequence.h_create;

import boki.learnjava.common.SampleData;

public class CryptoCurrencyPriceEmitter {
    private CryptoCurrencyPriceListener listener;

    public void setListener(CryptoCurrencyPriceListener listener) {
        this.listener = listener;
    }

    public void flowInto() {
        listener.onPrice(SampleData.btcPrices);
    }

    public void complete() {
        listener.onComplete();
    }
}