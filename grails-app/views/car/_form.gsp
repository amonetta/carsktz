<%@ page import="com.kaitzen.Car" %>

<g:hiddenField name="id" value="${carInstance.id}"/>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'year', 'error')} required">
    <label for="year">
        <g:message code="car.year.label" default="Year"/>
        <span class="required-indicator">*</span>
    </label>
    <g:field name="year" type="number" min="1768" max="2016" value="${carInstance.year}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'make', 'error')} required">
    <label for="make">
        <g:message code="car.make.label" default="Make"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="make" maxlength="50" required="" value="${carInstance?.make}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: carInstance, field: 'model', 'error')} required">
    <label for="model">
        <g:message code="car.model.label" default="Model"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="model" maxlength="50" required="" value="${carInstance?.model}"/>

</div>

