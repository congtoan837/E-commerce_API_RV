# 1. Sử dụng Maven + JDK 17 (Corretto)
FROM amazoncorretto:17-alpine AS build

# Cài Maven (nếu image chưa có sẵn)
RUN apk add --no-cache maven

# 2. Đặt thư mục làm việc và copy source code vào container
WORKDIR /app
COPY pom.xml .
COPY src ./src

# 3. Build project (bỏ qua test nếu muốn)
RUN mvn clean package -DskipTests

# ----------------------------------------------

# 4. Giai đoạn runtime (tối ưu hoá image nhỏ)
FROM amazoncorretto:17-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
