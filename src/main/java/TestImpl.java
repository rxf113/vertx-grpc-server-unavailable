import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.examples.helloworld.VertxGreeterGrpc;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.net.SocketAddress;
import io.vertx.grpc.client.GrpcClient;
import io.vertx.grpc.client.GrpcClientChannel;


/**
 * Test: When the server is not started, use VertxStub to send requests without any logs or exceptions
 */
public class TestImpl {

    Vertx vertx = Vertx.vertx();

    public static void main(String[] args) {
        TestImpl test = new TestImpl();
        test.test();
    }

    public void test() {

        //server not started

        GrpcClient client = GrpcClient.client(vertx);
        GrpcClientChannel channel = new GrpcClientChannel(client, SocketAddress.inetSocketAddress(8080, "localhost"));
        VertxGreeterGrpc.GreeterVertxStub stub = VertxGreeterGrpc.newVertxStub(channel);

        // ==========================================================
        Future<HelloReply> naturalFuture = stub.sayHello(HelloRequest.newBuilder().setName("hello").build());
        naturalFuture.onComplete(asyncResponse -> {
            if (asyncResponse.succeeded()) {
                String message = asyncResponse.result().getMessage();
                System.out.println("received msg: " + message);
            } else {
                Throwable cause = asyncResponse.cause();
                System.err.println(cause);
            }
        });

        // ==========================================================
        stub.sayHello((HelloRequest.newBuilder().setName("hello").build()))
                .onComplete(asyncResponse -> {
                    if (asyncResponse.succeeded()) {
                        String message = asyncResponse.result().getMessage();
                        System.out.println("received msg: " + message);
                    } else {
                        System.err.println(asyncResponse.cause());
                    }
                });


        // ==========================================================
        stub.sayHello((HelloRequest.newBuilder().setName("hello").build()))
                .onComplete(asyncResponse -> {
                    if (asyncResponse.succeeded()) {
                        String message = asyncResponse.result().getMessage();
                        System.out.println("received msg: " + message);
                    } else {
                        System.err.println(asyncResponse.cause());
                    }
                });
    }
}
