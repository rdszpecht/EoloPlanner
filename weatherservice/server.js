import * as grpc from 'grpc' ;
import { weatherService } from './interface.js';
import * as serviceImpl from './weatherService.js';
const server = new grpc.Server();

server.addService(weatherService.service, {'GetWeather': serviceImpl.GetWeather});

server.bind('127.0.0.1:9090', grpc.ServerCredentials.createInsecure());

console.log('gRPC server running at http://127.0.0.1:9090');

server.start();