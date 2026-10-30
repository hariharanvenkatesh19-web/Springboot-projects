# Online Food Order System

Java 17 + Spring Boot 3.5.6 + Spring Web + Spring Data JPA + MySQL.
All Java classes are in the same package: `com.example.foodorder`.

## Run
1. Start MySQL.
2. Run `CREATE DATABASE food_order_db;`
3. Replace `YOUR_MYSQL_PASSWORD` in `src/main/resources/application.properties`.
4. Open this folder in IntelliJ as a Maven project and wait for dependencies.
5. Run `FoodOrderApplication.java`.

## Postman endpoints
- POST `/api/orders` — create order
- GET `/api/orders` — list orders
- GET `/api/orders/{id}` — get order
- PUT `/api/orders/{id}` — update order
- PATCH `/api/orders/{id}/status` — JSON `{"status":"PREPARING"}`
- GET `/api/orders/{id}/total` — quantity × unit price
- DELETE `/api/orders/{id}` — delete order

POST JSON:
```json
{
  "customerName": "Hari",
  "foodItem": "Chicken Burger",
  "quantity": 2,
  "price": 250.00
}
```

This is a beginner learning version; validation and authentication are not included yet.
