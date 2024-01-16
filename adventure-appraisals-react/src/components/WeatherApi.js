import { useEffect, useState } from "react";

const WeatherApi = () => {
    const [weatherData, setWeatherData] = useState(null);
    const [loading, setLoading] = useState(true);
    const city = 'Orlando';
    const state = 'FL';

    useEffect(() => {
        const fetchWeather = async () => {
        try {
        const response = await fetch(`https://api.openweathermap.org/data/2.5/weather?lat=28.5383&lon=-81.3792&appid={cd98ff688924fb4a30ea8344860d0639}`);
        const data = await response.json();

        if (response.ok) {
        console.log("API Response:", data);
        setWeatherData(data);

        } else {
        console.error(`failed to fetch weather data. status: ${response.status}. message: ${data.message}`)
        }
        } catch (error) {
        console.error('Could not fetch weather data', error);
        } finally {
        setLoading(false);
        }
        };

        fetchWeather();
        }, [city]);

        const display = () => {
        if (loading) {
        return <p>Loading..</p>;
        }

        if (!weatherData || !weatherData.main) {
        return <p> No weather data available</p>
        }

        console.log("data:", weatherData);

        const { name } = weatherData;
        const { temp } = weatherData.main;
        const { main: weatherDescription } = weatherData.weather[0];
        const { country } = weatherData.sys

 return (
       <div>
         <p id="location">{name}, {country}</p>
         <p id="temperature">{temp}°C</p>
         <p id="description">{weatherDescription}</p>
       </div>
     );
   };

   return (
     <div>
       <h1>Weather Information</h1>
       {display()}
     </div>
   );
 };

 export default WeatherApi;