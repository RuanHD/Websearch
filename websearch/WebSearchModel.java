import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Perform "web search" (from a  file), notify the interested observers of each query.
 */
public class WebSearchModel {
    private final File sourceFile;
    private final List<ObserverFilterPair> observers = new ArrayList<>();

    public interface QueryObserver {
        void onQuery(String query);
    }

    public interface QueryFilter {
        boolean onMatch(String query);
    }
    
    private class ObserverFilterPair {
    QueryFilter filter;
    QueryObserver observer;

    ObserverFilterPair(QueryFilter filter, QueryObserver observer) {
        this.filter = filter;
        this.observer = observer;
    }
}

    public WebSearchModel(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void pretendToSearch() {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            while ( true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                notifyAllObservers(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addQueryObserver(QueryFilter filter, QueryObserver observer) {
        observers.add(new ObserverFilterPair(filter, observer));
}

    private void notifyAllObservers(String line) {
        for (QueryObserver obs : observers) {
            obs.onQuery(line);
        }
    }
}
