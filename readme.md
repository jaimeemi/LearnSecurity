Perfecto, Emilio. Vamos a arrancar con una serie de ejercicios prácticos de **Spring Security — Nivel Básico**, diseñados para avanzar de forma progresiva. Cada ejercicio va acompañado de sus objetivos, tips y qué deberías haber entendido al terminarlo. Usaremos un microservicio simple con CRUD y base en memoria (H2), como preferís.

---

## 🧪 **Nivel Básico — Ejercicio 1: Microservicio CRUD con rutas públicas y protegidas**

### 🎯 Objetivo:

* Crear un microservicio REST con Spring Boot.
* Implementar Spring Security con configuración mínima.
* Proteger algunas rutas y dejar otras públicas.

---

### 🧱 Requisitos técnicos:

* Spring Boot 3+
* Spring Web
* Spring Security
* H2 Database
* Spring Data JPA

---

### 🛠️ **Instrucciones**

#### Paso 1: Crea el proyecto (si querés te doy comando `spring init`)

* Entidad: `Producto` (id, nombre, precio).
* Controlador REST: CRUD básico (`/productos`).
* Repositorio JPA con datos ficticios en memoria.
* Base H2 con `data.sql` para poblar productos.

#### Paso 2: Agregá Spring Security

En `application.yml`:

```yaml
spring:
  security:
    user:
      name: emilio
      password: password123
  h2:
    console:
      enabled: true
```

#### Paso 3: Configuración de seguridad

```java
@Configuration
@EnableWebSecurity
public class SeguridadBasicaConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // no se necesita CSRF para APIs REST

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll() // consola H2 libre
                .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll() // solo GET públicos
                .anyRequest().authenticated() // el resto requiere login
            )
            .headers(headers -> headers.frameOptions().disable()) // permite usar H2 en navegador
            .httpBasic(); // autenticación básica

        return http.build();
    }
}
```

---

### 🧪 **Lo que tenés que lograr:**

1. Correr la app.
2. Ir a `/h2-console` sin login.
3. Hacer GET a `/api/productos` sin login.
4. Intentar hacer POST/PUT/DELETE y que requiera login.
5. Hacerlo con autenticación básica (usuario: `emilio`, pass: `password123`).

---

### ✅ **Checklist del ejercicio**

* [ ] CRUD funcional de `Producto`
* [ ] Consola H2 accesible sin login
* [ ] Seguridad básica configurada
* [ ] GET públicos, otros métodos protegidos
* [ ] Autenticación básica HTTP funcionando

---

### 🧠 ¿Qué aprendiste acá?

* Qué es `SecurityFilterChain`.
* Cómo proteger rutas específicas (por método o path).
* Cómo usar HTTP Basic (usuario/contraseña).
* Cómo excluir recursos (como H2) de la seguridad.
* Cuándo desactivar CSRF.

---

### 📦 ¿Querés que prepare el esqueleto del proyecto en un zip o te guío para generarlo con Spring Initializr?

Y cuando termines este, te paso el **Ejercicio 2: Login personalizado + UserDetailsService en memoria**. ¿Te parece?
