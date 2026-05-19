# Realtime Order Tracking System

A realtime order tracking system built using **Java, Spring Boot, MySQL, WebSocket, STOMP, SockJS, HTML/CSS/JavaScript, and Postman**.

The system automatically pushes updates to connected clients whenever database changes occur without using polling.
This project demonstrates realtime database-driven client notifications using WebSocket-based event propagation.

---

# Approach

The application follows an event-driven architecture.

Whenever an order is:
- created
- updated
- deleted

the backend:
1. Updates the MySQL database
2. Publishes a realtime WebSocket event
3. Pushes the update to all connected clients instantly

Instead of using continuous polling, WebSocket-based communication is used to achieve low-latency realtime updates efficiently.

---

# Technologies Used

- Java
- Spring Boot
- MySQL
- Spring Data JPA
- WebSocket
- STOMP
- SockJS
- HTML/CSS/JavaScript
- Postman

---

# Why This Approach?

Traditional polling continuously sends HTTP requests even when no updates exist, which increases:
- server load
- network traffic
- latency

WebSocket provides persistent bidirectional communication between server and clients, allowing the server to push updates only when database changes occur.

Benefits of this approach:
- Efficient realtime communication
- Reduced unnecessary requests
- Lower latency
- Better scalability compared to polling

STOMP was used for structured publish-subscribe messaging, and SockJS was added for fallback browser support.

---

# Features

- Create Orders
- Update Orders
- Delete Orders
- Realtime Client Updates
- WebSocket-based Communication
- Event-driven Architecture
- Live Browser Dashboard

---

# System Flow

```text
Postman
   ↓
REST API
   ↓
Spring Boot
   ↓
MySQL Database
   ↓
WebSocket Event
   ↓
Connected Clients
```

---

# API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/orders` | Create order |
| GET | `/orders` | Get all orders |
| PUT | `/orders/{id}` | Update order |
| DELETE | `/orders/{id}` | Delete order |

---

# WebSocket Configuration

| Endpoint | Purpose |
|---|---|
| `/ws` | WebSocket connection endpoint |
| `/topic/orders` | Realtime updates topic |

---

# Database Schema

Table Name: `orders`

| Column Name | Type |
|---|---|
| id | int |
| customer_name | varchar |
| product_name | varchar |
| status | varchar |
| updated_at | timestamp |

---

# Example API Requests

## Create Order

```http
POST http://localhost:8080/orders
```

### Request Body

```json
{
  "customerName": "Rahul",
  "productName": "Laptop",
  "status": "pending"
}
```

---

## Update Order

```http
PUT http://localhost:8080/orders/1
```

### Request Body

```json
{
  "customerName": "Rahul",
  "productName": "Laptop",
  "status": "shipped"
}
```

---

## Delete Order

```http
DELETE http://localhost:8080/orders/1
```

---

## Get All Orders

```http
GET http://localhost:8080/orders
```

---

# Example Realtime Event

```json
{
  "eventType": "ORDER_CREATED",
  "timestamp": "2026-05-18T17:00:00",
  "data": {
    "id": 1,
    "customerName": "Rahul",
    "productName": "Laptop",
    "status": "pending"
  }
}
```

---

# How to Run the Project

## 1. Clone Repository

```bash
git clone <your-github-repository-url>
```

---

## 2. Create MySQL Database

```sql
CREATE DATABASE realtime_orders;
```

---

## 3. Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/realtime_orders
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 4. Run Spring Boot Application

Run:

```text
RealtimeordersApplication.java
```

---

## 5. Open Browser Dashboard

```text
http://localhost:8080
```

---

## 6. Test APIs Using Postman

Use Postman to:
- Create orders
- Update orders
- Delete orders
- Fetch orders

Whenever database changes occur, connected clients receive realtime updates automatically through WebSocket.

---

# Scalability Considerations

Currently the project uses Spring Boot’s in-memory message broker for simplicity.

For enterprise-scale systems, the architecture can be extended using:
- Apache Kafka

This enables distributed realtime messaging and horizontal scalability.

---

# Future Improvements

- Kafka
- Docker Deployment
- Authentication & Authorization
- Order Analytics Dashboard

---

# Author

Shravani Budhe
