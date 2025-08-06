# Hotel Booking Application - User Service Flow Chart

## 1. Application Startup Flow

```mermaid
flowchart TD
    A[Application.java] --> B[Spring Boot Context]
    B --> C[Load application.properties]
    C --> D[Initialize Database Connection]
    D --> E[Configure Security]
    E --> F[Register JWT Filter]
    F --> G[Start Web Server on Port 8080]
    G --> H[Application Ready]
    
    style A fill:#e1f5fe
    style H fill:#c8e6c9
```

## 2. User Registration Flow

```mermaid
flowchart TD
    A[Client Request: POST /auth/register] --> B[UserSignUpSignInController.registerUser]
    B --> C[Validate UserReqDTO]
    C --> D{Validation Passed?}
    D -->|No| E[Return Validation Error]
    D -->|Yes| F[UserServiceImpl.registerUser]
    F --> G[Check if email exists]
    G --> H{Email Exists?}
    H -->|Yes| I[Throw UserAlreadyExistsException]
    H -->|No| J[Create UserEntity]
    J --> K[Encode Password with BCrypt]
    K --> L[Save User to Database]
    L --> M[Generate JWT Token]
    M --> N[Create AuthResponse]
    N --> O[Return Success Response]
    
    I --> P[GlobalExceptionHandler]
    P --> Q[Return 409 Conflict]
    
    E --> R[Return 400 Bad Request]
    
    style A fill:#e3f2fd
    style O fill:#c8e6c9
    style Q fill:#ffcdd2
    style R fill:#ffcdd2
```

## 3. User Login Flow

```mermaid
flowchart TD
    A[Client Request: POST /auth/login] --> B[UserSignUpSignInController.loginUser]
    B --> C[Validate AuthRequest]
    C --> D{Validation Passed?}
    D -->|No| E[Return Validation Error]
    D -->|Yes| F[UserServiceImpl.loginUser]
    F --> G[AuthenticationManager.authenticate]
    G --> H[CustomUserDetailsServiceImpl.loadUserByUsername]
    H --> I{User Found?}
    I -->|No| J[Throw UsernameNotFoundException]
    I -->|Yes| K[Validate Password]
    K --> L{Password Valid?}
    L -->|No| M[Throw InvalidCredentialsException]
    L -->|Yes| N[Generate JWT Token]
    N --> O[Create AuthResponse]
    O --> P[Return Success Response]
    
    J --> Q[GlobalExceptionHandler]
    Q --> R[Return 404 Not Found]
    
    M --> S[GlobalExceptionHandler]
    S --> T[Return 401 Unauthorized]
    
    E --> U[Return 400 Bad Request]
    
    style A fill:#e3f2fd
    style P fill:#c8e6c9
    style R fill:#ffcdd2
    style T fill:#ffcdd2
    style U fill:#ffcdd2
```

## 4. JWT Authentication Flow

```mermaid
flowchart TD
    A[Client Request with JWT Token] --> B[CustomJwtFilter.doFilterInternal]
    B --> C[Extract Authorization Header]
    C --> D{Header Starts with 'Bearer '?}
    D -->|No| E[Continue to Next Filter]
    D -->|Yes| F[Extract JWT Token]
    F --> G[JwtUtils.extractUsername]
    G --> H{Token Valid?}
    H -->|No| I[Continue to Next Filter]
    H -->|Yes| J[CustomUserDetailsServiceImpl.loadUserByUsername]
    J --> K{User Found?}
    K -->|No| L[Continue to Next Filter]
    K -->|Yes| M[JwtUtils.validateToken]
    M --> N{Token Valid for User?}
    N -->|No| O[Continue to Next Filter]
    N -->|Yes| P[Create UsernamePasswordAuthenticationToken]
    P --> Q[Set Authentication in SecurityContext]
    Q --> R[Continue to Next Filter]
    R --> S[Controller Method Execution]
    
    style A fill:#e3f2fd
    style S fill:#c8e6c9
```

## 5. Protected Endpoint Access Flow

