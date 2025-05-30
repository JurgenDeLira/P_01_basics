package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.atomic.AtomicLong;

public class P_05_Uni_Deferred {
    public static void main(String[] args) {
        System.out.println(" Deferred Uni");

        AtomicLong ids = new AtomicLong();

        Uni<Long> deferredUni = Uni.createFrom().deferred(() -> Uni.createFrom().item(ids::incrementAndGet));

        for (var i = 0; i < 5; i++){
            deferredUni.subscribe().with(System.out::println);
        }

    }
}
