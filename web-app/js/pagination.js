// Very helpful link (very!) : http://www.craigburke.com/2011/01/23/grails-ajax-list-with-paging-sorting-and-filtering.html
// option 2: http://www.craigburke.com/2011/01/01/grails-ajax-list-with-paging-and-sorting.html

jQuery(document).ready(function() {
        setupGridAjax();
        setupFilterAjax();
    });

// Turn all sorting and paging links into ajax requests for the grid
function setupGridAjax() {
    $("#grid").find(".step, th.sortable a").on('click', function(event) {
    //$("#grid").find($(".step")).on('click', function(event) {
        event.preventDefault();
        var url = $(this).attr('href');

        var grid = $(this).parents("table.ajax");
        $(grid).html($("#spinner").html());

        jQuery.ajax({
            type: 'GET',
            url: url,
            success: function(data) {
               // $(grid).fadeOut('fast', function() {$(this).html(data).fadeIn('slow');});
                jQuery("#grid").html(data)
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