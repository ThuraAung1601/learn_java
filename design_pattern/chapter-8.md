# Software Design and Architecture Chapter 8

## Pipeline Architecture Style

**Topology:** Pipes and Filters  
**Also Known As:** Pipes and Filters Architecture  

### Style Specifics

**Pipes:**
- Form the communication channel between filters.
- Typically unidirectional and point-to-point for performance.
- Accept input from one source and direct output to another.
- Favor smaller amounts of data for high performance.

**Filters:**
- Self-contained, independent, and generally stateless.
- Should perform one task only.
- Four types: Producer, Transformer, Tester, Consumer.

### Filter Types

**Producer:**  
- The starting point of a process.  
- Outbound only (source).  

**Transformer:**  
- Accepts input, optionally performs a transformation, and forwards it.  

**Tester:**  
- Accepts input, tests criteria, and optionally produces output based on the test.  

**Consumer:**  
- The termination point for the pipeline flow.  
- Persists results to a database or displays them on a UI.  

### Data Topologies
- Single database or one database per filter.  
- Example: `Capture Raw Data (producer) -> Time Series Sector (transformer) -> Trend Analyzer (transformer) -> Graphing Tool (consumer)`  

### Cloud Considerations
- Well-suited for cloud deployments due to modularity and separate filter types.

### Common Risks
- Overloading filters with too much responsibility.
- Introducing bidirectional communication between filters.
- Handling error conditions.
- Managing contracts between filters.

### Governance
- Govern role and responsibility of each filter type.

### Style Characteristics
| Characteristic       | Value |
|---------------------|-------|
| Overall Cost         | $     |
| Partitioning Type    | Technical |
| Number of Quanta     | 1     |
| Simplicity           | ☆☆    |
| Modularity           | ☆☆☆   |
| Maintainability      | ☆☆    |
| Testability          | ☆☆    |
| Deployability        | ☆☆    |
| Evolvability         | ^     |
| Responsiveness       | ☆     |
| Scalability          | ☆     |
| Elasticity           | ☆     |
| Fault Tolerance      | ☆     |

---

## Microkernel Architecture Style

**Also Known As:** Plug-in Architecture  
**Topology:** Core System and Plug-in Components  

### Style Specifics

**Core System:**
- Minimal functionality required to run the system.
- The "happy path": General processing flow with little custom processing.

**Plug-in Components:**
- Standalone, independent components with specialized processing.
- Isolate volatile code for better maintainability and testability.
- Independent of each other (no dependencies).

### Core System Design
- **Without Microkernel:** Client-specific customization in the core system leads to complex code.  
- **With Microkernel:** Separate plug-in components for each specific task. The core system locates and invokes the corresponding plug-ins.

### Core System Variations
- Layered Core System (Technically Partitioned)  
- Modular Core System (Domain Partitioned)  
- Embedded User Interface (Single Deployment)  
- Separate User Interface (Multiple Deployment Units)  
- Separate User Interface (Multiple Deployment Units, Both Microkernel)

### Plug-In Component Types
- **Runtime:** Added/removed at runtime without redeployment. Managed through frameworks (e.g., Spring).  
- **Compile-based:** Simpler to manage but require full redeployment on modification.

### Plug-In Communication
- Point-to-point communication between plug-ins and the core system.

### Plug-In Implementation
- Shared libraries (JAR, DLL, Gem).  
- Package names in Java or namespaces in C#.  
- Remote plug-in access using REST (each plug-in is a standalone service).

### Remote Plug-In Access
- **Benefits:** Better decoupling, scalability, throughput, runtime changes without frameworks, asynchronous communications.  
- **Drawbacks:** Difficult implementation/deployment, complexity, cost, and dependency on plug-in responsiveness.

### Registry
- A plug-in registry contains information about each plug-in module (name, data contract, remote access protocol details).

### Contracts
- Plug-in Contract: Standard contracts (behavior, input data, output data) or custom contracts (require adapters).  
- Implemented in XML, JSON, or objects.

### Data Topologies
- In-memory or embedded database.

### Cloud Considerations
- Deploy entire application on the cloud.  
- Place just the data in the cloud.  
- Segregate the core system on-premises and the plug-ins in the cloud.

### Common Risks
- Volatile Core: A core that undergoes constant change.  
- Plug-In Dependencies: Plug-ins communicating with each other.

### Governance
- Volatility checks for the core.  
- Rate of change in the core.  
- Contract tests.

### Style Characteristics
| Characteristic       | Value |
|---------------------|-------|
| Overall Cost         | $     |
| Partitioning Type    | Domain and Technical |
| Number of Quanta     | 1     |
| Simplicity           | ☆☆☆   |
| Modularity           | ☆☆☆   |
| Maintainability      | ☆☆    |
| Testability          | ☆☆☆☆  |
| Deployability        | ☆☆    |
| Evolvability         | ☆☆    |
| Responsiveness       | ☆☆    |
| Scalability          | ☆☆    |
| Elasticity           | ☆☆    |
| Fault Tolerance      | ☆☆    |

---

## Iterator and Composite Patterns

### Iterator Pattern
Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

**Problem Addressed:**  
Aggregate objects (lists, etc.) should allow traversal without exposing internal structure.

**Participants:**
- **Iterator:** Interface for accessing and traversing elements. `hasNext()`, `next()`, `remove()`  
- **ConcreteIterator:** Implements the Iterator interface, keeps track of the current position.  
- **IterableAggregate:** Interface for creating an iterator. `createIterator()`  
- **ConcreteAggregate:** Implements the IterableAggregate interface, returns an instance of a ConcreteIterator.  
- **Client:** Uses the iterator to traverse the aggregate.

**Design Principles:**
- **Single Responsibility Principle:** A class should have only one reason to change.  
- **Cohesion:** High cohesion is desired.

### Composite Pattern
Allows you to compose objects into tree structures to represent part-whole hierarchies. Lets clients treat individual objects and compositions of objects uniformly.

**Problem Addressed:**  
Discriminating between leaf-nodes and branches in tree-structured data leads to complex code.

**Participants:**
- **Component:** Interface for objects in the composition. Declares the interface for accessing and managing child components. Defines operations.  
- **Leaf:** Represents leaf objects in the composition. Implements the Component interface. Has no children.  
- **Composite:** Defines behavior for components having children. Stores child components. Implements the Component interface and defines operations for managing children.

**Implementation Issues:**  
Where should child management methods (`add()`, `remove()`, `getChild()`) be declared?

**Approaches:**  
- **In Composite:** Safer, different interfaces for Component and Composite.  
- **In Component:** Transparent, but potentially unsafe.

**External Iterator:**  
Uses recursion to traverse the composite structure. Can be complex to implement.
