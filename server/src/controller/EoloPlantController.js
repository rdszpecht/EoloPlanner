import * as eoloPlantRepository from '../repositories/EoloPlantRepository.js'
import * as queueProducer from '../queueHanderls/producer.js'

import * as topoService from '../services/TopoService.js';
import * as weatherService from '../services/WeatherService.js'
import * as eoloPlantService from '../services/EoloPlantService.js'

var id = 0;

async function getEoloPlants() {
    var result = await eoloPlantRepository.findAll();
    return result;
};

async function NewEoloPlant(data){
    var message = { id, "city": data.city };
    id++;
    queueProducer.sendMessage(message);
};

export default {
    getEoloPlants,
    NewEoloPlant
}