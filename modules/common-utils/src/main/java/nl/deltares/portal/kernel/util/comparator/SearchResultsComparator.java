package nl.deltares.portal.kernel.util.comparator;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.OrderByComparator;
import nl.deltares.portal.model.impl.AbsDsdArticle;
import nl.deltares.portal.model.impl.Registration;

public class SearchResultsComparator extends OrderByComparator<com.liferay.portal.kernel.search.Document> {
    @Override
    public int compare(Document o1, Document o2) {

        AbsDsdArticle dsdo1 = null;
        AbsDsdArticle dsdo2 = null;
        try {
            JournalArticle o1Article = JournalArticleLocalServiceUtil.getLatestArticle(Long.parseLong(o1.get("entryClassPK")));
            dsdo1 = AbsDsdArticle.getInstance(o1Article);
        } catch (PortalException e) {
            return 0;
        }

        try {
            JournalArticle o2Article = JournalArticleLocalServiceUtil.getLatestArticle(Long.parseLong(o2.get("entryClassPK")));
            dsdo2 = AbsDsdArticle.getInstance(o2Article);
        } catch (PortalException e) {
            return 0;
        }

        if (dsdo1 instanceof Registration && dsdo2 instanceof Registration){
            return ((Registration)dsdo2).getStartTime().compareTo(((Registration) dsdo1).getStartTime());
        }
//reverse order
        return dsdo1.getTitle().compareTo(dsdo2.getTitle());
    }


}