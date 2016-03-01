$(document).ready(function() {
    setupGridAjax();
    setupFilterAjax();
});

// Turn all sorting and paging links into ajax requests for the grid
function setupGridAjax() {
    $("#grid").find(".paginateButtons a, th.sortable a").live('click', function(event) {
        event.preventDefault();
        var url = $(this).attr('href');

        var grid = $(this).parents("table.ajax");
        $(grid).html($("#spinner").html());

        $.ajax({
            type: 'GET',
            url: url,
            success: function(data) {
                $(grid).fadeOut('fast', function() {$(this).html(data).fadeIn('slow');});
            }
        })
    });
}

// Turn any input changes or form submission within a filter div into an ajax call
function setupFilterAjax() {
    $('div.filters :input').change(function() {
        var filterBox = $(this).parents("div.filters");
        filterGrid(filterBox);
    });
    $("div.filters form").submit(function() {
        var filterBox = $(this).parents("div.filters");
        filterGrid(filterBox);
        return false;
    });
}

// Reload grid based on selections from the filter
function filterGrid(filterBox) {
    var grid = $(filterBox).next("div.grid");
    $(grid).html($("#spinner").html());

    var form = $(filterBox).find("form");
    var url = $(form).attr("action");
    var data = $(form).serialize();
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        success: function(data) {
            $(grid).fadeOut('fast', function() {$(this).html(data).fadeIn('slow');});
        }
    });
}