import { connect } from 'amqplib';

let channel = null;

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@localhost');

channel = await rabbitClient.createChannel();

channel.assertQueue("messages");

export function sendMessage(message){
    console.log("Produced to queue: '" + JSON.stringify(message) + "'");

    channel.sendToQueue("cities", Buffer.from(JSON.stringify(message)));
}