package P_01_basics;

import io.smallrye.mutiny.Multi;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class P_15_Multi_From_Emitter {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Multi from emitter");

        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);

        AtomicReference<ScheduledFuture<?>> ref = new AtomicReference<>();
        AtomicInteger counter = new AtomicInteger();
        CountDownLatch latch = new CountDownLatch(1);

        Multi.createFrom().emitter(emitter -> {
            ref.set(service.scheduleAtFixedRate(() -> {
                emitter.emit("tick");
                if (counter.getAndIncrement() == 5) {
                    ref.get().cancel(true);
                    emitter.complete();
                    latch.countDown();
                }
            }, 0, 500, TimeUnit.MILLISECONDS));
        })
                .subscribe().with(System.out::println, Throwable::printStackTrace,
                        () -> System.out.println("Done!"));

        latch.await();
        service.shutdown();
    }
}
