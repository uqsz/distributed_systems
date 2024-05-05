package sr.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import subs.Subscribe.*;
import subs.SubscriberGrpc;
import subs.SubscriberGrpc.*;


public class grpcClient 
{
	private final ManagedChannel channel;
	private final SubscriberStub Stub;
	private final StreamObserver<WeatherReply> responseObserverWeather;
    private final StreamObserver<CurrencyReply> responseObserverCurrency;
    private final StreamObserver<DiscountsReply> responseObserverDiscounts;
	public grpcClient(String remoteHost, int remotePort) 
	{
		channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
				.usePlaintext()
				.build();

		Stub = SubscriberGrpc.newStub(channel);

        responseObserverWeather = new StreamObserver<WeatherReply>() {
            @Override
            public void onNext(WeatherReply reply) {
                System.out.println("\n[WEATHER]: " + reply.getIsWeatherOk() + " | "+ reply.getResponseMessage());
            }
            @Override
            public void onError(Throwable t) {
                System.err.println("[WEATHER]: Error: " + t.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("\n[WEATHER]: Stream finished.");
            }

        };

        responseObserverCurrency = new StreamObserver<CurrencyReply>() {
            @Override
            public void onNext(CurrencyReply reply) {
                // Obsługa pojedynczej odpowiedzi
                System.out.println("[CURRENCY]: " + reply.getIsCurrencyOk() +" | Current: " + reply.getCurrentExchange() +" | " + reply.getResponseMessage());
            }
            @Override
            public void onError(Throwable t) {
                System.err.println("[CURRENCY]: Error: " + t.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("[CURRENCY]: Stream finished.");
            }

        };

        responseObserverDiscounts = new StreamObserver<DiscountsReply>() {
            @Override
            public void onNext(DiscountsReply reply) {
                List<Discount> discountsList = reply.getDiscountsList();
                System.out.println("[DISCOUNTS]: " +reply.getHowManyDiscounts() + " | " + reply.getResponseMessage());
                for (Discount discount : discountsList) {
                    System.out.println("\tProduct: " + discount.getDescription());
                    System.out.println("\t\tDiscount: " + discount.getDiscountValue()+"%");
                    System.out.println("\t\tCurrent price: " + discount.getCurrentPrice());
                }
            }
            @Override
            public void onError(Throwable t) {
                System.err.println("[DISCOUNTS]:Error: " + t.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("[DISCOUNTS]: Stream finished.");
            }

        };
	}

	public static void main(String[] args) throws Exception 
	{
		grpcClient client = new grpcClient("localhost", 50051);
		client.test();
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}


	public void test() throws InterruptedException
	{
		String line = null;
		java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

		do 	{
			try	{
				System.out.print("==> ");
				System.out.flush();
				line = in.readLine();
                switch (line) {
                    case "1":
                        WeatherRequest request1 = WeatherRequest.newBuilder().setCity("Cracow").setAcceptableDegrees(20).build();
                        Stub.getCityWeather(request1, responseObserverWeather);
                        break;
                    case "2":
                        CurrencyRequest request2 = CurrencyRequest.newBuilder().setAcceptableAmount(4.32).setCurrencyType(Currency.EUR).build();
                        Stub.getCurrencyExchange(request2, responseObserverCurrency);
                        break;
                    case "3":
                        DiscountsRequest request3 = DiscountsRequest.newBuilder().setShop(Shop.SHOP_1).build();
                        Stub.getDiscounts(request3, responseObserverDiscounts);
                        break;
                    default:
                        break;
                }
			}
			catch (java.io.IOException ex) {
				System.err.println(ex);
			}
		}
		while (!line.equals("x"));
		
		shutdown();
	}
}



	
