package cz.muni.fi.pa165.rest;

import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author Mjartan
 */
public class ImpressionListModel extends AbstractListModel {

    private List<ImpressionTO> impressions;

    public ImpressionListModel() {
        this.impressions = new ArrayList<ImpressionTO>();
    }

    public void setImpressions(List<ImpressionTO> impressions) {
        this.impressions = impressions;
        fireContentsChanged(this, 0, impressions.size());
    }

    public void delete(int index) {
        impressions.remove(index);
        fireContentsChanged(this, 0, impressions.size());
    }

    public void add(ImpressionTO im) {
        impressions.add(im);
        fireContentsChanged(this, 0, impressions.size());
    }

    public int getSize() {
        return impressions.size();
    }

    public Object getElementAt(int index) {
        return impressions.get(index).getIsbn() + " : " + impressions.get(index).getReleaseDate() + " - "
                + impressions.get(index).getAuthor() + " - " + impressions.get(index).getName();
    }
}
