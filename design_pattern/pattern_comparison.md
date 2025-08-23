# Pattern Comparison 

## 1. Creational Patterns

**Focus:** How objects are created.
**Idea:** Give flexible ways to create objects instead of always using `new`.

**Examples:**

* Factory → delegates object creation to subclasses

**Pros:** More control over object creation, flexible, reusable
**Cons:** Can be complex, sometimes unnecessary

---

## 2. Structural Patterns

**Focus:** How objects are combined or organized.
**Idea:** Build larger structures by composing objects.

**Examples:**

* Adapter → convert one interface to another
* Decorator → add features to an object dynamically
* Composite → represent tree-like structures (files and folders)
* Facade → provide a simple interface for complex systems

**Pros:** Simplifies complex systems, encourages reuse
**Cons:** Adds extra abstraction, may complicate debugging

---

## 3. Behavioral Patterns

**Focus:** How objects interact and communicate.
**Idea:** Define responsibilities and interactions between objects.

**Examples:**

* Observer → notify many when one changes
* Strategy → choose an algorithm at runtime
* Command → encapsulate actions (undo, redo)
  
**Pros:** Flexible communication, easier to change behavior
**Cons:** Often leads to many small classes, harder to follow

---

## Quick Comparison

| Category   | Focus                | Examples                               | Pros                                 | Cons                           |
| ---------- | -------------------- | -------------------------------------- | ------------------------------------ | ------------------------------ |
| Creational | Object creation      | Abstract Factory, Factory | More control, flexible creation      | Can be complex                 |
| Structural | Object composition   | Adapter, Decorator, Composite, Facade  | Simplifies design, promotes reuse    | Extra abstraction, debugging   |
| Behavioral | Object communication | Observer, Strategy, Command     | Flexible interactions, extensibility | Many classes, harder to follow |

---
---

# SOLID Principle

## 1. Creational Patterns
**SOLID Alignment:**
* **Single Responsibility Principle (SRP):** Object creation logic is separated from business logic
* **Open/Closed Principle (OCP):** Factories and builders allow adding new types without changing existing code
* **Dependency Inversion Principle (DIP):** Clients depend on abstractions (interfaces), not concrete classes

---

## 2. Structural Patterns
**SOLID Alignment:**
* **Open/Closed Principle (OCP):** Decorator allows new features without modifying original code
* **Interface Segregation Principle (ISP):** Adapter and Proxy help keep interfaces client-specific
* **Single Responsibility Principle (SRP):** Facade centralizes complex logic into one entry point without altering subsystems

---

## 3. Behavioral Patterns
**SOLID Alignment:**
* **Open/Closed Principle (OCP):** Strategy lets you add new algorithms without changing the context
* **Single Responsibility Principle (SRP):** Command encapsulates a single action in one class
* **Dependency Inversion Principle (DIP):** Observer and Mediator rely on abstractions to decouple senders and receivers

## Quick Comparison with SOLID

| Category   | Focus                | Common Patterns               | Main SOLID Principles Supported |
| ---------- | -------------------- | ----------------------------- | ------------------------------- |
| Creational | Object creation      | Singleton, Factory, Builder   | SRP, OCP, DIP                   |
| Structural | Object composition   | Adapter, Decorator, Composite | OCP, ISP, SRP                   |
| Behavioral | Object communication | Observer, Strategy, Command   | OCP, SRP, DIP                   |

---
---
