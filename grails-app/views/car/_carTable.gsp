<div class="col-md-12 col-lg-12">
    <g:javascript src="pagination.js"/>
    <table id="carsTable" class="ch-datagrid ajax">
        <thead>
        <tr>
            <g:sortableColumn scope="col" action="findCarsAjax" property="id" title="Id" params="${filters}" />
            <g:sortableColumn scope="col" action="findCarsAjax" property="year" title="Year" params="${filters}" />
            <g:sortableColumn scope="col" action="findCarsAjax" property="model" title="Model" params="${filters}" />
            <g:sortableColumn scope="col" action="findCarsAjax" property="make" title="Make" params="${filters}" />
            <g:sortableColumn scope="col" action="findCarsAjax" property="plate" title="Plate" params="${filters}" />
            <th>Owner</th>
            <th><button type="button" class="ch-btn" onclick="showNewCarDialog()">
                <span class="glyphicon glyphicon-plus"/>
            </button></th>
        </tr>
        </thead>
        <tbody>
            <g:render template="carEntry" collection="${cars}" var="car"/>
        </tbody>
    </table>

    <div class="col-md-12 col-lg-12 text-center">
        <g:paginate class="ch-pagination" controller="car" action="findCarsAjax" update="grid" total="${carsTotal}" params="${filters}" max="${filters.max? filters.max : 20}"/>
    </div>
</div>