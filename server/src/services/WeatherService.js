import * as weatherRepository from '../repositories/WeatherRepository.js'

export async function getWeather(city){
    return await weatherRepository.getWeather(city);
}