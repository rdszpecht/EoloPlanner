import * as eoloPlantRepository from '../repositories/EoloPlantRepository.js'
import * as topoService from '../services/TopoService.js';
import * as weatherService from '../services/WeatherService.js'
import * as eoloPlantService from '../services/EoloPlantService.js'


async function getEoloPlants() {
    var result = await eoloPlantRepository.findAll();
    return result;
};

async function NewEoloPlant(data){
    var planning = data.city;
    var weather = weatherService.getWeather(data.city);
    var topo = topoService.getTopographicDetails(data.city);
    topo.then((result) => planning += '-' + result.landscape).catch((error)=> {
      return error;
    });
    weather.then((result) => planning += '-' + result.weather).catch((error) => {
      return error;
    });
    return Promise.all([weather, topo]).then(() => {
      planning = eoloPlantService.setPlanning(planning, data.city);
      var eoloPlant = {"city": data.city, "planning": planning};
      return eoloPlantRepository.createEoloPlant(eoloPlant);
    });
}

export default {
    getEoloPlants,
    NewEoloPlant
}