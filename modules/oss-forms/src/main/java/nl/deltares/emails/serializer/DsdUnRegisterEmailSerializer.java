package nl.deltares.emails.serializer;

import com.liferay.portal.kernel.language.LanguageUtil;
import nl.deltares.emails.DsdEmail;

public class DsdUnRegisterEmailSerializer extends DsdRegistrationEmailSerializer{

    public void appendNotice(StringBuilder writer, DsdEmail content) {
    }

    public void appendRegistrationAction(StringBuilder writer, DsdEmail content) {
        writer.append("<p>");
        writer.append(LanguageUtil.format(content.getBundle(), "dsd.email.unregister.event", null));
        writer.append("</p>");
    }

    @Override
    protected void appendBusInfo(StringBuilder writer, DsdEmail content) {
        writer.append("<p>");
        writer.append(LanguageUtil.format(content.getBundle(), "dsd.email.unregister.busnotice", content.getRegistrationRequest().getBusTransferUrl() ));
        writer.append("</p>");
    }

    @Override
    protected void appendRemarks(StringBuilder writer, DsdEmail content) {
    }
}
