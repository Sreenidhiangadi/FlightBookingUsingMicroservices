package Excpetions;

public class validator {

    // Method that uses 'throws' to declare checked exception
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            // 'throw' actually creates the exception
            throw new InvalidAgeException("Age must be 18 or above to register!");
        } else {
            System.out.println("Age verified successfully!");
        }
    }

    // Method that throws a runtime (unchecked) exception
    public static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new InvalidNameRuntimeException("Name cannot be null or empty");
        } else {
            System.out.println("Name verified successfully");
        }
    }
}
