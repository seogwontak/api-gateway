# Spring Boot Project

## 📌 Project Overview

이 프로젝트는 Spring Boot를 기반으로 한 **API 인증 시스템**으로, Redis를 활용한 로그인 관리와  인증 및 권한 부여를 구현합니다.
**Access Token**과 **Refresh Token**을 사용하여 보안을 강화하였으며, Redis를 이용해 세션 관리를 효율적으로 수행합니다.

---

## 🔧 Features

- **JWT 기반 인증 및 권한 부여**
  - Access Token과 Refresh Token 관리
  - Redis를 사용한 토큰 저장 및 빠른 조회
- **로그인/로그아웃 관리**
  - 외부 인증 제공자(Google, Facebook 등) 연동 가능
- **Spring Boot 기반 REST API**
  - 사용자 인증 및 토큰 재발급 API 제공

---

## 🛠️ Tech Stack

- **Framework**: Spring Boot
- **Language**: Java 17
- **Database**: Redis
- **Authentication**: JWT
- **Build Tool**: Gradle 
- **Testing**: JUnit, MockMvc

---

## 🚀 Getting Started

### 1️⃣ Prerequisites

- Java 17 이상 설치
- Maven 또는 Gradle 설치
- Redis 서버 실행

### 2️⃣ Project Setup

1. 프로젝트 클론:
   ```bash
   git clone https://github.com/seogwontak/api-gateway.git
   ```


## 📋 API Endpoints

### 인증 API

| Method | Endpoint        | Description         |
| ------ | --------------- | ------------------- |
| POST   | `/api/login`  | 사용자 로그인       |
| POST   | `/api/logout` | 사용자 로그아웃     |
| POST   | `/api/token`  | Access Token 재발급 |

### 기타 API

| Method | Endpoint        | Description    |
| ------ | --------------- | -------------- |
| GET    | `/api/health` | 서버 상태 확인 |
