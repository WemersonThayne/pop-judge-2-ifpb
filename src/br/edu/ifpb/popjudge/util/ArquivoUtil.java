package br.edu.ifpb.popjudge.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.DatatypeConverter;

/**
 * Classe responsável por salvar e recuperar arquivos em Base64
 *
 */
public class ArquivoUtil {

    /**
     * * Salva arquivo base64 no disco
     *
     * @param base64 - representação do arquivo em base64
     * @param nomeArquivo - nome do arquivo COM A EXTENSÃO
     * @return
     */
    public static String salvarEmDisco(String base64, String nomeArquivo) {

        byte[] bytes = DatatypeConverter
                .parseBase64Binary(base64.contains("base64,") ? base64.split("base64,")[1] : base64);
        Path destinationFile = Paths.get(getDiretorio(), nomeArquivo);
        try {
            return Files.write(destinationFile, bytes).toAbsolutePath().toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Recupera base64 a partir de arquivo
     *
     * @param caminho - caminho absoluto do arquivo
     * @return
     */
    public static String readAsBase64(String caminho) {
        try {
            return DatatypeConverter.printBase64Binary(Files.readAllBytes(Paths.get(caminho)));
        } catch (IOException e) {
            return null;
        }
    }

    public static Boolean remover(String caminho) {
        Path file = Paths.get(caminho);
        try {
            return Files.deleteIfExists(file);
        } catch (IOException ex) {
            return Boolean.FALSE;
        }
    }

    private static String getDiretorio() {
        return System.getProperty("C:\\submisoes");
    }

}
