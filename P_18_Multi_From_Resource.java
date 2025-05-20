package P_01_basics;

import io.smallrye.mutiny.Multi;

public class P_18_Multi_From_Resource {
    public static void main(String[] args) {
        System.out.println("Multi from resource");

        Multi.createFrom()
                .resource(MyResource::new, MyResource::stream)
                .withFinalizer(MyResource::close)
                .subscribe().with(System.out::println);

    }

    static class MyResource {
        public Multi<Integer> stream() {
            System.out.println("stream");
            return Multi.createFrom().range(0, 10);
        }

        public void close() {
            System.out.println("close()");
        }
    }
}
