package org.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Stats implements StatsService {

    private final List<String> integers;
    private final List<String> floats;
    private final List<String> strings;

    public Stats(List<String> integers, List<String> floats, List<String> strings) {
        this.integers = integers;
        this.floats = floats;
        this.strings = strings;
    }

    @Override
    public void printShortStringStats() {
        if (strings.isEmpty()) {
            return;
        }
        System.out.println("\nСтатистика по типу String:");
        System.out.println("Количество: " + strings.size());
    }

    @Override
    public void printShortIntegerStats() {
        if (integers.isEmpty()) {
            return;
        }
        System.out.println("\nСтатистика по типу Integer:");
        System.out.println("Количество: " + integers.size());
    }

    @Override
    public void printShortFloatStats() {
        if (floats.isEmpty()) {
            return;
        }
        System.out.println("\nСтатистика по типу Float:");
        System.out.println("Количество: " + floats.size());
    }

    @Override
    public void printAllShortStats() {
        printShortStringStats();
        printShortIntegerStats();
        printShortFloatStats();
    }

    @Override
    public void printFullStringStats() {
        printShortStringStats();
        try {
            System.out.println("Размер самой короткой строки: " + getMinString(strings));
            System.out.println("Размер самой длинной строки: " + getMaxString(strings));
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    @Override
    public void printFullIntegerStats() {
        printShortIntegerStats();
        try {
            System.out.println("Минимальное число Integer: " + getIntMin(integers));
            System.out.println("Максимальное число Integer: " + getIntMax(integers));
            System.out.println("Сумма чисел Integer: " + getIntSum(integers));
            System.out.println("Среднее арифметическое Integer: " + getIntAverage(integers));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования строки в число: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Список пуст, не может быть найдено необходимое значение: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    @Override
    public void printFullFloatStats() {
        printShortFloatStats();
        try {
            System.out.println("Минимальное число Float: " + getFloatMin(floats));
            System.out.println("Максимальное число Float: " + getFloatMax(floats));
            System.out.println("Сумма чисел Float: " + getFloatSum(floats));
            System.out.println("Среднее арифметическое Float: " + getFloatAverage(floats));
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования строки в число: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.err.println("Список пуст, не может быть найдено необходимое значение: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    @Override
    public void printAllFullStats() {
        printFullStringStats();
        printFullIntegerStats();
        printFullFloatStats();
    }


    private static String getMinString(List<String> strings) {
       return strings.stream()
               .filter(s -> s != null && !s.isEmpty())
               .map(String::length)
               .min(Integer::compare)
               .get().toString();
    }

    private static String getMaxString(List<String> strings) {
        return strings.stream()
                .filter(s -> s != null && !s.isEmpty())
                .map(String::length)
                .max(Integer::compare)
                .get().toString();
    }

    private static String getIntMin(List<String> integers) {
        return integers.stream()
                .map(BigInteger::new)
                .reduce(BigInteger::min)
                .toString();
    }

    private static String getIntMax(List<String> integers) {
        return integers.stream()
                .map(BigInteger::new)
                .reduce(BigInteger::max)
                .toString();
    }

    private static String getIntSum(List<String> integers) {
        return integers.stream()
                .map(BigInteger::new)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }

    private static String getIntAverage(List<String> integers) {
        return integers.stream()
                .map(BigInteger::new)
                .reduce(BigInteger.ZERO, BigInteger::add)
                .divide(BigInteger.valueOf(integers.size()))
                .toString();
    }

    private static String getFloatMin(List<String> numbers) {
        return numbers.stream()
                .map(BigDecimal::new) // Преобразование строки в BigDecimal
                .min(BigDecimal::compareTo)
                .get().toString();
    }

    private static String getFloatMax(List<String> numbers) {
        return numbers.stream()
                .map(BigDecimal::new)
                .max(BigDecimal::compareTo)
                .get().toString();
    }

    private static String getFloatSum(List<String> numbers) {
        return numbers.stream()
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .toString();
    }

    private static String getFloatAverage(List<String> numbers) {
        return numbers.stream()
                .map(BigDecimal::new)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(numbers.size(), BigDecimal.ROUND_HALF_UP))
                .toString();
    }

}
