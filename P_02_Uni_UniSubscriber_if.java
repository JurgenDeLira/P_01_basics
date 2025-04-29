package P_01_basics;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.UniSubscriber;
import io.smallrye.mutiny.subscription.UniSubscription;

public class P_02_Uni_UniSubscriber_if {
    public static void main(String[] args) {
        System.out.println(" Hola mundo con UniSubscriber");

        String nombre = "error"; // Cambia a otro valor como "juan" para evitar el error

        Uni<String> uni = Uni.createFrom().item(nombre)
                .onItem().transformToUni(item -> {
                    if ("error".equalsIgnoreCase(item)) {
                        return Uni.createFrom().failure(new RuntimeException("Nombre no permitido"));
                    } else {
                        return Uni.createFrom().item("Bienvenido, " + item);
                    }
                });

        uni.subscribe().withSubscriber(new UniSubscriber<String>() {
            @Override
            public void onSubscribe(UniSubscription subscription) {
                System.out.println("onSubscribe");
                subscription.request(1); // Necesario para que funcione correctamente
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