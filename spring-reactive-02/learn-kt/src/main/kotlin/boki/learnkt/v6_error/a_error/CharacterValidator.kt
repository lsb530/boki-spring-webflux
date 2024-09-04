package boki.learnkt.v6_error.a_error

import java.util.zip.DataFormatException

object CharacterValidator {

    @Throws(DataFormatException::class)
    fun isAlphabeticCharacter(ch: Char) {
        if (!Character.isAlphabetic(ch.code)) {
            throw DataFormatException("Not Alphabetic")
        }
    }
}