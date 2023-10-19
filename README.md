#                                      Weather Results
   

# Implemented in Spring Boot

	Build a simple REST request implemented in Springboot.
		a. Call the end point below:
		https://api.weather.gov/gridpoints/MLB/33,70/forecast
		b. Return the following result structure for the current day.
		{
		"daily":[{
		"day_name": "Monday",
		"temp_high_celsius": 27.2,
		"forecast_blurp": "Partly Sunny"
		}]
		}
	Any transformation should utilize the reactive stack (Mono, Flux etc.)

# Output:

	<img width="1280" alt="weather pic" src="https://github.com/vidyacr7/Weather-Results/assets/148466953/c9a69fec-5165-476b-b868-dcc5aa14805b">
