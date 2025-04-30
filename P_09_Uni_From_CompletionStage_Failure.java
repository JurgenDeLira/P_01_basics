package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class P_09_Uni_From_CompletionStage_Failure {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("⚡️ Uni from CompletionStage - con fallo");

        var cs = CompletableFuture.supplyAsync(() -> {
            throw new RuntimeException("¡Algo salió mal!".toUpperCase());
        }, CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS))
                ;

        Uni.createFrom().completionStage(cs)
                .subscribe().with(
                        item -> System.out.println("Resultado: " + item),
                        failure -> System.out.println("Error: " + failure.getMessage())
                );

        Thread.sleep(2000);
    }
}
