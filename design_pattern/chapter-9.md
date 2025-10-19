# Chapter 9
# Software Design and Architecture

## Bridge Pattern

### Problem
"Hardening of the software arteries" occurs when subclassing an abstract base class is used to provide alternative implementations, leading to compile-time binding between interface and implementation. The abstraction and implementation cannot be independently extended or composed.

### Bottom-Up Design Example
Consider shapes (Abstract) drawing themselves using different drawing libraries (Implementations) such as Monitor, Printer, or OffScreenBuffer. Each library might have slightly different methods and signatures.

**Examples of Drawing Libraries**  
**Monitor:**  
```text
draw_a_line(x1, y1, x2, y2)
draw_a_circle(x, y, r)
````

**Printer:**

```text
drawline(x1, x2, y1, y2)
drawcircle(x, y, r)
```

**Examples of Shapes**
We want to create collections of rectangles and circles and have them draw themselves regardless of the medium.

```text
Shape
+draw()

Rectangle
+draw()

Circle
+draw()
```

### Approach One: Inheritance for Specialization

* Create subclasses for each combination of shape and library (e.g., MonitorRect, PrinterRect, MonitorCircle, PrinterCircle).
  **Problem:** Class explosion (`n × m` subclasses for `n` shapes and `m` libraries).

**Design Problems:**

* Redundancy (duplication)
* Tight Coupling (subclasses highly dependent on drawing libraries)
* Weak Cohesion (shapes need to know about their drawing libraries)

### Approach Two: Inheritance for Library Specialization

* Create subclasses for each library, then subclasses of those for each shape.
  **Problem:** Same scaling issues as Approach One.

### Solution: Bridge Pattern

* **Encapsulate what varies:** Shapes and Drawing Libraries.
* **Abstract DrawingService:** Defines a uniform interface for drawing operations.
* **Wrap each library** behind the `DrawingService` interface (e.g., `WrapMonitor`, `WrapPrinter`).
* **Favor Delegation:** Shape delegates to DrawingLibrary.

**Classes:**

```text
Shape
+draw()

DrawingService
+drawLine(x1, y1, x2, y2)
+drawCircle(x, y)

Rectangle
+draw()

Circle
+draw()

WrapMonitor
+drawLine(x1, y1, x2, y2)
+drawCircle(x, y)

WrapPrinter
+drawLine(x1, y1, x2, y2)
+drawCircle(x, y)
```

**Definition:**
The Bridge Pattern decouples an abstraction from its implementation so that the two can vary independently.

**Class Diagram:**

```text
Abstraction
+operation()

Variation
+operation()
imp.operationImpl()

Implementor
+operationImpl()

ConcreteImplementorA
+operationImpl()

ConcreteImplementorB
+operationImpl()
```

---

## Mediator Pattern

### Problem

Dependencies between potentially reusable pieces can lead to "spaghetti code."

* "All or nothing" reuse
* "Change one and fix the rest" maintenance
* Distributed behavior is hard to extend
* Participants cannot be reused in other contexts

### Solution

* **Encapsulate what varies:** Interconnections
* Promote loose coupling by preventing objects from referring to each other explicitly
* Vary their interaction independently

**Definition:**
The Mediator Pattern defines an object that encapsulates how a set of objects interact.

**Class Diagram:**

```text
Mediator
    |
Colleague
    |
ConcreteMediator
    |
ConcreteColleague1
ConcreteColleague2
```

**Code Example:**

```java
abstract class Mediator {
    public abstract void colleagueChanged(Colleague c);
}

abstract class Colleague {
    private Mediator myMediator;
    public Colleague(Mediator mediator) { this.myMediator = mediator; }
    public void changed(){ myMediator.colleagueChanged(this); }
}
```

**Example: GUI Components**

```java
class ListBox extends Colleague { ... }
class Button extends Colleague { ... }
class TextField extends Colleague { ... }
class CheckBox extends Colleague { ... }
class FontDialogBox extends Mediator { ... }
```

---

## Proxy Pattern

### Problem

Objects overburden each other with networking, security, access coherence, or historic interface requirements. Core functionality should not include these concerns.

### Definition

The Proxy Pattern provides a surrogate or placeholder for another object to control access to it.

**Class Diagram:**

```text
<<Subject>>
+request()

