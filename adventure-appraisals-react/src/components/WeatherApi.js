import { useEffect, useState } from "react";

const WeatherApi = () => {

    const [weatherData, setWeatherData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null)
    const [itineraryName, setItineraryName] = useState(null);
    const [ itinerary, setItinerary ] = useState({});

    var url = window.location.pathname;
       const itineraryId = Number(url.substring(url.lastIndexOf('/') + 1));

   useEffect(() => {

       fetch(`http://localhost:8080/itineraries/itinerary/${itineraryId}`)
       .then(res => res.json())
       .then(result => {


       setItinerary(result);
 if (result && result.id !== undefined) {
        setItinerary(result);
      } else {
        console.error('Invalid result:', result);
      }
    })
    .catch((error) => {
      console.error('Error fetching itinerary:', error);
       });
   }, [itineraryId]);

   useEffect(() => {
        const fetchWeather = async () => {
    try {
          const response = await fetch(`http://localhost:8080/weather/itinerary/${itineraryId}`);
           const data = await response.json();

           console.log('Data from server:', data);

        console.log('Weather data:', data.weatherData);
        console.log('Destination name:', data.itineraryName);


        if (response.ok) {
        const weatherDataObject = JSON.parse(data.weatherData);
        setWeatherData(weatherDataObject);
        setItineraryName(data.itineraryName);

        } else {
            setError(data.message || 'Failed to fetch weather data');
        }
        } catch (error) {
            setError('Could not fetch weather data');
       } finally {
            setLoading(false);
       }
       };

        fetchWeather();
        }, [itineraryId]);

      const display = () => {
        if (loading) {
        return <p className='text-center text-1xl text-blue-900 font-bold mt-4'> Loading..</p>;
        }
         if (error) {
                return (
               <div className='text-center text-1xl text-blue-900 font-bold mt-4'>
               Error: {error}
               </div>
             );
           }

           if (!weatherData) {

            return(
                   <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
                   Could not find weather data </p>
                );
           }

           const cityName = weatherData?.name || 'N/A';
             const temperature = weatherData?.main?.temp || 'N/A';
             const feelsLike = weatherData?.main?.feels_like || 'N/A';
             const weatherDescription = weatherData?.weather?.[0]?.description || 'N/A';
             const humidity = weatherData?.main?.humidity || 'N/A';

                console.log('Temperature:', temperature);
             console.log(temperature);


 return (
       <div>
       <>
       <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
         {cityName} </p>
         <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
         Temperature: {temperature} °F, Feels like : {feelsLike} °F </p>
         <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
          Weather Conditions: {weatherDescription} </p>
          <p className='text-center text-1xl text-blue-900 font-bold mt-4'>
          Humidity : {humidity}% </p>
       </>
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