<div>
    <g:javascript src="pagination.js"/>
    <table id="carsTable" class="table table-striped ajax">
        <thead>
        <tr>
            <g:sortableColumn action="findCarsAjax" property="id" title="Id" params="${filters}" />
            <g:sortableColumn action="findCarsAjax" property="year" title="Year" params="${filters}" />
            <g:sortableColumn action="findCarsAjax" property="model" title="Model" params="${filters}" />
            <g:sortableColumn action="findCarsAjax" property="make" title="Make" params="${filters}" />
            <g:sortableColumn action="findCarsAjax" property="plate" title="Plate" params="${filters}" />
            <th>Owner</th>
            <th/>
        </tr>
        </thead>
        <tbody>
            <g:each var="car" in="${cars}">
                <tr id="carID${car.id}" class="carEntry" onclick="edit(${car})">
                    <g:render template="carCols" bean="${car}" var="car"/>
                </tr>
            </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate controller="car" action="findCarsAjax" update="grid" total="${carsTotal}" params="${filters}" />
    </div>
</div>