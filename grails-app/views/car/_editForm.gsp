<form class="form-horizontal">
    <fieldset>
        <!-- Form Name -->
        <legend>Car</legend>

        <div class="form-group">
            <g:hiddenField id="idInput" name="id" value="${car.id}"/>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="year">Year</label>
            <div class="col-md-4">
                <g:field id="yearInput" name="year" type="number" placeholder="Car Year" class="form-control input-md" required="" value="${car.year}"/>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="maleInput">Make</label>
            <div class="col-md-4">
                <g:field id="makeInput" name="make" type="text" placeholder="Car maker" class="form-control input-md" required="" value="${car.make}"/>
            </div>
        </div>

        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="modelInput">Model</label>
            <div class="col-md-4">
                <g:field id="modelInput" name="model" type="text" placeholder="Car Model" class="form-control input-md" required="" value="${car.model}"/>
            </div>
        </div>

        <!-- Button -->
        <div class="form-group">
            <div class="col-md-4">
                <g:submitToRemote url='[controller: "car", action: "update", id:"${car.id"]' class="btn btn-primary"
                                  id="submitInputButton" name="submitInputButton" value="Update" update="carID${car.id}"/>
            </div>
        </div>
    </fieldset>

</form>