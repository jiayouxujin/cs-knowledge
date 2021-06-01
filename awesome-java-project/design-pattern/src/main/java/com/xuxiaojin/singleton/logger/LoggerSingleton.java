package com.xuxiaojin.singleton.logger;

import com.xuxiaojin.singleton.Singleton;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class LoggerSingleton {

    private Writer writer;

    private static LoggerSingleton logger = new LoggerSingleton();

    private LoggerSingleton() {
        File file = new File("log.txt");
        try {
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LoggerSingleton getLogger() {
        return logger;
    }

    public void log(String message){
        try {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
