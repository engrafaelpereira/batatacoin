package com.batatacoin.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSystem {

	private static final Logger log = LoggerFactory.getLogger(FileSystem.class);

	public static void salvar(String path, String nomeArquivo, String mensagem) {
		try {
			File diretorio = new File(path);
			if (!diretorio.exists()) {
				diretorio.mkdir();
			}
			String caminhoArquivo = String.format("%s%s", path, nomeArquivo);
			File arquivo = new File(caminhoArquivo);
			if (arquivo.exists() && !arquivo.isDirectory()) {
				Files.write(Paths.get(caminhoArquivo), String.format("\n%s", mensagem).getBytes(),
						StandardOpenOption.APPEND);
			} else {
				Files.write(Paths.get(caminhoArquivo), mensagem.getBytes(), StandardOpenOption.CREATE);
			}

		} catch (IOException e) {
			log.error("Erro ao salvar mensagem", e);
		}
	}

	public static String lerUltimaLinha(String path, String nomeArquivo) {
		String ultimaLinha = null;
		try {
			File arquivo = new File(String.format("%s%s", path, nomeArquivo));
			if (arquivo.exists() && !arquivo.isDirectory()) {
				Path pathArquivo = Paths.get(path, nomeArquivo);
				List<String> listaLinhas = Files.readAllLines(pathArquivo);
				ultimaLinha = listaLinhas.get(listaLinhas.size()-1);
			}
		} catch (IOException e) {
			log.error("Erro ao ler última linha do arquivo", e);
		}
		return ultimaLinha;
	}
}
