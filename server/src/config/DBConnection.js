import {Sequelize, Model, DataTypes} from 'sequelize';


export async function initDatabase(sequelize){
    await sequelize.sync();
}

export var sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
    dialect: 'mysql'});

process.on('exit', async () => {
    await sequelize.close();
    debug(`Closing mysql connection`);
});