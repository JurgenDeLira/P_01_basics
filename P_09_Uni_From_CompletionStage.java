package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class P_09_Uni_From_CompletionStage {
    public static void main(String[] args) throws InterruptedException{
        System.out.println(" Uni from Completion Stage");

        var cs = CompletableFuture
                .supplyAsync(() -> "Hello!", CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS))
                .thenApply(String::toUpperCase);

        Uni.createFrom().completionStage(cs)
                .subscribe().with(System.out::println, failure -> System.out.println(failure.getMessage()));

        Thread.sleep(2000);
    }
}
