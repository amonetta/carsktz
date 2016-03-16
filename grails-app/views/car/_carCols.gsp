<td class="carId">${car.id}</td>
<td class="carYear">${car.year}</td>
<td class="carMake">${car.make}</td>
<td class="carModel">${car.model}</td>
<g:if test="${car.plate}">
    <td class="carPlate">${car.plate}</td>
</g:if>
<g:else>
    <td class="carPlate"/>
</g:else>
<g:if test="${car.owner}">
    <td class="carOwner">
        <p hidden="true" class="ownerId">${car.owner.id}</p>
        <p hidden="true" class="ownerDescription">${car.owner.apellido}, ${car.owner.nombre}</p>
        <a href="/carsktz/owner/show/${car.owner.id}">${car.owner.apellido}, ${car.owner.nombre}</a></td>
</g:if>
<g:else>
    <td class="carOwner"/>
</g:else>
<td class="carDelete">
    <button class="ch-btn" onclick="deleteCarAjax(event, ${car.id})">
        <span class="glyphicon glyphicon-trash"/>
    </button>
</td>