// Very helpful link (very!) : http://www.craigburke.com/2011/01/23/grails-ajax-list-with-paging-sorting-and-filtering.html
// option 2: http://www.craigburke.com/2011/01/01/grails-ajax-list-with-paging-and-sorting.html

// Turn all sorting and paging links into ajax requests for the grid
function setupGridAjax() {
    $("#grid").find(".step, th.sortable a").on('click', function(event) {
        event.preventDefault();
        var url = $(this).attr('href');

        var grid = $(this).parents("table.ajax");
        $(grid).html($("#spinner").html());

        jQuery.ajax({
            type: 'GET',
            url: url,
            success: function(data) {
                jQuery("#grid").html(data)
            }
        })
    });
}