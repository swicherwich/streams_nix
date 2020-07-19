package com.nix.homework;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class StreamUtils {


	public static List<String> matchRegexInStringList(String regex, List<String> strings) {
		List<String> regexStrings = new ArrayList<>();
		Pattern pattern = Pattern.compile(regex);
		strings.stream().forEach(string -> {
			Matcher matcher = pattern.matcher(string);
			while (matcher.find()) {
				regexStrings.add(string);
			}
		});
		return regexStrings;
	}

	public static List<Year> getSortedLeapYears(List<Year> years) {
		return years.stream().filter(Year::isLeap).sorted().collect(Collectors.toList());
	}
	
	public static List<String> getPalindromeStrings(List<String> strings) {
		List<String> palindromes = new ArrayList<>();
		strings.stream().forEach(s -> {
			String[] stringArray = s.split("[\\s]+");
			for(String str: stringArray) {
				StringBuilder sb = new StringBuilder(str);
				if(sb.reverse().toString().equals(str)) {
					palindromes.add(sb.toString());
				}
			}
		});
		return palindromes;
	}
	
	private static boolean isPrime(BigInteger bi) {
		for (int i = 2; i < bi.intValue(); ++i) {
			if (bi.intValue() % i == 0) return false;
		}
		return true;
	}
	public static List<BigInteger> getPrimeSequence(long n) {
		Stream<BigInteger> bigIntegerStream = Stream.iterate(BigInteger.ONE, number -> number.add(BigInteger.ONE));
		return  bigIntegerStream.filter(StreamUtils::isPrime).limit(n).collect(Collectors.toList());
	}
	
	public static Map<Month, Long> getBirthDayMonthCount(List<LocalDate> bDays) {
		return bDays.stream().collect(groupingBy(LocalDate::getMonth, counting()));
	}
	
	public static void divideAndSortIntegerList(Integer[] array) {
		Supplier<Stream<Integer>> streamSupplier = () -> Arrays.stream(array);
		System.out.print("Even numbers: ");
		streamSupplier.get().filter(i -> (i % 2 == 0)).sorted(Comparator.reverseOrder()).forEach(i -> System.out.print(i + " "));
		System.out.print("\nOdd numbers: ");
		streamSupplier.get().filter(i -> (i % 2 != 0)).sorted(Comparator.reverseOrder()).forEach(i -> System.out.print(i + " "));
	}
	
	public static UnaryOperator applyUnaryOperator(List<UnaryOperator> operators) {
		return operators.stream().reduce(s -> s, (a, b) -> s -> b.apply(a.apply(s)));
	}
}
