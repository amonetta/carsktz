<td class="carEditLink"><a href="http://localhost:8080/carsktz/car/edit/${car.id}">${car.id}</a></td>
<td class="carYear">${car.year}</td>
<td class="carMake">${car.make}</td>
<td class="carModel">${car.model}</td>
<td class="carDelete">
    <g:form>
        <g:hiddenField name="id" value="${car.id}"/>
            <g:submitToRemote url='[controller: "car", action: "delete", id:"${car.id}"]' method="DELETE">
                <span class="glyphicon glyphicon-remove"></span> &nbsp
            </g:submitToRemote>
    </g:form>
</td>