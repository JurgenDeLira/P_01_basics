package P_01_basics;

import io.smallrye.mutiny.Uni;

public class P_01_Uni {

    public static void main(String[] args) {
        System.out.println("Hola mundo!");

        Uni<String> uni = Uni.createFrom().item("Hello, world!");

        uni.subscribe().with(System.out::println);

    }
}
