# Spring Security

## why spring security?

Application security is not fun and its challenging to implement with our custom code

Spring Security built by a team at Spring who are good at security scenarios. Using Spring security we can secure web
apps
with minimum configurations. So there is no need to re-invent the wheel here

Spring security handles common security vulnerabilities like CSRF, CORS etc. For any security vulnerabilities
identified, the framework will be patched immediately as it is being used by many organizations

It helps us secure our pages/API paths, enforce roles, method level security etc with minimum configurations

It supports various standards of security to implement authentication, like using username/password authentication, JWT
tokens, OAuth2, OpenID

## Spring Security Internal Flow

![spring-security-architecture](assets/spring-security-architecture.png)

### Spring Security Filters

These are a series of filters that intercept each request and work together to identify if authentication is required or
not, if authentication is required, accordingly navigate user to login page or use existing details stored during
initial authentication

### Authentication

Filters like UsernamePasswordAuthenticationFilter will extract username/password from the HTTP request and prepare
Authentication type object

### Authentication Manager

Once received request from filter, it delegates the validating of the user details to the available authentication
providers available

### Authentication Provider

It has the core logic of validating user details for authentication

### UserDetailsManager/UserDetailsService

Helps in retrieving, creating, updating, deleting the user details from the DB/storage systems

### PasswordEncoder

Service interface that helps in encoding and hashing the password

### Security Context

Once the request has been authenticated, the authentication will usually be stored in a thread-local securityContext
managed by SecurityContextHolder
This helps during the upcoming requests from the user

## Sequence flow

![spring security flow](assets/spring-security-flow.png)

**high-level detail**

![spring security flow](assets/high-level-detail.png)

## User management

Important classes and Interfaces

![user management](assets/user-management.png)

### Password Management

#### Encoding

Encoding refers to the process of converting data from one form to another and has nothing to do with cryptography

it involves no secret and completely reversible

Encoding can't be used for securing data.

Encoding algorithms include:
ASCII, BASE64, UNICODE

#### Encryption

Its defined as the process of transforming data in such a way that guarantees confidentiality

To achieve confidentiality, encryption requires the use of a secret which, in cryptographic terms, we call key.

Encryption can be reversible by using decryption with the help of the key
As long as the key is confidential, encryption can be considered as secured

#### Hashing

In hashing, data is converted to the hash value using some hashing function

Data once hashed is non-reversible. One cannot determine the original data from a hash value generated

Given some arbitrary data along with the output of the hashing algorithm, one can verify whether the data matches the
original input data without needing to see the original data

## Cross-Origin Resource Sharing (CORS)

This is a protocol that enables scripts running on a browser client to interact with resources from a different origin

For Example, if an App UI tries to make an API call on a different domain, it would be blocked from doing so by default
due to CORS

CORS is not a security issue/attack but the default protection provided by browsers to stop sharing the
data/communication between different origins

different origins means

* a different scheme (HTTP or HTTPS)
* a different domain
* a different port

![cors](assets/cors.png)

### How to fix

* **use @CrossOrigin annotation as follows**

```
@CrossOrigin(origins = "http://localhost:4200") // this allows a specified domain
@CrossOrigin(origins = {"http://localhost:3000", "http://another-frontend.com"}) // it will allow the listed origins
@CrossOrigin(origins = "*") // this allows any domain
```

This is a tedious job in a large application

* **Global cors handling**

we start by defining a bean of type CorsConfigurationSource where we set the required cors config

such as

```
 @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
```

then configure in the securityfilterchain bean such as

```
  @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults()); // by default, uses a Bean by the name of corsConfigurationSource
        return http.build();
    }
```














