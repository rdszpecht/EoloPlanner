import { connect } from 'amqplib';

let channel = null;

process.on('exit', (code) => {
    channel.close();
    console.log(`Closing rabbitmq channel`);
});

const rabbitClient = await connect('amqp://guest:guest@localhost');

channel = await rabbitClient.createChannel();

channel.assertQueue("progresses", {durable: false});

channel.consume("progresses", (msg) => {

    console.log("Consumed from queue: '", msg.content.toString()+ "'");

}, { noAck: true });