package com.mz_dev.prueba_tecnica.core

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MarvelApiAuth {
    companion object{
        private const val publicKey = "24487a97e5270272301c01d3f8f2e112"
        private const val privateKey = "91a5bfa3f5131cbd30e5587f7200ca67ef8e863a"
    }


    fun generateTimestamp(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date())
    }

    private fun md5(input: String): String {
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            val digest = md5.digest(input.toByteArray())
            val result = StringBuilder(digest.size * 2)

            for (b in digest) {
                result.append(String.format("%02x", b))
            }

            return result.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    fun generateHash(timestamp: String): String {
        val input = "$timestamp$privateKey$publicKey"
        return md5(input)
    }

    fun getPublicApiKey(): String{
        return publicKey
    }
}