package org.example.schedexecservtest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        AtomicInteger esCounter = new AtomicInteger(); //AtomicInteger для требования effective final в лямбде
        final int iterations = 10;
        final Thread mainThread = Thread.currentThread();

        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
        es.scheduleWithFixedDelay(() -> {
            esCounter.getAndIncrement();
            System.out.println("Executor service, esCounter=" + esCounter);

            //mainThread.interrupt();

            if (esCounter.get() >= iterations) {
                es.shutdown();
            }
        }, 3, 3, TimeUnit.SECONDS);
        try {
            if (es.awaitTermination(20, TimeUnit.SECONDS)) {
                //если поставить точку останова внутри лямбды, и таймаут случится раньше, чем лямбда выполнится iterations число раз -
                // лямбда в любом случае выполнится iterations раза, просто мы не будем её ждать в main потоке.
                // Но программа не завершится, пока не завершится поток es, т.е. пока лямбда не выполнится iterations число раз.
                System.out.println("Executor service completed before timeout");
            } else {
                System.out.println("Executor service completed by timeout");
                //Если поместить сюда es.shutdown(), то es не будет принимать новые задачи по истечении таймаута, и программа завершится.
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception caught"); //несмотря на исключение, лямбда всё равно выполнится iteration число раз
            e.printStackTrace();
        }
    }
}
