## Pattern Summary


| Pattern                 | Simple English Meaning                     | Use Case                                         | Pros                                                   | Cons                                                  | SOLID Support | Type       |
| ----------------------- | ------------------------------------------ | ------------------------------------------------ | ------------------------------------------------------ | ----------------------------------------------------- | ------------- | ---------- |
| State                   | Change behavior when state changes         | Vending machines, game characters                | Simplifies complex conditional logic                   | Can increase number of classes                        | OCP, SRP      | Behavioral |
| Iterator                | Access elements without exposing structure | Collection traversal (lists, trees)              | Uniform interface for iteration                        | Overhead for simple collections                       | SRP           | Behavioral |
| Mediator                | Centralize complex communication           | Chat room, UI components interaction             | Reduces class dependencies                             | Mediator can become complex                           | SRP, OCP      | Behavioral |
| Chain of Responsibility | Pass requests along a chain until handled  | Logging, request handling, validation pipelines  | Decouples sender and receiver                          | Hard to debug and control flow                        | OCP, SRP      | Behavioral |
| Singleton               | Ensure one instance globally               | Configuration manager, logging                   | Controlled access to single instance                   | Can lead to hidden dependencies                       | N/A           | Creational |
| Builder                 | Step-by-step object construction           | Complex object creation (e.g., documents, meals) | Improves readability, allows different representations | Requires multiple classes                             | SRP           | Creational |
| Composite               | Treat groups of objects as one             | File system, UI hierarchies                      | Simplifies client code                                 | Harder to restrict component types                    | OCP, LSP      | Structural |
| Bridge                  | Separate abstraction from implementation   | Cross-platform UI, graphics renderers            | Improves flexibility and scalability                   | Increases complexity                                  | OCP, SRP      | Structural |
| Proxy                   | Control access to another object           | Remote proxy, lazy loading, security proxy       | Adds security and performance optimization             | Can add overhead and complexity                       | SRP           | Structural |
| Flyweight               | Reuse shared objects efficiently           | Text editors (character objects), game objects   | Reduces memory usage                                   | Complex to implement shared states                    | SRP           | Structural |

---
**Low-level Principles:**

* ✅ **Encapsulation:** Each state hides its internal behavior.
* ✅ **Interfaces not Implementation:** Context interacts via the `State` interface.
* ✅ **Composition over Inheritance:** Context *has-a* state rather than extending it.
* ✅ **Loose Coupling:** Context doesn’t depend on specific state classes.

---

## Low-Level Principle Mapping for Each Pattern

| Pattern                     | Encapsulation                                   | Interfaces not Implementation                | Composition over Inheritance                | Loose Coupling                                |
| --------------------------- | ----------------------------------------------- | -------------------------------------------- | ------------------------------------------- | --------------------------------------------- |
| **State**                   | ✅ State behavior hidden inside concrete classes | ✅ Uses `State` interface                     | ✅ Context holds state objects               | ✅ Context unaware of concrete states          |
| **Iterator**                | ✅ Hides traversal logic from client             | ✅ `Iterator` interface defines access        | ⚪ Uses object containment for collection    | ✅ Client only depends on interface            |
| **Mediator**                | ✅ Centralizes interaction logic                 | ✅ Components use `Mediator` interface        | ✅ Components communicate *through* mediator | ✅ Reduces direct dependencies                 |
| **Chain of Responsibility** | ✅ Each handler encapsulates its logic           | ✅ Uses `Handler` interface                   | ✅ Next handler *composed* in chain          | ✅ Sender decoupled from receivers             |
| **Singleton**               | ⚪ Limited encapsulation (global access)         | ⚪ No interface (direct static access)        | ⚪ No composition used                       | ⚪ Tightly coupled global instance             |
| **Builder**                 | ✅ Encapsulates object construction              | ✅ `Builder` interface/steps define process   | ✅ Uses internal builder object              | ✅ Client decoupled from construction steps    |
| **Composite**               | ✅ Each component encapsulates structure         | ✅ `Component` interface defines contract     | ✅ Composed child components                 | ✅ Uniform treatment via abstraction           |
| **Bridge**                  | ✅ Separates abstraction and implementation      | ✅ `Device` interface used                    | ✅ Abstraction *has* an implementor          | ✅ Abstraction independent from implementation |
| **Proxy**                   | ✅ Controls access internally                    | ✅ `Subject`/`Image` interface defines access | ✅ Proxy *has* a real subject                | ✅ Client unaware of real vs proxy             |
| **Flyweight**               | ✅ Internal (intrinsic) state hidden             | ✅ Uses `Shape` interface                     | ✅ Factory composes shared instances         | ✅ Clients reuse shared objects                |

