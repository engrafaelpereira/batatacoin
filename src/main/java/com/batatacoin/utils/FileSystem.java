package com.batatacoin.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class FileSystem {
	
	private static final Logger log = LoggerFactory.getLogger(FileSystem.class);
	
	public static void salvar(String path, String nomeArquivo, String mensagem) {
		try {
			File diretorio = new File(path);
			if(!diretorio.exists()) {
				diretorio.mkdir();
			}
			String caminhoArquivo = String.join(path, nomeArquivo);
			File arquivo = new File(caminhoArquivo);
			if(arquivo.exists() && !arquivo.isDirectory()) { 
				Files.write(Paths.get(caminhoArquivo), mensagem.getBytes(), StandardOpenOption.APPEND);
			} else {
				Files.write(Paths.get(caminhoArquivo), mensagem.getBytes(), StandardOpenOption.CREATE);
			}
			
		} catch (IOException e) {
			log.error("Erro ao salvar mensagem", e);
		}
	}
}
