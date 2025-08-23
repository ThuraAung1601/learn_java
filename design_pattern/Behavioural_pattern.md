# Behavioural pattern

# 1. Observer Pattern

**Definition:** One object (subject) notifies many observers automatically when it changes.

**Use case:** News feed, stock price updates, event listeners in GUI.

**Pros:**

* Loose coupling between subject and observers.
* Easy to add/remove observers.

**Cons:**

* Can cause performance issues if many observers exist.
* Hard to debug due to unexpected update chains.

**SOLID:** Follows **Open/Closed Principle** (add new observers without modifying subject).

**Java Example:**

```java
import java.util.*;

interface Observer {
    void update(String news);
}

class NewsAgency {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    void addObserver(Observer o) { observers.add(o); }
    void removeObserver(Observer o) { observers.remove(o); }

    void setNews(String news) {
        this.news = news;
        for (Observer o : observers) o.update(news);
    }
}

class NewsChannel implements Observer {
    private String channelName;
    NewsChannel(String name) { this.channelName = name; }

    public void update(String news) {
        System.out.println(channelName + " received: " + news);
    }
}

public class ObserverDemo {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        agency.addObserver(new NewsChannel("Channel A"));
        agency.addObserver(new NewsChannel("Channel B"));
        agency.setNews("Elections coming soon!");
    }
}
```

```
@startuml
interface Observer {
  +update(news)
}

class NewsAgency {
  -observers : List<Observer>
  -news : String
  +addObserver(o)
  +removeObserver(o)
  +setNews(news)
}

class NewsChannel implements Observer {
  -channelName : String
  +update(news)
}

NewsAgency --> Observer
NewsChannel ..|> Observer
@enduml
```

---

# 2. Strategy Pattern

**Definition:** Define a family of algorithms, encapsulate them, and make them interchangeable.

**Use case:** Payment methods (credit card, PayPal), sorting algorithms.

**Pros:**

* Easily switch algorithms at runtime.
* Removes conditional logic.

**Cons:**

* Many small classes.
* Client must know which strategy to use.

**SOLID:** Follows **Open/Closed** (add new strategies without changing old ones).

**Java Example:**

```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " with Credit Card"); }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) { System.out.println("Paid " + amount + " with PayPal"); }
}

class ShoppingCart {
    private PaymentStrategy strategy;
    ShoppingCart(PaymentStrategy strategy) { this.strategy = strategy; }
    void checkout(int amount) { strategy.pay(amount); }
}

public class StrategyDemo {
    public static void main(String[] args) {
        ShoppingCart cart1 = new ShoppingCart(new CreditCardPayment());
        cart1.checkout(100);

        ShoppingCart cart2 = new ShoppingCart(new PayPalPayment());
        cart2.checkout(200);
    }
}
```

```
@startuml
interface PaymentStrategy {
  +pay(amount)
}

class CreditCardPayment implements PaymentStrategy
class PayPalPayment implements PaymentStrategy

class ShoppingCart {
  -strategy : PaymentStrategy
  +checkout(amount)
}

CreditCardPayment ..|> PaymentStrategy
PayPalPayment ..|> PaymentStrategy
ShoppingCart --> PaymentStrategy
@enduml
```

---

# 3. Command Pattern

**Definition:** Encapsulates a request as an object.

**Use case:** Undo/Redo, remote controls, task queues.

**Pros:**

* Supports undo/redo.
* Decouples sender and receiver.

**Cons:**

* More classes needed.
* Overhead if too many commands.

**SOLID:** Supports **Single Responsibility** (command object has one job).

**Java Example:**

```java
interface Command {
    void execute();
}

class Light {
    void on() { System.out.println("Light is ON"); }
    void off() { System.out.println("Light is OFF"); }
}

class LightOnCommand implements Command {
    private Light light;
    LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}

class RemoteControl {
    private Command command;
    void setCommand(Command command) { this.command = command; }
    void pressButton() { command.execute(); }
}

public class CommandDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command onCommand = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(onCommand);
        remote.pressButton();
    }
}
```

```
@startuml
interface Command {
  +execute()
}

class Light {
  +on()
  +off()
}

class LightOnCommand implements Command {
  -light : Light
  +execute()
}

class RemoteControl {
  -command : Command
  +setCommand(command)
  +pressButton()
}

LightOnCommand ..|> Command
RemoteControl --> Command
LightOnCommand --> Light
@enduml
```

