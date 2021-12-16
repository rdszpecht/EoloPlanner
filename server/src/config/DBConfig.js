/** import {DataTypes } from 'sequelize';
import {models} from "./DBConnection.js";

export async function initDatabase(sequelize){
    
    models.EoloPlant.init({
        city: DataTypes.STRING,
        planning: DataTypes.STRING
    }, { sequelize, modelName: 'eoloPlant' });

    await sequelize.sync();
}
*/