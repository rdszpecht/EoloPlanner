import { connect } from 'amqplib';
import { eventEmitter } from '../websockets/Websockets.js';
import * as eoloPlantService from '../services/EoloPlantService.js'

let channel = null;
let queue = 'eoloplantCreationProgressNotifications';

const rabbitmqHost = process.env.RABBITMQ_HOST || "localhost";

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@' + rabbitmqHost);

channel = await rabbitClient.createChannel();

channel.assertQueue(queue, {durable: false});

channel.consume(queue, async function(message) {

    var object = JSON.parse(message.content);
    console.log("Queue consume: " + message.content);
    await eoloPlantService.modifyEoloPlant(object);
    eventEmitter.emit('onMessage', object);

}, { noAck: true });