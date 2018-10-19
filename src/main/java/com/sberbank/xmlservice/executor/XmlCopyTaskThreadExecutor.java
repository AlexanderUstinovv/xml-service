package com.sberbank.xmlservice.executor;

import com.sberbank.xmlservice.util.XmlFileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class XmlCopyTaskThreadExecutor implements CopyTaskThreadExecutor {

    @Override
    public List<Map<String, String>> copyFiles(String[] fileList, String destination) {
        var taskList = new ArrayList<Callable<Map<String, String>>>();
        var fileHandler = new XmlFileHandler();

        for (var item : fileList) {
            taskList.add(new CopyTask(item, destination, fileHandler));
        }

        var executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Future<Map<String, String>>> taskResults = null;

        try {
            taskResults = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            executorService.shutdown();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.out.println("Some tasks interrupted");
        }
        finally {
            if (!executorService.isTerminated()) {
                System.out.println("Non-finished tasks canceled");
            }
            executorService.shutdownNow();
        }

        List<Map<String, String>> results = null;

        if (taskResults != null && !taskResults.isEmpty()) {
            results = new ArrayList<>();
            for (var item : taskResults) {
                try {
                    results.add(item.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        return results;
    }

    private final int numberOfThreads = Runtime.getRuntime().availableProcessors() + 1;
}
