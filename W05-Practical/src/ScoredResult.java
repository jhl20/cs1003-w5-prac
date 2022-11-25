
public class ScoredResult<T> implements Comparable<ScoredResult<T>> {

    private T result;
    private double score;

    /**
     * Construct a ScoredResult object from a result and its score.
     * By default, ScoredResult objects are sorted by their score in descending order.
     *
     * @param result Some data, typically the result of a similarity search
     * @param score The score associated with this result
     */
    public ScoredResult(T result, double score) {
        this.result = result;
        this.score = score;
    }

    /**
     * Return the result stored in this object
     *
     * @return The result
     */
    public T getResult() {
        return result;
    }

    /**
     * Return the value of the score stored in this object
     *
     * @return The score
     */
    public double getScore() {
        return score;
    }

    /**
     * This method defines how ScoredResult object are sorted.
     * Thanks to this definition they will be sorted by the "score" in descending order.
     *
     * @param other The ScoredResult object we are comparing against
     * @return The result of the comparison
     */
    @Override
    public int compareTo(ScoredResult<T> other) {
        return Double.compare(other.score, this.score);
    }
    public String toString() {
        return String.format("%.4f", this.score) + " " + this.result;       //this override ensures the score is in 4 decimal places
    }
}
