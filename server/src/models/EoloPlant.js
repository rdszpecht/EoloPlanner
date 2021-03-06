import { sequelize } from '../config/DBConnection.js';
import sequelizePkg from 'sequelize';

const { DataTypes, Model } = sequelizePkg;

export class EoloPlant extends Model { }

EoloPlant.init({
    city: { type: DataTypes.STRING },
    progress: { type: DataTypes.INTEGER},
    planning: DataTypes.STRING
}, { sequelize, modelName: 'eoloPlant' });