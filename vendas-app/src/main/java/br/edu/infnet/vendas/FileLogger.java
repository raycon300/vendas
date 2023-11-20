package br.edu.infnet.vendas;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FileLogger {

    private static final Logger logger = Logger.getLogger(FileLogger.class.getName());

    private static void initializeLogger(String logFileName) throws IOException {
        FileHandler fileHandler = new FileHandler(logFileName);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
    }

    public static void logException(String mensagem) throws IOException {
        initializeLogger("files/log.txt");
        logger.log(Level.SEVERE, mensagem);
    }
}