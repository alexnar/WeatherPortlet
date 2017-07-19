create table Weather_Weather (
	weatherId LONG not null primary key,
	temperature DOUBLE,
	description VARCHAR(75) null
);

create table Weather_WeatherForecast (
	weatherForecastId LONG not null primary key,
	cityName VARCHAR(75) null,
	resourceName VARCHAR(75) null,
	forecastDate DATE null
);

create table Weather_Weather_WeatherForecast (
	companyId LONG not null,
	weatherId LONG not null,
	weatherForecastId LONG not null,
	primary key (weatherId, weatherForecastId)
);