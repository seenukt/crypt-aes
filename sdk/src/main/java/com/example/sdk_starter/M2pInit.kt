package com.example.sdk_starter

import android.util.Log
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object M2pInit {
    /**
     * AES Decryption
     */

    fun decrypt(
        cipherText: String,
        key: String
    ): String? {
        try {
            val cipher = getCipher(key, Cipher.DECRYPT_MODE)
            val plainText = cipher.doFinal(Base64.getDecoder().decode(cipherText))
            return String(plainText)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
            Log.d("Exception", "decrypt: ${e.message}")
        } catch (e: UnsupportedOperationException) {
            e.printStackTrace()
            Log.d("Exception", "decrypt: ${e.printStackTrace()}")
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
            Log.d("Exception", "decrypt: ${e.printStackTrace()}")
        }
        return null
    }

    /**
     *AES Encryption
     */
    fun encrypt(txtToEncrypt: String, key: String): String? {
        try {
            val cipher = getCipher(key, Cipher.ENCRYPT_MODE)
            val cipherText = cipher.doFinal(txtToEncrypt.toByteArray())
            return Base64.getEncoder().encodeToString(cipherText)
        } catch (e: InvalidKeyException) {
            Log.d("Exception", "decrypt: ${e.message}")
        } catch (e: UnsupportedOperationException) {
            Log.d("Exception", "decrypt: ${e.printStackTrace()}")
        } catch (e: InvalidAlgorithmParameterException) {
            Log.d("Exception", "decrypt: ${e.printStackTrace()}")
        }
        return null
    }

    /**
     *Get Cipher
     */
    fun getCipher(key: String, mode: Int): Cipher {
        val secretKeySpec = SecretKeySpec(key.toByteArray(), "AES")
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(mode, secretKeySpec, IvParameterSpec(ByteArray(16)))
        return cipher
    }
}