### Test not start server, use VertxStub send request

#### Run:  class TestImpl main()

#### desc:  When the server is unavailable, the client does not have any exception logs


#### Replenish :
The **VertxClientCall** under the **io.vertx.grpc.client** package was modified by me. 
If uncommented, it overrides `io.vertx.grpc.client.VertxClientCall` in **vertx-grpc-client**.
Running TestUmlp again will correctly print the error