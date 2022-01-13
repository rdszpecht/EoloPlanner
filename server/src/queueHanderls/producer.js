import { connect } from 'amqplib';

let channel = null;
let queue = 'eoloplantCreationRequests' 

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@localhost');

channel = await rabbitClient.createChannel();

channel.assertQueue(queue, {durable:false});

export function sendMessage(message){
    console.log("Produced to queue: '" + JSON.stringify(message) + "'");

    channel.sendToQueue(queue, Buffer.from(JSON.stringify(message)));
}