package com.nix.homework;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.DoubleStream;

public class Main {
	public static void main(String[] args) {
	
		TaskOne();
		TaskTwo();
//		 returns strings that don't have a number at the beginning
		TaskThree("^[^\\d].*", new ArrayList<String>(Arrays.asList("1Hello This Is A Test String", "Test Test", "Just Testing")));
		TaskFour(new ArrayList<>(Arrays.asList("level   dog", "peep madam answer", "language car")));
		TaskFive(10);
		TaskSix();
		TaskSeven(new Integer[]{1, 2, 3, 15, 61, 57, 8, 23, 54});
		TaskEight();
	}
	
	public static void TaskOne() {
		System.out.println("\nTask one: find max, min, average and sum of double array");
		double[] doubles = {1.2, 3.2, 6.31, 2.12, 5.43};
		Supplier<DoubleStream> doubleStream = () -> Arrays.stream(doubles);
		
		double max = doubleStream.get().max().getAsDouble();
		double min = doubleStream.get().min().getAsDouble();
		double sum = doubleStream.get().sum();
		double average = doubleStream.get().average().getAsDouble();
		
		System.out.println("Array: " + Arrays.toString(doubles));
		System.out.println(
				"\nmax: " + max
						+ "\nmin: " + min
						+ "\nsum: " + sum
						+ "\naverage: " + average
		);
	}
	
	public static void TaskTwo() {
		System.out.println("\nTask two: returns sorted leap years");
		List<Year> years = new ArrayList<>();
		years.add(Year.of(2001));
		years.add(Year.of(2002));
		years.add(Year.of(2005));
		years.add(Year.of(2012));
		years.add(Year.of(2016));
		
		List<Year> sortedLeapYears = StreamUtils.getSortedLeapYears(years);
		System.out.println("Sorted array of years: " + sortedLeapYears.toString());
	}
	
	public static void TaskThree(String regex, List<String> strings) {
		System.out.println("\nTask three: get strings that match given regex");
		System.out.println("String list: " + strings);
		List<String> result = StreamUtils.matchRegexInStringList(regex, strings);
		System.out.println("Regex matching strings: " + result);
	}
	
	public static void TaskFour(List<String> strings) {
		System.out.println("\nTask four: find string palindromes:");
		List<String> palindromes= StreamUtils.getPalindromeStrings(strings);
		System.out.println("palindromes = " + palindromes);
	}
	
	public static void TaskFive(long n) {
		System.out.println("\nTask five: generate sequence of prime numbers:");
		List<BigInteger> bigIntegers = StreamUtils.getPrimeSequence(n);
		System.out.println("bigIntegers = " + bigIntegers);
	}
	
	public static void TaskSix() {
		System.out.println("\nTask six: count birthdays in each month:");
		List<LocalDate> list = new ArrayList<>();
		
		list.add(LocalDate.of(1992, Month.JUNE, 13));
		list.add(LocalDate.of(1999, Month.APRIL, 10));
		list.add(LocalDate.of(1989, Month.OCTOBER, 3));
		list.add(LocalDate.of(1986, Month.APRIL, 22));
		list.add(LocalDate.of(2000, Month.JUNE, 7));
		
		Map<Month, Long> birthDays = StreamUtils.getBirthDayMonthCount(list);
		System.out.println("birthDays = " + birthDays);
	}
	
	public static void TaskSeven(Integer[] integers) {
		System.out.println("\nTask seven: divide and sort odd and even numbers:");
		System.out.println("Initial list: " + Arrays.toString(integers));
		StreamUtils.divideAndSortIntegerList(integers);
	}
	
	public static void TaskEight() {
		System.out.println("s -> s.toLowerCase()");
		System.out.println("s -> [ + s + ]");
		System.out.println("s -> \" \" + s + \" \"");
		List<UnaryOperator> operators = new ArrayList<>();
		
		UnaryOperator<String> unaryOperator1 = s -> s.toLowerCase();
		UnaryOperator<String> unaryOperator3 = s -> "\"" + s + "\"";
		UnaryOperator<String> unaryOperator2 = s -> "[ " + s + " ]";
		
		operators.add(unaryOperator1);
		operators.add(unaryOperator2);
		operators.add(unaryOperator3);
		
		String strTest = "Test String";
		UnaryOperator composite = StreamUtils.getUnaryOperator(operators);
		System.out.println("Result: " + composite.apply(strTest));
	}
}
