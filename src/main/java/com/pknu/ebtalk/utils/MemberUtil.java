package com.pknu.ebtalk.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberUtil {
    // 문자열에서 영어만 추출
    public static String extractEnglish(String input) {
        // 정규 표현식을 사용하여 영어 알파벳만 추출
        StringBuilder englishText = new StringBuilder();
        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            englishText.append(matcher.group());
        }
        return englishText.toString();
    }
}
