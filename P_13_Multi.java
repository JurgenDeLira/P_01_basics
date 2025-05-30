package P_01_basics;

import io.smallrye.mutiny.Multi;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class P_13_Multi {
    public static void main(String[] args) {
        System.out.println("Hello world");

        // -------------------------------------------------------------------------------------------------- //

        Multi.createFrom().items(1, 2, 3)
                .subscribe().with(
                        subscription -> {
                            System.out.println("Subscription: " + subscription);
                            subscription.request(10);
                        },
                        item -> System.out.println("item = " + item),
                        failure -> System.out.println("failure = " + failure.getMessage()),
                        () -> System.out.println("Completed"));

        System.out.println("----");

        Multi.createFrom().range(10, 15)
                .subscribe().with(System.out::println);

        var randomNumbers = Stream
                .generate(ThreadLocalRandom.current()::nextInt)
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("----");

        Multi.createFrom().iterable(randomNumbers)
                .subscribe().with(System.out::println);
    }
}
