package jp.ne.keisuke.kiuchi.devicekanrisan.utils.commons

import java.util.regex.Pattern

class QRCodeDecorder {
    companion object {
        fun decodeUserInfo(text: String): List<String> {
            return text.split(Pattern.compile("\\s{5,}"))
        }

        fun decodeDeviceInfo(text: String): List<String> {
            return text.split(",")
        }
    }
}