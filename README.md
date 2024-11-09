# Airae

Airae is a skincare guide that offers robust product filtering based on skin type and skin concerns. You can also explore weather data and relevant case studies, helping you make informed skincare choices in any climate.

## Features

- **Product Filter:** Search products tailored for various skin types and concerns.
- **Weather Data:** View climate information for different locations to better adapt your skincare routine.
- **Case Studies and References:** Explore scientific and dermatological resources related to skincare and environmental factors.
- **Product Listings:** Each product includes essential information like brand, ingredients, and skin compatibility.

## Tech Stack

#### Frontend
- **React**: Component-based JavaScript library
- **TypeScript**: Adds static typing for enhanced development
- **Vite**: Development and build tool
- **React Router**: In-app navigation
- **Redux Toolkit**: State management
- **Bootstrap**: Responsive UI framework

#### Backend
- **Java**: Core language for backend development
- **Spring Boot**: RESTful API framework
- **MongoDB**: NoSQL database
- **Spring Data MongoDB**: Data access for MongoDB

#### Data Seeding
- **Node.js**: Executes scripts to populate MongoDB with sample data

#### API Integrations
- **WeatherAPI**: Real-time weather data

#### Development Tools
- **Git and GitHub**: Version control
- **GitKraken**: Git workflow management
- **Postman**: API testing

## Getting Started

Follow the steps below to set up Airae on your local machine.

**Prerequisites:**
- Node.js (for the frontend)
- Java JDK 17+ (for the backend)
- MongoDB Atlas (for a cloud-based MongoDB instance)
- WeatherAPI Key (for real-time weather data)

#### Creating a WeatherAPI Key:
- Go to WeatherAPI and sign up for an account.
- After logging in, navigate to the API keys section to create and obtain your API key.
- Copy the key to use in the .env file in the backend setup.

## Installation

1. **Clone the repository**
```bash
  git clone https://github.com/kaydelarose/airae.git
  cd airae
```

2. **Backend Setup**
- Navigate to the backend folder
- Create a .env file in the backend directory with the following structure:
```bash
MONGO_USER=<your_mongo_user>
MONGO_PASS=<your_mongo_pass>
WEATHER_API=<your_weather_api_key>
```

3. **Run Data Seeding**
- Navigate to frontend directory to seed sample data into MongoDB:
```bash
node seedProducts.js
```

4. **Install and Run the Application**
- Start the backend:
```bash
./mvnw spring-boot:run
```
- Start the frontend:
```bash
cd ../frontend
npm install
npm run dev
```

5. **Access the Application**
- Open your browser and go to http://localhost:5173


## License

[MIT](https://choosealicense.com/licenses/mit/)

## Acknowledgements

 - Special thanks to [WeatherAPI](https://www.weatherapi.com/)
 - Product data sourced from reliable skincare brands
