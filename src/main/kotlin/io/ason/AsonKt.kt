package io.ason

/**
 * Kotlin support for ASON.
 *
 * Provides inline reified functions for idiomatic Kotlin decoding.
 *
 * NOTE: Currently ASON relies on reflection with a no-argument constructor and
 * mutable properties. If you are using Kotlin `data class`, please ensure
 * properties are declared as `var` and you provide default values for all
 * properties (which generates a no-arg constructor behind the scenes).
 * Nullable types (e.g., `String?`) are natively supported.
 */

/**
 * Decode an ASON string to a single instance of [T].
 */
inline fun <reified T> decode(input: String): T = Ason.decode(input, T::class.java)

/**
 * Decode an ASON byte array to a single instance of [T].
 */
inline fun <reified T> decode(input: ByteArray): T = Ason.decode(input, T::class.java)

/**
 * Decode an ASON string to a List of [T].
 */
inline fun <reified T> decodeList(input: String): List<T> = Ason.decodeList(input, T::class.java)

/**
 * Decode an ASON byte array to a List of [T].
 */
inline fun <reified T> decodeList(input: ByteArray): List<T> = Ason.decodeList(input, T::class.java)
