package P_01_basics;

import io.smallrye.mutiny.Uni;

import java.util.List;

public class P_12_Uni_Disjoint {
    public static void main(String[] args) {
        System.out.println("Uni disjoint");

        Uni.createFrom().item(List.of(1, 2, 3, 4, 5))
                .onItem().disjoint()
                .subscribe().with(System.out::println);
    }
}