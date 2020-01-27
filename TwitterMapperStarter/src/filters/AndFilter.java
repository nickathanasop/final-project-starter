package filters;

import twitter4j.Status;

import java.util.ArrayList;
import java.util.List;

public class AndFilter implements Filter{
    private final Filter firstChild;
    private final Filter secondChild;

    public AndFilter(Filter firstChild, Filter secondChild) {
        this.firstChild = firstChild;
        this.secondChild = secondChild;
    }

    /**
     * An and filter matches when both of its children match
     * @param s     the tweet to check
     * @return      whether or not it matches
     */
    @Override
    public boolean matches(Status s) {
        return firstChild.matches(s) && secondChild.matches(s);
    }

    @Override
    public List<String> terms() {
        List<String> terms = new ArrayList<>();
        terms.addAll(firstChild.terms());
        terms.addAll(secondChild.terms());
        return terms;
    }

    public String toString() {
        return "(" + firstChild.toString() + " and " + secondChild.toString() + ")";
    }
}
