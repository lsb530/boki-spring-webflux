package boki.learnkt.v1_newsequence.h_create

interface CryptoCurrencyPriceListener {
    fun onPrice(priceList: List<Int>)

    fun onComplete()
}