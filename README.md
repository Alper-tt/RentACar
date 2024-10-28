# Rent a Car Uygulaması

Bu proje, araç kiralama işlemlerini gerçekleştirebileceğiniz basit bir Rent a Car uygulamasıdır. Uygulama, Spring Boot ve ilgili teknolojileri kullanarak geliştirilmiştir ve kullanıcıların araçları listeleyip kiralayabilmesine, araç kiralama işlemlerini başlatıp bitirmesine olanak tanır. Proje ayrıca JWT tabanlı kimlik doğrulama ve rol tabanlı yetkilendirme içerir.

## Özellikler

- **Kullanıcı Rolleri**: Uygulama, iki farklı kullanıcı rolü sunar:
  - `ROLE_MERCHANT`: Araç sahibi, araçları ekleyebilir ve silebilir.
  - `ROLE_CUSTOMER`: Araç kiralama işlemleri gerçekleştirebilir ve yalnızca kendi kiraladığı araçları görüntüleyebilir.
- **JWT Tabanlı Kimlik Doğrulama**: Kullanıcılar, kayıt ve giriş işlemlerinde bir JWT token alır ve bu token ile yetkilendirilir.
- **Swagger UI Desteği**: Swagger UI ile API endpoint'leri kolayca test edilebilir ve yetkilendirme yapılabilir.
- **Veritabanı Desteği**: Geliştirme ortamı için H2 veritabanı kullanılmıştır, ancak kolayca başka veritabanlarına uyarlanabilir.
- **Katmanlı Mimari**: Repository, Service ve Controller katmanlarından oluşan bir yapı ile geliştirilmiştir.
- **Özel Response Yapısı**: Dönen veriler için özelleştirilmiş response yapıları kullanılır.

## Gereksinimler

- **Java 17+**
- **Maven**
- **Spring Boot**

## Kullanılan Bağımlılıklar

Proje için aşağıdaki bağımlılıklar kullanılmıştır:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-impl</artifactId>
</dependency>
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-jackson</artifactId>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
</dependency>
```

## Özellikler

- **Kullanıcı Rolleri**: Uygulama, iki farklı kullanıcı rolü sunar:
  - `ROLE_MERCHANT`: Araç sahibi, araçları ekleyebilir ve silebilir.
  - `ROLE_CUSTOMER`: Araç kiralama işlemleri gerçekleştirebilir ve yalnızca kendi kiraladığı araçları görüntüleyebilir.
- **JWT Tabanlı Kimlik Doğrulama**: Kullanıcılar, kayıt ve giriş işlemlerinde bir JWT token alır ve bu token ile yetkilendirilir.
- **Swagger UI Desteği**: Swagger UI ile API endpoint'leri kolayca test edilebilir ve yetkilendirme yapılabilir.
- **Veritabanı Desteği**: Geliştirme ortamı için H2 veritabanı kullanılmıştır, ancak kolayca başka veritabanlarına uyarlanabilir.
- **Katmanlı Mimari**: Repository, Service ve Controller katmanlarından oluşan bir yapı ile geliştirilmiştir.
- **Özel Response Yapısı**: Dönen veriler için özelleştirilmiş response yapıları kullanılır.

## Uygulama Mimarisi ve Akışı

### Kullanıcı Rolleri ve Yetkilendirme

- Kullanıcılar `ROLE_MERCHANT` ve `ROLE_CUSTOMER` rolleri ile sisteme kayıt olabilirler. 
- Kayıt işlemi sırasında kullanıcıdan `username`, `email`, `password` ve `role` bilgileri alınır.
- Giriş işlemi sonrası JWT token oluşturulur ve bu token ile yetkilendirme yapılır.
- Her bir endpoint için farklı yetkilendirme kuralları belirlenmiştir. Örneğin:
  - `POST /car/create` ve `DELETE /car/delete` işlemleri sadece `ROLE_MERCHANT` için izinlidir.
  - `GET customer/{customerId}/cars` işlemi sadece kendi araçlarını görmek isteyen `ROLE_CUSTOMER` için izinlidir.

### Kullanılabilir API Endpoint'leri

- **Kullanıcı İşlemleri**
  - `POST /auth/register`: Kullanıcı kaydı.
  - `POST /auth/login`: Kullanıcı girişi ve JWT token alma.
  
- **Araç İşlemleri**
  - `GET /car`: Tüm araçları listeler.
  - `POST /car/create`: Yeni araç oluşturur (`ROLE_MERCHANT` gereklidir).
  - `DELETE /car/delete`: Mevcut bir aracı siler (`ROLE_MERCHANT` gereklidir).

- **Kiralama İşlemleri**
  - `GET /rent`: Tüm kiralanmış araçları listeler (`ROLE_MERCHANT` veya `ROLE_CUSTOMER` gereklidir).
  - `POST /rent/start`: Kiralama başlatır (`ROLE_CUSTOMER` gereklidir).
  - `POST /rent/finish`: Kiralama işlemini sonlandırır (`ROLE_CUSTOMER` gereklidir).

- **Müşteri ve Merchant İşlemleri**
  - `GET customer/{customerId}/cars`: Belirtilen müşteri ID'sine göre kiralanan araçları listeler (`ROLE_CUSTOMER` gereklidir).
  - `POST /merchant/addCar`: Bir aracı belirtilen merchant'a ekler (`ROLE_MERCHANT` gereklidir).
  - `GET /{merchantId}/cars`: Belirtilen merchant ID'sine göre araçları listeler (`ROLE_MERCHANT` gereklidir).

### Swagger UI

Swagger UI entegrasyonu sayesinde API endpoint'leri daha anlaşılır bir şekilde görüntülenebilir ve test edilebilir. JWT token ile yetkilendirme işlemleri Swagger üzerinden yapılabilir.

## Kurulum

1. **Projeyi Klonlayın:**

   ```bash
   git clone https://github.com/kullaniciAdi/rent-a-car.git
   cd rent-a-car

2. **Bağımlılıkları Yükleyin ve Uygulamayı Başlatın::**

   ```bash
   mvn clean install
   mvn spring-boot:run

## H2 Veritabanı Konsoluna Erişin

- H2 veritabanı konsoluna erişmek için adrese gidin: http://localhost:8080/h2-console

- Gerekli bilgileri girin:
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Kullanıcı Adı**: `test`
- **Şifre**: (boş bırakın)

## Swagger UI Üzerinden Test Edin

- Swagger UI'ye erişerek API'yi test etmek için adrese gidin: http://localhost:8080/swagger-ui.html




