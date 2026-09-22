/**
 * Watches the search queries
 */
public class Snooper {
    private final WebSearchModel model;

    public Snooper(WebSearchModel model) {
        this.model = model;

        // Observador 1: Palavra 'friend'
        model.addQueryObserver(
            query -> query.toLowerCase().contains("friend"),
            query -> System.out.println("Oh Yes! " + query)
        );

        // Observador 2: Mais de 60 caracteres
        model.addQueryObserver(
            query -> query.length() > 60,
            query -> System.out.println("So long " + query)
        );
    }
}
