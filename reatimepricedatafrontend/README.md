# Real-time Price Data Frontend

## Overview

This project is a React application that interfaces with a backend service to provide real-time price data for various coins. It utilizes Redux Toolkit for state management, allowing for seamless fetching, storing, and updating of coin data and prices.

## Features

- **Coin Data Management**: Retrieve and display a list of all available coins.
- **Price Data Management**: Fetch and display the latest price data for selected coins.
- **State Management**: Efficiently manage application state using Redux Toolkit.
- **Asynchronous Data Fetching**: Perform asynchronous operations with Redux Thunks.

## Project Structure

- `store`:
  - `index.ts`: Configures and exports the Redux store.
  - `coinSlice.ts`: Manages the state for the selected coin.
  - `pricesAndCoinsSlice.ts`: Manages the state for coins and their prices, including asynchronous fetching.

## Prerequisites

- Node.js
- npm

## Setup & Run

```sh
 cd client
 npm install
 npm run build
 npm start
```
