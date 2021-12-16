var EoloPlant;

export function setModel(model){
    EoloPlant = model;
    console.log("EOLOPLANT: " + EoloPlant);
}

export async function findAll(){
    return await EoloPlant.findAll();
}

export async function findByCity(city){
    var result = await EoloPlant.findOne({ where: {city}});
    return result.dataValues;
}

export async function createEoloPlant(plant){
    var result = await EoloPlant.create(plant);
    await EoloPlant.sync();
    return result;
}