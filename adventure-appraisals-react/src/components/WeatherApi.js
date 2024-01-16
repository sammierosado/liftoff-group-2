import { useEffect, useState } from "react";

const WeatherApi = () => {
    const [weatherData, setWeatherData] = useState(null);
    const [loading, setLoading] = useState(true);
    const city = 'Orlando';
    const state = 'FL';

    useEffect(() => {
        const fetchWeather = async () => {
        try {
        const response = await fetch(`https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=cd98ff688924fb4a30ea8344860d0639&units=imperial`);
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
        return <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
        No weather data available</p>
        }

        console.log("data:", weatherData);

        const { name } = weatherData;
        const { temp } = weatherData.main;
        const { main: weatherDescription } = weatherData.weather[0];
        const { country } = weatherData.sys

 return (
       <div>
       <p className='text-center text-1xl text-blue-900 font-bold mt-4'
         id="location">{name}, {country}</p>
         <p className='text-center text-1xl text-blue-900 font-bold mt-4'
          id="temperature">Temperature: {temp} °F</p>
         <p className='text-center text-1xl text-blue-900 font-bold mt-4'
          id="description">Weather Conditions: {weatherDescription}</p>
       </div>
     );
   };

   return (
     <div>
     <h1 className='text-center text-2xl text-blue-900 font-bold mt-4'>
      Current Weather
      </h1>
       {display()}
     </div>
   );
 };

 export default WeatherApi;