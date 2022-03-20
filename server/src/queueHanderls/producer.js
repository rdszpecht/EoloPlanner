import { connect } from 'amqplib';

let channel = null;
let queue = 'eoloplantCreationRequests' 

const rabbitmqHost = process.env.RABBITMQ_HOST || "localhost";

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@' + rabbitmqHost);

channel = await rabbitClient.createChannel();

channel.assertQueue(queue, {durable:false});

export function sendMessage(message){
    console.log("Produced to queue: '" + JSON.stringify(message) + "'");

    channel.sendToQueue(queue, Buffer.from(JSON.stringify(message)));
}