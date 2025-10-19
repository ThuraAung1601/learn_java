# Software Design and Architecture

## Design Principles

### High-Level Principles (SOLID)

* **Single Responsibility Principle (SRP):** Every object should have a single responsibility, and all its services should be focused on carrying it out. A class should have only one reason to change.
* **Open/Closed Principle (OCP):** Classes should be open for extension, but closed for modification.
* **Liskov Substitution Principle (LSP):** Subtypes must be substitutable for their base types without altering correctness.
* **Interface Segregation Principle (ISP):** Clients should not be forced to depend on methods they do not use.
* **Dependency Inversion Principle (DIP):** Depend upon abstractions, not concretions. High-level modules should not depend on low-level modules; both should depend on abstractions.

### Low-Level Principles

* **Encapsulate what varies**
* **Favor composition over inheritance**
* **Program to interfaces, not implementations**
* **Strive for loose coupling**

---

## Design Patterns

### Observer Pattern

* **Problem:** Related objects need to communicate to maintain consistency.
* **Solution:** One-to-many dependency; when one object changes state, all dependents are updated.
* **Components:** Subject, Concrete Subject, Observer, Concrete Observer.
* **Push vs. Pull:** Push sends all data; Pull lets observers request needed data.
* **Benefits:** Loose coupling, runtime extensibility, independent reuse.

### Delegation Pattern

* **Concept:** Pass a request to another object.
* **Design Principle:** Favor composition over inheritance.
* **Benefits:** Better abstraction, less code, dynamic relationships.

### Strategy Pattern

* **Problem:** Need interchangeable behaviors.
* **Solution:** Define family of algorithms, encapsulate, and make them interchangeable.
* **Components:** Strategy, Concrete Strategy, Context.
* **Principles:** Encapsulate what varies, program to interfaces, favor composition.

### Decorator Pattern

* **Problem:** Inheritance explosion.
* **Solution:** Dynamically add responsibilities by wrapping objects.
* **Properties:** Runtime decoration, wrapper adds behavior.
* **Benefits:** Adheres to OCP, flexible responsibility assignment.

### Factory Method Pattern

* **Problem:** Too many dependencies on concrete classes.
* **Solution:** Define interface for object creation, subclasses decide instantiation.
* **Benefits:** Decoupling, flexible creation, supports related families.

### Abstract Factory Pattern

* **Problem:** Need families of related objects without specifying classes.
* **Solution:** Interface for creating families of objects.
* **Benefits:** Decouples client, supports object families, flexible creation.

### Template Method Pattern

* **Problem:** Duplicated code is hard to maintain.
* **Solution:** Define skeleton in a method, defer steps to subclasses.
* **Concepts:** Abstract class with template, concrete subclasses, optional hooks.
* **Benefits:** Reuse, centralized control, extensible.
* **Comparison:** Template Method (inheritance) vs Strategy (composition).

### Command Pattern

* **Problem:** Large switch/if-else for actions.
* **Solution:** Encapsulate request as object; supports parameterization, queuing, undo.
* **Components:** Command, Concrete Command, Receiver, Invoker, Client.
* **Benefits:** Decouples invoker/receiver, supports undo, logging, queues.

### Facade Pattern

* **Problem:** Complex subsystem interfaces.
* **Solution:** Provide unified, high-level interface.
* **Benefits:** Simplifies use, reduces dependencies.

### Adapter Pattern

* **Problem:** Interface incompatibility.
* **Solution:** Convert interface to expected one.
* **Types:** Object Adapter (composition), Class Adapter (inheritance).
* **Benefits:** Incompatible classes can work together, integration flexibility.

### Memento Pattern

* **Problem:** Need to save/restore state without exposing internals.
* **Solution:** Capture and externalize state.
* **Components:** Originator, Memento, Caretaker.
* **Benefits:** Preserves encapsulation, supports undo/redo.

---

## Architectural Characteristics

### Categories

* **Operational:** Availability, continuity, performance, recoverability.
* **Structural:** Maintainability, portability, upgradeability, extensibility, modularity.
* **Cloud:** Scalability, elasticity, zone-based availability, region-based security.
* **Cross-Cutting:** Privacy, security, usability, supportability.

### Importance

* Define success criteria.
* Influence architecture structure.

### Considerations

* Limit to avoid overengineering.
* Consider explicit and implicit needs.

### Identification

* Domain requirements.
* Implicit domain knowledge.
* Domain stakeholder concerns.

### Prioritization

* Prioritize characteristics by context.

---

## Documentation

**Architecture Characteristics Worksheet**

* System/Project
* Architect/Team
* Top 3 Driving Characteristics
* Implicit Characteristics
* Domain/Quantum
* Date
* Next Review

**Composite Characteristics**

* Agility = Maintainability + Testability + Deployability
* Reliability = Availability + Fault tolerance + Data integrity + Data consistency

---

## Laws of Software Architecture

* Everything is a trade-off.
* Why is more important than how.
* Most decisions exist on a spectrum.

---

## Expectations of an Architect

* Make and analyze architecture decisions.
* Stay current with trends.
* Ensure compliance.
* Understand technologies and business domain.
* Lead team and manage politics.

---

## Architectural Thinking

* Know difference between architecture and design.
* Balance breadth and depth.
* Reconcile trade-offs.
* Translate business drivers into architecture concerns.

---

## Technical Breadth

* Stuff you know
* Stuff you know you don’t know
* Stuff you don’t know you don’t know

---

## Analyzing Trade-Offs

* Every solution has trade-offs.
* No absolute right or wrong, only trade-offs.

---

## Understanding Business Drivers

* Translate business needs into architectural characteristics like scalability, performance, availability.

---

## Modularity

### Definition

* Ability to separate and recombine system components.

