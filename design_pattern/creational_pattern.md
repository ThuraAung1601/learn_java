## Factory Method Pattern

**Idea:**
- Define an interface for creating an object, 
- but let subclasses decide which class to instantiate.
- One object at a time.

**Use Case Scenario:**

* A document editor that needs to create different types of documents (Word, PDF).
* Factories from different regions
* A game that creates different types of enemies (Zombie, Dragon).

**Pros:**

* Easy to extend with new product types.
* Removes direct `new` usage from client code.

**Cons:**

* More classes than simple `new`.
* Each product variation needs its own subclass.

**SOLID:**

* **SRP:** separates object creation from business logic.
* **OCP:** can add new product classes without modifying old code.

**Example Java Code:**

```java
// Product Interface
interface Document {
    void open();
}

// Concrete Products
class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word Document");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF Document");
    }
}

// Creator (Factory Method)
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// Concrete Factories
class WordFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

// Scenario
public class FactoryMethodDemo {
    public static void main(String[] args) {
        DocumentFactory factory = new WordFactory();
        Document doc = factory.createDocument();
        doc.open();

        factory = new PdfFactory();
        doc = factory.createDocument();
        doc.open();
    }
}
```

---

## Abstract Factory Pattern

**Idea:**
Provide an interface to create **families of related objects** without specifying their concrete classes.
Creates multiple products that belong together.

**Use Case Scenario:**

* Cross-platform UI toolkit: create Windows or Mac UI (Button + Checkbox).
* Car factory: create ElectricCar family (engine + battery) or GasCar family (engine + tank).

**Pros:**

* Keeps product families consistent.
* Easy to switch entire product families at once.

**Cons:**

* Adding a new product type requires changes in all factories.
* More complex than Factory Method.

**SOLID:**

* **SRP:** separates product family creation from client code.
* **OCP:** can add new families without modifying client code.
* **DIP:** client depends on abstract interfaces, not concrete classes.

**Example Java Code:**

```java
// Abstract Products
interface Button {
    void paint();
}

interface Checkbox {
    void check();
}

// Concrete Products - Windows
class WindowsButton implements Button {
    public void paint() {
        System.out.println("Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    public void check() {
        System.out.println("Windows Checkbox");
    }
}

// Concrete Products - Mac
class MacButton implements Button {
    public void paint() {
        System.out.println("Mac Button");
    }
}

class MacCheckbox implements Checkbox {
    public void check() {
        System.out.println("Mac Checkbox");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacFactory implements GUIFactory {
    public Button createButton() {
        return new MacButton();
    }
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// Scenario
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        GUIFactory factory = new WindowsFactory();
        Button b1 = factory.createButton();
        Checkbox c1 = factory.createCheckbox();
        b1.paint();
        c1.check();

        factory = new MacFactory();
        Button b2 = factory.createButton();
        Checkbox c2 = factory.createCheckbox();
        b2.paint();
        c2.check();
    }
}
```

---

## Key Differences

| Feature | Factory Method                | Abstract Factory                     |
| ------- | ----------------------------- | ------------------------------------ |
| Scope   | Creates one product at a time | Creates families of related products |
| Example | Create Word OR PDF document   | Create Windows OR Mac UI toolkit     |
| Pros    | Simple, easy to extend        | Keeps product families consistent    |
| Cons    | Many subclasses needed        | Harder to add new product types      |
| SOLID   | SRP, OCP                      | SRP, OCP, DIP                        |

---

## Summary 

* **Factory Method** → choose **one product** at a time.
* **Abstract Factory** → choose a **whole set of products** that belong together.
