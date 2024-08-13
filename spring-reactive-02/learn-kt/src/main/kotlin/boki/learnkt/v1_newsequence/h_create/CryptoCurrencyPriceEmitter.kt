package boki.learnkt.v1_newsequence.h_create

import boki.learnjava.common.SampleData

class CryptoCurrencyPriceEmitter {
    private var listener: CryptoCurrencyPriceListener? = null

    fun setListener(listener: CryptoCurrencyPriceListener) {
        this.listener = listener
    }

    fun flowInto() {
        listener?.onPrice(SampleData.btcPrices)
    }

    fun complete() {
        listener?.onComplete()
    }
}