---

## Architecture Summary

| Architecture | 💰 Cost | 🧩 Partition | 🔢 Quanta | ✨ Simplicity |
|---------------|---------|--------------|-----------|---------------|
| Layered | 👍 Good | 🛠️ Technical | 1 | 👍 Good |
| Event-driven | ⚪ Average | 🌐 Domain | M | ⚪ Average |
| Microkernel | ⚪ Average | 🛠️ Technical | M | ⚪ Average |
| Microservices | 👎 Bad | 🌐 Domain | M | 👎 Bad |
| Modular Monolithic | 👍 Good | 🌐 Domain | M | 👍 Good |
| Pipeline | ⚪ Average | 🛠️ Technical | M | 👍 Good |
| Service-based | ⚪ Average | 🌐 Domain | M | 👍 Good |
| SOA | 👎 Bad | 🌐 Domain | M | 👎 Bad |
| Space-based | 👎 Bad | 🌐 Domain | M | ⚪ Average |

| Architecture | 🛠️ Maintain | ✅ Test | 🚀 Deploy | 🔄 Evolve | ⚡ Resp | 📈 Scale | 🌐 Elastic | ⚠️ Fault | Reason |
|---------------|-------------|---------|-----------|-----------|---------|----------|------------|----------|--------|
| Layered | ⚪ Avg | 👍 Good | 👎 Bad | ⚪ Avg | ⚪ Avg | ⚪ Avg | 👎 Bad | 👎 Bad | Layer dependencies make deploy & fault isolation hard |
| Event-driven | 👍 Good | ⚪ Avg | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Independent event-driven components enable scaling & resilience |
| Microkernel | 👍 Good | 👍 Good | ⚪ Avg | 👍 Good | ⚪ Avg | ⚪ Avg | ⚪ Avg | 👍 Good | Plugins are maintainable & isolated but core changes affect deploy/latency |
| Microservices | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Fully independent services allow high deployability, scale, and fault tolerance |
| Modular Monolithic | ⚪ Avg | 👍 Good | 👎 Bad | ⚪ Avg | 👍 Good | ⚪ Avg | ⚪ Avg | 👎 Bad | Single deploy restricts evolution & increases risk of cascading failures |
| Pipeline | 👍 Good | 👍 Good | ⚪ Avg | 👍 Good | ⚪ Avg | 👍 Good | ⚪ Avg | ⚪ Avg | Stages are modular & testable, but failures propagate through pipeline |
| Service-based | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | ⚪ Avg | ⚪ Avg | ⚪ Avg | Coarse-grained services ease deploy but limit elastic scaling & fault isolation |
| SOA | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | ⚪ Avg | Shared bus/middleware creates dependency, slowing testing, scaling, and evolution |
| Space-based | 👍 Good | ⚪ Avg | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | 👍 Good | Partitioned memory grid allows independent scaling, deployment, and fault tolerance |

---


## Patterns
# 1. State Pattern

**Definition:** Allows an object to change its behavior when its internal state changes.

**Use case:** Vending machines, traffic lights, game character states.

**Pros:**

* Removes complex conditional logic.
* Improves maintainability.

**Cons:**

* Increases number of classes.
* Can be overkill for simple logic.

**Solutions for thread safety**

