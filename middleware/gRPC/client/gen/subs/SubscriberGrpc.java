package subs;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: subscribe.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SubscriberGrpc {

  private SubscriberGrpc() {}

  public static final java.lang.String SERVICE_NAME = "subs.Subscriber";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<subs.Subscribe.WeatherRequest,
      subs.Subscribe.WeatherReply> getGetCityWeatherMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCityWeather",
      requestType = subs.Subscribe.WeatherRequest.class,
      responseType = subs.Subscribe.WeatherReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<subs.Subscribe.WeatherRequest,
      subs.Subscribe.WeatherReply> getGetCityWeatherMethod() {
    io.grpc.MethodDescriptor<subs.Subscribe.WeatherRequest, subs.Subscribe.WeatherReply> getGetCityWeatherMethod;
    if ((getGetCityWeatherMethod = SubscriberGrpc.getGetCityWeatherMethod) == null) {
      synchronized (SubscriberGrpc.class) {
        if ((getGetCityWeatherMethod = SubscriberGrpc.getGetCityWeatherMethod) == null) {
          SubscriberGrpc.getGetCityWeatherMethod = getGetCityWeatherMethod =
              io.grpc.MethodDescriptor.<subs.Subscribe.WeatherRequest, subs.Subscribe.WeatherReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCityWeather"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.WeatherRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.WeatherReply.getDefaultInstance()))
              .setSchemaDescriptor(new SubscriberMethodDescriptorSupplier("GetCityWeather"))
              .build();
        }
      }
    }
    return getGetCityWeatherMethod;
  }

  private static volatile io.grpc.MethodDescriptor<subs.Subscribe.CurrencyRequest,
      subs.Subscribe.CurrencyReply> getGetCurrencyExchangeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrencyExchange",
      requestType = subs.Subscribe.CurrencyRequest.class,
      responseType = subs.Subscribe.CurrencyReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<subs.Subscribe.CurrencyRequest,
      subs.Subscribe.CurrencyReply> getGetCurrencyExchangeMethod() {
    io.grpc.MethodDescriptor<subs.Subscribe.CurrencyRequest, subs.Subscribe.CurrencyReply> getGetCurrencyExchangeMethod;
    if ((getGetCurrencyExchangeMethod = SubscriberGrpc.getGetCurrencyExchangeMethod) == null) {
      synchronized (SubscriberGrpc.class) {
        if ((getGetCurrencyExchangeMethod = SubscriberGrpc.getGetCurrencyExchangeMethod) == null) {
          SubscriberGrpc.getGetCurrencyExchangeMethod = getGetCurrencyExchangeMethod =
              io.grpc.MethodDescriptor.<subs.Subscribe.CurrencyRequest, subs.Subscribe.CurrencyReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCurrencyExchange"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.CurrencyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.CurrencyReply.getDefaultInstance()))
              .setSchemaDescriptor(new SubscriberMethodDescriptorSupplier("GetCurrencyExchange"))
              .build();
        }
      }
    }
    return getGetCurrencyExchangeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<subs.Subscribe.DiscountsRequest,
      subs.Subscribe.DiscountsReply> getGetDiscountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDiscounts",
      requestType = subs.Subscribe.DiscountsRequest.class,
      responseType = subs.Subscribe.DiscountsReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<subs.Subscribe.DiscountsRequest,
      subs.Subscribe.DiscountsReply> getGetDiscountsMethod() {
    io.grpc.MethodDescriptor<subs.Subscribe.DiscountsRequest, subs.Subscribe.DiscountsReply> getGetDiscountsMethod;
    if ((getGetDiscountsMethod = SubscriberGrpc.getGetDiscountsMethod) == null) {
      synchronized (SubscriberGrpc.class) {
        if ((getGetDiscountsMethod = SubscriberGrpc.getGetDiscountsMethod) == null) {
          SubscriberGrpc.getGetDiscountsMethod = getGetDiscountsMethod =
              io.grpc.MethodDescriptor.<subs.Subscribe.DiscountsRequest, subs.Subscribe.DiscountsReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDiscounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.DiscountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  subs.Subscribe.DiscountsReply.getDefaultInstance()))
              .setSchemaDescriptor(new SubscriberMethodDescriptorSupplier("GetDiscounts"))
              .build();
        }
      }
    }
    return getGetDiscountsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubscriberStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriberStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriberStub>() {
        @java.lang.Override
        public SubscriberStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriberStub(channel, callOptions);
        }
      };
    return SubscriberStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubscriberBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriberBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriberBlockingStub>() {
        @java.lang.Override
        public SubscriberBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriberBlockingStub(channel, callOptions);
        }
      };
    return SubscriberBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubscriberFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubscriberFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubscriberFutureStub>() {
        @java.lang.Override
        public SubscriberFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubscriberFutureStub(channel, callOptions);
        }
      };
    return SubscriberFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getCityWeather(subs.Subscribe.WeatherRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.WeatherReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCityWeatherMethod(), responseObserver);
    }

    /**
     */
    default void getCurrencyExchange(subs.Subscribe.CurrencyRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.CurrencyReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCurrencyExchangeMethod(), responseObserver);
    }

    /**
     */
    default void getDiscounts(subs.Subscribe.DiscountsRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.DiscountsReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDiscountsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Subscriber.
   */
  public static abstract class SubscriberImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SubscriberGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Subscriber.
   */
  public static final class SubscriberStub
      extends io.grpc.stub.AbstractAsyncStub<SubscriberStub> {
    private SubscriberStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriberStub(channel, callOptions);
    }

    /**
     */
    public void getCityWeather(subs.Subscribe.WeatherRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.WeatherReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetCityWeatherMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getCurrencyExchange(subs.Subscribe.CurrencyRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.CurrencyReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetCurrencyExchangeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDiscounts(subs.Subscribe.DiscountsRequest request,
        io.grpc.stub.StreamObserver<subs.Subscribe.DiscountsReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetDiscountsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Subscriber.
   */
  public static final class SubscriberBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SubscriberBlockingStub> {
    private SubscriberBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriberBlockingStub(channel, callOptions);
    }

    /**
     */
    public java.util.Iterator<subs.Subscribe.WeatherReply> getCityWeather(
        subs.Subscribe.WeatherRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetCityWeatherMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<subs.Subscribe.CurrencyReply> getCurrencyExchange(
        subs.Subscribe.CurrencyRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetCurrencyExchangeMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<subs.Subscribe.DiscountsReply> getDiscounts(
        subs.Subscribe.DiscountsRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetDiscountsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Subscriber.
   */
  public static final class SubscriberFutureStub
      extends io.grpc.stub.AbstractFutureStub<SubscriberFutureStub> {
    private SubscriberFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubscriberFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubscriberFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_CITY_WEATHER = 0;
  private static final int METHODID_GET_CURRENCY_EXCHANGE = 1;
  private static final int METHODID_GET_DISCOUNTS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_CITY_WEATHER:
          serviceImpl.getCityWeather((subs.Subscribe.WeatherRequest) request,
              (io.grpc.stub.StreamObserver<subs.Subscribe.WeatherReply>) responseObserver);
          break;
        case METHODID_GET_CURRENCY_EXCHANGE:
          serviceImpl.getCurrencyExchange((subs.Subscribe.CurrencyRequest) request,
              (io.grpc.stub.StreamObserver<subs.Subscribe.CurrencyReply>) responseObserver);
          break;
        case METHODID_GET_DISCOUNTS:
          serviceImpl.getDiscounts((subs.Subscribe.DiscountsRequest) request,
              (io.grpc.stub.StreamObserver<subs.Subscribe.DiscountsReply>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetCityWeatherMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              subs.Subscribe.WeatherRequest,
              subs.Subscribe.WeatherReply>(
                service, METHODID_GET_CITY_WEATHER)))
        .addMethod(
          getGetCurrencyExchangeMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              subs.Subscribe.CurrencyRequest,
              subs.Subscribe.CurrencyReply>(
                service, METHODID_GET_CURRENCY_EXCHANGE)))
        .addMethod(
          getGetDiscountsMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              subs.Subscribe.DiscountsRequest,
              subs.Subscribe.DiscountsReply>(
                service, METHODID_GET_DISCOUNTS)))
        .build();
  }

  private static abstract class SubscriberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SubscriberBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return subs.Subscribe.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Subscriber");
    }
  }

  private static final class SubscriberFileDescriptorSupplier
      extends SubscriberBaseDescriptorSupplier {
    SubscriberFileDescriptorSupplier() {}
  }

  private static final class SubscriberMethodDescriptorSupplier
      extends SubscriberBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    SubscriberMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SubscriberGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubscriberFileDescriptorSupplier())
              .addMethod(getGetCityWeatherMethod())
              .addMethod(getGetCurrencyExchangeMethod())
              .addMethod(getGetDiscountsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
