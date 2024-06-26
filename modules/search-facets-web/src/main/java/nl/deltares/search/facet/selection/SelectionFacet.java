package nl.deltares.search.facet.selection;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.search.BooleanClause;
import com.liferay.portal.kernel.search.BooleanClauseFactoryUtil;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.facet.BaseFacet;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.search.filter.TermFilter;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.facet.Facet;
import com.liferay.portal.search.filter.FilterBuilders;

public class SelectionFacet extends BaseFacet implements Facet {

    public SelectionFacet(String fieldName, SearchContext searchContext, FilterBuilders filterBuilders) {
        super(searchContext);
        setFieldName(fieldName);
        _filterBuilders = filterBuilders;
    }

    @Override
    public String getAggregationName() {
        String aggregationName;
        if (_aggregationName != null) {
            aggregationName = _aggregationName;
        } else {
            aggregationName = getFieldName();
        }
        return aggregationName;
    }

    @Override
    public String[] getSelections() {
        return _selections;
    }

    @Override
    public void select(String... selections) {
        _selections = selections;
    }

    @Override
    public void setAggregationName(String aggregationName) {
        _aggregationName = aggregationName;
    }


    @Override
    protected BooleanClause<Filter> doGetFacetFilterBooleanClause() {
        if (ArrayUtil.isEmpty(_selections)) {
            return null;
        }

        SearchContext searchContext = getSearchContext();

        String selectionString = _selections[0];

        String selection = StringPool.BLANK;

        if (!isStatic() && Validator.isNotNull(selectionString)) {
            selection = selectionString;
        }

        TermFilter termFilter = new TermFilter(
                getFieldName(), selection);

        return BooleanClauseFactoryUtil.createFilter(
                searchContext, termFilter, BooleanClauseOccur.MUST);
    }

    private String _aggregationName;
    @SuppressWarnings("FieldCanBeLocal")
    private final FilterBuilders _filterBuilders;
    private String[] _selections = {};
}
