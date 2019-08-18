package com.packtpub.jeepatterns.behavioral.visitor;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class IndentedFileVisitor extends SimpleFileVisitor<Path> {
	int indent = 0;

	@Override
	public FileVisitResult preVisitDirectory(Path directory, BasicFileAttributes attrs) throws IOException {
		System.out.println(directory);
		indent += 4;
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		for (int i = 0; i < indent; i++) {
			System.out.print(' ');
		}
		System.out.println(file.getFileName());
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path directory, IOException exc) throws IOException {
		indent -= 4;
		return FileVisitResult.CONTINUE;
	}
}
