package learnbyteaching.todos.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7994740953532861726L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
