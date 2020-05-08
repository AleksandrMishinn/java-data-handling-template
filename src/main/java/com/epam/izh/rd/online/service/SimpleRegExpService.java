package com.epam.izh.rd.online.service;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegExpService implements RegExpService {

    /**
     * Метод должен читать файл sensitive_data.txt (из директории resources) и маскировать в нем конфиденциальную информацию.
     * Номер счета должен содержать только первые 4 и последние 4 цифры (1234 **** **** 5678). Метод должен содержать регулярное
     * выражение для поиска счета.
     *
     * @return обработанный текст
     */
    @Override
    public String maskSensitiveData() {

        String processedText = "";
        String textLine;
        File file = new File("src/main/resources/sensitive_data.txt");
        Pattern pattern = Pattern.compile("(\\d{4})\\s(\\d{4})\\s(\\d{4})\\s(\\d{4})");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {

                textLine = bufferedReader.readLine();
                Matcher matcher = pattern.matcher(textLine);

                if (matcher.find()) {
                    processedText = matcher.replaceAll("$1 **** **** $4");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processedText;
    }

    /**
     * Метод должен считыввать файл sensitive_data.txt (из директории resources) и заменять плейсхолдер ${payment_amount} и ${balance} на заданные числа. Метод должен
     * содержать регулярное выражение для поиска плейсхолдеров
     *
     * @return обработанный текст
     */
    @Override
    public String replacePlaceholders(double paymentAmount, double balance) {

        String processedText = "";
        String textLine;
        File file = new File("src/main/resources/sensitive_data.txt");
        Pattern patternPaymentAmount = Pattern.compile("\\$\\{payment_amount}");
        Pattern patternBalance = Pattern.compile("\\$\\{balance}");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (bufferedReader.ready()) {

                textLine = bufferedReader.readLine();

                Matcher matcherPaymentAmount = patternPaymentAmount.matcher(textLine);
                if (matcherPaymentAmount.find()) {
                    processedText = matcherPaymentAmount.replaceAll(String.valueOf((int) paymentAmount));
                }

                Matcher matcherBalance = patternBalance.matcher(processedText);
                if (matcherBalance.find()) {
                    processedText = matcherBalance.replaceAll(String.valueOf((int) balance));
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processedText;
    }
}
