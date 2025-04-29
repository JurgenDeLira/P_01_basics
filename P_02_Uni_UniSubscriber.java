package P_01_basics;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.UniSubscriber;
import io.smallrye.mutiny.subscription.UniSubscription;

public class P_02_Uni_UniSubscriber {
    public static void main(String[] args) {
        System.out.println(" Hola mundo con UniSubscriber");

        Uni<String> uni = Uni.createFrom().item("Hello, world");

        uni.subscribe().withSubscriber(new UniSubscriber<String>() {
            @Override
            public void onSubscribe(UniSubscription subscription) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onItem(String item) {
                System.out.println("onItem = " + item);
            }

            @Override
            public void onFailure(Throwable failure) {
                System.out.println("onFailure = " + failure.getMessage());

            }
        });
    }
}
