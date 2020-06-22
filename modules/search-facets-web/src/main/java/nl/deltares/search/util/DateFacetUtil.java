package nl.deltares.search.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateFacetUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate getStartDate(String date) throws DateTimeParseException {
        LocalDate startDate = parseDate(date);
        if (startDate == null) {
            startDate = getDefaultStartDate();
        }
        return startDate;
    }

    public static LocalDate getEndDate(String date) throws DateTimeParseException {
        LocalDate endDate = parseDate(date);
        if (endDate == null) {
            endDate = getDefaultEndDate();
        }
        return endDate;
    }

    public static LocalDate parseDate(String date) throws DateTimeParseException {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            LOG.error("Error parsing date", e);
        }
        return localDate;
    }

    public static LocalDate getDefaultStartDate() {
        int currentYear = Year.now().getValue();
        return LocalDate.of(currentYear, Month.JANUARY.getValue(), 1);
    }

    public static LocalDate getDefaultEndDate() {
        int currentYear = Year.now().getValue();
        YearMonth month = YearMonth.from(LocalDate.of(currentYear, Month.DECEMBER.getValue(), 1));
        return month.atEndOfMonth();
    }

    private static final Log LOG = LogFactoryUtil.getLog(DateFacetUtil.class);

}
