import * as eoloPlantRepository from '../repositories/EoloPlantRepository.js'
import * as queueProducer from '../queueHanderls/producer.js'

async function GetEoloPlants() {
    var result = await eoloPlantRepository.findAll();
    return result;
};

async function NewEoloPlant(data){
    var plant = {
        city: data.city,
        progress: 0,
        planning: null
    }
    plant = await eoloPlantRepository.createEoloPlant(plant);
    var message = { id: plant.id, "city": data.city };
    queueProducer.sendMessage(message);
    return plant;
};

export default {
    GetEoloPlants,
    NewEoloPlant
}