### Measuring Modularity

* **Cohesion:** Relatedness of module parts.
* **Coupling:** Dependencies between modules.
* **Connascence:** Change in one requires change in another.

### Coupling Metrics

* **Afferent Coupling (Ca):** Incoming dependencies.
* **Efferent Coupling (Ce):** Outgoing dependencies.
* **Abstractness (A):** Na/Nc.
* **Instability (I):** Ce / (Ca+Ce).
* **Distance (D):** |A + I – 1|.

### Connascence Types

* **Static:** Name, type, position, algorithm.
* **Dynamic:** Execution, timing, values, identity.

---

## Scope of Architectural Characteristics

### System-Level

Availability, customizability, elasticity, feasibility, i18n, performance, portability, reliability, scalability, security, simplicity, usability.

### Component-Level

Availability, scalability, performance, customizability, reliability, elasticity, security, feasibility, simplicity, i18n, usability, portability.

---

## Architecture Quantum

* Independently deployable unit with high cohesion.
* Contains all needed components to function independently.

---

## Measuring and Governing Characteristics

### Metrics

* Performance, cyclomatic complexity.
* Agility = Testability + Deployability + Maintainability.
* Testability: via code coverage.
* Deployability: deployment success rate, duration, issues.
* Maintainability: time to apply change.

### Governance

* **Fitness Function:** Measures architecture integrity.

  * Atomic (single characteristic).
  * Holistic (combination).
* Examples: Jdepend, ArchUnit, Netflix Chaos Monkey.

---

## Refactoring

### Definition

Change system structure without changing behavior.

### How to Refactor Safely

* Test constantly.

### Code Smells

Duplicated code, long methods, large classes, shotgun surgery, feature envy, data clumps, primitive obsession, switch statements, lazy class, temporary field, message chains, middle man, inappropriate intimacy, refused bequest, excessive comments.

### Common Refactoring Patterns

Extract method, move method, replace conditional with polymorphism, introduce null object, separate query from modifier, encapsulate collection.

---

## Component-Based Thinking

* **Logical Components:** Functional building blocks.
* **Physical Components:** Services, UIs, databases.

### Process

1. Analyze roles and responsibilities.
2. Identify core components.
3. Assign stories.
4. Analyze characteristics.
5. Refactor or add components.

### Component Coupling

* **Static:** Compile-time/synchronous.
* **Temporal:** Transactional/time-based.

**Law of Demeter:** Limited knowledge of other components.

---

## Architecture Partitioning

* **Technical partitioning**
* **Domain partitioning**

---

## Conway’s Law

> Organizations design systems that mirror their communication structures.


| Architecture | 💰 Cost | 🧩 Partition | ✨ Simplicity | 📝 Description |
|---------------|---------|--------------|---------------|----------------|
| Layered | 👍 Good | 🛠️ Technical | 👍 Good | Components organized in layers with clear separation of concerns |
| Event-driven | ⚪ Average | 🌐 Domain | ⚪ Average | Uses events to decouple producers and consumers for asynchronous flow |
| Microkernel | ⚪ Average | 🛠️ Technical | ⚪ Average | Minimal core system with extensible plugins for added functionality |
| Microservices | 👎 Bad | 🌐 Domain | 👎 Bad | Many small services independently deployable and scalable |
| Modular Monolithic | 👍 Good | 🌐 Domain | 👍 Good | Single deployable application with internally modular components |
| Pipeline | ⚪ Average | 🛠️ Technical | 👍 Good | Sequential stages process data in a modular pipeline structure |
| Service-based | ⚪ Average | 🌐 Domain | 👍 Good | Coarse-grained services that group functionality by business domain |
| SOA | 👎 Bad | 🌐 Domain | 👎 Bad | Services communicate asynchronously via a shared bus or middleware |
| Space-based | 👎 Bad | 🌐 Domain | ⚪ Average | In-memory data grid with distributed processing units for scalability |


| Architecture | 🛠️ Maintain | ✅ Test | 🚀 Deploy | 🔄 Evolve | ⚡ Resp | 📈 Scale | 🌐 Elastic | ⚠️ Fault | Reason |
|---------------|-------------|---------|-----------|-----------|---------|----------|------------|----------|--------|
| Layered | ⚪ Avg | 👍 Good | 👎 Bad | ⚪ Avg | ⚪ Avg | ⚪ Avg | 👎 Bad | 👎 Bad | Layer dependencies make deploy & fault isolation hard |
| Event-driven | 👍 Good | ⚪ Avg | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Independent event-driven components enable scaling & resilience |
| Microkernel | 👍 Good | 👍 Good | ⚪ Avg | 👍 Good | ⚪ Avg | ⚪ Avg | ⚪ Avg | 👍 Good | Plugins are maintainable & isolated but core changes affect deploy/latency |
| Microservices | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Fully independent services allow high deployability, scale, and fault tolerance |
| Modular Monolithic | ⚪ Avg | 👍 Good | 👎 Bad | ⚪ Avg | 👍 Good | ⚪ Avg | ⚪ Avg | 👎 Bad | Single deploy restricts evolution & increases risk of cascading failures |
| Pipeline | 👍 Good | 👍 Good | ⚪ Avg | 👍 Good | ⚪ Avg | 👎 Bad | 👎 Bad | 👎 Bad | Stages are modular & testable, but failures propagate through pipeline |
| Service-based | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | ⚪ Avg | ⚪ Avg | ⚪ Avg | Coarse-grained services ease deploy but limit elastic scaling & fault isolation |
| SOA | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | Shared bus/middleware creates dependency, slowing testing, scaling, and evolution |
| Space-based | 👍 Good | ⚪ Avg | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Partitioned memory grid allows independent scaling, deployment, and fault tolerance |

---


