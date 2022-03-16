package com.dada.javaparser;

import java.util.Stack;
import java.util.stream.Stream;

public class Test {
	
	public static int ONE_BILLION = 1000000000;
	private double memory = 0;
	
	public Double calc(String input) {
		String[] tokens = input.split(" ");
		Stack<Double> numbers = new Stack<>();
		Stream.of(tokens).forEach(t -> {
			double a;
			double b;
			switch(t){
			case "+":
				b = numbers.pop();
				a = numbers.pop();
				numbers.push(a + b);
				break;
			case "/":
				b = numbers.pop();
				a = numbers.pop();
				numbers.push(a / b);
				break;
			case "-":
				b = numbers.pop();
				a = numbers.pop();
				numbers.push(a - b);
				break;
			case "*":
				b = numbers.pop();
				a = numbers.pop();
				numbers.push(a * b);
				break;
			default:
				numbers.push(Double.valueOf(t));
			}
		});
		return numbers.pop();
	}
	public double memoryRecall(){
		this.memory++;
		return memory;
	}
	
	public void memoryClear(){
		memory = 0;
	}
	
	public void memoryStore(double value){
		memory = value;
	}

}
