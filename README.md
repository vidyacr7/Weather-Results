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
