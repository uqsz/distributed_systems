# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc
import warnings

import subscribe_pb2 as subscribe__pb2

GRPC_GENERATED_VERSION = '1.63.0'
GRPC_VERSION = grpc.__version__
EXPECTED_ERROR_RELEASE = '1.65.0'
SCHEDULED_RELEASE_DATE = 'June 25, 2024'
_version_not_supported = False

try:
    from grpc._utilities import first_version_is_lower
    _version_not_supported = first_version_is_lower(GRPC_VERSION, GRPC_GENERATED_VERSION)
except ImportError:
    _version_not_supported = True

if _version_not_supported:
    warnings.warn(
        f'The grpc package installed is at version {GRPC_VERSION},'
        + f' but the generated code in subscribe_pb2_grpc.py depends on'
        + f' grpcio>={GRPC_GENERATED_VERSION}.'
        + f' Please upgrade your grpc module to grpcio>={GRPC_GENERATED_VERSION}'
        + f' or downgrade your generated code using grpcio-tools<={GRPC_VERSION}.'
        + f' This warning will become an error in {EXPECTED_ERROR_RELEASE},'
        + f' scheduled for release on {SCHEDULED_RELEASE_DATE}.',
        RuntimeWarning
    )


class SubscriberStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.GetCityWeather = channel.unary_stream(
                '/subs.Subscriber/GetCityWeather',
                request_serializer=subscribe__pb2.WeatherRequest.SerializeToString,
                response_deserializer=subscribe__pb2.WeatherReply.FromString,
                _registered_method=True)
        self.GetCurrencyExchange = channel.unary_stream(
                '/subs.Subscriber/GetCurrencyExchange',
                request_serializer=subscribe__pb2.CurrencyRequest.SerializeToString,
                response_deserializer=subscribe__pb2.CurrencyReply.FromString,
                _registered_method=True)
        self.GetDiscounts = channel.unary_stream(
                '/subs.Subscriber/GetDiscounts',
                request_serializer=subscribe__pb2.DiscountsRequest.SerializeToString,
                response_deserializer=subscribe__pb2.DiscountsReply.FromString,
                _registered_method=True)


class SubscriberServicer(object):
    """Missing associated documentation comment in .proto file."""

    def GetCityWeather(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def GetCurrencyExchange(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def GetDiscounts(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_SubscriberServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'GetCityWeather': grpc.unary_stream_rpc_method_handler(
                    servicer.GetCityWeather,
                    request_deserializer=subscribe__pb2.WeatherRequest.FromString,
                    response_serializer=subscribe__pb2.WeatherReply.SerializeToString,
            ),
            'GetCurrencyExchange': grpc.unary_stream_rpc_method_handler(
                    servicer.GetCurrencyExchange,
                    request_deserializer=subscribe__pb2.CurrencyRequest.FromString,
                    response_serializer=subscribe__pb2.CurrencyReply.SerializeToString,
            ),
            'GetDiscounts': grpc.unary_stream_rpc_method_handler(
                    servicer.GetDiscounts,
                    request_deserializer=subscribe__pb2.DiscountsRequest.FromString,
                    response_serializer=subscribe__pb2.DiscountsReply.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'subs.Subscriber', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class Subscriber(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def GetCityWeather(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(
            request,
            target,
            '/subs.Subscriber/GetCityWeather',
            subscribe__pb2.WeatherRequest.SerializeToString,
            subscribe__pb2.WeatherReply.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)

    @staticmethod
    def GetCurrencyExchange(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(
            request,
            target,
            '/subs.Subscriber/GetCurrencyExchange',
            subscribe__pb2.CurrencyRequest.SerializeToString,
            subscribe__pb2.CurrencyReply.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)

    @staticmethod
    def GetDiscounts(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(
            request,
            target,
            '/subs.Subscriber/GetDiscounts',
            subscribe__pb2.DiscountsRequest.SerializeToString,
            subscribe__pb2.DiscountsReply.FromString,
            options,
            channel_credentials,
            insecure,
            call_credentials,
            compression,
            wait_for_ready,
            timeout,
            metadata,
            _registered_method=True)
