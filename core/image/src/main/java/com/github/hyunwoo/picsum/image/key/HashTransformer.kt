package com.github.hyunwoo.picsum.image.key

import java.security.MessageDigest

fun interface HashTransformer {
    fun execute(input: String, type: HashType): String
}

class HashTransformerImpl : HashTransformer {
    override fun execute(input: String, type: HashType): String {
        val bytes = MessageDigest.getInstance(type.value)
            .digest(input.toByteArray())


        val result = StringBuilder(bytes.size * 2)
        bytes.forEach {
            val i = it.toInt()
            result.append(HEX[i shr 4 and 0x0f])
            result.append(HEX[i and 0x0f])
        }

        return result.toString()
    }

    companion object {
        const val HEX = "KEYKEYNUNUKEYKEY"
    }
}
