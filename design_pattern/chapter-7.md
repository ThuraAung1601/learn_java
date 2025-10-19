# Chapter 7
# Software Design and Architecture: Foundations and Patterns

## Architecture Styles
Describe named relationships of components, covering various architectural characteristics. Names act as shorthand among experienced architects (similar to design patterns).

### Common Architecture Styles
- Layered architecture  
- Microkernel architecture  
- Microservices architecture  
- Modular monolithic architecture  
- Event-driven architecture  
- Pipeline architecture  
- Service-based architecture  
- Service-oriented architecture  
- Space-based architecture  

### Categorizing Architecture Styles

**Based on Deployment Model:**
- **Monolithic:** All components deployed as a single unit.  
- **Distributed:** Components deployed separately as multiple units.  

**Based on Technical Partitioning:**
- Layered  

**Based on Domain Partitioning:**
- Modular monolith  

---

## Monolithic vs. Distributed Architectures

**Monolithic:**
- Single deployment unit.  
- Includes Layered, Modular Monolith, Pipeline, and Microkernel architectures.  

**Distributed:**
- Multiple deployment units connected through remote access protocols.  
- Includes Service-based, Event-driven, Space-based, Service-oriented, and Microservices architectures.  

### Monolithic Architectures: Pros
- Easy to develop and understand.  
- Easy to debug (all code in one place).  
- Often inexpensive to build.  
- Good for rapid product development.  

### Monolithic Architectures: Cons
- All-or-nothing scaling.  
- Unreliable: a single bug can crash the entire application.  

### Distributed Architectures: Pros
- Highly scalable (components scale independently).  
- Encourage modularity, which simplifies testing.  

### Distributed Architectures: Cons
- Expensive to develop, maintain, and debug.  
- Complex due to network communication between services.  

---

## Layered Architecture Style

A technically partitioned architecture based on separation of concerns.

### Logical Topology
- Presentation Layer  
- Business Layer  
- Persistence Layer  
- Database Layer  

### Physical Topology
- Multiple instances of the logical topology can be deployed.  

### Layers of Isolation
- **Closed Layer:** Request must go through each layer.  
- **Open Layer:** Request can bypass layers.  
- Isolation ensures changes in one layer generally don't impact others.  

### Adding Layers
- Adding a services layer can enforce restrictions on layer access.  

### Other Considerations
- Good starting point when the final architecture is unknown.  
- Avoid the "sink hole architecture" anti-pattern (pass-through processing).  

### Data Topologies
- Single, monolithic database is common.  

### Cloud Considerations
- **Advantage:** Technical partitioning fits well with cloud deployments.  
- **Disadvantage:** Communication latency between on-premises and cloud can be problematic.  

### Common Risks
- Fault tolerance: Single failure can crash the entire application.  
- Availability: High mean time to recover (MTTR).  

### Governance
- Excellent for governance.  
- Easy to define Fitness Function libraries.  
- Allows automated governance of relationships between layers.  

### Team Topology Considerations
- Independent of team topologies.  

### Style Characteristics
| Characteristic       | Value |
|---------------------|-------|
| Overall Cost         | $     |
| Partitioning Type    | Technical |
| Number of Quanta     | 1     |
| Simplicity           | ☆     |
| Modularity           | ☆☆    |
| Maintainability      | ☆☆    |
| Testability          | ☆☆☆   |
| Deployability        | ☆     |
| Evolvability         | ☆     |
| Responsiveness       | ☆     |
| Scalability          | ☆     |
| Elasticity           | ☆     |
| Fault Tolerance      | ☆     |

### When to Use
- Small, simple applications or websites.  
- Tight budget and time constraints.  
- Unsure which architecture style is best.  

### When Not to Use
- When the system requires high maintainability, agility, testability, and deployability.  

---

## Modular Monolith Style

A domain-partitioned architecture where functionality is grouped by domain area (modules).

### Topology
- Functionality is grouped by domain area, with domains called modules.  

### Monolithic Structure
- All source code in one place.  
- Easy to maintain, test, and deploy.  
- Requires strict governance to maintain module boundaries.  
- Risk of excessive code reuse and communication between modules.  

### Modular Structure
- Modules are self-contained artifacts (JAR/DLL).  
- Suited to independent teams working on separate modules.  
- Works well for larger, complex systems where modules require different expertise.  
- Tends to have less reuse and less module communication.  

