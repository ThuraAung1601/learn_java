# Chapter 10
# Software Design and Architecture

## Microservices Architecture Style

### Core Idea
Extreme domain partitioning, self-deployed services with their own data — a "Share Nothing Architecture." Inspired by Domain-Driven Design (DDD).

### Key Principles
- Favor duplication over coupling  
- Each service is single-purpose  

### Topology
- **API Layer:** Handles client requests and routes them to the appropriate services  
- **Service:** Each has its own module and database  

### Style Specifics
- **Bounded Context:** Binds service functionality and data together to avoid coupling  
- **Granularity:** Impacts performance (network calls, security checks, transactions). Proper granularity is crucial  

### Guidelines
- **Purpose:** Extreme domain partitioning  
- **Transactions:** Service boundary aligns with business workflow and entities  
- **Choreography:** Bundle services if communication overhead is high  
- **Data Isolation:** No shared schemas or databases; teams choose the best database for their service  
- **API Layer:** Should not act as a mediator; logic belongs within bounded contexts  

### Operational Reuse
- **Sidecar Pattern:** For monitoring, logging, circuit breakers  
- **Service Mesh:** Unified operational interface and holistic access  

### Frontends
- **Ideal:** Each service has its own UI  
- **Practical:**  
  - **Monolithic Frontend:** Single UI calling backend services via API  
  - **Microfrontend Pattern:** UI components emitted by each service, coordinated by frontend  

### Communication
- Choose synchronous or asynchronous communication to keep services decoupled  
- **Protocol-aware heterogeneous interoperability:** Services know which protocol to use  
- **Choreography:** Asynchronous, decoupled events  
- **Orchestration:** Asynchronous with a mediator  
- **Transactions:** Avoid cross-service transactions; use **Sagas** if necessary  

### Data Topology
- Monolithic database is not feasible  
- **Database-per-Service** is typical  
- Sharing data between a few microservices is possible  

### Cloud Considerations
- Well-suited for cloud deployments (VMs, containers, databases)  

### Common Risks
- Services too small  
- Excessive communication  
- Over-sharing data  
- Reusing code and sharing functionality  

### Governance
- Monitor and control coupling between services  

### Style Characteristics
| Characteristic    | Rating |
|-----------------|--------|
| Overall Cost      | $$$$$ |
| Partitioning Type | Domain |
| Number of Quanta  | 1 to many |
| Simplicity        | ☆ |
| Modularity        | ★★★★★ |
| Maintainability   | ★★★★★ |
| Testability       | ★★★★★ |
| Deployability     | ★★★★★ |
| Evolvability      | ★★★★★ |
| Responsiveness    | ★★ |
| Scalability       | ★★★★★ |
| Elasticity        | ★★★★★ |
| Fault Tolerance   | ★★★★★ |

---

## Chain of Responsibility Pattern

### Intent
Give more than one object a chance to handle a request, avoiding tight coupling between sender and receiver.

### Structure
- **Handler:** Defines interface for handling requests; may hold reference to next handler  
- **ConcreteHandler:** Handles request if responsible; otherwise forwards  
- **Client:** Initiates request to the first ConcreteHandler  

### Implementation
- Each handler has a `successor` field  
- `setSuccessor()` defines chain  
- `handleRequest()` processes or forwards request  

### Benefits
- Decouples sender and receiver  
- Simplifies object structure  
- Allows dynamic addition/removal of responsibilities  

### Drawbacks
- Request may not be handled  
- Hard to observe runtime behavior; debugging can be difficult  

---

## Flyweight Pattern

### Intent
Use sharing to efficiently support large numbers of fine-grained objects.

### Core Idea
- Distinguish between **intrinsic (shared)** and **extrinsic (unique)** state  
- Share objects with the same intrinsic state  

### Structure
- **Flyweight:** Interface for acting on extrinsic state  
- **ConcreteFlyweight:** Implements Flyweight, stores intrinsic state  
- **FlyweightFactory:** Creates/manages Flyweight objects  
- **Client:** Stores extrinsic state and holds references to Flyweights  

### Implementation
- FlyweightFactory maintains a pool of Flyweight objects  
- Returns existing Flyweight if available; otherwise, creates a new one  

### When to Use
- Application uses a large number of objects  
- Storage costs are high  
- Most object state can be made extrinsic  
- Many objects can be replaced by shared objects  
- Object identity is not important  

### Consequences
- Storage savings through object sharing  
- Tradeoff between object count and intrinsic state size  

### Example
Suppose we have 100,000 Rectangle objects. Each has:  
- **Color:** intrinsic (shared)  
- **Size:** extrinsic (unique)  

Instead of creating 100,000 objects, create one Rectangle per unique color. Size is passed as an argument to `draw()`.  
**RectangleFactory** ensures only one Rectangle exists per color.
