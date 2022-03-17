package com.dada.javaparser;

import java.io.File;
import java.util.regex.Pattern;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.visitor.ModifierVisitor;

public class ModifyingVisitorStarter {

	private static class IntegerLiteralModifier extends ModifierVisitor<Void> { 
		@Override
		public FieldDeclaration visit(FieldDeclaration fd, Void arg) { 
			super.visit(fd, arg);
			fd.getVariables().forEach(v -> 
			v.getInitializer().ifPresent(i -> 
			i.ifIntegerLiteralExpr(il -> 
			v.setInitializer(formatWithUnderscores(il.getValue())) 
			) 
		  ) 
		);
			return fd; 
		} 
	}
	
	private static final Pattern LOOK_AHEAD_THREE = Pattern.compile("(\\d)(?=(\\d{3})+$)");
	
	static String formatWithUnderscores(String value){ 
		String withoutUnderscores = value.replaceAll("_", ""); 
		System.out.println("Java Language");
		return LOOK_AHEAD_THREE.matcher(withoutUnderscores).replaceAll("$1_");	
	}
	
	private static final String FILE_PATH = "src/main/java/com/dada/javaparser/ReversePolishNotation.java";
	public static void main(String[] args) throws Exception {
		CompilationUnit cu = StaticJavaParser.parse(new File(FILE_PATH));
		ModifierVisitor<?> numericLiteralVisitor = new IntegerLiteralModifier(); 
		numericLiteralVisitor.visit(cu, null);
		System.out.println(cu.toString());
	}
}
