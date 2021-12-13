var vowels = ['a','e','i','o','u'];

export function getWeather(city){
    if(isVowel(city[0])){
        return "Rainy";
    } else {
        return "Sunny";
    }
}

function isVowel(char){
    return vowels.includes(char.toLowerCase());
}