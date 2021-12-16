import {Sequelize, Model, DataTypes} from 'sequelize';


export async function initDatabase(sequelize){
    models.EoloPlant.init({
        city:  {type: DataTypes.STRING},
        planning: DataTypes.STRING
    }, { sequelize, modelName: 'eoloPlant' });

    await sequelize.sync();
}

export var sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
    dialect: 'mysql'});

var models = {
    "EoloPlant": class EoloPlant extends Model { }
};