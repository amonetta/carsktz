<td class="carEditLink"><a href="http://localhost:8080/carsktz/car/edit/${car.id}">${car.id}</a></td>
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
        <a href="/carsktz/owner/show/${car.owner.id}">${car.owner.apellido}, ${car.owner.nombre}</a></td>
</g:if>
<g:else>
    <td class="carOwner"/>
</g:else>
<td class="carDelete">
    <g:form id="deleteFormID${car.id}">
        <g:hiddenField name="id" value="${car.id}"/>
        <button onclick='jQuery.ajax({
            type:"DELETE",
            data:$("#deleteFormID" + ${car.id}).serialize(),
             url:"/carsktz/car/delete/" + ${car.id},
             success:function(data,textStatus){
                 $("#carID" + ${car.id}).remove()
             },error:function(XMLHttpRequest,textStatus,errorThrown){}
        });
        return false'
                class="btn btn-default">
            <span class="glyphicon glyphicon-trash"/>
        </button>
    </g:form>
</td>