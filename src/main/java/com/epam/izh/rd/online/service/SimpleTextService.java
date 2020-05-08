package com.epam.izh.rd.online.service;

public class SimpleTextService implements TextService {

    /**
     * Реализовать функционал удаления строки из другой строки.
     *
     * Например для базовой строки "Hello, hello, hello, how low?" и строки для удаления ", he"
     * метод вернет "Hellollollo, how low?"
     *
     * @param base - базовая строка с текстом
     * @param remove - строка которую необходимо удалить
     */
    @Override
    public String removeString(String base, String remove) {
        return base.replace(remove, "");
    }

    /**
     * Реализовать функционал проверки на то, что строка заканчивается знаком вопроса.
     *
     * Например для строки "Hello, hello, hello, how low?" метод вернет true
     * Например для строки "Hello, hello, hello!" метод вернет false
     */
    @Override
    public boolean isQuestionString(String text) {
        return text.endsWith("?");
    }

    /**
     * Реализовать функционал соединения переданных строк.
     *
     * Например для параметров {"Smells", " ", "Like", " ", "Teen", " ", "Spirit"}
     * метод вернет "Smells Like Teen Spirit"
     */
    @Override
    public String concatenate(String... elements) {

        StringBuilder concatenatedString = new StringBuilder();

        for (String currentString : elements) {
            concatenatedString.append(currentString);
        }

        return concatenatedString.toString();
    }

    /**
     * Реализовать функционал изменения регистра в вид лесенки.
     * Возвращаемый текст должен начинаться с прописного регистра.
     *
     * Например для строки "Load Up On Guns And Bring Your Friends"
     * метод вернет "lOaD Up oN GuNs aNd bRiNg yOuR FrIeNdS".
     */
    @Override
    public String toJumpCase(String text) {

        char[] words = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(words.length);

        for (int i = 0; i < words.length; i++) {

            if (i % 2 == 1) {
                words[i] = Character.toUpperCase(words[i]);
            } else {
                words[i] = Character.toLowerCase(words[i]);
            }
            stringBuilder.append(words[i]);
        }

        return stringBuilder.toString();
    }

    /**
     * Метод определяет, является ли строка палиндромом.
     *
     * Палиндром - строка, которая одинаково читается слева направо и справа налево.
     *
     * Например для строки "а роза упала на лапу Азора" вернется true, а для "я не палиндром" false
     */
    @Override
    public boolean isPalindrome(String string) {

        if (string.trim().length() == 0) {
            return false;
        }

        String stringWithoutWhitespaces = string.replace(" ", "");
        StringBuilder stringBuilder = new StringBuilder(stringWithoutWhitespaces);
        stringBuilder.reverse();

        return stringBuilder.toString().equalsIgnoreCase(stringWithoutWhitespaces);
    }
}
