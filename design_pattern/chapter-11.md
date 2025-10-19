# Chapter 11
# Software Design and Architecture

## Event-Driven Architecture (EDA)

### Microservice Characteristics
- **Structural:** Domain partitioning  
- **Engineering:** Simplicity, Modularity, Maintainability, Testability, Deployability, Evolvability  
- **Operational:** Responsiveness, Scalability, Elasticity, Fault Tolerance  
- **Overall Cost:** $$$$$ (5 stars)  
- **Partitioning Type:** Domain  
- **Number of Quanta:** 1 to many  

### Examples and Use Cases
- **Auction System:** Requires scalability, elasticity, responsiveness  

### Core Concepts
- **Asynchronous Communication:** Enables parallel processing, faster systems  
- **Events:** Broadcast something that has already happened  
- **Messages:** Request something that needs to be done; sent to a single service via queues  
- **Initiating Event:** Starts the event flow  
- **Derived Events:** Generated in response to initiating event  
- **Event Processors:** Components that process events  

### Topologies

#### Broker Topology
- **Use:** High responsiveness and dynamic control  
- **Components:** Initiating event, event broker, event processor, derived events  
- **Features:** Federated broker contains all event channels; services subscribe, execute logic, and publish events  
- **Advantages:** Highly decoupled processors, scalable, responsive, high performance, fault-tolerant  
- **Disadvantages:** Workflow control, error handling, recoverability, restart capabilities, data inconsistency  

#### Mediator Topology
- **Use:** Workflow control required  
- **Components:** Initiating event, event queue, event mediator, event channels, event processors  
- **Workflow:** Mediator manages workflow; processors report back after handling events  
- **Implementation:**  
  - Simple error handling: Apache Camel, Mule ESB, Spring Integration  
  - Complex conditional workflows: Apache ODE, Oracle BPEL Process Manager  
- **Types of Mediators:** Simple, Hard, Complex  

### Asynchronous & Broadcast Capabilities
- Asynchronous communication improves responsiveness  
- Event processors can broadcast events to other components  

### Data Topologies
| Type                     | Description |
|--------------------------|-------------|
| Monolithic Database      | Centralized; all data available; disadvantages: fault tolerance, scalability, single quantum |
| Domain Database          | Better fault tolerance and scalability; cross-domain data may require synchronous calls |
| Dedicated Database       | Each service has its own database; cross-service data may require synchronous calls |

### Cloud Considerations
- Cloud-friendly; leverages asynchronous services  
- Matches elastic nature of cloud infrastructure  

### Common Risks
- Nondeterministic event processing  
- Excessive static coupling  
- Excessive synchronous communication between processors  
- Overall state management  

### Governance
- Static coupling: contract management  
- Dynamic coupling: synchronous calls  

### EDA vs. Microservices
| Feature               | Event-Driven Architecture | Microservices Architecture |
|-----------------------|---------------------------|---------------------------|
| Performance           | High                      | Limited                   |
| Bounded Contexts      | No physical bounded context | Physical bounded contexts |
| Data Granularity      | Flexible                  | Database-per-service      |
| Service Granularity   | Any size                  | Single-purpose ("micro") |
| Event Processing      | Something that has happened | Something that needs to happen |
| Communication Style   | Asynchronous              | Synchronous              |

### Style Characteristics
| Aspect                | Rating |
|-----------------------|--------|
| Structural            | Technical |
| Engineering           | Simplicity, Modularity, Maintainability, Testability, Deployability, Evolvability |
| Operational           | Responsiveness, Scalability, Elasticity, Fault Tolerance |
| Overall Cost          | $$$ (3 stars) |
| Partitioning Type     | Technical |
| Number of Quanta      | 1 to many |

---

## Builder Pattern

### Intent
Separate construction of a complex object from its representation, allowing the same construction process to create different representations.  

### Participants
- **Builder:** Abstract interface for creating parts of a product  
- **ConcreteBuilder:** Implements Builder; assembles product, provides retrieval interface  
- **Director:** Constructs object using Builder interface  
- **Product:** Complex object under construction  

### Implementation
1. Client creates Director and configures with Builder  
2. Director notifies Builder to build parts  
3. Builder assembles parts  
4. Client retrieves product  

### Example
- **Pizza Creation:** Different PizzaBuilder implementations (e.g., HawaiianPizzaBuilder, SpicyPizzaBuilder) used with Waiter (Director) to construct pizzas  

---

## Visitor Pattern

### Intent
Apply operations to a set of objects at runtime, decoupling operations from object structure.  

### Participants
- **Visitor:** Declares visit operations for each class of ConcreteElement  
- **ConcreteVisitor:** Implements Visitor operations  
- **Element:** Defines `accept()` method for Visitor  
- **ConcreteElement:** Implements `accept()` method  

### Implementation
1. Define Visitor interface with `visit()` for each ConcreteElement  
2. Implement ConcreteVisitor classes  
3. Each ConcreteElement calls `visitor.visit(this)` in `accept()`  

### Example
- Performing operations on **Trash objects** (Aluminum, Paper, Glass) using **PriceVisitor** and **WeightVisitor**