```mermaid
flowchart TD
    A[Client Request to Protected Endpoint] --> B[CustomJwtFilter]
    B --> C{JWT Token Valid?}
    C -->|No| D[Return 401 Unauthorized]
    C -->|Yes| E[SecurityContextHolder.getAuthentication]
    E --> F{User Authenticated?}
    F -->|No| G[Return 401 Unauthorized]
    F -->|Yes| H[Check @PreAuthorize Annotation]
    H --> I{User Has Required Role?}
    I -->|No| J[Return 403 Forbidden]
    I -->|Yes| K[Execute Controller Method]
    K --> L[Service Layer Processing]
    L --> M[Return Response]
    
    style A fill:#e3f2fd
    style M fill:#c8e6c9
    style D fill:#ffcdd2
    style G fill:#ffcdd2
    style J fill:#ffcdd2
```

## 6. User Profile Retrieval Flow

```mermaid
flowchart TD
    A[Client Request: GET /auth/profile] --> B[UserSignUpSignInController.getCurrentUserProfile]
    B --> C[Check @PreAuthorize Annotation]
    C --> D{User Has Required Role?}
    D -->|No| E[Return 403 Forbidden]
    D -->|Yes| F[UserServiceImpl.getUserByEmail]
    F --> G[UserRepository.findByEmail]
    G --> H{User Found?}
    H -->|No| I[Throw UserNotFoundException]
    H -->|Yes| J[Convert to UserRespDTO]
    J --> K[Return Success Response]
    
    I --> L[GlobalExceptionHandler]
    L --> M[Return 404 Not Found]
    
    style A fill:#e3f2fd
    style K fill:#c8e6c9
    style E fill:#ffcdd2
    style M fill:#ffcdd2
```

## 7. Admin User Management Flow

```mermaid
flowchart TD
    A[Admin Request: GET /auth/users] --> B[UserSignUpSignInController.getAllUsers]
    B --> C[Check @PreAuthorize hasRole('ADMIN')]
    C --> D{User is ADMIN?}
    D -->|No| E[Return 403 Forbidden]
    D -->|Yes| F[UserServiceImpl.getAllUsers]
    F --> G[UserRepository.findAll]
    G --> H[Convert List<UserEntity> to List<UserRespDTO>]
    H --> I[Return Success Response]
    
    A2[Admin Request: PUT /auth/users/{id}] --> B2[UserSignUpSignInController.updateUser]
    B2 --> C2[Check @PreAuthorize hasRole('ADMIN')]
    C2 --> D2{User is ADMIN?}
    D2 -->|No| E2[Return 403 Forbidden]
    D2 -->|Yes| F2[UserServiceImpl.updateUser]
    F2 --> G2[UserRepository.findById]
    G2 --> H2{User Found?}
    H2 -->|No| I2[Throw UserNotFoundException]
    H2 -->|Yes| J2[Update User Fields]
    J2 --> K2[UserRepository.save]
    K2 --> L2[Convert to UserRespDTO]
    L2 --> M2[Return Success Response]
    
    I2 --> N2[GlobalExceptionHandler]
    N2 --> O2[Return 404 Not Found]
    
    style A fill:#e3f2fd
    style A2 fill:#e3f2fd
    style I fill:#c8e6c9
    style M2 fill:#c8e6c9
    style E fill:#ffcdd2
    style E2 fill:#ffcdd2
    style O2 fill:#ffcdd2
```

## 8. Password Change Flow

```mermaid
flowchart TD
    A[Client Request: POST /auth/change-password] --> B[UserSignUpSignInController.changePassword]
    B --> C[Check @PreAuthorize hasAnyRole]
    C --> D{User Has Required Role?}
    D -->|No| E[Return 403 Forbidden]
    D -->|Yes| F[UserServiceImpl.changePassword]
    F --> G[UserRepository.findById]
    G --> H{User Found?}
    H -->|No| I[Throw UserNotFoundException]
    H -->|Yes| J[BCrypt.matches oldPassword]
    J --> K{Old Password Correct?}
    K -->|No| L[Throw InvalidCredentialsException]
    K -->|Yes| M[BCrypt.encode newPassword]
    M --> N[UserRepository.save]
    N --> O[Return Success Response]
    
    I --> P[GlobalExceptionHandler]
    P --> Q[Return 404 Not Found]
    
    L --> R[GlobalExceptionHandler]
    R --> S[Return 401 Unauthorized]
    
    style A fill:#e3f2fd
    style O fill:#c8e6c9
    style E fill:#ffcdd2
    style Q fill:#ffcdd2
    style S fill:#ffcdd2
```

