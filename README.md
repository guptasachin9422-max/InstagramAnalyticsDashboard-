# Instagram Analytics Dashboard

A **Spring Boot-based Instagram Analytics project** that integrates with the **Meta Graph API** to retrieve and analyze Instagram account and media data.

The project allows users to retrieve Instagram profile information, posts, engagement metrics, views, and account insights.

## 🚀 Features

* 📊 Fetch Instagram account information
* 👥 Retrieve followers and following count
* 📸 Retrieve Instagram posts and reels
* ❤️ Retrieve likes and comments
* 👁️ Retrieve video and reel views
* 📈 Retrieve Instagram insights
* 📊 Analyze Instagram engagement and media performance
* 🔎 Search Instagram posts by caption
* 🌐 REST API-based backend
* 💾 Store Instagram data in PostgreSQL

## 🛠️ Tech Stack

* **Java 25**
* **Spring Boot 3.5.6**
* **Spring Data JPA**
* **PostgreSQL**
* **Meta Graph API v23.0**
* **RestTemplate**
* **Jackson**
* **Lombok**
* **Maven**

## 📌 How to Run

### 1. Clone the project

```bash
git clone <your-repository-url>
cd <project-folder>
```

### 2. Configure Meta API

Add your Instagram API credentials to:

```text
src/main/resources/application.properties
```

Example:

```properties
instagram.business.id=YOUR_INSTAGRAM_BUSINESS_ID
instagram.page.access.token=YOUR_ACCESS_TOKEN
```

> **Never commit your real access token or database credentials to GitHub.**

### 3. Start the application

Run the Spring Boot application using your IDE or:

```bash
mvn spring-boot:run
```

For Windows:

```bash
.\mvnw.cmd spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## 📊 Instagram Profile

Retrieve Instagram account information:

```text
http://localhost:8080/instagram/profile
```

The API retrieves information such as:

* Username
* Biography
* Followers
* Following
* Post count
* Profile picture

## 🎥 Instagram Media

Retrieve Instagram posts and reels:

```text
http://localhost:8080/media/fetch
```

Media information includes:

* Media ID
* Caption
* Media type
* Media URL
* Permalink
* Thumbnail
* Timestamp
* Likes
* Comments
* Views

## 📈 Instagram Insights

Retrieve Instagram account insights:

```text
http://localhost:8080/insights
```

The project currently works with metrics such as:

* Reach
* Impressions
* Profile Views

## 📊 Analytics

Retrieve overall Instagram analytics:

```text
http://localhost:8080/analytics/overall
```

The analytics include:

* Followers
* Following
* Posts
* Total likes
* Total comments
* Total views
* Images
* Videos
* Reels

## 🔎 Search Media

Search Instagram media by caption:

```text
http://localhost:8080/media/search?caption=fitness
```

## 📂 Project Structure

```text
src
└── main
    ├── java
    │   └── com.instagram.InstagramAnalyticsDashboard
    │       ├── controller
    │       ├── service
    │       ├── repository
    │       ├── entity
    │       └── config
    │
    └── resources
        └── application.properties
```

## 🔄 API Flow

```text
Instagram Business Account
          ↓
    Meta Graph API
          ↓
      Spring Boot
          ↓
       REST API
          ↓
      PostgreSQL
          ↓
      Analytics
```

## ⚠️ Security

Do not upload the following to GitHub:

* Meta Access Token
* Facebook Page Access Token
* Database Password
* API Keys
* Other private credentials

Use environment variables or a local configuration file for sensitive values.

## 👨‍💻 Project Status

**Currently under development.**

The project is being developed to explore **Meta Graph API integration, Instagram analytics, Instagram media data, PostgreSQL, and REST API development using Spring Boot.**
