<#assign dsdUtils = serviceLocator.findService("nl.deltares.portal.utils.DsdRegistrationUtils") />
<#assign eventId = .vars['reserved-article-id'].getData() />
<#assign event = dsdUtils.getEvent(groupId,eventId) />
<#assign location = event.getEventLocation() />
<#if event.isEventInPast() >
    <#assign isEventPast = "past-event"/>
<#else>
    <#assign isEventPast = "upcoming-event"/>
</#if>


<div class="c-events page">
    <div class="c-events__item ${isEventPast}">
        <div class="clearfix">
            <div class="media-section">
                <#if eventImage.getData()?? && eventImage.getData() != "">
                    <img
                            class="c-events__item__image"
                            alt="${eventImage.getAttribute("alt")}"
                            data-fileentryid="${eventImage.getAttribute("fileEntryId")}"
                            src="${eventImage.getData()}" />
                </#if>
            </div>
            <div class="data-section">
                <div class="c-events__item__date">
                    <span>${dateUtil.getDate(event.getStartDay(), "dd", locale)}</span>
                    ${dateUtil.getDate(event.getStartDay(), "MMM", locale)}
                </div>
                <h3 class="c-events__item__title h1">${event.getTitle()}</h3>
                <p class="c-events__item__time-date-place">
                    <span class="c-events__item__time-date-place__date">
                        ${dateUtil.getDate(event.getStartDay(), "dd MMM yyyy", locale)}
                        <#if event.isMultiDayEvent() >
                            &nbsp;-&nbsp;${dateUtil.getDate(event.getEndDay(), "dd MMM yyyy", locale)}
                        </#if>
                    </span>
                    <span class="c-events__item__time-date-place__time">
                        ${dateUtil.getDate(event.getStartDay(), "HH:mm", locale)} -
                        ${dateUtil.getDate(event.getEndDay(), "HH:mm", locale)}</span>

                    <span class="c-events__item__time-date-place__place">
                        <br>${location.getCity()}, ${location.getCountry()}
                    </span>

                </p>

                <div class="c-events__item__introduction">
                    <p class="font-weight-regular">${eventIntroduction.getData()}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="c-events__item__description">
        ${eventFullDescription.getData()}
    </div>

</div>