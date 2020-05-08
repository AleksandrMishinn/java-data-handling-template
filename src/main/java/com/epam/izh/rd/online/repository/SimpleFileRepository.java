package com.epam.izh.rd.online.repository;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleFileRepository implements FileRepository {

    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */
    @Override
    public long countFilesInDirectory(String path) {

        File[] files = getFilesInDirectory(path);

        long numberOfFiles = 0;

        for (File currentFile : files) {

            if (currentFile.isDirectory()) {
                numberOfFiles += countFilesInDirectory(path + "/" + currentFile.getName());
            } else {
                numberOfFiles++;
            }
        }

        return numberOfFiles;
    }


    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {

        File[] files = getFilesInDirectory(path);

        long numberOfDirectory = 1;

        for (File currentFile : files) {
            if (currentFile.isDirectory()) {
                numberOfDirectory += countDirsInDirectory(path + "/" + currentFile.getName());
            }
        }

        return numberOfDirectory;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {

        File[] filesFrom = getFilesInDirectory(from);

        for (File currentFile : filesFrom) {
            try {
                Files.copy(currentFile.toPath(), Paths.get(getPathToResourceDirectory(to + "/" + currentFile.getName())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {

        File creatingFile = new File(getPathToResourceDirectory(path) + "/" + name);

        try {
            return creatingFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {

        File ReadingFile = new File("src/main/resources/" + fileName);
        StringBuilder fileContents = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ReadingFile))) {

            while (bufferedReader.ready()) {
                fileContents.append(bufferedReader.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContents.toString();
    }

    private File[] getFilesInDirectory(String directoryPath) {
        File directory = new File(getPathToResourceDirectory(directoryPath));
        return directory.listFiles();
    }

    private String getPathToResourceDirectory(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);

        return resource.getPath();
    }

}
