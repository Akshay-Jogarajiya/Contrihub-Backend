# ContribHub 🚀  
A Java Spring Boot project that tracks and ranks developers based on their open-source and technical community contributions on GitHub and StackOverflow.

---

## 📌 Features

- 🔍 **GitHub Integration**: Fetch and store public profile, repository count, followers, stars, and contributions.
- 🎓 **StackOverflow Integration**: Retrieve reputation, badges, and user profile data.
- 💾 **PostgreSQL Database**: Stores and manages all user data.
- 📊 **Contribution Dashboard**: REST APIs to view GitHub & StackOverflow stats for any user.
- 🏆 **Leaderboard System**: Ranks users by GitHub followers or StackOverflow reputation.
- ⏱️ **Auto Scheduler**: Automatically updates user stats every 24 hours.
- 🔐 (Optional) JWT-based authentication for private dashboard.

---

## 🛠️ Tech Stack

| Technology       | Description                  |
|------------------|------------------------------|
| Java             | Programming Language         |
| Spring Boot      | Backend Framework            |
| Spring Data JPA  | ORM Layer                    |
| PostgreSQL       | Relational Database          |
| REST APIs        | Data Access for Frontend     |
| Scheduler        | Periodic Data Update         |

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven
- PostgreSQL
- GitHub API Token
- StackOverflow User IDs

### Setup Instructions

1. Clone the repository:
   ```bash
   git clone [https://github.com/your-username/contribhub.git](https://github.com/Akshay-Jogarajiya/Contrihub-Backend.git)
   cd Contrihub-Backend
2. Set up PostgreSQL database and update application.properties.
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
