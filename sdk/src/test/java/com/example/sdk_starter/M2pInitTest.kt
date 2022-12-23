package com.example.sdk_starter

import io.mockk.*
import org.junit.Test
import junit.framework.TestCase
import java.util.*
import javax.crypto.Cipher

class M2pInitTest : TestCase() {
    private val key = "1234567890123456"
    private val dataToEncrypt = " M2P Data "

    @Test
    fun test_encrypt() {
        mockkObject(M2pInit)
        mockkStatic(Base64::class)

        val keyByteArray = dataToEncrypt.toByteArray()
        val cipher: Cipher = mockk()

        //Setup
        every { M2pInit.getCipher(key, Cipher.ENCRYPT_MODE) } returns cipher
        every { cipher.doFinal(keyByteArray) } returns keyByteArray
        every { Base64.getEncoder().encodeToString(keyByteArray) } returns "cipherText"

        //Invocation
        val encryptedString = M2pInit.encrypt(dataToEncrypt, key)

        //Assertion
        assertEquals("cipherText", encryptedString)
    }

    @Test
    fun test_decrypt() {
        mockkObject(M2pInit)
        val cipher: Cipher = mockk()
        val encryptedString = M2pInit.encrypt(dataToEncrypt, key)
        val byteString = "M2P Data".toByteArray()

        //setup
        every { M2pInit.getCipher(key, Cipher.DECRYPT_MODE) } returns cipher
        every { cipher.doFinal(Base64.getDecoder().decode(encryptedString)) } returns byteString

        //Invocation
        val assertion = String(byteString)
        val decryptData = encryptedString?.let { M2pInit.decrypt(it, "1234567890123456") }

        //Assertion
        assertEquals(assertion, decryptData)
    }
}
