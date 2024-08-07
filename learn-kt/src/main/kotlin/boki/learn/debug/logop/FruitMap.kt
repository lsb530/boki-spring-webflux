package boki.learn.debug.logop

object FruitMap {
    val fruits: MutableMap<String, String> = HashMap()

    init {
        fruits["banana"] = "바나나"
        fruits["apple"] = "사과"
        fruits["pear"] = "배"
        fruits["grape"] = "포도"
    }
}