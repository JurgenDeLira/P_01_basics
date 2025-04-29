package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.io.IOException;

public class P_08_Uni_From_Failure {
    public static void main(String[] args) {
        System.out.println("Uni from failure");

        Uni.createFrom().failure(new IOException("Boom"))
                .subscribe().with(System.out::println, failure -> System.out.println(failure.getMessage()));

        Uni.createFrom().failure(() -> new IOException("Badaboom"))
                .subscribe().with(System.out::println, failure -> System.out.println(failure.getMessage()));
    }
}
