package com.dada.javaparser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;


public class VoidVisitorStarter {

	private static class MethodNamePrinter extends VoidVisitorAdapter<Void> {
		@Override
		public void visit(MethodDeclaration md, Void arg) {
			super.visit(md, arg);
			System.out.println("Method Name Printed: " + md.getName());
		}
	}
	
	private static class MethodNameCollector extends VoidVisitorAdapter<List<String>> {
		@Override
		public void visit(MethodDeclaration md, List<String> collector) {
			super.visit(md, collector);		
			collector.add(md.getNameAsString());
		}
	}
	
	private static class MethodNameCompare extends VoidVisitorAdapter<List<String>> {
		@Override
		public void visit(MethodDeclaration md, List<String> collector) {
			super.visit(md, collector);
			//System.out.println(md.getBody().toString());
			collector.add(md.getNameAsString());
			collector.add(md.getBody().toString());
		}
	}
	
	private static final String FILE_PATH = "src/main/java/com/dada/javaparser/ReversePolishNotation.java";
	private static final String FILE_PATH_1 = "src/main/java/com/dada/javaparser/Test.java";
	public static void main(String[] args) throws Exception {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		//System.out.println(cu);
//		VoidVisitor<Void> methodNameVisitor = new MethodNamePrinter();	
//		methodNameVisitor.visit(cu, null);
		
		List<String> methodNames = new ArrayList<>();
		List<String> methodNames_2 = new ArrayList<>();
		//VoidVisitor<List<String>> methodNameCollector = new MethodNameCollector();
//		methodNameCollector.visit(cu, methodNames);
//		methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
		
		
		VoidVisitor<List<String>> methodNameCompare = new MethodNameCompare();
		methodNameCompare.visit(cu, methodNames_2);
		methodNames_2.forEach(n -> System.out.println("Method Name Collected: " + n));
		System.out.println("*************************************");
		cu = StaticJavaParser.parse(new File(FILE_PATH_1));
		
		methodNameCompare.visit(cu, methodNames);
		methodNames.forEach(n -> System.out.println("Method Name Collected: " + n));
		
		
		for(int i=0;i<methodNames.size();i=i+2) {
			if(methodNames.get(i+1).equals(methodNames_2.get(i+1))) continue;
			System.out.println("Method Name is: " + methodNames.get(i));
		}
	}
	
	
}
