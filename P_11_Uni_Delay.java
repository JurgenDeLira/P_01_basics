package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class P_11_Uni_Delay {
    public static void main(String[] args) {
        System.out.println("Uni delay");

        Uni.createFrom().item(666)
                .onItem().delayIt().by(Duration.ofSeconds(1))
                .subscribe().with(System.out::println);

        System.out.println("⏰");

        Uni.createFrom().item(666)
                .onItem().delayIt()
                .until(n -> Uni.createFrom().completionStage(
                        supplyAsync(
                                () -> "Ok",
                                CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS))))
                .subscribe().with(System.out::println);
    }
}
