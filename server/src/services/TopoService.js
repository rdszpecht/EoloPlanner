import * as topoRepository from "../repositories/TopoRepository.js"

export async function getTopographicDetails(city){
    try {
        return await topoRepository.getTopographicDetails(city);
    } catch(error){
        throw(error);
    }
}