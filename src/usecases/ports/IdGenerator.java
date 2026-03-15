package usecases.ports;

public interface IdGenerator {
    String nextId(String prefix);
}
