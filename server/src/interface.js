import * as grpc from '@grpc/grpc-js';
import * as protoLoader from '@grpc/proto-loader';

var packageDefinition = protoLoader.loadSync('./WeatherService.proto',
    {
        keepCase: true,
        longs: String,
        enums: String,
        defaults: true,
        oneofs: true
    });

var weatherServiceProto = grpc.loadPackageDefinition(packageDefinition);
export var weatherService = weatherServiceProto.es.grpc.WeatherService;