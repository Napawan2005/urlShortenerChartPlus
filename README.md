
## Docker

### การติดตั้ง (Installation)

1. ติดตั้ง Docker และ Docker Compose ตามระบบปฏิบัติการของคุณ
2. ไปที่โฟลเดอร์โครงการ (`B:`) ผ่าน terminal หรือ PowerShell
3. รันคำสั่ง:

   ```bash
   docker-compose up --build -d
   ```

    * `--build` จะบังคับให้สร้าง image ใหม่จาก `Dockerfile`
    * `-d` รัน service ใน background

### วิธีการใช้งาน (Usage)
 **สร้างด้วย MVN** 

   ```bash
   mvn clean package -DskipTests
   ```
   
* เช็คสถานะ containers:

  ```bash
  docker-compose ps
  ```
* ดู logs ของ service `app`:

  ```bash
  docker-compose logs -f app
  ```
* หยุดและลบ containers:

  ```bash
  docker-compose down
  ```

ไฟล์สำคัญ:

* **Dockerfile**: สร้าง image ของแอป Spring Boot
* **docker-compose.yml**: กำหนดบริการ `db` (PostgreSQL) และ `app` (Spring Boot)

---

## Spring Boot

### Dependencies (pom.xml)

| dependency                               | ทำหน้าที่                            |
| ---------------------------------------- | ------------------------------------ |
| spring-boot-starter-data-jdbc            | รองรับ JDBC interaction              |
| spring-boot-starter-data-jpa             | JPA / Hibernate integration          |
| spring-boot-starter-jdbc                 | DataSource และ JDBC support          |
| spring-boot-starter-web                  | Spring MVC, REST API support         |
| spring-boot-devtools (runtime, optional) | Auto-restart, LiveReload duringพัฒนา |
| postgresql (runtime)                     | PostgreSQL JDBC driver               |
| lombok (optional)                        | สร้าง getter/setter อัตโนมัติ        |
| spring-boot-starter-test (test)          | Testing framework                    |

### Files and Responsibilities

```
src/main/java/com/bam/urlshortenerchartplus
├─ UrlShortenerChartPlusApplication.java    # Entry point ของแอป
├─ controller/
│   ├─ RedirectUrlController.java              # Redirect API สำหรับ path พารามิเตอร์เดียว
│   └─ UrlController.java                   # REST API ชุด /api/v1
├─ model/
│   ├─ UrlMapping.java                      # Entity mapping ไปยังตาราง url_shortener
│   └─ UrlMappingRepository.java            # JpaRepository สำหรับ UrlMapping
└─ service/
    └─ UrlService.java                      # Business logic: สร้างและค้นหา short URL
```

* **application.properties**: การตั้งค่า DataSource, JPA
* **schema.sql**: (ถ้ามี) คำสั่ง SQL เริ่มต้น สำหรับสร้างตาราง

### Functions (fn)

* `UrlService.createShortUrl(String originalUrl, String shortUrl)`

    * ตรวจสอบว่า `shortUrl` ไม่ซ้ำในฐานข้อมูล
    * สร้าง `UrlMapping` ใหม่ พร้อม URL ต้นฉบับ และ `creatUrl`
    * บันทึกลง repository และคืนค่าข้อความประกอบลิงก์และเวลา process

* `UrlService.getOriginalUrl(String shortUrl)`

    * ค้นหาจาก repository ตาม `shortUrl`
    * คืนค่า `originalUrl` หรือตี exception ถ้าไม่พบ

### REST APIs

| HTTP Method | Path              | คำอธิบาย                                                                                                                                                                                                                 |
| ----------- | ----------------- |--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET         | `/api/v1`         | ทดสอบว่า service พร้อมใช้งาน (return "Hello World")                                                                                                                                                                      |
| POST        | `/api/v1/shorten` | รับ JSON ของ `UrlMapping` (originalUrl, shortUrl) สร้างลิงก์ย่อ คืนข้อความลิงก์และเวลา <br/> โดยกรอกแค่<br/> - shortUrl : (ตามด้วย path ที่เราต้องการเปลี่ยน)<br/> - originalUrl : (url หรีอลิงค์ที่เราต้องการจะเปลี่ยน) |
| GET         | `/{urlCode}`      | Redirect ไปยัง original URL ตาม `urlCode`                                                                                                                                                                                |

---

## Database (DB)

* ใช้ **PostgreSQL** (เวอร์ชัน 15 ตาม docker-compose)
* การตั้งค่าเชื่อมต่ออยู่ใน `application.properties`:

  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/shortenurl
  spring.datasource.username=postgres
  spring.datasource.password=root
  spring.jpa.hibernate.ddl-auto=update
  ```

### ตารางหลัก

#### `url_shortener`

| คอลัมน์       | ชนิด    | คำอธิบาย                               |
| ------------- | ------- | -------------------------------------- |
| id            | BIGINT  | Primary Key, auto-generated            |
| short\_url    | VARCHAR | โค้ดลิงก์ย่อ (unique)                  |
| original\_url | VARCHAR | URL ต้นฉบับ (ไม่ null)                 |
| creat\_url    | VARCHAR | URL เต็มสำหรับ redirect/back-reference |

> JPA จะสร้าง/อัปเดตตารางตาม Entity (`ddl-auto=update`)

---

*เอกสารนี้สรุปภาพรวมโครงสร้างและวิธีใช้งานของโปรเจกต์ `urlShortenerChartPlus`*
