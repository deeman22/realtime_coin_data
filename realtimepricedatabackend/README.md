# Real-time Price Data Backend

## Overview

This project is a Spring Boot application that provides real-time price data for various coins. It fetches data from external APIs and stores it in a MongoDB database. The application includes several RESTful endpoints to retrieve coin data and price history.

## Features

- **Coin Data Management**: Retrieve a list of all available coins.
- **Price Data Management**: Fetch and store the latest price data for coins.
- **Scheduled Tasks**: Periodically fetch and update coin prices from an external API.
- **Exception Handling**: Robust error handling and logging for API interactions.

## Project Structure

- `controller`:
  - `CoinController`: Manages endpoints for retrieving coin data.
  - `PriceController`: Manages endpoints for retrieving price history data.
  - `PriceCronController`: Manages scheduled tasks for updating price data.
- `entity`:
  - `CoinData`: Represents coin metadata.
  - `CoinPriceHistory`: Represents historical price data for coins.
- `repository`:
  - `CoinPriceHistoryRepository`: MongoDB repository for coin price history data.
  - `CoinRepository`: MongoDB repository for coin metadata.
- `service`:
  - `CoinService`: Business logic for managing coin data.
  - `PriceService`: Business logic for managing price data.
  - `PriceCronService`: Business logic for fetching and storing updated prices.

## Prerequisites

- Java 17 or later
- Maven
- MongoDB

## Setup & Run

1. **cd into realtimepricedatabackend**:
   ```sh
   cd realtimepricedatabackend
   ```
2. **Configure MongoDB**:

   - change `spring.data.mongodb.uri` in `src/main/resources/application.properties` with your mongoDB URL.
     Example : mongodb+srv://{username}:{password}@cluster0.6wtlokx.mongodb.net/{database}?retryWrites=true&w=majority&appName=Cluster0

   - In data mongoDB data base create two collections : `coins` and `coin_price_history`

3. **Install dependencies**:
   ```sh
   cd mvn clean install
   ```
4. **Start the application**:
   ```sh
   mvn spring-boot:run
   ```

# Access the API

- **Base URL**: http://localhost:8080/real-time-data
- **End Points**:
  1. Get all coins: GET /coin
  2. Get price list for a specific coin: GET /price/{coinId}?count={count}
  3. Trigger price update manually: GET /price-cron/update-price
  4. Project/Database Health Check: GET /actuator/health

# Testing

1. **Run tests**:
   ```sh
   mvn test
   ```

# Improvements

- add more unit test cases.
