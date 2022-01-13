import express from 'express';
import { graphqlHTTP } from 'express-graphql';
import { buildSchema } from 'graphql';
import cors from 'cors';

import { readFileSync } from 'fs';
import { fileURLToPath } from 'url';
import { dirname } from 'path';

import eoloPlantController from './controller/EoloPlantController.js';
import * as db from './config/DBConnection.js';
import './queueHanderls/consumer.js';
import { configWebsockets } from './websockets/Websockets.js';

var sequelize = db.sequelize;
await db.initDatabase(sequelize);

// Construct a schema, using GraphQL schema language
const schema = buildSchema(readFileSync(dirname(fileURLToPath(import.meta.url)) + '/eoloplants.graphqls').toString());

export const app = express();

app.use(cors());
configWebsockets(app);

app.use('/graphql', graphqlHTTP({
  schema: schema,
  rootValue: eoloPlantController,
  graphiql: true
}));

app.use('/', express.static('public'))

app.listen(3000, ()=>{
    console.log('Running a GraphQL API server at http://localhost:3000/graphql');
});