<%@ include file="/META-INF/resources/dsd_init.jsp" %>
<%@ page import="com.liferay.journal.model.JournalArticleDisplay" %>
<%@ page import="nl.deltares.forms.internal.RegistrationFormDisplayContext" %>
<%@ page import="nl.deltares.portal.utils.DsdSessionUtils" %>
<%@ page import="java.util.List" %>

<%
    String ddmTemplateKey = (String) request.getAttribute("ddmTemplateKey");
    DsdSessionUtils dsdSessionUtils = (DsdSessionUtils) request.getAttribute("dsdSessionUtils");
    RegistrationFormDisplayContext registrationFormDisplayContext =
            new RegistrationFormDisplayContext(liferayPortletRequest, liferayPortletResponse,
                    dsdParserUtils, dsdSessionUtils, themeDisplay.getScopeGroupId(), registrationId);
%>

<c:set var="registrationId" value="<%= registrationId%>"/>

<c:if test="${not empty registrationId}">
    <div class="registration-item">

        <div class="d-flex">
            <div class="float-left p-3">
                <aui:input
                        name="registration_${registrationId}"
                        label=""
                        type="checkbox"
                        data-price="<%= mainRegistration.getPrice() %>"
                        cssClass="parent-registration"
                        checked="true"/>
            </div>
            <div class="float-left w-100">
                <%
                    JournalArticleDisplay articleDisplay = registrationFormDisplayContext
                            .getArticleDisplay(ddmTemplateKey, registrationId, themeDisplay);
                %>
                <liferay-journal:journal-article-display
                        articleDisplay="<%= articleDisplay %>"
                />
            </div>
        </div>

        <%
            List<Registration> children = registrationFormDisplayContext
                    .getChildRegistrations(scopeGroupId, registrationId);
        %>
        <% if (children.size() > 0) { %>
        <h3>
            <liferay-ui:message key="dsd.registration.step1.child.registrations"/>

        </h3>
        <% } %>
        <c:forEach var="childRegistration" items="<%= children %>">
            <div class="d-flex">
                <div class="float-left p-3">
                    <aui:input
                            name="registration_${childRegistration.articleId}"
                            label=""
                            type="checkbox"
                            data-price="${childRegistration.getPrice()}"
                            cssClass="child-registration"
                            checked="${dsdSessionUtils.isUserRegisteredFor(user, childRegistration)}"
                    />
                </div>
                <div class="float-left w-100">
                    <%
                        Registration childArticle = (Registration) pageContext.getAttribute("childRegistration");

                        JournalArticleDisplay childrenArticleDisplay = registrationFormDisplayContext
                                .getArticleDisplay(ddmTemplateKey, childArticle.getJournalArticle().getArticleId(), themeDisplay);
                    %>
                    <liferay-journal:journal-article-display
                            articleDisplay="<%= childrenArticleDisplay %>"
                    />
                </div>
            </div>
        </c:forEach>

    </div>
</c:if>