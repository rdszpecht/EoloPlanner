import expressws from 'express-ws';
import * as events from 'events';

export const eventEmitter = new events.EventEmitter();

export function configWebsockets(app){
    expressws(app);
    
    app.ws('/notifications', (ws, req) => {
    let citySubscription;
    eventEmitter.on('onMessage', function(message){
        if(message.city == citySubscription){
            ws.send(JSON.stringify(message));
            if(message.completed) { citySubscription = ""; }
        }
    });
    ws.on('message', function(city){
        citySubscription = city;
    });

    ws.on('close', function(){
        console.log("connection closed");
    });
    });
}