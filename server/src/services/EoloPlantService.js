import * as eoloPlantRepository from '../repositories/EoloPlantRepository.js';

export function setPlanning(planning, city){
    if(city.toLowerCase()[0] <= "m"){
        return planning.toLowerCase();
    } else {
        return planning.toUpperCase();
    }
}

export async function modifyEoloPlant(plant){
    eoloPlantRepository.modifyEoloPlant(plant);
}