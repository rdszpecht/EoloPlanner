export function setPlanning(planning, city){
    if(city.toLowerCase()[0] <= "m"){
        return planning.toLowerCase();
    } else {
        return planning.toUpperCase();
    }
}