package cz.muni.fi.pa165.library.dtos;

import java.io.Serializable;

/**
 * Class representing a book transfer object
 *
 * @author Mjartan
 */
public class BookTO implements Serializable {

    private Long id;
    private String condition;
    private ImpressionTO impression;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public ImpressionTO getImpression() {
        return impression;
    }

    public void setImpression(ImpressionTO impression) {
        this.impression = impression;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookTO other = (BookTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "condition=" + condition + '}';
    }
}