### Module Communication
- **Peer-to-peer approach:** Modules communicate directly.  
- **Mediator approach:** Modules communicate through a central mediator.  

### Data Topologies
- Multiple modules sharing one or more databases.  

### Cloud Considerations
- Not generally well-suited for cloud deployment, though small systems can leverage cloud services.  

### Common Risks
- Becomes too large to maintain, test, and deploy.  
- Slow change implementation.  
- Unexpected breakages.  
- Team members interfering with each other.  
- Slow startup times.  
- Overboard code reuse.  

### Governance
- Define and enforce module compliance.  
- Control the amount of communication between modules.  

### Team Topology Considerations
- Works best when teams are organized by domain area (cross-functional teams).  

### Style Characteristics
| Characteristic       | Value |
|---------------------|-------|
| Overall Cost         | $     |
| Partitioning Type    | Domain |
| Number of Quanta     | 1     |
| Simplicity           | ☆     |
| Modularity           | ☆☆☆   |
| Maintainability      | ☆☆    |
| Testability          | ☆☆    |
| Deployability        | ☆☆    |
| Evolvability         | ☆☆    |
| Responsiveness       | ☆     |
| Scalability          | ☆     |
| Elasticity           | ☆     |
| Fault Tolerance      | ☆     |

### When to Use
- Tight budget and time constraints.  
- Unclear architectural direction.  
- Domain-focused teams (cross-functional).  
- Majority of changes are domain-based.  
- Teams engaging in Domain-Driven Design (DDD).  

### When Not to Use
- Systems requiring high scalability, elasticity, availability, fault tolerance, responsiveness, and performance.  
- When the majority of changes are technically oriented.  

---

## Singleton and State Patterns

### Design Principles
**High-level:** Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion  
**Low-level:** Encapsulate what varies, Program to interfaces, Favor composition, Loose coupling  

---

### Singleton Pattern
Ensures a class has only one instance and provides a global access point.

**Problem:**  
Multiple instantiations lead to incorrect behavior, overuse of resources, and inconsistent results.

**Class Diagram:**  
A class with a private constructor and a public static method to get the instance.

**Solution:**  
```java
public class Singleton {
    private static Singleton uniqueInstance;
    private Singleton() {} // private constructor
    public static Singleton getInstance() { // static method
        if (uniqueInstance == null) { // Lazy instantiation
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}
````

### Thread Safety

Multiple threads can cause problems during instantiation.

**Thread Safety Solutions:**

**1. Synchronized Method**

```java
public static synchronized Singleton getInstance() {
    if (uniqueInstance == null) {
        uniqueInstance = new Singleton();
    }
    return uniqueInstance;
}
```

* Expensive, impacts performance.

**2. Eager Instantiation**

```java
private static Singleton uniqueInstance = new Singleton();
```

* JVM handles instance creation before threads have access. Always creates singleton even if not used.

**3. Double-Checked Locking**

```java
private volatile static Singleton uniqueInstance;
public static Singleton getInstance() {
    if (uniqueInstance == null) {
        synchronized (Singleton.class) {
            if (uniqueInstance == null) {
                uniqueInstance = new Singleton();
            }
        }
    }
    return uniqueInstance;
}
```

* `volatile` keyword ensures visibility across threads. Doesn't work in Java < 1.4

**4. Lazy Loading (Initialization-on-demand holder idiom)**

```java
public class Singleton {
    private Singleton() {}
    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
    }
    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

**Benefits:**

* Ensures only one instance.
* Provides global access.

---

### State Pattern

Allows an object to alter its behavior when its internal state changes. The object will appear to change its class.

**Problem:**
Methods with large conditional statements based on object state are difficult to maintain and extend.

**Class Diagram:**
A context class holds a state and delegates calls to it. State classes implement a `State` interface.

**State Interface:**

```java
public interface State {
    public void doThis();
    public void doThat();
}
```

**Implementation:**

* Each state implements the `State` interface and defines specific behavior.
* The context class holds a reference to the current state and delegates method calls to it.
* State transitions are handled within the state classes, modifying the context state as needed.

**Example: Gumball Machine**

* States: `NoQuarter`, `HasQuarter`, `Sold`, `SoldOut`, `WinnerState`

```
