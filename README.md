# Práctica Mini‑ORM (Parte 1 y Parte 2 con Spring Boot)

Este paquete contiene **dos proyectos Maven** listos:
- `mini-orm/` — Ejercicio 1 (Java + Lombok + Reflexión, sin Spring Boot).
- `mini-orm-boot/` — Ejercicio 2 (Spring Boot + Web + Lombok, repos In‑Memory).

## Requisitos
- JDK 21+
- Maven 3.9+
- (Para la parte 2) cURL o Postman

## Cómo compilar y ejecutar

### Parte 1 — mini-orm (CLI)
```bash
cd mini-orm
mvn -q -DskipTests package
java -cp target/mini-orm-1.0.0.jar com.miniorm.app.Main
```

### Parte 2 — mini-orm-boot (API REST)
```bash
cd mini-orm-boot
mvn spring-boot:run
# Probar:
curl -X POST http://localhost:8080/users -H "Content-Type: application/json"      -d '{"name":"Jane","email":"jane@mail.com","password":"123"}'
curl http://localhost:8080/users
```
## Ramas sugeridas (Git)
1) Crea tu repositorio en GitHub (vacío). Luego:
```bash
git init
git add .
git commit -m "chore: bootstrap practica mini-orm"
git remote add origin <URL_DE_TU_REPO>
```
2) **Rama `ejercicio-1`** (Parte 1):
```bash
git checkout -b ejercicio-1
git add mini-orm
git commit -m "feat(ejercicio-1): mini-orm sin Spring Boot"
git push -u origin ejercicio-1
```
3) **Rama `ejercicio-2`** (Parte 2):
```bash
git checkout -b ejercicio-2
git add mini-orm-boot
git commit -m "feat(ejercicio-2): mini-orm con Spring Boot (in-memory)"
git push -u origin ejercicio-2
```
4) **Rama `main`** (README con aprendizajes):
```bash
git checkout -b main
# Edita este README, agrega tus reflexiones y pasos realizados
git add README.md
git commit -m "docs: reflexiones y conceptos aprendidos"
git push -u origin main
```