RealSubject
+request()

Proxy
+request() // proxy instantiates real subject
```

**Types of Proxies:**

* Remote Proxy
* Virtual Proxy
* Protection Proxy
* Cache Proxy
* Firewall Proxy
* Synchronization Proxy
* Smart Reference Proxy

**Proxy vs Decorator:**

* Proxy controls access to an object
* Decorator adds behavior to an object

---

## Service-Based Architecture Style

### Examples and Use Cases

* User interfaces: Receiving, Recycling, Accounting, Customer (web & kiosk)
* Services: Receiving Assessment, Recycling, Accounting, Reporting, Quoting, Item Status

### Topology

* Distributed macro layered structure:

  * Separately deployed user interface
  * Separately deployed remote coarse-grained services (domain services)
  * A monolithic database

### Style Specifics

* Domain service design: API Facade, Business Logic, Persistence layers
* User interface variants: Monolithic, Domain-Based, Service-Based
* API Gateway Options: Proxy or Gateway
* Data Topologies: Database per service or shared database

### Cloud Considerations

* Works well in cloud environments
* Domain services as containerized services
* Leverage cloud file storage, database, and messaging

### Common Risks

* Too much communication between domain services
* Too many domain services (testing, deployment, monitoring, DB connections)

### Governance

* Ensure changes don’t span multiple domain services
* Govern communication between services

### Style Characteristics

| Characteristic    | Rating    |
| ----------------- | --------- |
| Partitioning type | Domain    |
| Number of quanta  | 1 to many |
| Simplicity        | ⋆⋆⋆       |
| Modularity        | ⋆⋆⋆⋆      |
| Maintainability   | ⋆⋆⋆       |
| Testability       | ⋆⋆        |
| Deployability     | ⋆⋆⋆       |
| Evolvability      | ⋆⋆        |
| Responsiveness    | ⋆⋆⋆       |
| Scalability       | ⋆⋆⋆⋆      |
| Elasticity        | ⋆⋆⋆       |
| Fault tolerance   | ⋆⋆⋆       |

---

## Orchestration-Driven Service-Oriented Architecture

### Examples and Use Cases

* Enterprise-wide integration of heterogeneous systems

### Philosophy

* Enterprise-level reuse with no duplicated services

### Topology

* Business Services (BS)
* Enterprise Service Bus (ESB) – Orchestration Engine & Integration Hub
* Enterprise Services (ES), Application Services (AS), Infrastructure Services (IS)

### Taxonomy

* **Business Services:** Input/output/schema info, no code
* **Enterprise Services:** Fine-grained, shared implementations
* **Application Services:** Fine-grained, single application
* **Infrastructure Services:** Operational concerns (monitoring, logging, auth)

### Message Flow

* All requests go through the orchestration engine

### Trade-Offs

* Reuse at service level leads to high coupling
* Changes have ripple effects
* Domain concepts spread thinly

### Style Characteristics

| Characteristic    | Rating    |
| ----------------- | --------- |
| Partitioning type | Technical |
| Number of quanta  | 1 to many |
| Simplicity        | ⋆⋆⋆       |
| Modularity        | ⋆⋆⋆⋆      |
| Maintainability   | ⋆⋆        |
| Testability       | ⋆⋆        |
| Deployability     | ⋆⋆        |
| Evolvability      | ⋆⋆        |
| Responsiveness    | ⋆⋆        |
| Scalability       | ⋆⋆⋆       |
| Elasticity        | ⋆⋆⋆       |
| Fault tolerance   | ⋆⋆⋆       |