* Add synchronized to getInstance() (expensive).
* Create eager instance: private static Singleton uniqueInstance = new Singleton(); (JVM handles creation).
* Double-checked locking (requires volatile keyword, doesn't work in Java < 1.4).
* Lazy Loading: use a static inner class to hold the instance.


**SOLID:** Follows **Open/Closed Principle** and **Single Responsibility Principle**.

**Java Example:**

```java
interface State {
    void handle();
}

class OnState implements State {
    public void handle() { System.out.println("Light is ON"); }
}

class OffState implements State {
    public void handle() { System.out.println("Light is OFF"); }
}

class LightSwitch {
    private State state;
    void setState(State state) { this.state = state; }
    void press() { state.handle(); }
}

public class StateDemo {
    public static void main(String[] args) {
        LightSwitch light = new LightSwitch();
        light.setState(new OnState());
        light.press();
        light.setState(new OffState());
        light.press();
    }
}
```

---

# 2. Iterator Pattern

**Definition:** Provides a way to access elements of a collection sequentially without exposing its internal structure.

**Use case:** Looping through lists, trees, or custom collections.

**Pros:**

* Simplifies traversal logic.
* Works uniformly across different collection types.

**Cons:**

* Adds extra classes.
* Overhead for simple collections.

**SOLID:** Follows **Single Responsibility Principle**.

**Java Example:**

```java
import java.util.*;

public class IteratorDemo {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Iterator<String> it = names.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
```

---

# 3. Mediator Pattern

**Definition:** Defines an object that centralizes communication between other objects.

**Use case:** Chat rooms, GUI components, aircraft control systems.

**Pros:**

* Reduces direct dependencies between objects.
* Simplifies communication management.

**Cons:**

* Mediator can become too complex.

**SOLID:** Follows **Single Responsibility** and **Open/Closed Principles**.

**Java Example:**

```java
import java.util.*;

interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) { users.add(user); }

    public void sendMessage(String msg, User sender) {
        for (User u : users)
            if (u != sender) u.receive(msg);
    }
}

abstract class User {
    protected ChatMediator mediator;
    User(ChatMediator m) { this.mediator = m; }
    abstract void send(String msg);
    abstract void receive(String msg);
}

class ChatUser extends User {
    private String name;
    ChatUser(ChatMediator m, String name) { super(m); this.name = name; }
    void send(String msg) { mediator.sendMessage(name + ": " + msg, this); }
    void receive(String msg) { System.out.println(name + " received " + msg); }
}

public class MediatorDemo {
    public static void main(String[] args) {
        ChatMediator chat = new ChatRoom();
        User a = new ChatUser(chat, "Alice");
        User b = new ChatUser(chat, "Bob");
        chat.addUser(a); chat.addUser(b);
        a.send("Hi Bob!");
        b.send("Hey Alice!");
    }
}
```

---

# 4. Chain of Responsibility Pattern

**Definition:** Passes a request along a chain of handlers until one handles it.

**Use case:** Logging systems, authentication chains, event processing.

**Pros:**

* Decouples sender and receiver.
* Easy to add/remove handlers.

**Cons:**

* Hard to track request flow.

**SOLID:** Follows **Open/Closed Principle** and **Single Responsibility Principle**.

**Java Example:**

```java
abstract class Handler {
    protected Handler next;
    public void setNext(Handler next) { this.next = next; }
    public abstract void handle(String request);
}

class AuthHandler extends Handler {
    public void handle(String request) {
        if (request.equals("AUTH")) System.out.println("Auth handled.");
        else if (next != null) next.handle(request);
    }
}

class LogHandler extends Handler {
    public void handle(String request) {
        if (request.equals("LOG")) System.out.println("Log handled.");
        else if (next != null) next.handle(request);
    }
}

public class ChainDemo {
    public static void main(String[] args) {
        Handler auth = new AuthHandler();
        Handler log = new LogHandler();
        auth.setNext(log);

        auth.handle("LOG");
        auth.handle("AUTH");
    }
}
```
---

# 6. Singleton Pattern

**Definition:** Ensures a class has only one instance and provides a global point of access to it.

**Use case:** Logging system, configuration manager, thread pool.

**Pros:**

* Controlled single instance.
* Saves memory and ensures consistent state.

**Cons:**

* Can make unit testing harder.
* Can introduce hidden dependencies.

**SOLID:** Slightly violates **Single Responsibility**, as it controls both creation and logic.

**Java Example:**

```java
class Singleton {
    private static Singleton instance;

    private Singleton() { } // private constructor

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    public void showMessage() {
        System.out.println("Singleton instance active!");
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2); // true
        s1.showMessage();
    }
}
```

---

# 7. Builder Pattern

**Definition:** Separates the construction of a complex object from its representation.

**Use case:** Building objects with many optional parameters (e.g., meals, houses, HTTP requests).

**Pros:**

* Clear and readable object construction.
* Allows different representations of the same object.

**Cons:**

* Increases code complexity with extra builder classes.

**SOLID:** Follows **Single Responsibility** and **Open/Closed Principles**.

**Java Example:**

```java
class Meal {
    private String main;
    private String side;
    private String drink;

    private Meal(Builder b) {
        this.main = b.main;
        this.side = b.side;
        this.drink = b.drink;
    }

    static class Builder {
        private String main;
        private String side;
        private String drink;

        Builder setMain(String main) { this.main = main; return this; }
        Builder setSide(String side) { this.side = side; return this; }
        Builder setDrink(String drink) { this.drink = drink; return this; }

        Meal build() { return new Meal(this); }
    }

    public String toString() {
        return main + ", " + side + ", " + drink;
    }
}

public class BuilderDemo {
    public static void main(String[] args) {
        Meal meal = new Meal.Builder()
                        .setMain("Burger")
                        .setSide("Fries")
                        .setDrink("Cola")
                        .build();
        System.out.println(meal);
    }
}
```

---

# 8. Composite Pattern

**Definition:** Composes objects into tree structures to represent part-whole hierarchies.

**Use case:** File systems, UI hierarchies, organizational charts.

**Pros:**

* Treats individual and composite objects uniformly.
* Simplifies client code.

**Cons:**

* Hard to restrict types of components.

**SOLID:** Supports **Open/Closed** and **Liskov Substitution Principles**.

**Java Example:**

```java
import java.util.*;

interface Component {
    void showDetails();
}

class Leaf implements Component {
    private String name;
    Leaf(String name) { this.name = name; }
    public void showDetails() { System.out.println(name); }
}

class Composite implements Component {
    private String name;
    private List<Component> children = new ArrayList<>();
    Composite(String name) { this.name = name; }

    public void add(Component c) { children.add(c); }
    public void showDetails() {
        System.out.println(name + ":");
        for (Component c : children) c.showDetails();
    }
}

public class CompositeDemo {
    public static void main(String[] args) {
        Leaf file1 = new Leaf("File1.txt");
        Leaf file2 = new Leaf("File2.txt");
        Composite folder = new Composite("Folder");
        folder.add(file1);
        folder.add(file2);
        folder.showDetails();
    }
}
```

---

# 9. Bridge Pattern

**Definition:** Decouples abstraction from implementation so both can vary independently.

**Use case:** Cross-platform UI, graphics rendering, device drivers.

**Pros:**

* Improves flexibility and scalability.
* Decouples code layers.

**Cons:**

* Adds abstraction layers, increasing complexity.

**SOLID:** Follows **Open/Closed** and **Single Responsibility Principles**.

**Java Example:**

```java
interface Device {
    void turnOn();
    void turnOff();
}

class TV implements Device {
    public void turnOn() { System.out.println("TV ON"); }
    public void turnOff() { System.out.println("TV OFF"); }
}

class RemoteControl {
    protected Device device;
    RemoteControl(Device d) { this.device = d; }

    void pressOn() { device.turnOn(); }
    void pressOff() { device.turnOff(); }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Device tv = new TV();
        RemoteControl remote = new RemoteControl(tv);
        remote.pressOn();
        remote.pressOff();
    }
}
```

---

# 10. Proxy Pattern

**Definition:** Provides a surrogate or placeholder to control access to another object.

**Use case:** Lazy loading, access control, remote proxies, caching.

**Pros:**

* Adds security, performance, and control.
* Supports lazy initialization.

**Cons:**

* Increases complexity.
* Can introduce latency.

**SOLID:** Follows **Single Responsibility Principle**.

**Java Example:**

```java
interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;
    RealImage(String filename) { this.filename = filename; loadFromDisk(); }
    private void loadFromDisk() { System.out.println("Loading " + filename); }
    public void display() { System.out.println("Displaying " + filename); }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    ProxyImage(String filename) { this.filename = filename; }

    public void display() {
        if (realImage == null) realImage = new RealImage(filename);
        realImage.display();
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        Image img = new ProxyImage("photo.jpg");
        img.display(); // loads + displays
        img.display(); // only displays
    }
}
```

---

# 11. Flyweight Pattern

**Definition:** Reduces memory usage by sharing common data between multiple objects.

**Use case:** Text editors (characters), game objects, particle systems.

**Pros:**

* Reduces memory footprint.
* Increases performance when many small objects exist.

**Cons:**

* Complex to implement shared vs. unique state.

**SOLID:** Supports **Single Responsibility Principle**.

**Java Example:**

```java
import java.util.*;

interface Shape {
    void draw();
}

class Circle implements Shape {
    private String color;
    private int x, y, radius;

    Circle(String color) { this.color = color; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setRadius(int r) { this.radius = r; }

    public void draw() {
        System.out.println("Circle: color=" + color + ", x=" + x + ", y=" + y + ", r=" + radius);
    }
}

class ShapeFactory {
    private static final Map<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);
        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color: " + color);
        }
        return circle;
    }
}

public class FlyweightDemo {
    private static final String[] colors = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            Circle circle = (Circle) ShapeFactory.getCircle(colors[i % colors.length]);
            circle.setX(i * 10);
            circle.setY(i * 20);
            circle.setRadius(5);
            circle.draw();
        }
    }
}
```
