import express from 'express';
import { graphqlHTTP } from 'express-graphql';
import { buildSchema } from 'graphql';
import cors from 'cors';
import * as topoService from './services/TopoService.js';
import * as weatherService from './services/WeatherService.js'
import * as eoloPlantService from './services/EoloPlantService.js'

import * as db from './config/DBConnection.js'
import * as eoloPlantRepository from './repositories/EoloPlantRepository.js'

var sequelize = db.sequelize;
await db.initDatabase(sequelize);
eoloPlantRepository.setModel(sequelize.models.eoloPlant);


// Construct a schema, using GraphQL schema language
const schema = buildSchema(`
  type Query {
    GetEoloPlants: [EoloPlant]
    
  }

  type Mutation {
    NewEoloPlant(city: String): EoloPlant
  }

  type EoloPlant  {
      id: ID,
      city: String,
      planning: String
  }
`);

// The root provides a resolver function for each API endpoint
const root = {
  GetEoloPlants: async (data) => {
    var result = await eoloPlantRepository.findAll();
    return result;
  },

  NewEoloPlant: async (data) => {
    var planning = data.city;
    var weather = weatherService.getWeather(data.city);
    var topo = topoService.getTopographicDetails(data.city);
    topo.then((result) => planning += '-' + result.landscape).catch((error)=> {
      return error;
    });
    weather.then((result) => planning += '-' + result.weather).catch((error) => {
      return error;
    });
    return Promise.all([weather, topo]).then(() => {
      planning = eoloPlantService.setPlanning(planning, data.city);
      var eoloPlant = {"city": data.city, "planning": planning};
      return eoloPlantRepository.createEoloPlant(eoloPlant);
    });
  }
};

const app = express();

app.use(cors());

app.use('/graphql', graphqlHTTP({
  schema: schema,
  rootValue: root,
  graphiql: true
}));


app.use('/', express.static('public'))

app.listen(3000, ()=>{
    console.log('Running a GraphQL API server at http://localhost:3000/graphql');
});