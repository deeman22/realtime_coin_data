# fomo_realtime_stock_data

This project is built with a micro-service architecture where the Backend and Frontend run independently.

# Backend

The backend is a REST API built using Spring Boot that provides real-time cryptocurrency data from the CoinGecko API. It stores coin information and price history in a MongoDB database and offers endpoints for retrieving this data.

Go to realtimepricedatabackend/README.md to run the backend or to know more.

- **Technologies Used**:

1.  Java
2.  Spring Boot
3.  Maven
4.  MongoDB

# Frontend

The frontend is a web application built using Next.js (React framework) and Redux for state management. It consumes the REST API provided by the backend to display cryptocurrency data in a user-friendly interface.

Go to realtimepricedatafrontend/README.md to run the frontend or to know more.

- **Technologies Used**:

1. Next.js
2. Redux Toolkit

# Summary

Backend: REST API providing real-time cryptocurrency data, built with Spring Boot and MongoDB.
Frontend: Web application consuming the REST API, built with Next.js and Redux.
This architecture ensures that both the backend and frontend can scale independently and communicate seamlessly through RESTful APIs.
