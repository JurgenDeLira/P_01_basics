package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

public class P_06_Uni_From_Emitter_Await {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("⚡️ Uni from emitter with delay");

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CountDownLatch emitterLatch = new CountDownLatch(1);

        Uni<String> uniFromEmitter = Uni.createFrom().emitter(emitter -> {
            forkJoinPool.submit(() -> {
                try {
                    Thread.sleep(2000); // Simula un trabajo que tarda 2 segundos
                    emitter.complete("✅ ¡Respuesta después de 2 segundos!");
                } catch (InterruptedException e) {
                    emitter.fail(e); // Emite un error si algo sale mal
                }
                emitterLatch.countDown(); // Libera el hilo principal
            });
        });

        uniFromEmitter.subscribe().with(System.out::println);

        System.out.println("⌛ Esperando respuesta...");
        emitterLatch.await(); // Bloquea hasta que countDown() sea llamado
        System.out.println("🏁 Fin del programa");
    }
}