## 9. Exception Handling Flow

```mermaid
flowchart TD
    A[Exception Thrown] --> B[GlobalExceptionHandler]
    B --> C{Exception Type?}
    
    C -->|UserAlreadyExistsException| D[Return 409 Conflict]
    C -->|UserNotFoundException| E[Return 404 Not Found]
    C -->|InvalidCredentialsException| F[Return 401 Unauthorized]
    C -->|UsernameNotFoundException| G[Return 404 Not Found]
    C -->|MethodArgumentNotValidException| H[Return 400 Bad Request]
    C -->|Generic Exception| I[Return 500 Internal Server Error]
    
    H --> J[Create Validation Error Map]
    J --> K[Return Error Response]
    
    style A fill:#ffcdd2
    style D fill:#ffcdd2
    style E fill:#ffcdd2
    style F fill:#ffcdd2
    style G fill:#ffcdd2
    style K fill:#ffcdd2
    style I fill:#ffcdd2
```

## 10. Database Operations Flow

```mermaid
flowchart TD
    A[Service Layer Request] --> B[Repository Interface]
    B --> C[Spring Data JPA]
    C --> D[MySQL Database]
    D --> E[Return Result]
    E --> F[Service Layer Processing]
    F --> G[Return Response]
    
    subgraph "Repository Methods"
        H[findByEmail]
        I[existsByEmail]
        J[findById]
        K[findAll]
        L[save]
        M[deleteById]
    end
    
    B --> H
    B --> I
    B --> J
    B --> K
    B --> L
    B --> M
    
    style A fill:#e3f2fd
    style G fill:#c8e6c9
```

## 11. Security Configuration Flow

```mermaid
flowchart TD
    A[SecurityConfiguration] --> B[SecurityFilterChain]
    B --> C[Disable CSRF]
    C --> D[Configure CORS]
    D --> E[Authorize Requests]
    E --> F[Configure Session Management]
    F --> G[Add JWT Filter]
    G --> H[Configure Authentication Provider]
    H --> I[Configure Password Encoder]
    I --> J[Configure Authentication Manager]
    J --> K[Security Configuration Complete]
    
    E --> L[Permit /auth/**]
    E --> M[Permit Swagger UI]
    E --> N[Authenticate All Other Requests]
    
    style A fill:#e1f5fe
    style K fill:#c8e6c9
```

## 12. Complete Request-Response Flow

```mermaid
flowchart TD
    A[Client Request] --> B[CustomJwtFilter]
    B --> C{JWT Token Valid?}
    C -->|No| D[401 Unauthorized]
    C -->|Yes| E[Controller Layer]
    E --> F[Service Layer]
    F --> G[Repository Layer]
    G --> H[Database]
    H --> I[Repository Layer]
    I --> J[Service Layer]
    J --> K[Controller Layer]
    K --> L[GlobalExceptionHandler]
    L --> M[Client Response]
    
    style A fill:#e3f2fd
    style M fill:#c8e6c9
    style D fill:#ffcdd2
```

## Color Legend

- 🔵 **Blue**: Input/Start points
- 🟢 **Green**: Success/End points  
- 🔴 **Red**: Error/Exception paths
- 🟡 **Yellow**: Processing/Decision points

## Key Components

1. **Controllers**: Handle HTTP requests and responses
2. **Services**: Business logic implementation
3. **Repositories**: Data access layer
4. **Security**: JWT authentication and authorization
5. **Exception Handling**: Centralized error management
6. **Validation**: Input validation and error handling

This flow chart provides a comprehensive view of how the Hotel Booking Application User Service handles authentication, authorization, and user management operations with JWT security.