---

# 4. Decorator Pattern (Behavioral Usage)

**Definition:** Add responsibilities to an object dynamically.

**Use case:** Adding logging, authentication, or compression to services.

**Pros:**

* Flexible extension.
* Avoids subclass explosion.

**Cons:**

* Many small wrapper classes.
* Can be complex to debug.

**SOLID:** Follows **Open/Closed** (extend behavior without modifying).

**Java Example:**

```java
interface Notifier {
    void send(String msg);
}

class BasicNotifier implements Notifier {
    public void send(String msg) { System.out.println("Sending: " + msg); }
}

class EmailDecorator implements Notifier {
    private Notifier notifier;
    EmailDecorator(Notifier notifier) { this.notifier = notifier; }
    public void send(String msg) {
        notifier.send(msg);
        System.out.println("Also sending email: " + msg);
    }
}

public class DecoratorDemo {
    public static void main(String[] args) {
        Notifier notifier = new EmailDecorator(new BasicNotifier());
        notifier.send("Hello World");
    }
}
```

```
@startuml
interface Notifier {
  +send(msg)
}

class BasicNotifier implements Notifier
abstract class NotifierDecorator implements Notifier {
  -notifier : Notifier
}

class EmailDecorator extends NotifierDecorator

BasicNotifier ..|> Notifier
NotifierDecorator ..|> Notifier
EmailDecorator --> NotifierDecorator
@enduml
```

---

# 5. Template Method

**Definition:** Define the skeleton of an algorithm, letting subclasses fill steps.

**Use case:** Data processing, report generation, game AI.

**Pros:**

* Code reuse of common logic.
* Easy to enforce a process.

**Cons:**

* Hard to change template once fixed.
* Can lead to rigid designs.

**SOLID:** Violates **Open/Closed** sometimes if base class is modified.

**Java Example:**

```java
abstract class DataProcessor {
    public final void process() {
        readData();
        processData();
        saveData();
    }
    abstract void readData();
    abstract void processData();
    void saveData() { System.out.println("Saving results"); }
}

class CSVProcessor extends DataProcessor {
    void readData() { System.out.println("Reading CSV"); }
    void processData() { System.out.println("Processing CSV data"); }
}

public class TemplateDemo {
    public static void main(String[] args) {
        DataProcessor p = new CSVProcessor();
        p.process();
    }
}
```

```
@startuml
abstract class DataProcessor {
  +process()
  #readData()
  #processData()
  +saveData()
}

class CSVProcessor extends DataProcessor

CSVProcessor ..|> DataProcessor
@enduml
```

---

# 6. Memento Pattern

**Definition:** Capture an object’s state so it can be restored later.

**Use case:** Undo in text editor, checkpoints in games.

**Pros:**

* Easy to implement undo.
* Encapsulates internal state.

**Cons:**

* Can use lots of memory.
* Care needed for complex objects.

**SOLID:** Follows **Single Responsibility** (originator stores state, caretaker manages history).

**Java Example:**

```java
class TextEditor {
    private String text;
    void setText(String text) { this.text = text; }
    String getText() { return text; }

    Memento save() { return new Memento(text); }
    void restore(Memento m) { text = m.getSavedText(); }

    static class Memento {
        private final String text;
        Memento(String text) { this.text = text; }
        private String getSavedText() { return text; }
    }
}

public class MementoDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.setText("Version 1");
        TextEditor.Memento m = editor.save();

        editor.setText("Version 2");
        System.out.println("Current: " + editor.getText());

        editor.restore(m);
        System.out.println("Restored: " + editor.getText());
    }
}
```

```
@startuml
class TextEditor {
  -text : String
  +setText(text)
  +getText()
  +save() : Memento
  +restore(m : Memento)
}

class Memento {
  -text : String
  +getSavedText()
}

TextEditor --> Memento
@enduml
```

---

## Summary

| Pattern   | Purpose                              | Example Use Case |
| --------- | ------------------------------------ | ---------------- |
| Observer  | Notify many when one changes         | News updates     |
| Strategy  | Choose algorithm at runtime          | Payment methods  |
| Command   | Encapsulate request                  | Remote control   |
| Decorator | Add new responsibilities dynamically | Logging, email   |
| Template  | Define algorithm skeleton            | Report pipeline  |
| Memento   | Save and restore state               | Undo in editor   |
