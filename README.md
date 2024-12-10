# StockTraderAPI

StockTraderAPI is a Java-based RESTful API that simulates a basic trading platform. It allows users to manage a virtual stock portfolio by performing operations such as buying and selling stocks, viewing real-time prices, checking portfolio performance, and monitoring cash balance. This project is designed to provide a simple yet functional trading experience for developers and enthusiasts.

## Features

- **User Portfolio Management**: Track owned stocks, including quantity and purchase price.
- **Stock Trading**:
  - Buy stocks using virtual cash.
  - Sell stocks from the portfolio.
- **Real-Time Price Checking**: Fetch current stock prices for supported symbols.
- **Portfolio Summary**: View a detailed breakdown of stock holdings and cash balance.
- **RESTful Endpoints**: API endpoints for easy integration and testing.

## Technologies Used

- **Java**: Core programming language.
- **Spring Boot**: Framework for building the RESTful API.
- **HashMap**: In-memory storage for user data and portfolio.

## Prerequisites

To run this project, ensure you have the following installed:

- Java 8 or higher.
- Maven or Gradle for dependency management.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/StockTraderAPI.git
cd StockTraderAPI
```

### 2. Build and Run the Project

Using Maven:
```bash
mvn spring-boot:run
```

Using Gradle:
```bash
gradle bootRun
```

The API will be accessible at `http://localhost:8080/api/trade`.

### 3. Explore the Endpoints

Use a tool like Postman or cURL to test the following endpoints:

#### a. Buy Stocks
```
POST /api/trade/buy
Parameters: symbol, quantity
Example: /api/trade/buy?symbol=AAPL&quantity=1
```
#### b. Sell Stocks
```
POST /api/trade/sell
Parameters: symbol, quantity
Example: /api/trade/sell?symbol=AAPL&quantity=1
```
#### c. View Portfolio
```
GET /api/trade/portfolio
```
#### d. Check Stock Price
```
GET /api/trade/price
Parameter: symbol
Example: /api/trade/price?symbol=TSLA
```
#### e. Check Cash Balance
```
GET /api/trade/balance
```

## File Structure

```
StockTraderAPI
|├── src/main/java
|   ├── TradingController.java
|   └── (other supporting files)
|├── pom.xml (or build.gradle)
|└── README.md
```

- **TradingController.java**: The main REST controller handling API requests.
- **pom.xml/build.gradle**: Configuration file for Maven/Gradle dependencies.
- **README.md**: Documentation for the project.

## Example Usage

### Buying Stocks

Request:
```bash
curl -X POST "http://localhost:8080/api/trade/buy?symbol=AAPL&quantity=2"
```
Response:
```
Bought 2 shares of AAPL for $19.00
```

### Viewing Portfolio

Request:
```bash
curl -X GET "http://localhost:8080/api/trade/portfolio"
```
Response:
```json
{
  "AAPL": "Shares: 2, Price: $9.50",
  "Cash Balance": "$81.00"
}
```

## Contributing

We welcome contributions! To contribute:

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-name
   ```
3. Make your changes and commit:
   ```bash
   git commit -m "Add new feature"
   ```
4. Push to your branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

## License

This project is licensed under the MIT License. See `LICENSE` for details.

## Contact

For questions or feedback, please reach out to:
- **Michael Mordec** (creator)
- GitHub: [yourusername](https://github.com/yourusername)

