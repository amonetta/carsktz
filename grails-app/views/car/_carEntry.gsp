<tr id="carID${car.id}" class="carEntry" onclick="edit('${'#carID' + car.id}')">
    <g:render template="carCols" bean="${car}" var="car"/>
</tr>