import {Sequelize, Model, DataTypes} from 'sequelize';

const mysqlHost = process.env.MYSQL_HOST || "localhost";
const mysqlPort = process.env.MYSQL_PORT || "3306";

export async function initDatabase(sequelize){
    await sequelize.sync();
}

export var sequelize = new Sequelize('eoloplantsDB', 'root', 'password', {
    dialect: 'mysql',
    host:mysqlHost,
    port:mysqlPort});

process.on('exit', async () => {
    await sequelize.close();
    debug(`Closing mysql connection`);
});