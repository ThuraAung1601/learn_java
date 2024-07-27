package lab1.solutions.pack1;

public class Dog_661606 {
    private Breed_661606 breed;
    private int weight;

    public Dog_661606(Breed b, int w) {
        breed = b;
        weight = w;
    }

    public Breed_661606 getBreed() {
        return this.breed;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((breed == null) ? 0 : breed.hashCode());
        result = prime * result + weight;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Dog_661606 other = (Dog_661606) obj;
        if (breed != other.breed)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return "Dog [breed=" + breed + ", weight=" + weight + "]";
    }
}
