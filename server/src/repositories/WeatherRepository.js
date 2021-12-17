import * as grpc from "@grpc/grpc-js"
import { weatherService } from "../interface.js"

var client = new weatherService('localhost:9090', grpc.credentials.createInsecure());

export async function getWeather(city){
    var object = {city}
    return new Promise((resolve, reject) => client.GetWeather(object, function(err, response) {
        if(err) {
            return reject(err)
        }
        resolve(response)        
    }))
}