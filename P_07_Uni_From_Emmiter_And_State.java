package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.concurrent.atomic.AtomicInteger;

public class P_07_Uni_From_Emmiter_And_State {
    public static void main(String[] args) {
        System.out.println("uni from emitter and state");

        Uni<Integer> uniFromEmitterAndState = Uni.createFrom()
                .emitter(AtomicInteger::new, (i, e) -> e.complete(i.addAndGet(10)));

        for (var i = 0; i < 5; i++) {
            uniFromEmitterAndState.subscribe().with(System.out::println);
        }
    }
}
