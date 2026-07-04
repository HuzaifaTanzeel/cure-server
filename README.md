# cure-server

Healthcare REST API — Spring Boot · Java 17 · SQL Server

---

## System overview

```mermaid
flowchart TB
  subgraph clients [Clients]
    WebApp[Web / Mobile Client]
  end

  subgraph api [cure-server]
    REST[REST Controllers]
    SVC[Service Layer]
    JPA[Spring Data JPA]
    REST --> SVC --> JPA
  end

  subgraph external [External Services]
    DB[(SQL Server)]
    SMTP[Gmail SMTP]
  end

  WebApp -->|HTTPS| REST
  JPA --> DB
  SVC -->|OTP Email| SMTP
```

---

## Layered architecture

```mermaid
flowchart LR
  subgraph presentation [Presentation]
    C[Controllers]
    DTO[DTOs]
  end

  subgraph business [Business]
    S[Services]
    M[Mappers / ModelMapper]
  end

  subgraph persistence [Persistence]
    R[Repositories]
    E[JPA Entities]
  end

  C --> DTO
  C --> S
  S --> M
  S --> R
  R --> E
```

---

## Domain modules

```mermaid
flowchart TB
  CF[CommonFramework]

  CF --> Auth[Auth & UAM]
  CF --> Party[Party / Practitioner]
  CF --> Org[Facility & Practice]
  CF --> Addr[Address Lookups]

  CF --> Patient[Patient Module]
  Patient --> MH[Medical History]
  Patient --> Allergy[Allergies]

  CF --> Clinical[Clinical Module]
  Clinical --> Enc[Encounters]
  Clinical --> Appt[Appointments]
  Clinical --> Sched[Scheduling]
  Clinical --> Dx[Diagnosis]
  Clinical --> Med[Medications]
  Clinical --> Obs[LOINC / Observations]
```

---

## API surface

```mermaid
flowchart LR
  subgraph routes [REST Endpoints]
    P["/practitioner"]
    L["/authenticateUser"]
    PT["/api/Patient"]
    EN["/api/Encounter"]
    F["/facility"]
    PR["/practice"]
    T["/types"]
  end

  P --> Reg[Registration + OTP]
  P --> Geo[Countries / Provinces / Cities]
  L --> Login[User Authentication]
  PT --> Hist[Allergies · Family · Social · Surgical History]
  EN --> Care[Diagnosis · Meds · Procedures · Appointments]
  F --> Fac[Facility Management]
  PR --> Prac[Practice Management]
```

---

## Practitioner onboarding

```mermaid
sequenceDiagram
  participant Client
  participant API as cure-server
  participant DB as SQL Server
  participant Mail as Gmail SMTP

  Client->>API: POST /practitioner/create
  API->>DB: Persist registration request
  API->>Mail: Send OTP
  API-->>Client: requestId

  Client->>API: POST /practitioner/validate-otp
  API->>DB: Verify OTP
  API-->>Client: Validated

  Client->>API: POST /authenticateUser
  API->>DB: Authenticate credentials
  API-->>Client: UserLoginDTO
```

---

## Clinical encounter flow

```mermaid
flowchart TD
  A[Schedule Availability] --> B[Create Appointment]
  B --> C[Create Encounter]
  C --> D[Record Diagnosis]
  C --> E[Service Requests]
  C --> F[Medication Requests]
  C --> G[Clinical Procedures]
  D --> H[Patient Record Updated]
  E --> H
  F --> H
  G --> H
```

---

## Deployment

```mermaid
flowchart TB
  subgraph cicd [CI/CD]
    GH[GitHub Push]
    GA[GitHub Actions]
    GH --> GA
    GA -->|mvn clean install| JAR[Spring Boot JAR]
  end

  subgraph prod [Production]
    JAR --> Azure[Azure Web App]
    Azure --> AzureDB[(Azure SQL)]
  end

  subgraph local [Local Dev]
    DC[Docker Compose]
    DC --> App[cure-server]
    DC --> MSSQL[(SQL Server 2022)]
    App --> MSSQL
  end
```

---

## Tech stack

| | |
|---|---|
| **Runtime** | Java 17 · Spring Boot 3.1 |
| **Data** | Spring Data JPA · Microsoft SQL Server |
| **Mapping** | ModelMapper · custom DTO mappers |
| **Deploy** | Azure Web App · Docker Compose |
