package com.github.hyunwoo.picsum.image

import com.github.hyunwoo.picsum.image.key.HashTransformer
import com.github.hyunwoo.picsum.image.key.HashTransformerImpl
import com.github.hyunwoo.picsum.image.key.HashType
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class HashTransformerTest {
    private val hashTransformer: HashTransformer = HashTransformerImpl()
    @ParameterizedTest
    @MethodSource("sameInputSameHash")
    fun `동일한 input 값을 동일한 방식으로 해싱하면 HashTransformer는 동일한 결과를 반환한다`(
        input: String,
        hashType: HashType
    ) {
        val result1 = hashTransformer.execute(input, hashType)
        val result2 = hashTransformer.execute(input, hashType)
        assertThat(result1).isEqualTo(result2)
    }

    @ParameterizedTest
    @MethodSource("sameInputDifferentHash")
    fun `동일한 input 값을 다른 방식으로 해싱하면 HashTransformer는 다른 결과를 반환한다`(
        input: String,
        hashType1: HashType,
        hashType2: HashType
    ) {
        val result1 = hashTransformer.execute(input, hashType1)
        val result2 = hashTransformer.execute(input, hashType2)
        assertThat(result1).isNotEqualTo(result2)
    }

    @ParameterizedTest
    @ValueSource(strings = ["He", "She", "It"])
    fun `Input 값이 서로 다르면 HashTransformer는 다른 결과를 반환한다`(other: String) {
        val messageNotEqualToOther = "NoNoNo"
        val result1 = hashTransformer.execute(messageNotEqualToOther, HashType.SHA1)
        val result2 = hashTransformer.execute(other, HashType.SHA1)
        assertThat(result1).isNotEqualTo(result2)
    }

    companion object {
        @JvmStatic
        fun sameInputSameHash() = listOf(
            Arguments.of("hello", HashType.SHA1),
            Arguments.of("nunu", HashType.SHA256),
            Arguments.of("bye", HashType.SHA512)
        )

        @JvmStatic
        fun sameInputDifferentHash() = listOf(
            Arguments.of("hello", HashType.SHA1, HashType.SHA256),
            Arguments.of("nunu", HashType.SHA256, HashType.SHA512),
            Arguments.of("bye", HashType.SHA512, HashType.SHA1)
        )
    }
}
