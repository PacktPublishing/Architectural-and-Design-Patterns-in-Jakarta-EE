package com.packtpub.jeepatterns.behavioral.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class VerySimpleFileVisitor extends SimpleFileVisitor<Path> {
	@Override
	public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs) throws IOException {
		System.out.println(directory);
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println(file.getFileName());
		return FileVisitResult.CONTINUE;
	}
}
