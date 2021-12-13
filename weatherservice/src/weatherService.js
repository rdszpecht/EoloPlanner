import { getWeather } from './cityService.js'

export async function GetWeather(call, callback){

    console.log('Request received: '+JSON.stringify(call));

    var { city } = call.request;

    var timeOut = Math.random() * 2000 + 1000;
    
    await new Promise(r => setTimeout(r, timeOut));

    callback(null, { city, weather: getWeather(city)})
}